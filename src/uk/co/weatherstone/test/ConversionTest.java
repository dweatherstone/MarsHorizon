package uk.co.weatherstone.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.co.weatherstone.build.Conversion;

class ConversionTest {
	
	private Conversion conversion;
	
	@BeforeEach
	void setUp() throws Exception {
		conversion = new Conversion();
	}

	@Test
	void test() {
		conversion.addInput("Power", 1);
		conversion.addInput("Data", 1);
		conversion.addSuccess("Comms", 5);
		assertEquals(conversion.toString(), "1 Data, 1 Power => 5 Comms");
	}

}
