package layout.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_usuario;
import com.example.pablosanjuan.core.controlers.Main;

public class Registro extends ActionBarActivity implements View.OnClickListener {

    private Button b_guardar;
    private EditText ed_nombre,ed_direccion,ed_usuario,ed_contrasena;
    private Manager_usuario manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        manager = new Manager_usuario(this);
        ed_nombre = (EditText) findViewById(R.id.edt_nombre_reg);
        ed_direccion = (EditText) findViewById(R.id.edt_direccion_reg);
        ed_usuario = (EditText) findViewById(R.id.edt_usuario_reg);
        ed_contrasena = (EditText) findViewById(R.id.edt_contraseña_reg);
        b_guardar = (Button) findViewById(R.id.btn_grdr_registro);
        b_guardar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_grdr_registro:
                String var_nombre = ed_nombre.getText().toString();
                String var_direccion = ed_direccion.getText().toString();
                String var_usuario = ed_usuario.getText().toString();
                String var_contrasena = ed_contrasena.getText().toString();

                manager.insertar(var_nombre, var_direccion, var_usuario, var_contrasena);
                Intent ir_log = new Intent().setClass(com.example.pablosanjuan.core.controlers.Registro.this, Main.class);
                startActivity(ir_log);
                finish();
                break;
        }
    }
}
