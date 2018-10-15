package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

public class PolynomTest {
	Polynom berechne = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
	
	Polynom addsub1 = new Polynom(5,4,-2,3,9.5,8);
	Polynom addsub2 = new Polynom(3,7,6,-6,0,4,7,11);
	
	Polynom differenzier = new Polynom(-10,0,0.5,-1,2);
    Polynom integr = new Polynom(0,1,-3,8);
    
	@Test
	public void testBerechnen() {

		assertEquals("Funktion berechnen funktioniert nicht", "[9.5, 25.5, 59017.5, 109017.5]",
				Arrays.toString(berechne.berechne(1, -1, 5, -5)));

	}
	
	@Test
	public void testAddieren() {
		
		assertEquals("Funktion zum addieren Fehlerhaft!","[8.0, 11.0, 4.0, -3.0, 9.5, 4.0, 7.0, 11.0]",Arrays.toString((addsub1.addiere(addsub2).getKoeffizienten())));
	}

	@Test
	public void testSubtrahieren() {
		
		assertEquals("Funktion zum subtrahieren Fehlerhaft!","[2.0, -3.0, -8.0, 9.0, 9.5, -12.0, -7.0, -11.0]",Arrays.toString((addsub1.subtrahiere(addsub2).getKoeffizienten())));
	}
	
	@Test
	public void testDifferenzieren() {
		
		assertEquals("Funktion zum differenzieren Fehlerhaft!","[0.0, 1.0, -3.0, 8.0]",Arrays.toString((differenzier.differenzier()).getKoeffizienten()));
	}
	
	@Test
	public void testIntegrieren() {
		
		assertEquals("Funktion zum integrieren Fehlerhaft!","[0.0, 0.0, 0.5, -1.0, 2.0]",Arrays.toString((integr.integrier()).getKoeffizienten()));
	}
	
}
