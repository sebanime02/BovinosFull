package layout.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.vo.registro_productivo.LecheVO;

import java.util.List;

public class MenuLeche extends Fragment {


    ListView lista;
    DbLeche dbLeche;
    LecheAdapter lecheAdapter;
    private List<LecheVO> listaleche;
    private com.melnykov.fab.FloatingActionButton btn_agregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_leche, container, false);

        btn_agregar = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_leche);


        lista = (ListView) rootView.findViewById(R.id.lista_leche);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClick(position);
            }
        });
        dbLeche = new DbLeche(getActivity());
        listaleche = dbLeche.getRegistros();
        lecheAdapter = new LecheAdapter(getActivity(), listaleche);
        lista.setAdapter(lecheAdapter);


        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_leche:
                        Intent intent = new Intent(getActivity(), IngresarLeche.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return rootView;
    }

    private void itemClick(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new MenuLeched();
        String id = listaleche.get(position).getIdSis();
        String idbovino = listaleche.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("idbov",idbovino);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }


}
