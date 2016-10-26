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
import com.example.pablosanjuan.core.adapters.ControlAdapter;
import com.example.pablosanjuan.core.models.Manager_control;
import com.example.pablosanjuan.core.vo.ControlVO;

import java.util.List;

/**
 * Created by Pablo Sanjuan on 28/05/2015.
 */
public class Fragment5 extends Fragment {

    private Button  b_borrar5;
    ListView lista;
    ControlAdapter adapter5;
    Manager_control manager5;
    private List<ControlVO> listaRegistros;
    private com.melnykov.fab.FloatingActionButton btn_add_control5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_controles, container, false);

        manager5 = new Manager_control(getActivity());
        lista = (ListView) rootView.findViewById(R.id.lista5);
        listaRegistros = manager5.getRegistros();
        adapter5 = new ControlAdapter(getActivity(), listaRegistros);
        lista.setAdapter(adapter5);
        btn_add_control5 = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_control);
        b_borrar5 = (Button) rootView.findViewById(R.id.btn_borrar5);








        btn_add_control5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_control:
                        Intent intent = new Intent(getActivity(), IngresarControl.class);
                        startActivity(intent);
                        break;


                }

            }
        });

        b_borrar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {


                    case R.id.btn_borrar5:
                        manager5.borrarRegistros();
                        break;
                }

            }
        });








        return rootView;
    }


}