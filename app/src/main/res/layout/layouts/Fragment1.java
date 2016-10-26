package layout.layouts;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.adapters.InventarioAdapter;
import com.example.pablosanjuan.core.models.Manager_inventario;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.List;

/**
 * Created by Pablo Sanjuan on 28/05/2015.
 */

public class Fragment1 extends Fragment {

    private Button  b_borrar;
    ListView lista;
    InventarioAdapter adapter;
    Manager_inventario manager;
    private List<InventarioVO> listaRegistros;
    private TextView txt_info;
    private com.melnykov.fab.FloatingActionButton  btn_add_bovino;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inventario, container, false);

        txt_info = (TextView) rootView.findViewById(R.id.info_invemtario);
        manager = new Manager_inventario(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista1);
        listaRegistros = manager.getRegistros();
        adapter = new InventarioAdapter(getActivity(), listaRegistros);
        lista.setAdapter(adapter);

        b_borrar = (Button) rootView.findViewById(R.id.btn_borrar);


        b_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {


                    case R.id.btn_borrar:
                        manager.borrarRegistros();
                        break;
                }

            }
        });

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Avgardn.ttf");





        btn_add_bovino = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_bovino);


        //b_borrar6.setOnClickListener(this);

        btn_add_bovino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_bovino:
                        Intent intent = new Intent(getActivity(), IngresarBovino.class);
                        startActivity(intent);
                        break;


                }

            }
        });





        return rootView;
    }


}