package core.loader;

import java.util.ArrayList;

import programme.test.AnschlussTest;
import programme.test.FahrTest;
import programme.test.FahrTest2;
import programme.test.Linienverfolgung;
import programme.test.SensorTest;
import programme.test.TestProgramm;
import programme.util.MotorenMessen;
import core.fahren.FahrMotor;
import core.template.programm.Programm;

public final class ProgrammLoader {
	/** Dieses Feld enthält die Liste aller Programme */
	public static final ArrayList<Programm> Programme = new ArrayList<Programm>();

	static {
		// XXX Hier neue Instanzen der Programme erstellen

		new Linienverfolgung();
		new FahrTest();
		new FahrTest2();
		new MotorenMessen(new FahrMotor[] { FahrMotor.A.setDurchmesser(5.5),
				FahrMotor.B.setDurchmesser(5.5),
				FahrMotor.C.setDurchmesser(5.5) });
		new TestProgramm();
		new SensorTest();
		new AnschlussTest();
	}
}
