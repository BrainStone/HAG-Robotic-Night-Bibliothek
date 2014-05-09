package core.variablen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import core.template.util.FileUtils;

@SuppressWarnings("deprecation")
public class Variable {

	public static void remove(String var) {
		File f = new File("var." + var + ".dat");
		remove(f);
	}

	private static void remove(File f) {
		if (f.exists()) {
			f.delete();
		}
	}

	public static void set(String var, Object value, VariableHandler handler) {
		DataOutputStream ds = null;
		try {
			File f = new File("var." + var + ".dat");
			ds = new DataOutputStream(new FileOutputStream(f));
			ds.writeUTF(handler.save(value));
		} catch (IOException e) {
		} finally {
			try {
				ds.close();
			} catch (Exception e) {
				// Stirb leise
			}
		}
	}

	public static Object get(String var, VariableHandler handler) {
		DataInputStream ds = null;
		try {
			File f = new File("var." + var + ".dat");
			if (!f.exists())
				throw new FileNotFoundException(f.toString());
			ds = new DataInputStream(new FileInputStream(f));
			return handler.read(ds.readUTF());
		} catch (IOException e) {
			return null;
		} finally {
			try {
				ds.close();
			} catch (Exception e) {
				// Stirb leise
			}
		}
	}
}
