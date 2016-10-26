package layout.layouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.controlers.plan_vacunacion.Detalle_Alertas_vacunacion;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.models.Manager_vacunas;
import com.example.pablosanjuan.core.vo.InventarioVO;
import com.example.pablosanjuan.core.vo.VacunaVO;

import java.util.ArrayList;
import java.util.List;


public class Detalle_PlanVacunacion extends ActionBarActivity {
    private ListView listView;
    private ImageView img;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__plan_vacunacion);
        listView=(ListView)findViewById(R.id.listViewDetallesPV);
        img=(ImageView)findViewById(R.id.imgDetallepv);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=Integer.parseInt(bundle.get("id").toString());
        }
        try{
            InventarioVO bovino= new Manager_inventario(this).getRegistrosById(id);
            img.setImageURI(Uri.parse(bovino.getFoto()));
            List<VacunaVO> vacunas=new Manager_vacunas(this).getRegistrosByIdBovino(bovino.getIdS());
            ArrayList<String> datos=new ArrayList<>();
            if(vacunas!=null){
                for(int i=0;i<vacunas.size();i++){
                    VacunaVO vacu=vacunas.get(i);
                    datos.add(""+vacu.getNombreVacuna()+"\n"+vacu.getDosisvacuna()+"\n"+vacu.getViaAdmivacuna()+"\n"+vacu.getFechaVacunacion());

                }
                listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos));
            }

        }catch (Exception e){}

    }



}
