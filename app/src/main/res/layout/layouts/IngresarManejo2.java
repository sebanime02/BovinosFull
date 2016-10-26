package layout.layouts;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_manejo;
import com.example.pablosanjuan.core.controlers.Main;

/**
 * Created by sebastian on 07/06/15.
 */
public class IngresarManejo2  extends ActionBarActivity implements View.OnClickListener  {

    private Button btn_guardar,btn_atras;


    private EditText  e_tratamiento, e_producto,e_observaciones;
    String[] manejo;
    private Manager_manejo manager0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_manejo2);
        Bundle bundl=getIntent().getExtras();
        if(bundl!=null){
            manejo= bundl.getStringArray("manejo");
        }



        e_tratamiento = (EditText) findViewById(R.id.edt_tratamiento6);
        e_producto = (EditText) findViewById(R.id.edt_producto6);

        e_observaciones = (EditText) findViewById(R.id.edit_observaciones6);
        btn_guardar = (Button) findViewById(R.id.btn_guardar_manejo);

        manager0 = new Manager_manejo(this);

        btn_guardar.setOnClickListener(this);




    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar_manejo:
                if (validar(e_tratamiento.getText().toString(), e_producto.getText().toString(), e_observaciones.getText().toString()) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                } else {

                    String var1 = manejo[0];
                    String var2 = manejo[1];
                    String var3 = manejo[2];
                    String var4 = manejo[3];

                    String var5 = e_tratamiento.getText().toString();
                    String var6 = e_producto.getText().toString();

                    String var7 = e_observaciones.getText().toString();

                    manager0.inserta(var1, var2, var3, var4, var5, var6, var7);
                    Intent ir_main = new Intent().setClass(IngresarManejo2.this, Main.class);
                    startActivity(ir_main);
                    finish();
                }

        }
        }


    public Boolean validar( String tratamiento, String producto,String observacion) {
        if ( tratamiento.equals("") || producto.equals("")  || observacion.equals("")  ) {
            return false;
        }else {
            return true;
        }
    }



}
