package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistence {
	private static File file;
	private static String fileName;
	private static FileReader fileReader;
	private static FileWriter fileWriter;
	private static BufferedReader bufferedReader;
	private static BufferedWriter bufferedWriter;

	/**
	 * 
	 * @param name
	 *            Nombre que tendra el archivo
	 */
	public Persistence(String name) {
		Persistence.fileName = name;
	}
	
	public static void setFileName(String name) {
		Persistence.fileName = name;
	}

	/**
	 * 
	 * @param mode
	 *            Verdadero para leer, falso para escribir
	 * @throws IOException
	 */
	public static void open(boolean mode) throws IOException {
		file = new File(fileName);
		if (mode) {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
		} else {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
		}
	}

	public static String readFile() throws IOException {
		return bufferedReader.readLine();
	}
	
	public static void delete() {
		file.delete();
	}

	public static void writer(String chain) throws IOException {
		bufferedWriter.write(chain);
		bufferedWriter.newLine();
	}

	/**
	 * Cierra para lectura y escritura
	 * 
	 * @throws IOException
	 */
	public static void close() throws IOException {
		if ((bufferedReader != null)) {
			bufferedReader.close();
			fileReader.close();
		} else {
			bufferedWriter.close();
			fileWriter.close();
		}
	}

	public static float getSize() {
		return file.length();
	}
	
	public static void main(String[] args) {
		new Persistence("Test_uno");
		try {
			open(false);
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		delete();
	}
	
}
