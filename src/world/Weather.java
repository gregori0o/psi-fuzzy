package world;

import java.util.Random;

public class Weather {
    private int temperature = 15;
    private int sun = 50;
    private int rain = 0; //0 - brak deszczu, 1 - słaby deszcz, 2 - mocny deszcz
    public Random generator = new Random ();

    public void heatUp () {
        if (temperature < 45)
            temperature += 1;
    }

    public void coolDown () {
        if (temperature > 0)
            temperature -= 1;
    }

    public void changeTemperature (int change) {
        int new_temp = temperature + change;
        if (new_temp < 0)
            new_temp = 0;
        if (new_temp > 45)
            new_temp = 45;
        temperature = new_temp;
    }

    public void changeSun (int change) {
        int new_sun = sun + change;
        if (new_sun < 0)
            new_sun = 0;
        if (new_sun > 100)
            new_sun = 100;
        sun = new_sun;
    }

    public void changeRain (int level) {
        rain = level;
    }

    public void changeWeather () {
        int temp_change = generator.nextInt(17) - 8; //-8 do 8
        int sun_change = generator.nextInt(31) - 15; //-15 do 15
        int rain_level = generator.nextInt(40); //5% szans na deszcz
        if (rain_level > 2) rain_level = 0;
        changeTemperature (temp_change);
        changeSun(sun_change);
        changeRain(rain_level);
    }

    public int calcHumidity (int old_value) {
        double t = temperature;
        double s = sun;
        double delta = 1 - (t*t / 3000);
        delta -= s / 20000;
        int new_value = (int) (delta * old_value);
        if (rain == 1)
            new_value += 3;
        else if (rain == 2)
            new_value += 7;
        if (new_value > 100)
            new_value = 100;
        return new_value;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getSun() {
        return sun;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getRain() {
        return rain;
    }

    @Override
    public String toString() {
        return "Pogoda: " +
                "temperatura = " + temperature +
                ", nasłonecznienie = " + sun +
                "%, poziom deszczu = " + rain;
    }
}
