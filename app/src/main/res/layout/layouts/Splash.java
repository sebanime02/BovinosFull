package layout.layouts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.controlers.Logueo;
import com.example.pablosanjuan.core.controlers.Main;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class Splash extends ActionBarActivity {


    private static final long SPLASH_SCREEN_DELAY = 3000;
    private SharedPreferences prefs;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences("datos", Context.MODE_PRIVATE);
        txt = (TextView) findViewById(R.id.txt_splash);

        Typeface font = Typeface.createFromAsset(getAssets(), "Avgardn.ttf");
        txt.setTypeface(font);
        crear_folder();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (prefs.getBoolean("validar_sesion", false)) {
                    Intent ir_log = new Intent().setClass(com.example.pablosanjuan.core.controlers.Splash.this, Main.class);
                    startActivity(ir_log);
                    finish();
                }else{
                    Intent ir_princ = new Intent().setClass(com.example.pablosanjuan.core.controlers.Splash.this, Logueo.class);
                    startActivity(ir_princ);
                    finish();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void crear_folder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/ModBovino");
        boolean success = true;
        if (!folder.exists()) {
//	        Toast.makeText(this, "carpeta creada", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
//	        Toast.makeText(this, "La carpeta ya esxiste", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "fallo al crear carpeta", Toast.LENGTH_SHORT).show();
        }
    }
}
