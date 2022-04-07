package co.grandcircus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import co.grandcircus.Assessment;
import co.grandcircus.testutils.AssessmentProxy;
import co.grandcircus.testutils.IOTester;
import co.grandcircus.testutils.Output;

class AssessmentTest {

	@Test
	void test1_printsMenuInLoop() {
		IOTester.start();
		IOTester.setInput("1", "3");

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");
	}

	@Test
	void test2_exitsLoop() {
		IOTester.start();
		IOTester.setInput("3");

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");

		assertThrows(AssertionError.class, () -> {
			out.skipToLineContainsMatch("3.{1,3}Exit");
		});
	}

	@Test
	void test2_exitsLoop2() {
		IOTester.start();
		IOTester.setInput("1", "3");

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");

		assertThrows(AssertionError.class, () -> {
			out.skipToLineContainsMatch("3.{1,3}Exit");
		});
	}

	@Test
	void test3_badInput() {
		IOTester.start();
		IOTester.setInput("8", "3");

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");

		out.skipToLineContains("Sorry");

		out.skipToLineContainsMatch("1.{1,3}Burger");
		out.nextLineContainsMatch("2.{1,3}Soda");
		out.nextLineContainsMatch("3.{1,3}Exit");
	}

	@Test
	void test4a_subtotalBurgers() {

		assertEquals(AssessmentProxy.calculateSubtotal(2, 0), 5.0);
	}

	@Test
	void test4b_subtotalMixed() {

		assertEquals(AssessmentProxy.calculateSubtotal(2, 1), 6.25);
	}

	@Test
	void test4c_subtotalMethodIsDefined() {

		AssessmentProxy.calculateSubtotal(0, 1);
	}

	@ParameterizedTest
	@CsvSource({ "1 2 2 3,5.35,6.6", "2 1 1 3,6.6,5.35" })
	void test5_total(String in, String subtotal, String notSubtotal) {
		IOTester.start();
		IOTester.setInput(in.split(" "));

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		out.skipToLineContains("Subtotal");
		String line = out.skipToLineContains("Total");
		assertTrue(line.contains(subtotal));
		assertFalse(line.contains(notSubtotal));
	}

	@ParameterizedTest
	@CsvSource({ "1 1 2 3,2", "1 2 1 2 1 1 3,4" })
	void test6_burgerAsterisk(String in, int expectedCount) {
		IOTester.start();
		IOTester.setInput(in.split(" "));

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		String line = out.skipToLineContainsMatch("(Burger.*\\*)|(\\*.*Burger)");
		int count = line.length() - line.replace("*", "").length();
		assertEquals(expectedCount, count);
	}

	@ParameterizedTest
	@CsvSource({ "2 2 1 3,2", "2 1 2 1 2 2 3,4" })
	void test7_sodaAsterisk(String in, int expectedCount) {
		IOTester.start();
		IOTester.setInput(in.split(" "));

		Assessment.main(new String[0]);

		Output out = IOTester.getOutput();
		String line = out.skipToLineContainsMatch("(Soda.*\\*)|(\\*.*Soda)");
		int count = line.length() - line.replace("*", "").length();
		assertEquals(expectedCount, count);
	}

	@AfterEach
	void teardown() {
		IOTester.end();
	}

}
