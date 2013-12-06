package programme.test;

import core.fahren.FahrMotor;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;

public class FahrTest2 extends Programm {

	@Override
	public String name() {
		return "FahrTest2";
	}

	@Override
	public void run() {
		FahrMotor.C.setDurchmesser(5.5);
		FahrMotor.C.fahre(10);

		ButtonHelfer.warteAufEnter();

		FahrMotor.C.fahre(30);

		ButtonHelfer.warteAufEnter();

		FahrMotor.C.fahre(2);
	}
}