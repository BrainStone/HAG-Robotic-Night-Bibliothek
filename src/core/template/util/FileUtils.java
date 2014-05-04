package core.template.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils {
	
	/**
	 * Copiert zwei dateien
	 * @param from start datei
	 * @param to ziel datei (falls sie existiert wird sie gelöscht)
	 */
	public static void copy(File from, File to){
		if(to.exists()){
			to.delete();
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			to.createNewFile();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to)));
			String line = br.readLine();
			while(line != null){
				bw.append(line);
				bw.newLine();
				
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
