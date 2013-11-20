package core.template.util;

public class Timing {
	public static void warte(int sekunden) {
		warte(sekunden, Thread.currentThread());
	}

	public static void warte(int sekunden, Thread thread) {
		Sonstiges.überprüfeProgrammStatus();

		try {
			thread.wait(sekunden);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Sonstiges.überprüfeProgrammStatus();
	}
	
	public static void warteAufBeenden(Thread thread) {
		warteAufBeenden(thread, Long.MAX_VALUE);
	}

	public static void warteAufBeenden(Thread thread, long sekunden) {
		Sonstiges.überprüfeProgrammStatus();

		try {
			thread.join(sekunden);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Sonstiges.überprüfeProgrammStatus();
	}
}