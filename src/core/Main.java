package core;

import lejos.nxt.Button;
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
		final Menu<Programm> menu = new Menu<Programm>(ProgrammLoader.Programme);

		while (true) {
			final Programm p = menu.auswahl();
			p.start();

			while (p.läuft()) {
				Button.waitForAnyPress(100);

				if (Button.ESCAPE.isDown()) {
					p.kill();
				}
			}
		}
	}
}