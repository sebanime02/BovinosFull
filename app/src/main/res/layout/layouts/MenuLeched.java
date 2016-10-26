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
import com.example.pablosanjuan.core.vo.registro_productivo.LecheDVO;

import java.util.List;


public class MenuLeched extends Fragment {


    ListView lista;
    DbLecheD dbLecheD;
    LecheDAdapter lecheDAdapter;
    private List<LecheDVO> listaleche;
    private String idlec="";
    private String idbov = "";
    private com.melnykov.fab.FloatingActionButton btn_agregar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_leched, container, false);

        btn_agregar = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_leched);
        idlec = getArguments().getString("id");
        idbov = getArguments().getString("idbov");
        lista = (ListView) rootView.findViewById(R.id.lista_leched);
        dbLecheD = new DbLecheD(getActivity());
        listaleche = dbLecheD.getRegistros(idlec);

        lecheDAdapter = new LecheDAdapter(getActivity(), listaleche,idbov);
        lista = (ListView) rootView.findViewById(R.id.lista_leched);
        lista.setAdapter(lecheDAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClick(position);
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_leched:
                        Intent intent = new Intent(getActivity(), IngresarLecheD.class);
                        intent.putExtra("id",idlec);
                        startActivity(intent);
                        break;
                }
            }
        });

        return rootView;
    }

    private void itemClick(int position) {
        String fecha = listaleche.get(position).getFecha();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new LecheDia();
        Bundle bundle = new Bundle();
        bundle.putString("fecha",fecha);
        bundle.putString("id",idlec);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
