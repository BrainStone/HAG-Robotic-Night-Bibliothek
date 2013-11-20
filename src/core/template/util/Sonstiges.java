package core.template.util;

import core.template.programm.ProgrammEnde;

public class Sonstiges {
	public static void überprüfeProgrammStatus() {
		überprüfeProgrammStatus(Thread.currentThread());
	}

	public static void überprüfeProgrammStatus(Thread thread) {
		if (thread.isInterrupted())
			throw new ProgrammEnde();
	}
}