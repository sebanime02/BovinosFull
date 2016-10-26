package layout.layouts;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.ManagerRegReproductivo;
import com.example.pablosanjuan.core.controlers.Main;


public class IngresarRegReporductivo4 extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup rdgGrupo;
    private String[] reg_repro4;
    private Button btn_atras, btn_sigte;
    private String var_cria, var_interv_partos, var_dias_vacios, var_dias_lactancia;
    private EditText interv_partos, dias_vacios, dias_lactancia;
    private ManagerRegReproductivo manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarregreporductivo4);

        manager = new ManagerRegReproductivo(this);
        Bundle bundl=getIntent().getExtras();
        if(bundl!=null){
            reg_repro4= bundl.getStringArray("reg_repro3");
        }
        btn_atras = (Button) findViewById(R.id.btn_atras_ing_rep_4);
        btn_sigte = (Button) findViewById(R.id.btn_siguiente_ing_rep_4);
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo_reg_crias);
        interv_partos = (EditText) findViewById(R.id.edt_inter_partos);
        dias_vacios = (EditText) findViewById(R.id.edt_dias_vacios);
        dias_lactancia = (EditText) findViewById(R.id.edt_dias_lactancia);

        btn_atras.setOnClickListener(this);
        btn_sigte.setOnClickListener(this);
        rdgGrupo.setOnCheckedChangeListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        btn_atras.setTypeface(font);
        btn_sigte.setTypeface(font);
    }

    public void onClick(View v) {

        var_interv_partos = interv_partos.getText().toString();
        var_dias_vacios = dias_vacios.getText().toString();
        var_dias_lactancia = dias_lactancia.getText().toString();

        switch (v.getId()) {
            case R.id.btn_siguiente_ing_rep_4:
                if (validar(var_cria, var_interv_partos, var_dias_vacios, var_dias_lactancia) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos y elegir un empadre", Toast.LENGTH_LONG).show();
                } else {

                    String var_id = reg_repro4[0];
                    String var_foto = reg_repro4[1];
                    String var_nombre = reg_repro4[2];
                    String var_fecha_monta = reg_repro4[3];
                    String var_peso = reg_repro4[4];
                    String var_edad = reg_repro4[5];
                    String var_concicion = reg_repro4[5];
                    String var_empadre = reg_repro4[7];
                    String var_pajilla = reg_repro4[8];
                    String var_raza = reg_repro4[9];
                    String var_procedencia = reg_repro4[10];
                    String var_id_reproductor = reg_repro4[11];
                    String var_preñez = reg_repro4[12];
                    String var_posible_parto = reg_repro4[13];
                    String var_fecha_parto = reg_repro4[14];
                    String var_num_parto = reg_repro4[15];
                    String var_cria_resul = var_cria;
                    String var_interv_partos_resul = var_interv_partos;
                    String var_dias_vacios_resul = var_dias_vacios;
                    String var_dias_lactancia_resul = var_dias_lactancia;

                    manager.insertar(var_id,var_nombre,var_fecha_monta,var_peso,var_edad,var_concicion,var_empadre,var_pajilla,var_raza,var_procedencia,var_id_reproductor,
                            var_preñez,var_posible_parto,var_fecha_parto,var_num_parto,var_cria_resul,var_interv_partos_resul,var_dias_vacios_resul,var_dias_lactancia_resul);
                    Intent ir_reg4 = new Intent(this, Main.class);
                    startActivity(ir_reg4);
                    finish();

                }
                break;
            case R.id.btn_atras_ing_rep_4:
                super.onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgVivo) {
            var_cria = "Vivo";
        } else if (checkedId == R.id.rdgMuerto) {
            var_cria = "Muerto";
        }
    }

    public Boolean validar(String var_cria, String var_interv_partos, String var_dias_vacios, String var_dias_lactancia) {
        if (var_cria.equals("") || var_interv_partos.equals("") || var_dias_vacios.equals("") || var_dias_lactancia.equals("")) {
            return false;
        }else {
            return true;
        }
    }
}