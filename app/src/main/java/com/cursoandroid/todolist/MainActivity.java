package com.cursoandroid.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
            banco.execSQL("CREATE TABLE IF NOT EXISTS tarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    entradaTarefa = txtTarefa.getText().toString();
                    salvarTarefa(entradaTarefa);
                }
            });

            //get cursor
            cursor = banco.rawQuery("SELECT * FROM tarefas", null);

            //get columns index
            indiceColunaId = cursor.getColumnIndex("id");
            indiceColunaTarefa = cursor.getColumnIndex("tarefa");

            //set cursor to first element
            cursor.moveToFirst();

            while (cursor != null){
                Log.i("RESULTADO - ", "ID [" + cursor.getString(indiceColunaId) + "] " + cursor.getString(indiceColunaTarefa));
                cursor.moveToLast();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void salvarTarefa(String tarefa){
        try {
            if (!tarefa.equals("")){
                banco.execSQL("INSERT INTO tarefas (tarefa) VALUES('" + tarefa + "')");
                Toast.makeText(MainActivity.this, R.string.tarefaSalva, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, R.string.tarefaVazia, Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
