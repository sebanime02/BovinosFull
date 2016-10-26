package layout.layouts;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_control;
import com.example.pablosanjuan.core.controlers.Main;


/**
 * Created by sebastian on 04/06/15.
 */
public class IngresarControl2 extends ActionBarActivity {


    private Button btn_guardar;


    private EditText e_diagnostico, e_tratamiento, e_producto, e_dosis, e_frecuencia,e_observaciones;
    String[] control;
    private Manager_control manager0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_control2);
        Bundle bundl=getIntent().getExtras();
        if(bundl!=null){
            control= bundl.getStringArray("control");
        }

        e_diagnostico = (EditText) findViewById(R.id.edt_diagnostico);
        e_tratamiento = (EditText) findViewById(R.id.edt_tratamiento);
        e_producto = (EditText) findViewById(R.id.edt_producto);
        e_dosis = (EditText) findViewById(R.id.edt_dosis);
        e_frecuencia = (EditText) findViewById(R.id.edt_frecuencia);
        e_observaciones = (EditText) findViewById(R.id.edit_observaciones);
        btn_guardar = (Button) findViewById(R.id.btn_guardar_Control);
        manager0 = new Manager_control(this);




    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar_Control:
                if (validar(e_diagnostico.getText().toString(), e_tratamiento.getText().toString(), e_producto.getText().toString(), e_dosis.getText().toString(), e_frecuencia.getText().toString(),e_observaciones.getText().toString()) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                } else {

                    String var1 = control[0];
                    String var2 = control[1];
                    String var3 = control[2];
                    String var4 = control[3];
                    String var5 = e_diagnostico.getText().toString();
                    String var6 = e_tratamiento.getText().toString();
                    String var7 = e_producto.getText().toString();
                    String var8 = e_dosis.getText().toString();
                    String var9 = e_frecuencia.getText().toString();
                    String var10 = e_observaciones.getText().toString();

                    manager0.inserta(var1,var2,var3,var4,var5, var6, var7, var8, var9,var10);
                    Intent ir_main = new Intent().setClass(IngresarControl2.this, Main.class);
                    startActivity(ir_main);
                    finish();
                }
        }
    }

    public Boolean validar(String diagnostico, String tratamiento, String producto, String dosis, String frecuencia,String observacion) {
        if (diagnostico.equals("") || tratamiento.equals("") || producto.equals("") || dosis.equals("") || frecuencia.equals("")  || observacion.equals("")  ) {
            return false;
        }else {
            return true;
        }
    }

}
