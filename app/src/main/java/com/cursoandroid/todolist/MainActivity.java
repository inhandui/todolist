package com.cursoandroid.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText txtTarefa;
    private Button btnAdicionar;
    private ListView lvTarefas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTarefa = findViewById(R.id.txtTarefa);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        lvTarefas = findViewById(R.id.lvTarefas);
    }
}
