package core.loader;

import java.util.ArrayList;

import programme.test.FahrTest;
import programme.test.TestProgramm;
import core.template.programm.Programm;

public final class ProgrammLoader {
	/** Dieses Feld enthält die Liste aller Programme */
	public static final ArrayList<Programm> Programme = new ArrayList<Programm>();

	static {
		// XXX Hier neue Instanzen der Programme erstellen

		new FahrTest();
		new TestProgramm();
	}
}