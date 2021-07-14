package sg.edu.rp.c346.id20004969.p09_ndp_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Song> al;
    ListView lv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv = findViewById(R.id.lv);
        btn = findViewById(R.id.btntop);
        al = new ArrayList<Song>();
        ArrayAdapter adapter= new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(adapter);
        DBHelpers dbh = new DBHelpers(MainActivity2.this);
        al.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = al.get(position);
                Intent intent = new Intent(MainActivity2.this, EditActivity.class);
                intent.putExtra("data", target);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelpers dbh = new DBHelpers(MainActivity2.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                adapter.notifyDataSetChanged();
            }
        });

    }
}