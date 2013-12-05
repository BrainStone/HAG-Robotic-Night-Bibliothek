package programme.test;

import lejos.nxt.MotorPort;
import core.fahren.FahrMotoren;
import core.template.programm.Programm;

public class FahrTest1 extends Programm {
	private int durchmesser = 0;

	public FahrTest1(int durchmesser) {
		this.durchmesser = durchmesser;
	}

	@Override
	public String name() {
		return "FahrTest1";
	}

	@Override
	public void run() {
		final FahrMotoren f = new FahrMotoren(MotorPort.A, MotorPort.B,
				durchmesser);
		for (int i = 0; i < 4; i++) {
			f.gradeaus(50, 100);
			f.drehen(90, 100);
		}
	}
}