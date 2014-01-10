package programme.test;

import core.fahren.Fahren;
import core.template.programm.Programm;

public class FahrTest extends Programm {

	@Override
	public String name() {
		return "FahrTest";
	}

	@Override
	public void run() {
		Fahren fahren = Fahren.AB(12.0, 5.5);
		
		// fahren.fahre(0, true);
		
		fahren.fahre(10, true);
		fahren.drehe(90, true);
		fahren.fahre(10, true);
	}
}