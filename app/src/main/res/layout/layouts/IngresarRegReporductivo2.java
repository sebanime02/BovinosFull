package layout.layouts;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;


public class IngresarRegReporductivo2 extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button btn_atras, btn_sigte;
    private String[] reg_repro, reg_repro2;
    private RadioGroup rdgGrupo;
    private String var_empadre = "";
    private EditText num_pajilla, raza, procedencia;
    private String variable_pajilla = "";
    private String variable_raza = "";
    private String variable_procedencia = "";
    private RadioButton rdbutton_monta, rdbutton_inseminacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresarregreporductivo2);
        Bundle bundl=getIntent().getExtras();

        btn_atras = (Button) findViewById(R.id.btn_atras_ing_rep_2);
        btn_sigte = (Button) findViewById(R.id.btn_siguiente_ing_rep_2);
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo_empadre);
        rdbutton_monta = (RadioButton) findViewById(R.id.rdgMontaNatural);
        rdbutton_inseminacion = (RadioButton) findViewById(R.id.rdgInseminacion);
        num_pajilla = (EditText) findViewById(R.id.num_pajilla);
        raza = (EditText) findViewById(R.id.raza_animlal);
        procedencia = (EditText) findViewById(R.id.procedencia);

        btn_atras.setOnClickListener(this);
        btn_sigte.setOnClickListener(this);
        rdgGrupo.setOnCheckedChangeListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        btn_atras.setTypeface(font);
        btn_sigte.setTypeface(font);

        num_pajilla.setVisibility(View.INVISIBLE);
        raza.setVisibility(View.INVISIBLE);
        procedencia.setVisibility(View.INVISIBLE);

        if(bundl!=null){
            reg_repro = bundl.getStringArray("reg_repro");
        }
    }

    public void onClick(View v) {

        if (rdbutton_inseminacion.isChecked()){
            variable_pajilla = num_pajilla.getText().toString();
            variable_raza = raza.getText().toString();
            variable_procedencia = procedencia.getText().toString();
        }else if (rdbutton_monta.isChecked()){
            variable_pajilla = "null";
            variable_raza = "null";
            variable_procedencia = "null";
        }

        switch (v.getId()) {
            case R.id.btn_siguiente_ing_rep_2:
                if (validar(variable_pajilla, variable_raza, variable_procedencia, var_empadre) == false) {
                    Toast.makeText(this, "Debe Llenar Todos Los Campos", Toast.LENGTH_LONG).show();
                } else {
                    continuar();
                }
                break;
            case R.id.btn_atras_ing_rep_2:
                super.onBackPressed();
                break;
        }
    }

    private void continuar() {
        reg_repro2 = new String[11];
        reg_repro2[0] = reg_repro[0];
        reg_repro2[1] = reg_repro[1];
        reg_repro2[2] = reg_repro[2];
        reg_repro2[3] = reg_repro[3];
        reg_repro2[4] = reg_repro[4];
        reg_repro2[5] = reg_repro[5];
        reg_repro2[6] = reg_repro[6];
        reg_repro2[7] = var_empadre;
        reg_repro2[8] = variable_pajilla;
        reg_repro2[9] = variable_raza;
        reg_repro2[10] = variable_procedencia;
        Intent ir_reg2 = new Intent(this, IngresarRegReporductivo3.class);
        ir_reg2.putExtra("reg_repro2", reg_repro2);
        startActivity(ir_reg2);
    }

    public Boolean validar(String pajilla, String raza, String procedencia, String empadre) {
        if (pajilla.equals("") || raza.equals("") || procedencia.equals("") || empadre.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgMontaNatural) {
            var_empadre = "Monta Natural";
            num_pajilla.setVisibility(View.INVISIBLE);
            raza.setVisibility(View.INVISIBLE);
            procedencia.setVisibility(View.INVISIBLE);

        } else if (checkedId == R.id.rdgInseminacion) {
            var_empadre = "Inseminacion Artificial";

            num_pajilla.setVisibility(View.VISIBLE);
            raza.setVisibility(View.VISIBLE);
            procedencia.setVisibility(View.VISIBLE);
        }
    }
}