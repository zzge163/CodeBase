package com.sxgis.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {

    private static Calculator calculator = new Calculator();
	
	@Before
	public void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	public void testAdd() {
		calculator.add(20);
		assertEquals(20, calculator.getResult());
	}

	@Test
	public void testSubstract() {
		calculator.add(20);
		calculator.substract(2);
		assertEquals(18, calculator.getResult());
	}

	@Ignore("Multiply() Not yet implemented")
	@Test
	public void testMultiply() {
	}

	@Test
	public void testDivide() {
		calculator.add(20);
		 calculator.divide(2);
		 assertEquals(10, calculator.getResult());
	}

}
