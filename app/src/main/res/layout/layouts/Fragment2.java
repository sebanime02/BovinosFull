package layout.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.adapters.RegistroReproductivoAdapter;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.List;

/**
 * Created by Pablo Sanjuan on 28/05/2015.
 */
public class Fragment2 extends Fragment implements View.OnLongClickListener {

    private   Button b_borrar_reg_repro;
    ListView lista;
    RegistroReproductivoAdapter adapter;
    Manager_inventario manager1;
    private List<InventarioVO> listaRegistros;
    private com.melnykov.fab.FloatingActionButton  btn_add_registro;
    TextView info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reg_reproductivo, container, false);

        info = (TextView) rootView.findViewById(R.id.info_reproductivo);
        manager1 = new Manager_inventario(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista_frag2);
        listaRegistros = manager1.getRegistros();
        adapter = new RegistroReproductivoAdapter(getActivity(), listaRegistros);
        lista.setAdapter(adapter);
        btn_add_registro =  (com.melnykov.fab.FloatingActionButton)rootView.findViewById(R.id.btn_add_reg_reproductivo);
        b_borrar_reg_repro = (Button) rootView.findViewById(R.id.btn_borrar_reg_repro);








        btn_add_registro = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_add_reg_reproductivo);


        //b_borrar6.setOnClickListener(this);

        btn_add_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_add_reg_reproductivo:
                        Intent intent = new Intent(getActivity(), IngresarRegReporductivo1.class);
                        startActivity(intent);
                        break;


                }

            }
        });
        b_borrar_reg_repro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {


                    case R.id.btn_borrar_reg_repro:
                        manager1.borrarRegistros();
                        break;
                }

            }
        });





        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listaRegistros = new Manager_inventario(getActivity()).getRegistros();
        adapter =  new RegistroReproductivoAdapter(getActivity(), listaRegistros);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),DetalleRegReproductivo.class);
                intent.putExtra("id",l);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onLongClick(View v) {
        Long id = lista.getSelectedItemId();
        Intent intent=new Intent(getActivity(),IngresarRegReporductivo1.class);
        intent.putExtra("id",id);
        startActivity(intent);
        return false;
    }
}