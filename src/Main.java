import FuzzyControler.Fuzzy;
import simulation.Simulate;

//arguments:
// test <needs> <humidity> <temperature> <sun>
// simulation <steps> <needs1> <needs2>

public class Main {

    public static void main(String[] args) {

        try {
            String mode = args[0];
            if (mode.equals("test")) {
                int needs = Integer.parseInt(args[1]);
                int humidity = Integer.parseInt(args[2]);
                int temperature = Integer.parseInt(args[3]);
                int sun = Integer.parseInt(args[4]);
                Fuzzy fuzzy = new Fuzzy("fuzzy_def.txt");
                fuzzy.startCharts();
                fuzzy.calc(needs, humidity, temperature, sun);
                fuzzy.endCharts();
            }
            else if (mode.equals("simulation")) {
                int steps = Integer.parseInt(args[1]);
                int needs1 = Integer.parseInt(args[2]);
                int needs2 = Integer.parseInt(args[3]);
                Simulate simulation = new Simulate(needs1, needs2);
                simulation.run(steps);
                simulation.makeCharts();
            }
            else {
                System.out.println("Niepoprawny tryb, poprawne tryby to 'test' lub 'simulation'");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out
                    .println("Niepoprawna liczba parametrow. Przyklad: java FuzzyExample 'test' int<wymagania> int<wilgotnosc> int<temperatura> int<naslonecznienie> lub 'simulation' int<liczba kroków> int<wymagania1> int<wymagania2>");
        } catch (NumberFormatException ex) {
            System.out
                    .println("Niepoprawny parametr. Przyklad: java FuzzyExample 'test' int<wymagania> int<wilgotnosc> int<temperatura> int<naslonecznienie> lub 'simulation' int<liczba kroków> int<wymagania1> int<wymagania2>");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}