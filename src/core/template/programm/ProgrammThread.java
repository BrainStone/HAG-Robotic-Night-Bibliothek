package core.template.programm;

public class ProgrammThread extends Thread {
	private final Programm programm;

	public ProgrammThread(final Programm programm) {
		this.programm = programm;
	}

	@Override
	public void run() {
		try {
			programm.run();
		} catch (ProgrammEnde ende) {
			// Das Programm wurde beendet. Nichts machen
		} catch (Exception e) {
			// Verhindere, dass das Programm komplett abstürzt!
			e.printStackTrace();
		}
	}
}