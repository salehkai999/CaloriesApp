package comsaleh.caloriesapp;

public class Nutrition {

    private String name;
    private String source;
    private double calories;
    private double fats;
    private double serving;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getServing() {
        return serving;
    }

    public void setServing(double serving) {
        this.serving = serving;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", calories=" + calories +
                ", fats=" + fats +
                ", serving=" + serving +
                '}';
    }
}
