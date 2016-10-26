package layout.layouts;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.List;


public class IngresarLeche extends ActionBarActivity implements View.OnClickListener {

    EditText edt_lote;
    EditText edt_parto;
    EditText edt_nombre;
    ImageView image;
    AutoCompleteTextView edt_id;
    Button btn_guardar;
    DbLeche dbLeche;
    List<InventarioVO> bovinos;
    Manager_inventario dbManager_inventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_leche);
        edt_lote = (EditText) findViewById(R.id.edt_lote);
        edt_parto = (EditText) findViewById(R.id.edt_parto);
        edt_id = (AutoCompleteTextView) findViewById(R.id.edt_id_bovinol);
        edt_nombre = (EditText) findViewById(R.id.edt_nombre_bovinol);
        btn_guardar = (Button) findViewById(R.id.btn_guardar_Leche);
        btn_guardar.setOnClickListener(this);
        dbLeche = new DbLeche(this);
        dbManager_inventario =  new Manager_inventario(this);
        image=(ImageView)findViewById(R.id.imv_bovinol);
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

        if(dbManager_inventario.getRegistrosByIdBovino(edt_id.getText().toString())==null) {
            Toast.makeText(this, "El id del Bovino no existe", Toast.LENGTH_SHORT).show();
        }else {
        String id = edt_id.getText().toString();
        InventarioVO inv = new Manager_inventario(this).getRegistrosByIdBovino(id);
        id = ""+inv.getIdS();
        String parto = edt_parto.getText().toString();
        String lote = edt_lote.getText().toString();
        String total = "";
        dbLeche.inserta(this, id, lote, parto, total);
        }

    }

}
