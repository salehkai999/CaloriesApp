package comsaleh.caloriesapp;

public class CaloriesRunnable implements Runnable {

    private MainActivity mainActivity;
    private static final String url = "https://nutritionix-api.p.rapidapi.com/v1_1/";
    private static final String API_KEY = "8694c31524msh9489d792de20f42p137d32jsn7a3cd585ce55";
    private static final String HOST = "nutritionix-api.p.rapidapi.com";
    private String query;

    public CaloriesRunnable(String query,MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.query = query;
    }

    @Override
    public void run() {

    }
}
