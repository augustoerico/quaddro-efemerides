package br.com.erico.efemerides;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile on 22/09/2016.
 */
public class EfemerideDAO extends SQLiteOpenHelper implements DAO<Efemeride> {

    private Context context;
    private String TABELA = "datas";
    private String[] COLUNAS = {"idEfemeride", "mes", "dia", "descricao"};


//            ─────────▄──────────────▄
//            ────────▌▒█───────────▄▀▒▌
//            ────────▌▒▒▀▄───────▄▀▒▒▒▐
//            ───────▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
//            ─────▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
//            ───▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀██▀▒▌
//            ──▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌
//            ──▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
//            ─▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌
//            ─▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌
//            ─▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐
//            ▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒▒▒░░░░░░▒▒▒▒▌
//            ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐
//            ─▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌
//            ─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐
//            ──▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌
//            ────▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀
//            ───▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀
//            ──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀

    public EfemerideDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
        this.context = context;
    }

    @Override
    public void insert(Efemeride efemeride) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mes", efemeride.getMes());
        contentValues.put("dia", efemeride.getDia());
        contentValues.put("descricao", efemeride.getDescricao());

        getWritableDatabase().insert(TABELA, null, contentValues);
    }

    @Override
    public void update(Efemeride efemeride) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mes", efemeride.getMes());
        contentValues.put("dia", efemeride.getDia());
        contentValues.put("descricao", efemeride.getDescricao());

        getWritableDatabase().update(TABELA, contentValues, "id = ?", new String[] { String.valueOf(efemeride.getIdEfemeride()) });
    }

    @Override
    public void delete(int id) {

        getWritableDatabase().delete(TABELA, "id = ?", new String[] { String.valueOf(id)} );

    }

    @Override
    public Efemeride get(int id) {
        Efemeride efemeride = new Efemeride();

        try {

            Cursor cursor = getReadableDatabase().query(TABELA, COLUNAS, "id = ?", new String[] {"" + id}, null, null, null, null);

            while(cursor.moveToNext()) {
                efemeride.setIdEfemeride(cursor.getInt(0));
                efemeride.setMes(cursor.getInt(1));
                efemeride.setDia(cursor.getInt(2));
                efemeride.setDescricao(cursor.getString(3));
            }

        } catch (Exception ex) {
            Log.e("get", ex.getMessage());
        }

        return efemeride;
    }

    @Override
    public List<Efemeride> all() {
        List<Efemeride> efemerides = new ArrayList<>();

        try {

            Cursor cursor = getReadableDatabase().query
                    (TABELA, COLUNAS, null, null, null, null, null, null);

            while(cursor.moveToNext()) {
                Efemeride efemeride = new Efemeride();
                efemeride.setIdEfemeride(cursor.getInt(0));
                efemeride.setMes(cursor.getInt(1));
                efemeride.setDia(cursor.getInt(2));
                efemeride.setDescricao(cursor.getString(3));
                efemerides.add(efemeride);
            }

        } catch (Exception ex) {
            Log.e("get", ex.getMessage());
        }

        return efemerides;
    }

    public void saveData(String query) {
        getWritableDatabase().execSQL(query);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuffer comando = new StringBuffer();
        comando.append("CREATE TABLE " + TABELA + "( ");
        comando.append("idEfemerides " + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        comando.append("mes " + " INTEGER , ");
        comando.append("dia " + " INTEGER , ");
        comando.append("descricao " + " TEXT);");

        sqLiteDatabase.execSQL(comando.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        StringBuffer comando = new StringBuffer();
        comando.append("IF EXISTS DROP TABLE " + TABELA);


    }
}
