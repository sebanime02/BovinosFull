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
import com.example.pablosanjuan.core.vo.registro_productivo.CebaVO;

import java.util.List;


public class MenuCeba extends Fragment {


    ListView lista;
    DbCeba dbCeba;
    CebaAdapter cebaAdapter;
    private List<CebaVO> listaceba;
    private com.melnykov.fab.FloatingActionButton btn_agregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_ceba, container, false);

        lista = (ListView) rootView.findViewById(R.id.lista_ceba);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClick(position);
            }
        });


        dbCeba = new DbCeba(getActivity());
        listaceba = dbCeba.getRegistros();
        cebaAdapter = new CebaAdapter(getActivity(), listaceba);
        lista.setAdapter(cebaAdapter);

        btn_agregar = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_ceba);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_ceba:
                        Intent intent = new Intent(getActivity(), IngresarCeba.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return rootView;
    }

    private void itemClick(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new MenuCebaD();
        String id = listaceba.get(position).getIdSis();
        String fec = listaceba.get(position).getFecha();
        String peso = listaceba.get(position).getPeso();
        String idbov = listaceba.get(position).getId();

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("fec",fec);
        bundle.putString("peso",peso);
        bundle.putString("idbov",idbov);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
