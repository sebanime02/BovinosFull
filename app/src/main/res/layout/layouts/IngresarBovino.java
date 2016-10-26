package layout.layouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class IngresarBovino extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup rdgGrupo;
    private String var_genero="";
    private String var_fecha="";
    private EditText e_id, e_nomb;
    private String[] bovino;
    int eleccion;
    private Calendar calendar;
    private int year, month, day;
    private Button btn_fecha, btn_sig;
    private ImageButton imgbtn;
    private String name2 = "";
    private String foto_rq;
    private String name = "";
    private String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_bovino);

        e_id = (EditText) findViewById(R.id.edt_id_bovino);
        e_nomb = (EditText) findViewById(R.id.edt_nombre_bovino);
        btn_fecha = (Button) findViewById(R.id.btn_fecha);
        btn_sig = (Button) findViewById(R.id.btn_siguiente);
        rdgGrupo = (RadioGroup) findViewById(R.id.rdgGrupo);
        imgbtn = (ImageButton) findViewById(R.id.foto_bovino);
        imagen = Uri.parse("android.resource://com.example.pablosanjuan/core/drawable/add").toString();
        colocarImagen();
        registerForContextMenu(imgbtn);
        rdgGrupo.setOnCheckedChangeListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        e_id.setTypeface(font);
        e_nomb.setTypeface(font);
        btn_fecha.setTypeface(font);
        btn_sig.setTypeface(font);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgMacho) {
            var_genero = "Macho";
        } else if (checkedId == R.id.rdgHembra) {
            var_genero = "Hembra";
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente:
                if(validar(e_id.getText().toString(),e_nomb.getText().toString(),var_fecha,var_genero, name2)==false){
                    Toast.makeText(this,"Debe Llenar Todos Los Campos y Elegir una Foto",Toast.LENGTH_LONG).show();
                }else {
                    bovino = new String[5];
                    Intent ir_reg = new Intent(this, IngresarBovino2.class);

                        bovino[0] = e_id.getText().toString();
                        bovino[1] = name2;
                        bovino[2] = e_nomb.getText().toString();
                        bovino[3] = var_fecha;
                        bovino[4] = var_genero;

                    ir_reg.putExtra("bovino", bovino);
                    startActivity(ir_reg);
                }
                break;
            case R.id.btn_fecha:
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

    public Boolean validar(String id, String nombre, String fecha, String genero, String name2) {
        if (id.equals("") || nombre.equals("") || fecha.equals("") || genero.equals("") || name2.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nueva_foto:
                Intent intent_foto =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri output = Uri.fromFile(new File(name));
                intent_foto.putExtra(MediaStore.EXTRA_OUTPUT, output);
                eleccion = 1;
                startActivityForResult(intent_foto, eleccion);
                return true;

            case R.id.opc_galeria:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                eleccion = 2;
                startActivityForResult(intent, eleccion);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void colocarImagen() {
        name = imagen;
        imgbtn.setImageURI(Uri.parse(imagen));
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
            String nombre_foto = "/ModBovino/" + e_nomb.getText().toString() + e_id.getText().toString() + ".jpg";
            name = Environment.getExternalStorageDirectory().getPath() + nombre_foto;
            getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
    }

    @Override protected void onActivityResult(int requestCode,  int resultCode, Intent data) {

        if (requestCode == 1) {
            imgbtn.setImageBitmap(BitmapFactory.decodeFile(name));

            new MediaScannerConnectionClient() {
                private MediaScannerConnection msc = null; {
                    msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                }
                public void onMediaScannerConnected() {
                    msc.scanFile(name, null);
                }
                public void onScanCompleted(String path, Uri uri) {
                    msc.disconnect();
                }
            };
            name2 = name;
        }else if (requestCode == 2){
            if(data == null){
                Toast.makeText(getApplicationContext()	,"No se eligio la foto!", Toast.LENGTH_SHORT).show();
            }else{
                Uri selectedImage = data.getData();
                name2 = selectedImage.toString();
                imgbtn.setImageURI(selectedImage);
            }
        }
    }
}