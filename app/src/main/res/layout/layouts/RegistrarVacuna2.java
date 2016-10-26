package layout.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_vacunas;


public class RegistrarVacuna2 extends ActionBarActivity {
    private TableLayout tableLayout;
    private LinearLayout linearLayout;
    private Button btn_otra;
    private String[] datos;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    private Manager_vacunas manager_vacunas;
    private EditText edt_nom_vacuna,edt_dosis_vacuna;
    private Spinner sp_via_vacuna;
    private String[] via=new String[]{"VIA INTRAMUSCULAR","VIA INTRAVENOSA","VIA SUBCUTANEA","VIA INTRADERMICA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vacuna2);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout_vacunas);
        linearLayout=(LinearLayout)findViewById(R.id.linear);
        btn_otra=(Button)findViewById(R.id.btn_otro);
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            datos=bundle.getStringArray("datos");
        }
        checkBox1=(CheckBox)findViewById(R.id.checkbox1);
        checkBox2=(CheckBox)findViewById(R.id.checkbox2);
        checkBox3=(CheckBox)findViewById(R.id.checkbox3);
        checkBox4=(CheckBox)findViewById(R.id.checkbox4);
        checkBox5=(CheckBox)findViewById(R.id.checkbox5);
        checkBox6=(CheckBox)findViewById(R.id.checkbox6);
        manager_vacunas=new Manager_vacunas(this);
        edt_nom_vacuna=(EditText)findViewById(R.id.edt_nom_vacuna);
        edt_dosis_vacuna=(EditText)findViewById(R.id.edt_dosis_vacuna);
        sp_via_vacuna=(Spinner)findViewById(R.id.sp_via_vacuna);
        sp_via_vacuna.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,via));



    }



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_otro:
                if(tableLayout.getVisibility()==View.VISIBLE){
                    tableLayout.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    btn_otra.setText("Vacunas");
                }else
                {
                    tableLayout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                    btn_otra.setText("Otra");
                } break;
            case R.id.btn_guardar_pv:

                Boolean rta= validar(edt_nom_vacuna.getText().toString(),edt_dosis_vacuna.getText().toString());
                if(rta){
                    if(checkBox1.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Fiebre Aftosa","5ml","VIA INTRAVENOSA");
                    }
                    if(checkBox2.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Carbono Sintomatico","5ml","VIA INTRAVENOSA");
                    }
                    if(checkBox3.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Rabia","5ml","VIA INTRAVENOSA");
                    }
                    if(checkBox4.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Endema Maligno","5ml","VIA INTRAVENOSA");
                    }
                    if(checkBox5.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Septicemia Hemorragica","5ml","VIA INTRAVENOSA");
                    }
                    if(checkBox6.isChecked()){
                        manager_vacunas.inserta(datos[0],datos[1],"Carbono Bacteriano","5ml","VIA INTRAVENOSA");
                    }else{
                        manager_vacunas.inserta(datos[0],datos[1],edt_nom_vacuna.getText().toString(),edt_dosis_vacuna.getText()+"ml",sp_via_vacuna.getSelectedItem().toString());
                    }
                    Intent intent=new Intent(this,Detalle_PlanVacunacion.class);
                    intent.putExtra("id", datos[0]);
                    startActivity(intent);
                    this.finish();

                }else{
                    Toast.makeText(this,"Selecione una vacuna",Toast.LENGTH_SHORT).show();
                }
            break;

        }


    }
    Boolean validar(String nombre,String dosis){
        Boolean bandera=false;
        if(btn_otra.getText().equals("Otra")){
            if(checkBox1.isChecked()){
                bandera= true;
            }
            if(checkBox2.isChecked()){
                bandera= true;
            }
            if(checkBox3.isChecked()){
                bandera= true;
            }
            if(checkBox4.isChecked()){
                bandera= true;
            }
            if(checkBox5.isChecked()){
                bandera= true;
            }
            if(checkBox6.isChecked()){
                bandera= true;
            }
            return bandera;

        }else{
            if((!nombre.equals(""))&&(!dosis.equals(""))){
                bandera=true;
            }

            return bandera;

        }

    }
}
