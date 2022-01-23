package FuzzyControler;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Fuzzy {

    FIS fis;
    public Fuzzy (String fileName) {
        fis = FIS.load(fileName, false);
    }

    public void startCharts() {
        JFuzzyChart.get().chart(fis);
    }

    public void calc (int wymaganiaRosliny, int wilgotnoscGleby, int temperatura, int naslonecznienie) {
        fis.setVariable("wymagania_rosliny", wymaganiaRosliny);
        fis.setVariable("wilgotnosc_gleby", wilgotnoscGleby);
        fis.setVariable("temperatura", temperatura);
        fis.setVariable("naslonecznienie", naslonecznienie);

        fis.evaluate();
    }

    public void endCharts () {
        Variable v = fis.getVariable("nawodnienie");
        System.out.println(v);
        JFuzzyChart.get().chart(v, v.getDefuzzifier(), true);
    }

    public Variable getVariable () {
        return fis.getVariable("nawodnienie");
    }
}
