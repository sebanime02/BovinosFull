package layout.layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RegistrarVacuna1 extends ActionBarActivity {
    private EditText edt_nombre;
    private ImageView imgBovino;
    private String var_fecha="";
    private Button btn_fecha;
    private int year, month, day;
    private Calendar calendar;
    private Manager_inventario manager_inventario;
    private List<InventarioVO> bovinos;
    private String[] datos=new String[2];
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vacuna1);
        edt_nombre=(EditText)findViewById(R.id.edt_nombre_pv);
        btn_fecha=(Button)findViewById(R.id.btn_fecha_pv);
        imgBovino=(ImageView)findViewById(R.id.foto_bovino_pv);

        manager_inventario=new Manager_inventario(this);
        bovinos=manager_inventario.getRegistros();
        String[] items=new String[bovinos.size()];
        for(int i=0;i<bovinos.size();i++){
            items[i]=bovinos.get(i).getId();
        }
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.comple);


        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,items));
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mesaje(autoCompleteTextView.getText().toString());
                InventarioVO bovino=manager_inventario.getRegistrosByIdBovino(autoCompleteTextView.getText().toString());
                imgBovino.setImageURI(Uri.parse(bovino.getFoto()));
                edt_nombre.setText(bovino.getNombre());
                datos[0]=""+bovino.getIdS();

            }
        });

    }
    public void mesaje(String i){
        Toast.makeText(this,"ssadas"+i,Toast.LENGTH_SHORT).show();
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_fecha_pv:
                showDialog(999);
                break;
            case R.id.btn_siguiente_pv:
                Intent intent=new Intent(this,RegistrarVacuna2.class);
                datos[1]=var_fecha;
                if(!autoCompleteTextView.getText().equals("")&&!datos[1].equals("")){
                    if(manager_inventario.getRegistrosByIdBovino(autoCompleteTextView.getText().toString())==null){
                        Toast.makeText(this,"El id del Bovino no existe",Toast.LENGTH_SHORT).show();
                    }else{
                        intent.putExtra("datos",datos);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(this,"Verifique los campos",Toast.LENGTH_SHORT).show();
                }

                break;
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
        btn_fecha.setText(var_fecha);
    }

}
