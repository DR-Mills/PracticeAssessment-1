package co.grandcircus.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * A test utility for running through the output of an application.
 */
public class Output {

	private String[] lines;
	private int next = 0;

	public Output(String[] lines) {
		this.lines = lines;
	}
	
	public String nextLine() {
		if (next >= lines.length) {
			fail("End of output reached.");
		}
		return lines[next++];
	}
	
	public String skipTo(Predicate<String> predicate) {
		String line;
		do {
			line = nextLine();
		} while (!predicate.test(line));
		return line;
	}
	
	public String nextLineIs(String expectedLine) {
		String line = nextLine();
		assertEquals(expectedLine.toLowerCase(), line.toLowerCase());
		return line;
	}
	
	public String nextLineContains(String substring) {
		String line = nextLine();
		assertTrue(line.toLowerCase().contains(substring.toLowerCase()));
		return line;
	}
	
	public String nextLineMatches(String regex) {
		String line = nextLine();
		assertTrue(Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line).matches());
		return line;
	}
	
	public String nextLineContainsMatch(String regex) {
		String line = nextLine();
		assertTrue(Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line).find());
		return line;
	}
	
	public String skipToLine(String expectedLine) {
		return skipTo(line -> expectedLine.equalsIgnoreCase(line));
	}
	
	public String skipToLineContains(String substring) {
		return skipTo(line -> line.toLowerCase().contains(substring.toLowerCase()));
	}
	
	public String skipToLineMatches(String regex) {
		return skipTo(line -> Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line).matches());
	}
	
	public String skipToLineContainsMatch(String regex) {
		return skipTo(line -> Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line).find());
	}
	
	public String nextLineIsExactCase(String expectedLine) {
		String line = nextLine();
		assertEquals(expectedLine, line);
		return line;
	}
	
	public String nextLineContainsExactCase(String substring) {
		String line = nextLine();
		assertTrue(line.contains(substring));
		return line;
	}
	
	public String nextLineMatchesExactCase(String regex) {
		String line = nextLine();
		assertTrue(line.matches(regex));
		return line;
	}
	
	public String nextLineContainsMatchExactCase(String regex) {
		String line = nextLine();
		assertTrue(Pattern.compile(regex).matcher(line).find());
		return line;
	}
	
	public String skipToLineExactCase(String expectedLine) {
		return skipTo(line -> expectedLine.equals(line));
	}
	
	public String skipToLineContainsExactCase(String substring) {
		return skipTo(line -> line.contains(substring));
	}
	
	public String skipToLineMatchesExactCase(String regex) {
		return skipTo(line -> line.matches(regex));
	}
	
	public String skipToLineContainsMatchExactCase(String regex) {
		return skipTo(line -> Pattern.compile(regex).matcher(line).find());
	}
	
	
}
