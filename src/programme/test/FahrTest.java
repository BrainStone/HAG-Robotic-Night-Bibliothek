package programme.test;

import lejos.nxt.MotorPort;
import core.template.programm.Programm;
import fahren.FahrMotoren;

public class FahrTest extends Programm{
	
	@Override
	public void run() {
		//TODO : Eigentliche größe angeben.
		FahrMotoren f = new FahrMotoren(MotorPort.A, MotorPort.B, 7);
		for (int i = 0; i < 4; i++) {
			f.gradeaus(50, false);
			f.drehen(90);
		}
	}
}
