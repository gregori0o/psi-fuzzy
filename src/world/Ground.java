package world;

import FuzzyControler.Fuzzy;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Ground {
    private int needs;
    private int humidity;
    private int water_level; //0 - brak, 1 - słabo, 2 - normalnie, 3 - mocno, 4 - silnie
    private Fuzzy fuzzy;
    private Weather world;


    public Ground (Weather world, int plant) {
        needs = plant;
        water_level = 0;
        humidity = 50;
        this.world = world;
        fuzzy = new Fuzzy("fuzzy_def.txt");
    }

    public Ground (Weather world, int plant, int start_humidity) {
        this (world, plant);
        humidity = start_humidity;
    }

    public void addWater () {
        int new_humidity = (humidity + water_level * 5);
        if (new_humidity > 100) new_humidity = 100;
        humidity = new_humidity;
    }

    public void setWaterLevel () {
        fuzzy.calc(needs, humidity, world.getTemperature(), world.getSun());
        Variable v = fuzzy.getVariable();
        double value = v.getValue();
        int new_level;
        if (value < 2)
            new_level = 0;
        else if (value < 4)
            new_level = 1;
        else if (value < 6)
            new_level = 2;
        else if (value < 8)
            new_level = 3;
        else
            new_level = 4;
        water_level = new_level;
    }

    @Override
    public String toString() {
        return "Ziemia: " +
                "potrzeby = " + needs +
                ", wilgotność= " + humidity +
                "%, poziom nawodnienia = " + water_level;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWater_level() {
        return water_level;
    }

    public void updateHumidity () {
        humidity = world.calcHumidity(humidity);
    }
}
