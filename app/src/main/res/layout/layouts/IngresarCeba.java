package layout.layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
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


public class IngresarCeba extends ActionBarActivity implements View.OnClickListener{

    AutoCompleteTextView edt_id;
    EditText edt_edad;
    EditText edt_peso;
    Button btn_fecha;
    Button btn_guardar;
    DbCeba dbCeba;
    private int year, month, day;
    private Calendar calendar;
    ImageView image;
    String var_fecha = "";
    EditText edt_nombre;

    List<InventarioVO> bovinos;
    Manager_inventario dbManager_inventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_ceba);
        edt_id = (AutoCompleteTextView) findViewById(R.id.edt_id_bovinoc);
        edt_edad = (EditText) findViewById(R.id.edt_edad_destete);
        edt_peso = (EditText) findViewById(R.id.edt_peso_destete);
        btn_fecha = (Button) findViewById(R.id.btn_fecha_destete);
        btn_fecha.setOnClickListener(this);
        btn_guardar = (Button) findViewById(R.id.btn_guardar_ceba);
        btn_guardar.setOnClickListener(this);
        dbCeba = new DbCeba(this);
        edt_nombre = (EditText) findViewById(R.id.edt_nombre_bovinoc);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dbManager_inventario =  new Manager_inventario(this);
        image=(ImageView)findViewById(R.id.imv_bovinoc);
        bovinos = dbManager_inventario.getRegistros();
        try{
            String[] items = new String[bovinos.size()];
            for (int i = 0; i<bovinos.size(); i++){
                items[i] = bovinos.get(i).getId();
            }
            edt_id.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));
        }catch (Exception e){

        }

        edt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    InventarioVO bovino = dbManager_inventario.getRegistrosByIdBovino(edt_id.getText().toString());
                    edt_nombre.setText(bovino.getNombre());
                    image.setImageURI(Uri.parse(bovino.getFoto()));
                }catch (Exception e){
                    edt_nombre.setText("");
                    image.setImageURI(null);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_guardar_ceba:

                if(dbManager_inventario.getRegistrosByIdBovino(edt_id.getText().toString())==null) {
                    Toast.makeText(this, "El id del Bovino no existe", Toast.LENGTH_SHORT).show();
                }else {
                    String id = edt_id.getText().toString();
                    String fecha = btn_fecha.getText().toString();
                    String edad = edt_edad.getText().toString();
                    String peso = edt_peso.getText().toString();
                    if(fecha.equals("DD/MM/AAAA") || edad.equals("") || peso.equals("")) {
                        Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                    }else {
                        InventarioVO inv = new Manager_inventario(this).getRegistrosByIdBovino(id);
                        id = ""+inv.getIdS();
                        dbCeba.inserta(this, id, fecha, edad, peso);
                    }
                }
                break;

            case R.id.btn_fecha_destete:
                showDialog(999);
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
