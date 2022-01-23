package simulation;

import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;

import world.Ground;
import world.Weather;

import static java.lang.Thread.sleep;

public class Simulate {
    private Ground ground1;
    private Ground ground2;
    private Weather world;

    XYSeries humidity1 = new XYSeries( "Wilgotnosc" );
    XYSeries humidity2 = new XYSeries( "Wilgotnosc" );
    XYSeries water_level1 = new XYSeries( "Nawodnienie" );
    XYSeries water_level2 = new XYSeries( "Nawodnienie" );

    XYSeries temperature_s = new XYSeries( "Temperatura" );
    XYSeries sun_s = new XYSeries( "Naslonecznienie" );
    XYSeries rain_s = new XYSeries( "Deszcz" );


    public Simulate (int plant1, int plant2) {
        world = new Weather();
        ground1 = new Ground(world, plant1);
        ground2 = new Ground(world, plant2);
    }

    public void run (int steps) {
        int counter = 0;
        while (counter < steps) {
            System.out.println("Epoka: " + counter);
            world.changeWeather();
            ground1.updateHumidity();
            ground2.updateHumidity();
            ground1.setWaterLevel();
            ground2.setWaterLevel();
            try {
                sleep (2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ground1.addWater();
            ground2.addWater();
            try {
                sleep (1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            humidity1.add(counter, ground1.getHumidity());
            humidity2.add(counter, ground2.getHumidity());
            water_level1.add(counter, ground1.getWater_level() * 25);
            water_level2.add(counter, ground2.getWater_level() * 25);
            temperature_s.add(counter, world.getTemperature());
            sun_s.add(counter, world.getSun());
            rain_s.add(counter, world.getRain() * 50);

            System.out.println(world.toString());
            System.out.println(ground1.toString());
            System.out.println(ground2.toString());
            System.out.println("\n");
            counter += 1;
        }
    }

    public void makeCharts () throws IOException {
        XYSeriesCollection dataset1 = new XYSeriesCollection( );
        dataset1.addSeries( humidity1 );
        dataset1.addSeries( water_level1 );

        XYSeriesCollection dataset2 = new XYSeriesCollection( );
        dataset2.addSeries( humidity2 );
        dataset2.addSeries( water_level2 );

        XYSeriesCollection dataset3 = new XYSeriesCollection( );
        dataset3.addSeries( temperature_s );
        dataset3.addSeries( sun_s );
        dataset3.addSeries( rain_s );

        JFreeChart xylineChart1 = ChartFactory.createXYLineChart(
                "Ziemia pierwsza",
                "Epoka",
                "Wartość w %",
                dataset1,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFreeChart xylineChart2 = ChartFactory.createXYLineChart(
                "Ziemia druga",
                "Epoka",
                "Wartość w %",
                dataset2,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFreeChart xylineChart3 = ChartFactory.createXYLineChart(
                "Pogoda",
                "Epoka",
                "Wartość",
                dataset3,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1280;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File XYChart1 = new File( "ground1.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart1, xylineChart1, width, height);
        File XYChart2 = new File( "ground2.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart2, xylineChart2, width, height);
        File XYChart3 = new File( "weather.jpeg" );
        ChartUtilities.saveChartAsJPEG( XYChart3, xylineChart3, width, height);
    }
}
