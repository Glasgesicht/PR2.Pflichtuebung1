package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import java.text.DecimalFormat;

public class Polynom {

	// Polynome werden durch double[] realisiert
	// der double realisiert den koeffizienten, die Wertigkeit ist über die Sortierung im Array bestimmt.
	// Ein Grad, der in dem Polynom nicht vorkommt ist als 0 gespeichert ( da 0*x^n= 0 )
	private final double koeffizient[];
	private final int grad;

	// Definition eines Polynoms mittel elipse (varargs)
	Polynom(double... doubles) {
		this.koeffizient = doubles;
		this.grad = this.koeffizient.length;
	}

	// Zur vermeidung von fehlern wird ein leeres "Nullpolynom" durch eine einfache 0 realisiert
	Polynom() {
		this(0);
	}

	public double[] getKoeffizienten() {

		return koeffizient;
	}

	public double getKoeffizient(int a) {

		return koeffizient[a];
	}

	public int getGrad() {

		return grad;
	}

	// Prüft, ob ein bestimmter koefffizient positiv, oder negativ ist.
	public boolean isPositive(int grad) {

		return (getKoeffizient(grad) >= 0) ? true : false;
	}

	public boolean isNull(int grad) {

		return (Math.abs(getKoeffizient(grad)) < 0.0001) ? true : false;
	}
	
	public boolean equals(Polynom a, Polynom b) {

		Polynom test = subtrahiere(a,b);
		double[] polynom = test.getKoeffizienten();

		for(double i:polynom) {
			if(Math.abs(i) > 0.0001)
				return false;
		}
		return true;
	}

	// Formatierte Ausgabe eines Polynoms
	// Besser wäre eine formattierung des Polynoms als String, der dann z.B. mit Syo ausgegeben werden kann...
	public void print() {
		// Wir definieren das Format, in dem die Zahlen ausgeben wollen.
		// Hier ohne Nachkommestelle, wenn diese X,00 wäre. Sonst bis zu 10
		// Nachkommastellen (darüber wird gerundet)
		DecimalFormat numberFormatted = new DecimalFormat("0.##########");

		int grad = this.getGrad();
		boolean isNullpolynom = true;
		for (int i = grad - 1; i >= 0; i--) {
			

			// Grade mit der Wertigkeit "0" werden bei der Ausgabe ignoriert
			if (!this.isNull(i)) {
				isNullpolynom = false;
				// Formatierte Ausgabe der Elemente unseres Polynoms

				// Ist die Zahl positiv und nicht an erster Stelleß
				if (this.isPositive(i) && (i != grad - 1))
					// Wir geben ein "+" aus, wenn die Zahl positiv ist.
					System.out.print("+ ");
				// Ist die Zahl negativ?
				else if (!this.isPositive(i))
					System.out.print("- ");

				System.out.print(numberFormatted.format(Math.abs(this.getKoeffizient(i))));
				// Ausgabe ohne nx^1 (= nx) oder nx^0 (= n)
				if (i > 1)
					System.out.print("x^" + i + " ");
				else if (i == 1)
					System.out.print("x ");

			}
		}
		if(isNullpolynom)
			System.out.println("0");
		else
			System.out.print("\n");
	}

	/* Björn */
	public double[] berechne(double[]... X) {

		double[] ergebnis = {};
		return ergebnis;
	}

	/* Elvis Herdbrand */
	public Polynom addiere(Polynom a, Polynom b) {
		Polynom ergebnis = new Polynom();
		return ergebnis;
	}

	public Polynom subtrahiere(Polynom a, Polynom b) {
		Polynom ergebnis = new Polynom();
		return ergebnis;
	}

	/* Phelix Plörrking */

	public Polynom differenzier() {

		Polynom ergebnis = new Polynom();
		return ergebnis;
	}

	public Polynom integrier() {

		Polynom ergebnis = new Polynom();
		return ergebnis;
	}

	// genutzt zum Testen der Operationen
	public static void main(String args[]) {

		Polynom erster = new Polynom(1, -222.5, 0, 0.6, 4, 5, 0, -7);

		// Arbeiten mit einem "leeren Polynom"
		Polynom zweiter = new Polynom(2, 4, 0, -3, 5.43, -0.5, 1);
		Polynom nullpolynom = new Polynom();

		erster.print();
		zweiter.print();
		nullpolynom.print();
	}

}
