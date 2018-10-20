package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import java.text.DecimalFormat;

/**
 * @author Philipp Pörling
 * @author Bernhardt Alexander Scheibner
 * @author Elvis Herbrandt
 */
public final class Polynom {

    /**
     * Polynome werden durch double[] realisiert. Jeder Double des arrays bildet
     * einen koeffizienten. Der Grad ist über die Sortierung im Array bestimmt. 
     * Ein Grad, der in dem Polynom nicht vorkommt ist als 0 gespeichert ( da 0*x^n= 0 )
     */
    private final double[] koeffizient;
    private final int grad;

    // Definition eines Polynoms mittel elipse (varargs)
    public Polynom(double... doubles) {
        this.koeffizient = doubles;
        this.grad = this.koeffizient.length;
    }

    /**
     * Gibt alle Koeffizienten des Polynoms als double[] wieder.
     * 
     * @return double[] gibt alle Koeffizienten wieder
     * 
     */
    public double[] getKoeffizienten() {

        return koeffizient;
    }

    /**
     * Gibt definierten Koeffizienten eines Polynoms wieder
     * 
     * @param int Grad des Koeffizienten
     * @return double Wert des Koeffizienten
     */
    public double getKoeffizient(int a) {

        return koeffizient[a];
    }

    /**
     * Gibt den Grad eines Polynoms wieder
     * 
     * @return int Grad des Polynoms
     */
    public int getGrad() {

        return grad;
    }

    /**
     * Prüft, ob ein bestimmter koefffizient positiv oder negativ ist.
     * 
     * @param int Grad des Koeffizienten
     * @return boolean isPositive 
     */
    public boolean isPositive(int grad) {

        return (this.getKoeffizient(grad) >= 0) ? true : false;
    }

    /**
     * Prüft, ob ein definierter Grad eines Polynoms null ist.
     * 
     * @param grad Bestimmter Grad des Polynoms
     * @return boolean
     */
    public boolean isNull(int grad) {

        return (Math.abs(this.getKoeffizient(grad)) < 0.0001) ? true : false;
    }

    /**
     * Prüft, ob zwei Polynome identisch sind
     * 
     * @param Polynom a
     * @return boolean
     */
    public boolean equals(Polynom b) {

        Polynom test = this.subtrahiere(b);
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

        for (int i = this.getGrad() - 1; i >= 0; i--) {

            // Grade mit der Wertigkeit "0" werden bei der Ausgabe ignoriert
            if (!this.isNull(i)) {
                nullpolynom = false;
                // Formatierte Ausgabe der Elemente unseres Polynoms
                // Ist die Zahl positiv und nicht an erster Stelle?
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
     * @param double... X-Werte
     * @return double[] Wert des Polynoms bei eingesetzten X
     */
    public double[] berechne(double... x) {

        // Die übergebenen X
        double[] zuBestimmendeX = x;
        // Hier werden die Ergebnisse gespeichert.
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
     * @param Polynom B
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
     * @param Polynom b
     * @return Polynom c = Polynom a - Polynom c
     */
    public Polynom subtrahiere(Polynom b) {

        // Erstelle Hilfsarray mit der Länge des Polynom B
        double[] hilfsarray = new double[b.getGrad()];

        // Schleife, die das Polymon b negiert im Hilfsarray speichert
        for (int i = 0; i < b.getGrad(); i++)
            hilfsarray[i] = b.getKoeffizient(i) * (-1);

        // Polynom "this" addiert mit negiertem Polynom b
        return this.addiere(new Polynom(hilfsarray));
    }

    /**
     * Diffenzieren eines Polynoms
     * 
     * @return Polynom differnziert
     */
    public Polynom differenzier() {
        // Erzeuge ein Array mit 1 kleiner als unser Polynom, da beim differenzieren n^1 zu n^0 wird.

        double[] ergebnis = new double[this.getGrad() - 1];
        /*
         * Schema: Cx^v, wobei C=Koeffizient, x=x, und v=Exponent, bzw. Grad
         * C'=C*v und ^v-1
         * Jeder Koeffizient vom original Polynom * Grad des Koeffizienten im
         * original Polynom wird an eine Stelle weniger im Ergebnis-Array gesetzt. Dadurch wird ^v-1 realisiert.
         * ergebnis [0] = this.koeffizient[1]*1         
         * ergebnis [1] = this.koeffizient[2]*2
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
        // Erzeuge ein Array mit 1 größer als unser Polynom, da +C bzw. 0 noch rangehangen wird.
        double[] ergebnis = new double[this.getGrad() + 1];
        
        /*
         * Schema: Cx^v, wobei C=Koeffizient, x=x, und v=Exponent, bzw. Grad
         * F(x)=C*^v/^v' mit ^v'=^v+1 
         * Jeder Koeffizient vom original Polynom / Grad des Koeffizienten im
         * original Polynom wird an eine Stelle weiter im Ergebnis-Array gesetzt. Dadurch wird ^v+1 realisiert.
         * ergebnis [1] = this.koeffizient[0]/1         
         * ergebnis [2] = this.koeffizient[1]/2
         */
        
        //+C bzw. 0, an letzte Stelle des Polynoms
        ergebnis[0] = 0;

        for (int i = 1; i < ergebnis.length; i++) {
            ergebnis[i] = this.koeffizient[i - 1] / i;
        }

        return new Polynom(ergebnis);
    }

}
