package sg.edu.rp.c346.demodatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btnInsert;
    Button btnGetTask;
    TextView tvResults;
    ListView lv;
    ArrayList<Task> al;
    TasksAdapter aa;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetTask = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        btnInsert = findViewById(R.id.btnInsert);
        lv = findViewById(R.id.lv);




        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
            }
        });

      btnGetTask.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // Create the DBHelper object, passing in the
              // activity's Context
              DBHelper db = new DBHelper(MainActivity.this);

              // Insert a task
              ArrayList<String> data = db.getTaskContent();
              db.close();

              String txt = "";
              for (int i = 0; i < data.size(); i++) {
                  Log.d("Database Content", i +". "+data.get(i));
                  txt += i + ". " + data.get(i) + "\n";
              }
              tvResults.setText(txt);
              al = new ArrayList<Task>();

              al = db.getTasks();


              aa = new TasksAdapter(MainActivity.this, R.layout.row, al);

              lv.setAdapter(aa);

              aa.notifyDataSetChanged();

          }
      });

}
}
