package co.grandcircus.testutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class IOTester {
	
	// From java.util.Scanner
	private static final String LINE_SEPARATOR_PATTERN =
            "\r\n|[\n\r\u2028\u2029\u0085]";

	private static InputStream originalStdIn = System.in;
	private static ProxyInputStream stdIn = new ProxyInputStream(originalStdIn);
	
	private static PrintStream originalStdOut = System.out;
	private static ByteArrayOutputStream stdOut;

	static {
		System.setIn(stdIn);
	}

	public static void start() {
		stdOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stdOut));
	}
	
	public static void end() {
		stdOut = null;
		System.setOut(originalStdOut);
		stdIn.setInputStream(originalStdIn);
	}

	public static void setInput(String... lines) {
		String input = String.join(System.lineSeparator(), lines);
		stdIn.setInputStream(new ByteArrayInputStream(
				input.getBytes(StandardCharsets.UTF_8)));
	}
	
	public static String getOutputAsString() {
		if (stdOut == null) {
			return "";
		} else {
			try {
				stdOut.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return new String(stdOut.toByteArray(), StandardCharsets.UTF_8);
		}
	}
	
	public static String[] getOutputLines() {
		return getOutputAsString().split(LINE_SEPARATOR_PATTERN);
	}
	
	public static List<String> getOutputLinesAsList() {
		return Arrays.asList(getOutputLines());
	}
	
	public static Output getOutput() {
		return new Output(getOutputLines());
	}
}
