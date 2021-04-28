package comsaleh.caloriesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionViewHolder> {

    private static final String TAG = "NutritionAdapter";
    private ArrayList<Nutrition> nutritionList = new ArrayList<>();

    public NutritionAdapter(ArrayList<Nutrition> nutritionList) {
        this.nutritionList = nutritionList;
    }

    @NonNull
    @Override
    public NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_item_layout, parent, false);
        return new NutritionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionViewHolder holder, int position) {
        Nutrition nutrition = nutritionList.get(position);
        holder.nameTxt.setText(nutrition.getName());
        holder.servingTxt.setText(String.valueOf(nutrition.getServing()));
        holder.fatsTxt.setText(String.valueOf(nutrition.getFats())+" fats");
        holder.caloriesTxt.setText(String.valueOf(nutrition.getCalories()) + " calories");
        holder.sourceTxt.setText(String.valueOf(nutrition.getSource()));

    }

    @Override
    public int getItemCount() {
        return nutritionList.size();
    }
}
