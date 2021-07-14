package sg.edu.rp.c346.id20004969.p09_ndp_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText editTitle, editSingers, editYear;
    RadioGroup rgroup;
    Button btnUpd,btnDel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    TextView editID;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTitle = findViewById(R.id.etTitle);
        editSingers = findViewById(R.id.etSingers);
        editYear = findViewById(R.id.etYear);
        rgroup = findViewById(R.id.rgroup);
        btnUpd = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDelete);
        rb1 = findViewById(R.id.R1);
        rb2 = findViewById(R.id.R2);
        rb3 = findViewById(R.id.R3);
        rb4 = findViewById(R.id.R4);
        rb5 = findViewById(R.id.R5);
        editID = findViewById(R.id.editID);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");
        System.out.println(data);

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelpers dbh = new DBHelpers(EditActivity.this);
                if(editTitle.getText().toString().isEmpty() || editSingers.getText().toString().isEmpty() || editYear.getText().toString().isEmpty()){
                    Toast.makeText(EditActivity.this, "No input detected", Toast.LENGTH_SHORT).show();
                }
                else{
                    int selectedId = rgroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(selectedId);
                    int stars = Integer.parseInt(radioButton.getText().toString());

                    data.setTitle(editTitle.getText().toString());
                    data.setSingers(editSingers.getText().toString());
                    data.setYear(Integer.parseInt(editYear.getText().toString()));
                    data.setStar(stars);

                    System.out.println(data);
                    dbh.updateSong(data);
                    dbh.close();
                    Toast.makeText(EditActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelpers dbh = new DBHelpers(EditActivity.this);
                dbh.deleteNote(data.getId());
            }
        });
    }
}