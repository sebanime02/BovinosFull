package layout.layouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.vo.registro_productivo.LecheDDVO;

import java.util.List;


public class LecheDia extends Fragment {

    ListView lista;
    DbLecheD dbLecheD;
    LecheDDAdapter adapterLecheD;
    String idlec="";
    private List<LecheDDVO> listaleche;
    TextView textView;
    String fecha = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_leche_dia, container, false);
        idlec = getArguments().getString("id");
        fecha = getArguments().getString("fecha");
        lista = (ListView) rootView.findViewById(R.id.lista_leche_dia);
        dbLecheD = new DbLecheD(getActivity());
        textView = (TextView)rootView.findViewById(R.id.txv_reg_dia);
        textView.setText(textView.getText()+" "+fecha);
        listaleche = dbLecheD.getRegistrosdia(fecha, idlec);
        adapterLecheD = new LecheDDAdapter(getActivity(), listaleche);
        lista.setAdapter(adapterLecheD);

        return rootView;
    }

}