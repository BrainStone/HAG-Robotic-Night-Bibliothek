package core.templates.programm;

import lejos.nxt.Button;
import lejos.nxt.NXT;
import lejos.nxt.Sound;
import core.loader.ProgrammLoader;

public abstract class Programm {
	private ProgrammThread thread = null;

	public Programm() {
		ProgrammLoader.Programme.add(this);
	}

	/**
	 * Stopt das aktuelle Programm. Die Methode wartet maximal eine Sekunde um
	 * das Programm beenden zu lassen. Sollte es in dieser Zeit nicht beendet
	 * haben, wird ein sich wiederholender Warnton ausgegeben und man erhält die
	 * Option das komplette Programm zu beenden!
	 */
	public final void kill() {
		thread.interrupt();

		try {
			thread.join(1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		if (thread.isAlive()) {
			System.err.println("Das Programm läuft immernoch!");
			System.err
					.println("Drücken Sie Esc um das komplette Programm zu beenden!");

			Sound.playTone(1000, 500);
			Sound.playTone(500, 500);

			if (Button.ESCAPE.isDown()) {
				NXT.shutDown();
			}
		}
	}

	/**
	 * Gibt den Namn des Programms zurück.
	 * 
	 * @return Im standard
	 */
	public String name() {
		return this.getClass().toString();
	}

	/**
	 * In dieser Funktion wird das eigentliche Programm ausgefuehrt im externen
	 * Thread ausgefuehrt.<br>
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