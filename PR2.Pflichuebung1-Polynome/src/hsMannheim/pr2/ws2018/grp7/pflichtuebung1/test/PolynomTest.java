package hsMannheim.pr2.ws2018.grp7.pflichtuebung1.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

import hsMannheim.pr2.ws2018.grp7.pflichtuebung1.Polynom;

public class PolynomTest {
	Polynom berechne = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
	
	Polynom addsub1 = new Polynom(5,4,-2,3,9.5,-8);
	Polynom addsub2 = new Polynom(3,7,6,-6,0,4,7,11);
	Polynom ergebnisAddier = new Polynom(8.0, 11.0, 4.0, -3.0, 9.5, -4, 7.0, 11.0);
	Polynom ergebnisSubtr = new Polynom(2.0, -3.0, -8.0, 9.0, 9.5, -12, -7.0, -11.0);
	
	Polynom differenzier = new Polynom(-10,0,0.5,-1,2);
	Polynom ergebnisdiff = new Polynom (0.0, 1.0, -3.0, 8.0);
    Polynom integr = new Polynom(0,1,-3,8);
    Polynom ergebnisintegr = new Polynom(0.0, 0.0, 0.5, -1.0, 2.0);
    
	@Test
	public void testBerechnen() {

		assertEquals("Funktion berechnen funktioniert nicht", "[9.5, 25.5, 59017.5, 109017.5]",
				Arrays.toString(berechne.berechne(1, -1, 5, -5)));

	}
	
	@Test
	public void testAddieren() {
		
		assertEquals ("Funktion zum addieren Fehlerhaft!",ergebnisAddier.toString(),addsub1.addiere(addsub2).toString());
	}

	@Test
	public void testSubtrahieren() {

		assertEquals("Funktion zum subtrahieren Fehlerhaft",ergebnisSubtr.toString(),addsub1.subtrahiere(addsub2).toString());
	}
	
	@Test
	public void testDifferenzieren() {
		
		assertEquals("Funktion zum differenzieren Fehlerhaft",ergebnisdiff.toString(),differenzier.differenzier().toString());

	}
	
	@Test
	public void testIntegrieren() {
		
		assertEquals("Funktion zum integrieren Fehlerhaft",ergebnisintegr.toString(),integr.integrier().toString());
	}
	
}
