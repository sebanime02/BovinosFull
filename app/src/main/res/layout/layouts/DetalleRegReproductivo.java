package layout.layouts;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.models.ManagerRegReproductivo;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;
import com.example.pablosanjuan.core.vo.RegReproductivoVO;

import java.util.ArrayList;
import java.util.List;


public class DetalleRegReproductivo extends ActionBarActivity {
    private ListView listView;
    private ImageView img;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_rer_reprodccion);
        listView=(ListView)findViewById(R.id.listViewRegReproductivo);
        img=(ImageView)findViewById(R.id.imgDetalleRegRepro);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=Integer.parseInt(bundle.get("id").toString());
        }
        try{
            InventarioVO bovino= new Manager_inventario(this).getRegistrosById(id);
            img.setImageURI(Uri.parse(bovino.getFoto()));
            List<RegReproductivoVO> repro=new ManagerRegReproductivo(this).getRegistrosbyid(""+bovino.getIdS());
            ArrayList<String> datos=new ArrayList<>();
            if(repro!=null){
                for(int i=0;i<repro.size();i++){
                    RegReproductivoVO regRepro=repro.get(i);
                    datos.add("Nombre:"+regRepro.getNombre()+"\n"+"Fecha de Parto:"+regRepro.getFecha_Parto()+"\n"+"Fecha de Monte:"+regRepro.getFecha_Monte()+"\n"+"Fecha de Monte:"+regRepro.getFecha_Monte());
                }
                listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos));
            }

        }catch (Exception e){}
    }
}
