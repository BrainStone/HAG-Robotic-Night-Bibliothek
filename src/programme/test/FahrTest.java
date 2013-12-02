package programme.test;

import lejos.nxt.MotorPort;
import core.template.programm.Programm;
import fahren.FahrMotoren;

public class FahrTest extends Programm{
	private int durchmesser = 0;
	public FahrTest(int durchmesser) {
		this.durchmesser = durchmesser;
	}
	
	@Override
	public void run() {
		FahrMotoren f = new FahrMotoren(MotorPort.A, MotorPort.B, durchmesser);
		for (int i = 0; i < 4; i++) {
			f.gradeaus(50, 100);
			f.drehen(90, 100);
		}
	}
}
