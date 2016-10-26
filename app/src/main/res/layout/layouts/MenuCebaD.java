package layout.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.vo.registro_productivo.CebaDVO;

import java.util.List;


public class MenuCebaD extends Fragment {

    String idsis,fec,peso,idbov;
    com.melnykov.fab.FloatingActionButton btn_agregar;
    
    ListView lista;
    DbCebaD dbCebad;
    CebaDAdapter adapterCebad;
    private List<CebaDVO> listacebad;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_menu_cebad, container, false);

        idsis = getArguments().getString("id");
        fec = getArguments().getString("fec");
        peso = getArguments().getString("peso");
        idbov = getArguments().getString("idbov");

        //Toast.makeText(getActivity(),idsis+" "+fec,Toast.LENGTH_LONG).show();

        btn_agregar = (com.melnykov.fab.FloatingActionButton) rootView.findViewById(R.id.btn_agregar_cebad);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_agregar_cebad:
                        Intent intent = new Intent(getActivity(), IngresarCebaD.class);
                        intent.putExtra("id",idsis);
                        intent.putExtra("fec",fec);
                        intent.putExtra("peso",peso);
                        if(listacebad == null)
                        {
                            intent.putExtra("reg",1);
                        }else {
                            intent.putExtra("reg",0);
                        }
                        startActivity(intent);
                        break;
                }
            }

        });

        lista = (ListView) rootView.findViewById(R.id.lista_cebad);
        dbCebad = new DbCebaD(getActivity());
        listacebad = dbCebad.getRegistrosIdis(idsis);
        adapterCebad = new CebaDAdapter(getActivity(), listacebad,idbov);
        lista.setAdapter(adapterCebad);

        return rootView;
    }

}
