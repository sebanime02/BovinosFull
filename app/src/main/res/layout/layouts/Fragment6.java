package layout.layouts;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.adapters.ManejoAdapter;
import com.example.pablosanjuan.core.models.Manager_manejo;
import com.example.pablosanjuan.core.vo.ManejoVO;

import java.util.List;

/**
 * Created by sebastian on 07/06/15.
 */
public class Fragment6 extends Fragment {


    private Button b_borrar6;
    ListView lista;
    ManejoAdapter adapter;
    Manager_manejo manager6;
    private List<ManejoVO> listaRegistros;
    private com.melnykov.fab.FloatingActionButton btn_add_manejo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registro_manejo, container, false);

        manager6 = new Manager_manejo(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista6);
        listaRegistros = manager6.getRegistros();
        adapter = new ManejoAdapter(getActivity(), listaRegistros);
        lista.setAdapter(adapter);
        btn_add_manejo = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_manejo);

        //b_borrar6.setOnClickListener(this);

        btn_add_manejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_agregar_manejo:
                        Intent intent = new Intent(getActivity(), IngresarManejo.class);
                        startActivity(intent);
                        break;


                }

            }
        });





        return rootView;
    }


;

}
