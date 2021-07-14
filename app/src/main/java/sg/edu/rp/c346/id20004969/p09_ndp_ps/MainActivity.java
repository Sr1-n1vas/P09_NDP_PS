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

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    TextView txtTitle, txtSingers, txtYear;
    EditText etTitle, etSingers, etYear;
    RadioGroup RDGrp;
    RadioButton RB1, RB2 , RB3, RB4, RB5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnshwlist);
        txtTitle = findViewById(R.id.txtSongTitle);
        txtSingers = findViewById(R.id.txtSingers);
        txtYear = findViewById(R.id.txtYear);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        RDGrp = findViewById(R.id.grpstars);
        RB1 = findViewById(R.id.R1);
        RB2 = findViewById(R.id.R2);
        RB3 = findViewById(R.id.R3);
        RB4 = findViewById(R.id.R4);
        RB5 = findViewById(R.id.R5);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelpers dbh = new DBHelpers(MainActivity.this);
                boolean isChecked = true;
//                if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() || rb5.isChecked()){
//                    isChecked = false;
//                }

                if(etTitle.getText().toString().isEmpty() || etSingers.getText().toString().isEmpty() || etYear.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "No input detected", Toast.LENGTH_SHORT).show();
                }
                else{
                    String title = etTitle.getText().toString();
                    String singers = etSingers.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int selectedId = RDGrp.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    int stars = Integer.parseInt(radioButton.getText().toString());

                    dbh.insertSong(title, singers, year, stars);
                    Toast.makeText(MainActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}