package core.template.programm;

import core.fahren.FahrMotor;
import core.template.util.Timing;

public class ProgrammThread extends Thread {
	private final Programm programm;

	public ProgrammThread(Programm programm) {
		this.programm = programm;
	}

	/**
	 * Startet das übergebene Unterprogramm und fängt im Notfall Exceptions ab,
	 * um das ganze Programm nicht zu gefährden. Falls die
	 * ProgrammEnde-Exception geworfen wurde, beendet der Thread einfach.
	 */
	@Override
	public void run() {
		try {
			programm.run();

			while (FahrMotor.A.isMoving() || FahrMotor.B.isMoving()
					|| FahrMotor.C.isMoving()) {
				Timing.warte(100);
			}
		} catch (final ProgrammEnde ende) {
			// Das Programm wurde beendet. Nichts machen
		} catch (final Exception e) {
			// Verhindere, dass das Programm komplett abstürzt!
			e.printStackTrace();
		}

		FahrMotor.A.motorFrei();
		FahrMotor.B.motorFrei();
		FahrMotor.C.motorFrei();
	}
}