package core.loader;

import java.util.ArrayList;

import programme.TestProgramm;
import core.template.programm.Programm;

public final class ProgrammLoader {
	/** Dieses Feld enthält die Liste aller Programme */
	public static final ArrayList<Programm> Programme = new ArrayList<Programm>();

	static {
		// XXX Hier neue Instanzen der Programme erstellen

		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
		new TestProgramm();
	}
}