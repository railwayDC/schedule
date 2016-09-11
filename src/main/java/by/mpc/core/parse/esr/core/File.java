package by.mpc.core.parse.esr.core;

import java.io.FileInputStream;
import java.io.IOException;

public class File {
	public static String read(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		return new String(b);
	}
}
