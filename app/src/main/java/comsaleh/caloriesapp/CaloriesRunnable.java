package comsaleh.caloriesapp;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CaloriesRunnable implements Runnable {

    private MainActivity mainActivity;
    private static final String TAG = "CaloriesRunnable";
    private static final String URL = "https://nutritionix-api.p.rapidapi.com/v1_1/search/";
    private static final String API_KEY = "8694c31524msh9489d792de20f42p137d32jsn7a3cd585ce55";
    private static final String HOST = "nutritionix-api.p.rapidapi.com";
    private String query;

    public CaloriesRunnable(String query,MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.query = query;
    }

    @Override
    public void run() {

        Uri.Builder builder = Uri.parse(URL+query).buildUpon();
        builder.appendQueryParameter("fields","item_name,item_id,brand_name,nf_calories,nf_total_fat");
        String urlString = builder.toString();
        Log.d(TAG, "run: "+urlString);
        StringBuilder strBuilder = new StringBuilder();
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-key",API_KEY);
            connection.setRequestProperty("x-rapidapi-host",HOST);
            connection.connect();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                StringBuilder result = new StringBuilder();
                BufferedReader reader = null;
                Log.d(TAG, "run: "+connection.getResponseCode());
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                String line;
                while (null != (line = reader.readLine())) {
                    result.append(line).append("\n");
                }
                Log.d(TAG, "run: "+result.toString());
                return;
            }

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                strBuilder.append(data).append("\n");
            }
            Log.d(TAG, "run: "+strBuilder.toString());

        }
        catch (Exception e){
            Log.d(TAG, "run: "+e.toString());
        }
    }
}
