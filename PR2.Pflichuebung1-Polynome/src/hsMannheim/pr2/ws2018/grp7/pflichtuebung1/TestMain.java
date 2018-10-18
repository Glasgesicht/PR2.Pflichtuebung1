package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import java.util.Arrays;

public class TestMain {

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

        // Spielwiese Pörling - /
        System.out.println("\nSpielwiese Pörling:\n");
        Polynom diefferenz = new Polynom(-10, 0, 0.5, -1, 2);
        Polynom ergebnisDIF = diefferenz.differenzier();
        System.out.println(Arrays.toString(ergebnisDIF.getKoeffizienten()));
        diefferenz.print();
        ergebnisDIF.print();
        
        Polynom ftest = new Polynom();
        Polynom ztest = new Polynom(5);
        System.out.println("ergebnis:");
        
        ztest.differenzier().print();

        // Polynom integrale = new Polynom(6,6,6,6);
        // System.out.println(integrale.getGrad());
        // Polynom ergebnisINTE=integrale.integrier();
        // integrale.print();
        // ergebnisINTE.print();
    }
}
