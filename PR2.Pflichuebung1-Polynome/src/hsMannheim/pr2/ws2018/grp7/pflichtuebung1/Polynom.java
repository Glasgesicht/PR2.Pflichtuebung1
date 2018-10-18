package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author Philipp Pörling
 * @author Bernhardt Alexander Scheibner
 * @author Elvis Herbrandt
 */
public final class Polynom {

    /**
     * Polynome werden durch double[] realisiert der double realisiert den
     * koeffizienten, die Wertigkeit ist über die Sortierung im Array bestimmt. Ein
     * Grad, der in dem Polynom nicht vorkommt ist als 0 gespeichert ( da 0*x^n= 0 )
     */
    private double[] koeffizient;
    private int grad = -1;

    // Definition eines Polynoms mittel elipse (varargs)
    public Polynom(double... doubles) {
        this.koeffizient = doubles;
        this.grad = this.koeffizient.length;
    }

    /**
     * Gibt alle Koeffizienten des Polynoms als double[] wieder
     * 
     * @return double[]
     * 
     */
    public double[] getKoeffizienten() {

        return koeffizient;
    }

    /**
     * gibt definierten Koeffizienten eines Polynoms wieder
     * 
     * @param Grad
     * @return double
     */
    public double getKoeffizient(int a) {

        return koeffizient[a];
    }

    /**
     * Gibt grad eines Polynoms wieder
     * 
     * @return grad des Polynoms
     */
    public int getGrad() {

        return grad;
    }

    /**
     * Prüft, ob ein bestimmter koefffizient positiv, oder negativ ist.
     * 
     * @param grad
     * @return boolean isPositive
     */
    public boolean isPositive(int grad) {

        return (this.getKoeffizient(grad) >= 0) ? true : false;
    }

    /**
     * Prüft, ob ein definierter grad eines Polynoms null ist.
     * 
     * @param grad
     * @return boolean isNull
     */
    public boolean isNull(int grad) {

        return (Math.abs(this.getKoeffizient(grad)) < 0.0001) ? true : false;
    }

    /**
     * Prüft, ob zwei Polynome identisch sind
     * 
     * @param Polynom
     *            a
     * @param Polynom
     *            b
     * @return boolean
     */
    public boolean equals(Polynom b) {

        Polynom test = subtrahiere(b);
        double[] polynom = test.getKoeffizienten();

        for (double i : polynom) {
            if (Math.abs(i) > 0.0001)
                return false;
        }
        return true;
    }

    /**
     * Konvertiert Polynom zu einem String
     * 
     * @return Polynom als String in formattierter Form
     */
    @Override
    public String toString() {
        // Wir definieren das Format, in dem die Zahlen ausgeben wollen.
        // Hier ohne Nachkommestelle, wenn diese X,00 wäre. Sonst bis zu 10
        // Nachkommastellen (darüber wird gerundet)
        DecimalFormat numbertoString = new DecimalFormat("0.##########");
        StringBuffer ausgabe = new StringBuffer();
        boolean nullpolynom = true;
        if (this.getGrad() <= 0)
            return "0";
        for (int i = this.getGrad() - 1; i >= 0; i--) {

            // Grade mit der Wertigkeit "0" werden bei der Ausgabe ignoriert
            if (!this.isNull(i)) {
                nullpolynom = false;
                // Formatierte Ausgabe der Elemente unseres Polynoms

                // Ist die Zahl positiv und nicht an erster Stelleß
                if (this.isPositive(i) && (i != this.getGrad() - 1))
                    // Wir geben ein "+" aus, wenn die Zahl positiv ist.
                    ausgabe.append("+ ");
                // Ist die Zahl negativ?
                else if (!this.isPositive(i))
                    ausgabe.append("- ");

                ausgabe.append(numbertoString.format(Math.abs(this.getKoeffizient(i))));
                // Ausgabe ohne nx^1 (= nx) oder nx^0 (= n)
                if (i > 1)
                    ausgabe.append("x^" + i + " ");
                else if (i == 1)
                    ausgabe.append("x ");

            }
        }
        if (nullpolynom)
            return "0";
        return ausgabe.toString();
    }

    /**
     * Gibt Polynom formattiert in der Konsole aus
     * 
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * berechnet die Wertigkeit eines Polynoms bei eingesetzten X-Werten, mehere
     * X-Werte möglich
     * 
     * @param X-Werte
     * @return Wert des Polynoms bei eingesetzten X als double[]
     */
    public double[] berechne(double... x) {

        // Die übergebenen X
        double[] zuBestimmendeX = x;
        // Hier werden die Ergebnisse gespeiert.
        // Anzahl der Ergebnisse entspricht der Anzahl der übergebenen X
        double[] ergebnis = new double[zuBestimmendeX.length];

        // Loop wird für jedes gegebene X durchlaufen
        for (int i = 0; i < zuBestimmendeX.length; i++) {

            double zwischenergebnis = 0;
            // berechnen des wertes für aktuelles X
            for (int v = this.getGrad() - 1; v >= 0; v--) {
                //
                zwischenergebnis = zwischenergebnis + this.getKoeffizient(v) * (Math.pow(zuBestimmendeX[i], v));
            }
            ergebnis[i] = zwischenergebnis;
        }

        return ergebnis;
    }

    /**
     * Addiert 2 Polynome. Gibt Polynom als Rückgabe aus.
     * 
     * @this Polynom A
     * @param Polynom
     *            B
     * @return Polynom = Polynom A + Polynom B
     */
    public Polynom addiere(Polynom b) {
        // herausfinden wie groß das Hilfsarray werden muss, um sicher die Polynome
        // fassen zu können
        int size = Math.max(this.getGrad(), b.getGrad());
        // das Hilfsarray initialisieren
        double[] hilfsarray;
        hilfsarray = new double[size];

        for (int i = this.getGrad() - 1; i >= 0; i--) {
            hilfsarray[i] = this.getKoeffizient(i);
        }
        // jede Stelle in dem Hilfsarray mit jeder Stelle aus dem zweiten Polynom
        // addieren
        for (int i = b.getGrad() - 1; i >= 0; i--) {
            hilfsarray[i] = hilfsarray[i] + b.getKoeffizient(i);
        }

        // da unser Koeffizient final ist, muss das neue Polynom auf einen Rutsch
        // erstellt werden
        Polynom ergebnisAddition = new Polynom(hilfsarray);
        return ergebnisAddition;
    }

    /**
     * Subtrahiert Polynom a - Polynom b
     * 
     * @this Polynom a
     * @param Polynom
     *            b
     * @return Polynom c = Polynom a - Polynom c
     */
    public Polynom subtrahiere(Polynom b) {

        // Erstelle Hilfsarray mit der Länge des Polynom B
        double[] hilfsarray = new double[b.getGrad()];

        // Schleife, die das Polymon b negiert im ilfsarray speichert
        for (int i = 0; i < b.getGrad(); i++)
            hilfsarray[i] = b.getKoeffizient(i) * (-1);

        // Polynom "this" addiert mit negiertem Polynom b
        return this.addiere(new Polynom(hilfsarray));
    }

    /**
     * Diffenzieren eines Polynoms
     * 
     * @return Polynom, differnziert
     */
    public Polynom differenzier() {
        // Erzeuge ein Array mit 1 kleiner als unser Polynom

        double[] ergebnis = new double[this.getGrad() - 1];

        /*
         * Schleife um folgendes zu berechnen: An jeder Stelle vom Ursprügnlichem
         * Polynom wird Koeffizient * Grad
         */
        for (int n = 0; n < ergebnis.length; n++) {
            ergebnis[n] = this.koeffizient[n + 1] * (n + 1);
        }

        return new Polynom(ergebnis);
    }

    /**
     * Integrieren eines Polynoms, +C wird ignoriert.
     * 
     * @return Polynom, integriert
     */
    public Polynom integrier() {

        // Erzeuge ein Array mit 1 kleiner als unser Polynom
        double[] ergebnis = new double[this.getGrad() + 1];

        /*
         * Schleife um folgendes zu berechnen: An jeder Stelle vom Ursprügnlichem
         * Polynom wird Koeffizient * Grad // ergebnis[1]=this.koeffizient[0]/1; //
         */
        ergebnis[0] = 0;

        for (int i = 1; i < ergebnis.length; i++) {
            ergebnis[i] = this.koeffizient[i - 1] / i;
        }

        return new Polynom(ergebnis);
    }

}
