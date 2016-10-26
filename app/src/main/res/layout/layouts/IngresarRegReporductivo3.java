package layout.layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;

import java.util.Calendar;
import java.util.Date;


public class IngresarRegReporductivo3 extends ActionBarActivity implements View.OnClickListener {

    private Button btn_atras, btn_sigte, btn_preñez, btn_posible_parto, btn_fecha_parto;
    private String[] reg_repro2, reg_repro3;
    private String var_preñez,var_posi_parto,var_fecha_parto, var_id_reproductor;
    private String[] num_parto = {"1","2","3","4","5","6","7","8","9","10"};
    private Spinner spin_parto;
    private int year, month, day;
    private String var_fecha="";
    private int bandera=0;
    private EditText ed_id_reproductor;
    private String var_spinner;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarregreporductivo3);
        Bundle bundl=getIntent().getExtras();
        if(bundl!=null){
            reg_repro2= bundl.getStringArray("reg_repro2");
        }

        btn_atras = (Button) findViewById(R.id.btn_atras_ing_rep_3);
        btn_sigte = (Button) findViewById(R.id.btn_siguiente_ing_rep_3);
        btn_preñez = (Button) findViewById(R.id.btn_preñez);
        btn_posible_parto = (Button) findViewById(R.id.btn_fecha_posible_parto);
        btn_fecha_parto = (Button) findViewById(R.id.btn_parto);
        spin_parto = (Spinner) findViewById(R.id.spi_num_parto);
        ed_id_reproductor = (EditText) findViewById(R.id.edt_id_reprodutor_reg3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,num_parto);
        spin_parto.setAdapter(adapter);

        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        btn_atras.setTypeface(font);
        btn_sigte.setTypeface(font);

        btn_preñez.setOnClickListener(this);
        btn_posible_parto.setOnClickListener(this);
        btn_fecha_parto.setOnClickListener(this);
        btn_atras.setOnClickListener(this);
        btn_sigte.setOnClickListener(this);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente_ing_rep_3:

                var_id_reproductor =  ed_id_reproductor.getText().toString();
                var_spinner = spin_parto.getSelectedItem().toString();

                if (validar(var_id_reproductor, var_preñez, var_posi_parto, var_fecha_parto) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos y elegir numero de parto", Toast.LENGTH_LONG).show();
                } else {

                    reg_repro3 = new String[16];
                    reg_repro3[0] = reg_repro2[0];
                    reg_repro3[1] = reg_repro2[1];
                    reg_repro3[2] = reg_repro2[2];
                    reg_repro3[3] = reg_repro2[3];
                    reg_repro3[4] = reg_repro2[4];
                    reg_repro3[5] = reg_repro2[5];
                    reg_repro3[6] = reg_repro2[6];
                    reg_repro3[7] = reg_repro2[7];
                    reg_repro3[8] = reg_repro2[8];
                    reg_repro3[9] = reg_repro2[9];
                    reg_repro3[10] = reg_repro2[10];
                    reg_repro3[11] = var_id_reproductor;
                    reg_repro3[12] = var_preñez;
                    reg_repro3[13] = var_posi_parto;
                    reg_repro3[14] = var_fecha_parto;
                    reg_repro3[15] = var_spinner;

                    Intent ir_reg3 = new Intent(this, IngresarRegReporductivo4.class);
                    ir_reg3.putExtra("reg_repro3", reg_repro3);
                    startActivity(ir_reg3);
                }
                break;
            case R.id.btn_atras_ing_rep_3:
                super.onBackPressed();
                break;

            case R.id.btn_preñez:
                bandera = 1;
                showDialog(999);
                break;

            case R.id.btn_fecha_posible_parto:
                bandera = 2;
                showDialog(999);
                break;

            case R.id.btn_parto:
                bandera = 3;
                showDialog(999);
                break;
        }
    }

    public Boolean validar(String id_repro, String var_preñez, String var_posi_parto, String var_fecha_parto) {
        if (id_repro.equals("") || var_preñez.equals("") || var_posi_parto.equals("") || var_fecha_parto.equals("")) {
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
        switch (bandera){
            case 1:
                var_preñez = var_fecha;
                btn_preñez.setText(var_fecha);
                break;
            case 2:
                var_posi_parto = var_fecha;
                btn_posible_parto.setText(var_fecha);
                break;
            case 3:
                var_fecha_parto = var_fecha;
                btn_fecha_parto.setText(var_fecha);
                break;
        }
    }
}

