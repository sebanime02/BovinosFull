package layout.layouts;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
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

/**
 * Created by sebastian on 07/06/15.
 */
public class IngresarManejo  extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{



    private RadioGroup rdgGrupo6;
    private String var_evento="";
    private String var_fecha="";

    private EditText e_id6, e_nomb6,e_manejo;
    String[] manejo;
    int eleccion;
    private Calendar calendar;
    private int year, month, day;
    private Button btn_fecha6;
    private ImageButton imgbtn;
    private String name2 = "";
    private String foto_rq;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_manejo);


        e_id6 = (EditText) findViewById(R.id.edt_id_bovino6);
        e_nomb6 = (EditText) findViewById(R.id.edt_nombre_bovino6);
        btn_fecha6 = (Button) findViewById(R.id.btn_fecha6);
        rdgGrupo6 = (RadioGroup) findViewById(R.id.rdgGrupo6);
        imgbtn = (ImageButton) findViewById(R.id.foto_bovino);

        rdgGrupo6.setOnCheckedChangeListener(this);
        //registerForContextMenu(imgbtn);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        if (checkedId == R.id.rdgCorte) {
            var_evento = "Corte de Ombligo";

        } else if (checkedId== R.id.rdgCastracion) {
            var_evento = "Castracción";

        }
        else if (checkedId== R.id.rdgDescorne){

            var_evento = "Descorne";

        }
        else if(checkedId== R.id.rdgCascos){
            var_evento= "Arreglo de Cascos";

        }
        else if(checkedId== R.id.rdgOtro){

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.activity_alert_otro, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    var_evento= userInput.getText().toString();
                                }
                            })
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_siguiente6:
                if(validar(e_id6.getText().toString(),e_nomb6.getText().toString(),var_fecha,var_evento)==false){
                    Toast.makeText(this,"Debe Llenar Todos Los Campos",Toast.LENGTH_LONG).show();
                }else {
                    manejo = new String[4];
                    Intent ir_reg = new Intent(this, IngresarManejo2.class);

                    manejo[0] = e_id6.getText().toString();
                    manejo[1] = e_nomb6.getText().toString();
                    manejo[2] = var_fecha;
                    manejo[3] = var_evento;

                    ir_reg.putExtra("manejo",manejo);
                    startActivity(ir_reg);
                }
                break;
            case R.id.btn_fecha6:
                showDialog(999);
                break;
            case R.id.foto_bovino:

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
        btn_fecha6.setText("Fecha del Evento: " + var_fecha);
    }

    public Boolean validar(String id, String nombre, String fecha, String evento) {
        if (id.equals("") || nombre.equals("") || fecha.equals("") || evento.equals("")) {
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
                Uri output = Uri.fromFile(new File(name2));
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

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//			name2 = Environment.getExternalStorageDirectory() + "/test.jpg";
        getMenuInflater().inflate(R.menu.menu_foto_perfil, menu);
    }

    @Override protected void onActivityResult(int requestCode,  int resultCode, Intent data) {
        if (requestCode == 1) {
            foto_rq = name2;
            imgbtn.setImageBitmap(BitmapFactory.decodeFile(foto_rq));

            new MediaScannerConnection.MediaScannerConnectionClient() {
                private MediaScannerConnection msc = null; {
                    msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
                }
                public void onMediaScannerConnected() {
                    msc.scanFile(foto_rq, null);
                }
                public void onScanCompleted(String path, Uri uri) {
                    msc.disconnect();
                }
            };

            name2 = foto_rq;

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


