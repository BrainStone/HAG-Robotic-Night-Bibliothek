package core.template.util;

public class Timing {
	/**
	 * Lässt den aktuellen Thread die angegebene Anzahl an Millisekunden warten.<br>
	 * Außerdem wirft diese Funktion die ProgrammEnde-Exception falls der Thread
	 * unterbrochen werden soll.
	 * 
	 * @param millisekunden
	 *            Millisikeunde die gewarten werden.
	 */
	public synchronized static void warte(long millisekunden) {
		Sonstiges.überprüfeProgrammStatus();

		try {
			Thread.sleep(millisekunden);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		Sonstiges.überprüfeProgrammStatus();
	}

	/**
	 * Wartet auf die Beendigung des angegebenen Threads.<br>
	 * Außerdem wirft diese Funktion die ProgrammEnde-Exception falls der Thread
	 * unterbrochen werden soll.
	 * 
	 * @param thread
	 *            Der Thread auf den gewartet werden soll.
	 */
	public static void warteAufBeenden(Thread thread) {
		warteAufBeenden(thread, Long.MAX_VALUE);
	}

	/**
	 * Wartet auf die Beendigung des angegebenen Threads, aber nur maximal die
	 * angegebenen Millisekunden.<br>
	 * Außerdem wirft diese Funktion die ProgrammEnde-Exception falls der Thread
	 * unterbrochen werden soll.
	 * 
	 * @param thread
	 *            Der Thread auf den gewartet werden soll.
	 * @param millisekunden
	 *            Die Anzahl der maximal zu wartenden Millisekunden.
	 */
	public static void warteAufBeenden(Thread thread, long millisekunden) {
		Sonstiges.überprüfeProgrammStatus();

		try {
			thread.join(millisekunden);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}

		Sonstiges.überprüfeProgrammStatus();
	}
}