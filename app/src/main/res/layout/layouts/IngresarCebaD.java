package layout.layouts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.registro_productivo.CebaDVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class IngresarCebaD extends ActionBarActivity {

    String idsis,fec,peso,reg;
    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
    Manager_inventario dbManager_inventario;
    DbCebaD dbCebaD;
    CebaDVO cebaDVO;

    EditText edt_peso;
    EditText edt_gdp;
    EditText edt_fecha;

    int dia;
    Date fechaant = null;
    Date hoy = new Date();

    private Calendar calendar;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_cebad);

        Bundle bundle = getIntent().getExtras();
        idsis = bundle.get("id").toString();
        fec = bundle.get("fec").toString();
        peso = bundle.get("peso").toString();
        reg = bundle.get("reg").toString();

        dbManager_inventario = new Manager_inventario(this);
        dbCebaD = new DbCebaD(this);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if(reg.equals("1")){
            try {
                fechaant = form.parse(fec);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            cebaDVO = dbCebaD.getRegistrosUltimo(idsis);
            fec = cebaDVO.getFecha();
            try {
                fechaant = form.parse(fec);
                peso = cebaDVO.getPeso();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        long dias = hoy.getTime()-fechaant.getTime();
        dias = dias/ (1000 * 60 * 60 * 24);
        dia = (int)dias;


        Toast.makeText(this, idsis + " " + fec + " " +dia, Toast.LENGTH_LONG).show();

        edt_gdp = (EditText) findViewById(R.id.edt_gdp);
        edt_peso = (EditText) findViewById(R.id.edt_pesocd);
        edt_fecha = (EditText) findViewById(R.id.edt_fechacd);

        edt_fecha.setHint(edt_fecha.getHint()+" "+fec);

        edt_peso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Double gdp = (Double.parseDouble(edt_peso.getText().toString()) - Double.parseDouble(peso)) / dia;
                    int cifras = (int) Math.pow(10, 2);
                    edt_gdp.setText("" + Math.rint(gdp * cifras) / cifras);
                } catch (Exception e) {
                    edt_gdp.setText("GDP(Gg)");
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ingresar_ceba_d, menu);
        return true;
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

    public void onClick(View view) {
        String peso = edt_peso.getText().toString();
        String gdp = edt_gdp.getText().toString();
        if (peso.equals("")) {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            String fecha = ""+day+"/"+month+"/"+year;
            dbCebaD.inserta(this, idsis, fecha, gdp,peso);
        }
    }
}
