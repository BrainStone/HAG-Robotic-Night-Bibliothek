package core.loader;

import java.util.ArrayList;

import programme.TestProgramm;
import programme.test.FahrTest2;
import core.template.programm.Programm;

public final class ProgrammLoader {
	/** Dieses Feld enthält die Liste aller Programme */
	public static final ArrayList<Programm> Programme = new ArrayList<Programm>();

	static {
		// XXX Hier neue Instanzen der Programme erstellen
		
		new FahrTest2();
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