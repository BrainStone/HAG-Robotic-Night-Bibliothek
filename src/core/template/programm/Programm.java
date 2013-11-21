package core.template.programm;

import lejos.nxt.Button;
import lejos.nxt.NXT;
import lejos.nxt.Sound;
import core.loader.ProgrammLoader;
import core.template.util.Timing;

public abstract class Programm {
	private ProgrammThread thread = null;

	public Programm() {
		ProgrammLoader.Programme.add(this);
	}

	// TESTME Bitte Töne testen!
	/**
	 * Stoppt das aktuelle Programm. Die Methode wartet maximal eine Sekunde um
	 * das Programm beenden zu lassen. Sollte es in dieser Zeit nicht beendet
	 * haben, wird ein sich wiederholender Warnton ausgegeben und man erhält die
	 * Option das komplette Programm zu beenden!
	 */
	public final void kill() {
		thread.interrupt();

		Timing.warteAufBeenden(thread, 1000);

		if (thread.isAlive()) {
			System.err.println("Das Programm läuft immernoch!");
			System.err
					.println("Drücken Sie Esc um das komplette Programm zu beenden!");

			Sound.playTone(1000, 500);
			Sound.playTone(500, 500);

			while (thread.isAlive()) {
				if (Button.ESCAPE.isDown()) {
					NXT.shutDown();
				}

				Sound.playTone(500, 500);

				Timing.warteAufBeenden(thread, 100);
			}
		}
	}

	// TESTME Möglicherweiße muss hier etwas anderes hin!
	/**
	 * Gibt den Namen des Programms zurück.
	 * 
	 * @return Im standard Fall den Klassennamen
	 */
	public String name() {
		try {
			return this.getClass().toString();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return (this.toString());
		}
	}

	/**
	 * In dieser Funktion wird das eigentliche Programm im externen Thread
	 * ausgeführt.<br>
	 * <b>Jedes Programm muss diese Methode implementieren!</b>
	 */
	public abstract void run();

	/**
	 * Startet das Programm in einem neuen Thread.
	 */
	public final void start() {
		thread = new ProgrammThread(this);

		thread.start();
	}
}