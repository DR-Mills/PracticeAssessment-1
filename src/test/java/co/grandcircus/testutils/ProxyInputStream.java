package co.grandcircus.testutils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProxyInputStream extends FilterInputStream {
	
	// To prevent infinite loop of Scanner.next().
	private int countDown = 500;

	public ProxyInputStream(InputStream in) {
		super(in);
	}
	
	public void setInputStream(InputStream in) {
		this.in = in;
		countDown = 500;
	}

	// In order to keep any static shared Scanners alive, we can't permit out stream to send an EOF signal.
	@Override
	public int read() throws IOException {
		int b = super.read();
//		System.err.println("read: " + b);
		if (b < 0) {
			if (countDown-- > 0) {
				return '\n';
			}
		}
		return b;
	}

	// In order to keep any static shared Scanners alive, we can't permit out stream to send an EOF signal.
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int i = super.read(b, off, len);
//		System.err.println("read: " + i + " " + Arrays.toString(b)  + "(off=" + off + ", len=" + len + ")");
		if (i < 0) {
			if (countDown-- > 0) {
				b[off] = '\n';
				return 1;
			}
		}
		return i;
	}
	
	
}
