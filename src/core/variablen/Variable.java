package core.variablen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import core.template.util.FileUtils;

@SuppressWarnings("deprecation")
public class Variable {

	private static final char MAGICCHAR = '!';
	private HashMap<String, ? super Object> hm;
	private File file;
	
	/**
	 * Erstellt neue Instanz 
	 * @param file Die Datei von der gelesen und geschrieben werden soll
	 */
	public Variable(String file) {
		hm = new HashMap<>();
		this.file = new File(file + ".dat");
		BufferedReader br = null;
		try {
			if(!this.file.exists()){
				this.file.createNewFile();
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					this.file)));
			String line = br.readLine();
			while (line != null) {
				String[] data = cutatmagic(line);
				if (data.length == 3) {
					hm.put(data[1],
							VariableHandlerRegistry.get(data[0]).read(data[2]));
				}

				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * schreibt eine variable
	 * @param obj das Objekt das geschriben werden solls
	 * @param name der Name 
	 * @param variablehandler WelcherHandler genutzt werden soll
	 * @see core.variablen.VariableHandler Konsteante felder in core.variablen.VariableHandler
	 */
	@SuppressWarnings("unchecked")
	public <T> void write(T obj, String name, String variablehandler) {
		if (hm.containsKey(name)) {
			this.remove(name);
		}
		hm.put(name, obj);

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(this.file)));
			bw.append(variablehandler
					+ MAGICCHAR
					+ name
					+ MAGICCHAR
					+ ((VariableHandler<T>) VariableHandlerRegistry
							.get(variablehandler)).save(obj));
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Löscht eine variable
	 * @param name Die Variable
	 */
	public void remove(String name) {
		hm.remove(name);
		BufferedReader br = null;
		BufferedWriter bw = null;
		File temp = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					this.file)));
			String filename = file.getName();
			temp = new File(filename.substring(0, filename.lastIndexOf('.'))
					+ System.currentTimeMillis() + ".tmp");
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(temp)));
			String line = br.readLine();
			while (line != null) {
				if (cutatmagic(line)[1] == name) {
				} else {
					bw.append(line);
					bw.newLine();
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.file.delete();
		FileUtils.copy(temp, this.file);
		temp.delete();

	}
	
	/**
	 * Gibt eine Variable zurück
	 * @param name Der name Der Variable
	 * @return Die Variable
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T get(String name) {
		return (T) hm.get(name);
	}

	private static String[] cutatmagic(String s) {
		ArrayList<String> result = new ArrayList<String>();

		String temp = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == MAGICCHAR) {
				result.add(temp);
				temp = "";
			} else {
				temp = temp + c;
			}
		}
		return result.toArray(new String[0]);
	}

}
