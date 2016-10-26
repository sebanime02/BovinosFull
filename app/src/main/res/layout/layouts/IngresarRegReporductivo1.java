package layout.layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class IngresarRegReporductivo1 extends ActionBarActivity implements View.OnClickListener {

    private String[] reg_repro;
    private ImageButton imgBovino;
    private EditText edt_nombre_bovino, ed_peso_reg_repro, ed_edad_reg_repro, ed_cond_corporal;
    private Button btn_siguiente_reg_repro, btn_fecha_monta;
    private String var_fecha="";
    private Calendar calendar;
    private int year, month, day;
    private List<InventarioVO> bovinos;
    private AutoCompleteTextView auto_id_bovino;
    private Manager_inventario manager_inventario;
    int id;
    private String[] datos=new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarregreporductivo1);

        auto_id_bovino = (AutoCompleteTextView) findViewById(R.id.edt_id_bovino_reg_repro);
        edt_nombre_bovino = (EditText) findViewById(R.id.edt_nombre_bovino_reg_repro);
        imgBovino=(ImageButton)findViewById(R.id.foto_bovino);
        ed_peso_reg_repro = (EditText) findViewById(R.id.ed_peso_reg_repro);
        ed_edad_reg_repro = (EditText) findViewById(R.id.ed_edad_reg_repro);
        ed_cond_corporal = (EditText) findViewById(R.id.ed_cond_corporal);

        btn_siguiente_reg_repro = (Button) findViewById(R.id.btn_siguiente_reg_repro);
        btn_fecha_monta = (Button) findViewById(R.id.btn_fecha_monta);
        btn_fecha_monta.setOnClickListener(this);
        btn_siguiente_reg_repro.setOnClickListener(this);
        manager_inventario=new Manager_inventario(this);
        bovinos=manager_inventario.getRegistros();
        String[] items=new String[bovinos.size()];
        for(int i=0;i<bovinos.size();i++){
            items[i]=bovinos.get(i).getId();
        }
        auto_id_bovino.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,items));
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=Integer.parseInt(bundle.get("id").toString());
            InventarioVO bovino=manager_inventario.getRegistrosByIdBovino(""+id);
            imgBovino.setImageURI(Uri.parse(bovino.getFoto()));
            edt_nombre_bovino.setText(bovino.getNombre());
            datos[0]=""+bovino.getIdS();
        }
        auto_id_bovino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InventarioVO bovino=manager_inventario.getRegistrosByIdBovino(auto_id_bovino.getText().toString());
                imgBovino.setImageURI(Uri.parse(bovino.getFoto()));
                edt_nombre_bovino.setText(bovino.getNombre());
                datos[0]=""+bovino.getIdS();

            }
        });
        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        btn_siguiente_reg_repro.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_siguiente_reg_repro:

                if(validar(edt_nombre_bovino.getText().toString(),ed_peso_reg_repro.getText().toString(),ed_edad_reg_repro.getText().toString(),ed_cond_corporal.getText().toString())==false){
                    Toast.makeText(this, "Debe Llenar Todos Los Campos y Elegir una Foto", Toast.LENGTH_LONG).show();
                }else {
                    reg_repro = new String[7];
                    Intent ir_reg = new Intent(this, IngresarRegReporductivo2.class);

                    reg_repro[0] =  datos[0];
                    reg_repro[1] = "nombre_foto";
                    reg_repro[2] = edt_nombre_bovino.getText().toString();;
                    reg_repro[3] = var_fecha;
                    reg_repro[4] = ed_peso_reg_repro.getText().toString();
                    reg_repro[5] = ed_edad_reg_repro.getText().toString();
                    reg_repro[6] = ed_cond_corporal.getText().toString();

                    ir_reg.putExtra("reg_repro", reg_repro);
                    startActivity(ir_reg);
                }
            break;
            case R.id.btn_fecha_monta:
                showDialog(999);
                break;
        }
    }
    public Boolean validar(String nombre, String fecha_monta, String peso, String condi) {
        if (nombre.equals("") || fecha_monta.equals("") || peso.equals("") || condi.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog dialogDate = new DatePickerDialog(this, myDateListener, year, month, day);
            dialogDate.getDatePicker().setMaxDate(new Date().getTime());
            return dialogDate;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        var_fecha = (new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)).toString();
        btn_fecha_monta.setText(var_fecha);
    }
}
