package programme.test;

import core.fahren.FahrMotor;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;

public class FahrTest extends Programm {

	@Override
	public String name() {
		return "FahrTest";
	}

	@Override
	public void run() {
		FahrMotor.A.setDurchmesser(5.5).setGeschwindigkeit(-100);
		FahrMotor.B.setDurchmesser(5.5).setGeschwindigkeit(100);

		System.out.println(FahrMotor.A.getMaximaleGeschwindigkeit());
		System.out.println(FahrMotor.B.getMaximaleGeschwindigkeit());

		ButtonHelfer.warteAufEnter();

		FahrMotor.A.fahre(10);
		FahrMotor.B.fahre(10);

		ButtonHelfer.warteAufEnter();

		FahrMotor.A.fahre(30);
		FahrMotor.B.fahre(30);

		ButtonHelfer.warteAufEnter();

		FahrMotor.A.fahre(2);
		FahrMotor.B.fahre(2);
	}
}