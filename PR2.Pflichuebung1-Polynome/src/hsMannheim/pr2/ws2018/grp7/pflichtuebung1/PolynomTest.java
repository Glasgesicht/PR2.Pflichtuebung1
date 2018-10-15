package hsMannheim.pr2.ws2018.grp7.pflichtuebung1;


import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

public class PolynomTest {
	Polynom eins = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
	@Test
	public void testBerechnen() {
		
		assertEquals("Funktion berechnen funktioniert nicht","[9.5, 25.5, 59017.5, 109017.5]",Arrays.toString(eins.berechne(1,-1,5,-5)));
		
		
	}

}
