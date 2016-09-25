package br.com.erico.efemerides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_FILE_NAME = "MyPrefFile";

    private EditText email;
    private EditText password;
    private CheckBox rememberMe;
    private TextView lastAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);
        lastAccess = (TextView) findViewById(R.id.lastAccess);

        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, 0);

        boolean rememberMeField = settings.getBoolean("rememberMe", false);
        if (rememberMeField) {
            rememberMe.setChecked(true);
            email.setText(settings.getString("email", ""));
            password.setText(settings.getString("password", ""));
        }
        lastAccess.setText(settings.getString("lastLogin", ""));
    }

    public void onLogin(View view) {

        SharedPreferences settings = getSharedPreferences(PREF_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("rememberMe", rememberMe.isChecked());
        editor.putString("email", rememberMe.isChecked() ? email.getEditableText().toString().toLowerCase().trim() : "");
        editor.putString("password", rememberMe.isChecked() ? password.getEditableText().toString() : "");
        editor.putString("lastLogin", Calendar.getInstance().toString());

        editor.commit();

        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
