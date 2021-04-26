package comsaleh.caloriesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView dataTxt;
    EditText searchTxt;
    Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataTxt =  findViewById(R.id.dataView);
        searchTxt = findViewById(R.id.foodText);
    }

    public void search(View v){

        String str = searchTxt.getText().toString().trim();
        new Thread(new CaloriesRunnable(str,this)).start();
    }

}