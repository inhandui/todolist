package com.cursoandroid.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText txtTarefa;
    private Button btnAdicionar;
    private ListView lvTarefas;

    private SQLiteDatabase banco;
    private Cursor cursor;
    private int indiceColunaId;
    private int indiceColunaTarefa;

    private String entradaTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            txtTarefa = findViewById(R.id.txtTarefa);
            btnAdicionar = findViewById(R.id.btnAdicionar);
            lvTarefas = findViewById(R.id.lvTarefas);

            //Creating data base
            banco = openOrCreateDatabase("appTarefas", MODE_PRIVATE, null);

            //Creating table
            banco.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTERGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    entradaTarefa = txtTarefa.getText().toString();
                    banco.execSQL("INSERT INTO tarefas (tarefa) VALUES('" + entradaTarefa + "')");
                }
            });

            //get cursor
            cursor = banco.rawQuery("SELECT * FROM taferas", null);

            //get columns index
            indiceColunaId = cursor.getColumnIndex("id");
            indiceColunaTarefa = cursor.getColumnIndex("tarefa");

            //set cursor to first element
            cursor.moveToFirst();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
