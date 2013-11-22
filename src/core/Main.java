package core;

import core.loader.ProgrammLoader;
import core.template.programm.Programm;

public class Main {
	/**
	 * Programmeinstiegspunkt.<br>
	 * Diese Funktion sollte nicht von Hand aufgerufen werden.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		Menu<Programm> menu = new Menu<>(ProgrammLoader.Programme);
		menu.auswahl().start();
	}
}