package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author Philipp P�rling
 * @author Bernhardt Alexander Scheibner
 * @author Elvis Herbrandt
 */
public final class Polynom {

    /**
     * Polynome werden durch double[] realisiert der double realisiert den
     * koeffizienten, die Wertigkeit ist �ber die Sortierung im Array bestimmt. Ein
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
     * @return koeffizient
     * 
     */
    public double[] getKoeffizienten() {

        return koeffizient;
    }

    /**
     * @param Grad
     * @return Koeffizient des Polynoms am Grad
     */
    public double getKoeffizient(int a) {

        return koeffizient[a];
    }

    /**
     * @return grad des Polynoms
     */
    public int getGrad() {

        return grad;
    }

    /**
     * Pr�ft, ob ein bestimmter koefffizient positiv, oder negativ ist.
     * 
     * @param grad
     * @return boolean isPositive
     */
    public boolean isPositive(int grad) {

        return (this.getKoeffizient(grad) >= 0) ? true : false;
    }

    /**
     * Pr�ft, ob ein definierter grad eines Polynoms null ist.
     * 
     * @param grad
     * @return boolean isNull
     */
    public boolean isNull(int grad) {

        return (Math.abs(this.getKoeffizient(grad)) < 0.0001) ? true : false;
    }

    /**
     * Pr�ft, ob zwei Polynome identisch sind
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
        // Hier ohne Nachkommestelle, wenn diese X,00 w�re. Sonst bis zu 10
        // Nachkommastellen (dar�ber wird gerundet)
        DecimalFormat numbertoString = new DecimalFormat("0.##########");
        StringBuffer ausgabe = new StringBuffer();
        if (this.getGrad() <= 0)
            return "0";
        for (int i = this.getGrad() - 1; i >= 0; i--) {

            // Grade mit der Wertigkeit "0" werden bei der Ausgabe ignoriert
            if (!this.isNull(i)) {
                // Formatierte Ausgabe der Elemente unseres Polynoms

                // Ist die Zahl positiv und nicht an erster Stelle�
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
     * X-Werte m�glich
     * 
     * @param X-Werte
     * @return Wert des Polynoms bei eingesetzten X als double[]
     */
    public double[] berechne(double... x) {

        // Die �bergebenen X
        double[] zuBestimmendeX = x;
        // Hier werden die Ergebnisse gespeiert.
        // Anzahl der Ergebnisse entspricht der Anzahl der �bergebenen X
        double[] ergebnis = new double[zuBestimmendeX.length];

        // Loop wird f�r jedes gegebene X durchlaufen
        for (int i = 0; i < zuBestimmendeX.length; i++) {

            double zwischenergebnis = 0;
            // berechnen des wertes f�r aktuelles X
            for (int v = this.getGrad() - 1; v >= 0; v--) {
                //
                zwischenergebnis = zwischenergebnis + this.getKoeffizient(v) * (Math.pow(zuBestimmendeX[i], v));
            }
            ergebnis[i] = zwischenergebnis;
        }

        return ergebnis;
    }

    /**
     * Addiert 2 Polynome. Gibt Polynom als R�ckgabe aus.
     * 
     * @this Polynom A
     * @param Polynom
     *            B
     * @return Polynom = Polynom A + Polynom B
     */
    public Polynom addiere(Polynom b) {
        // herausfinden wie gro� das Hilfsarray werden muss, um sicher die Polynome
        // fassen zu k�nnen
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

        // Erstelle Hilfsarray mit der L�nge des Polynom B
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
         * Schleife um folgendes zu berechnen: An jeder Stelle vom Urspr�gnlichem
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
         * Schleife um folgendes zu berechnen: An jeder Stelle vom Urspr�gnlichem
         * Polynom wird Koeffizient * Grad // ergebnis[1]=this.koeffizient[0]/1; //
         * ergebnis[2]=this.koeffizient[1]/2; // ergebnis[3]=this.koeffizient[2]/3; //
         * ergebnis[4]=this.koeffizient[3]/4;
         */
        ergebnis[0] = 0;

        for (int i = 1; i < ergebnis.length; i++) {
            ergebnis[i] = this.koeffizient[i - 1] / i;
        }

        return new Polynom(ergebnis);
    }

    // genutzt zum Testen der Operationen
    public static void main(String args[]) {

        System.out.println("Gebe einige Polynome testweise aus:");
        Polynom erster = new Polynom(1, -222.5, 0, 0.6, 4, 5, 0, -7);

        // Arbeiten mit einem "leeren Polynom"
        Polynom zweiter = new Polynom(2, 4, 0, -3, 5.43, -0.5, 1);
        Polynom nullpolynom = new Polynom();

        erster.print();
        zweiter.print();
        nullpolynom.print();
        // Zur Aufgabenstellung "Berechne"
        System.out.println("Berechne Ergebnisse");
        Polynom eins = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
        // for (double i : eins.berechne(1, -1, 5, -5)) {
        // System.out.println(i);
        System.out.println(Arrays.toString(eins.berechne()));
        // }

        // Zur Aufgabenstelung "Addiere"
        Polynom addition1 = new Polynom(5, 4, -2, 3, 9.5, 8);
        Polynom addition2 = new Polynom(3, 7, 6, -6, 0, 4, 7, 11);
        // Das erwartete Ergebnis sollte in etwa so aussehen:
        // P1(x) + P2(x) = 11x^7 + 7x^6 4x^5 + 9,5x^4 - 3x^3 + 4x^2 + 11x + 8
        System.out.println(addition1.addiere(addition2).toString()); // sollte so eigentlich funktionieren, oder? :(
        System.out.println(addition1.subtrahiere(addition2).toString());

        // Spielwiese P�rling - /
        System.out.println("\nSpielwiese P�rling:\n");
        Polynom diefferenz = new Polynom(-10, 0, 0.5, -1, 2);
        Polynom ergebnisDIF = diefferenz.differenzier();
        System.out.println(Arrays.toString(ergebnisDIF.getKoeffizienten()));
        diefferenz.print();
        ergebnisDIF.print();

        // Polynom integrale = new Polynom(6,6,6,6);
        // System.out.println(integrale.getGrad());
        // Polynom ergebnisINTE=integrale.integrier();
        // integrale.print();
        // ergebnisINTE.print();

    }

}
