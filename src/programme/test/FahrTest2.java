package programme.test;

import lejos.nxt.Button;
import core.fahren.FahrMotor;
import core.template.programm.Programm;

public class FahrTest2 extends Programm {

	@Override
	public String name() {
		return "FahrTest2";
	}

	@Override
	public void run() {
		FahrMotor.C.setDurchmesser(10);
		FahrMotor.C.fahre(10);
		
		while(Button.waitForAnyPress() != Button.ID_ENTER)
			;
		
		FahrMotor.C.fahre(30);
		
		while(Button.waitForAnyPress() != Button.ID_ENTER)
			;
		
		FahrMotor.C.fahre(2);
	}
}