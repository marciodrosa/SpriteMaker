package com.spritemaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class CalculatorTest {
	
	private Calculator calculator;
	
	public CalculatorTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
		calculator = new Calculator();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void shouldCalculateFinalImageSizeWithOneRow() {
		// given:
		ImageSize size1 = new ImageSize();
		size1.width = 10;
		size1.height = 100;
		ImageSize size2 = new ImageSize();
		size2.width = 30;
		size2.height = 300;
		ImageSize size3 = new ImageSize();
		size3.width = 20;
		size3.height = 200;
		
		// when:
		ImageSize finalImageSize = calculator.calculateFinalImageSize(Arrays.asList(size1, size2, size3), 3);
		
		// then:
		assertEquals("The width is not the expected", 60, finalImageSize.width);
		assertEquals("The height is not the expected", 300, finalImageSize.height);
	}

	@Test
	public void shouldCalculateFinalImageSizeWithTwoRows() {
		// given:
		ImageSize size1 = new ImageSize();
		size1.width = 10;
		size1.height = 100;
		ImageSize size2 = new ImageSize();
		size2.width = 30;
		size2.height = 300;
		ImageSize size3 = new ImageSize();
		size3.width = 20;
		size3.height = 200;
		
		// when:
		ImageSize finalImageSize = calculator.calculateFinalImageSize(Arrays.asList(size1, size2, size3), 2);
		
		// then:
		assertEquals("The width is not the expected", 40, finalImageSize.width);
		assertEquals("The height is not the expected", 500, finalImageSize.height);
	}

	@Test
	public void shouldCalculateFinalImageSizeWith20Rows50Images250pixels() {
		// given:
		List<ImageSize> sizes = new ArrayList<>();
		for (int i=0; i<50; i++) {
			ImageSize size = new ImageSize();
			size.width = 250;
			size.height = 250;
			sizes.add(size);
		}
		
		// when:
		ImageSize finalImageSize = calculator.calculateFinalImageSize(sizes, 20);
		
		// then:
		assertEquals("The width is not the expected", 5000, finalImageSize.width);
		assertEquals("The height is not the expected", 750, finalImageSize.height);
	}
}
