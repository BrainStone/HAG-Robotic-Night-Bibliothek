package core.templates.programm;

public class ProgrammThread extends Thread {
	private final Programm programm;

	public ProgrammThread(final Programm programm) {
		this.programm = programm;
	}

	@Override
	public void run() {
		programm.run();
	}
}