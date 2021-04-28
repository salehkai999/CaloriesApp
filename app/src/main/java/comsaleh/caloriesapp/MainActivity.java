package comsaleh.caloriesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchTxt;
    RecyclerView recyclerView;
    NutritionAdapter nutritionAdapter;
    ArrayList<Nutrition> nutritionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchTxt = findViewById(R.id.foodText);
        recyclerView = findViewById(R.id.recycler);
        nutritionAdapter = new NutritionAdapter(nutritionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(nutritionAdapter);
    }

    public void search(View v){
        String str = searchTxt.getText().toString().trim();
        new Thread(new CaloriesRunnable(str,this)).start();
        searchTxt.setText("");  
    }

    public void showData(ArrayList<Nutrition> nutritionArrayList) {
        nutritionList.clear(); // clear the list
        nutritionList.addAll(nutritionArrayList);
        nutritionAdapter.notifyDataSetChanged(); // notify the recycler view that the data has changed

    }
}