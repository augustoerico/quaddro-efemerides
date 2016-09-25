package br.com.erico.efemerides;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mobile on 22/09/2016.
 */
public class TestandoBancoDeDados extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(TestandoBancoDeDados.this, "AEHOOOOO", Toast.LENGTH_LONG).show();
        Log.i("asdf", "ASDF");

        Efemeride efemeride1 = new Efemeride();
        efemeride1.setMes(9);
        efemeride1.setDia(22);
        efemeride1.setDescricao("Dia do contador");

        Efemeride efemeride2 = new Efemeride(9, 22, "Inicio da Primavera");

        EfemerideDAO dao = new EfemerideDAO(TestandoBancoDeDados.this);
        dao.insert(efemeride1);
        dao.insert(efemeride2);

        Toast.makeText(TestandoBancoDeDados.this, "QUEBRO", Toast.LENGTH_LONG).show();

    }
}
