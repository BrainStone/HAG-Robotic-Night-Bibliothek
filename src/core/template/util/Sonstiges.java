package core.template.util;

import core.template.programm.ProgrammEnde;

public class Sonstiges {
	/**
	 * Wirft die ProgrammEnde-Exception, wenn der aktuelle Thread unterbrochen
	 * werden soll.<br>
	 * <b>Diese Funktion sollte an so vielen Stelle wie möglich aufgerufen
	 * werden!</b>
	 */
	public static void überprüfeProgrammStatus() {
		überprüfeProgrammStatus(Thread.currentThread());
	}

	/**
	 * Wirft die ProgrammEnde-Exception, wenn der angegebene Thread unterbrochen
	 * werden soll.<br>
	 * <b>Diese Funktion sollte an so vielen Stelle wie möglich aufgerufen
	 * werden!</b>
	 * 
	 * @param thread
	 *            Der Thread der überprüft werden soll.
	 */
	public static void überprüfeProgrammStatus(Thread thread) {
		if (thread.isInterrupted())
			throw new ProgrammEnde();
	}
}