package br.com.erico.efemerides;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = {"Efemérides de ", "ontem", "hoje", "amanhã", "Importação Efemérides"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MenuActivity.this, android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = getListAdapter().getItem(position).toString();
        if (!item.isEmpty()) {
            if(item.startsWith("Efem")) {
                Intent intent = new Intent(MenuActivity.this, Tela1Activity.class);
                startActivity(intent);
            } else if(item.startsWith("Ontem")) {

            } else if(item.startsWith("Import")) {

                try {

                    InputStream stream = getResources().openRawResource(R.raw.efemerides);
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);

                    EfemerideDAO efemerideDAO = new EfemerideDAO(MenuActivity.this);

                    String linha = new String();
                    while ((linha = bufferedReader.readLine()) != null) {
                        efemerideDAO.saveData(linha);
                        Log.i("SAVE", linha);
                    }

                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage());
                }

            }
        }
    }
}
