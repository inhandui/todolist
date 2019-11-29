package com.cursoandroid.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText txtTarefa;
    private Button btnAdicionar;
    private ListView lvTarefas;

    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTarefa = findViewById(R.id.txtTarefa);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        lvTarefas = findViewById(R.id.lvTarefas);

        //Creating data base
        banco = openOrCreateDatabase("appTarefas", MODE_PRIVATE, null);

        //Creating table
        banco.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTERGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");
    }
}
