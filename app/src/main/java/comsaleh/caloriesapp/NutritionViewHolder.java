package comsaleh.caloriesapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NutritionViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView caloriesTxt;
    TextView sourceTxt;
    TextView fatsTxt;
    TextView servingTxt;


    public NutritionViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.nameTxt);
        caloriesTxt = itemView.findViewById(R.id.calories);
        sourceTxt = itemView.findViewById(R.id.sourceTxt);
        fatsTxt = itemView.findViewById(R.id.fatsTxt);
        servingTxt = itemView.findViewById(R.id.servingTxt);
    }
}
