package comsaleh.caloriesapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CaloriesRunnable implements Runnable {

    /* Don't forget to add internet permission to Manifest
    *
    *  <uses-permission android:name="android.permission.INTERNET" />
    *
    * */
    private MainActivity mainActivity;
    private static final String TAG = "CaloriesRunnable";
    private static final String URL = "https://nutritionix-api.p.rapidapi.com/v1_1/search/";
    private static final String API_KEY = "8694c31524msh9489d792de20f42p137d32jsn7a3cd585ce55"; // Use your own API key since the source may limit the amount of requests to 50/day
    private static final String HOST = "nutritionix-api.p.rapidapi.com";
    private static final ArrayList<Nutrition> NUTRITION_ARRAY_LIST = new ArrayList<>();
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
            processData(strBuilder.toString());
        }
        catch (Exception e){
            Log.d(TAG, "run: "+e.toString());
        }
    }

    public void processData(String data){

        try{
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = (JSONArray)jsonObject.get("hits");
            Log.d(TAG, "processData: "+jsonArray.length());
            NUTRITION_ARRAY_LIST.clear();
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jObject = jsonArray.getJSONObject(i);
                JSONObject fieldsObj = jObject.getJSONObject("fields");
                Nutrition nutritionObj = new Nutrition();
                nutritionObj.setName(fieldsObj.getString("item_name"));
                nutritionObj.setSource(fieldsObj.getString("brand_name"));
                nutritionObj.setCalories(fieldsObj.getDouble("nf_calories"));
                nutritionObj.setFats(fieldsObj.getDouble("nf_total_fat"));
                nutritionObj.setServing(fieldsObj.getDouble("nf_serving_size_qty"));
                NUTRITION_ARRAY_LIST.add(nutritionObj);
                Log.d(TAG, "processData: "+nutritionObj.toString());
            }

           this.mainActivity.runOnUiThread(new Runnable() { // must call using runOnUiThread otherwise it'll crash can't call UI elements from different threads
                @Override
                public void run() {
                    mainActivity.showData(NUTRITION_ARRAY_LIST);
                }
            });
        }
        catch (Exception e){
            Log.d(TAG, "processData: "+e.toString());
        }



    }
}
