package programme.test;

import core.fahren.Fahren;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;

public class FahrTest extends Programm {
	@Override
	public String name() {
		return "FahrTest";
	}

	@Override
	public void run() {
		Fahren fahren = Fahren.AB(12.0, 5.5);

		fahren.fahre(10, true);
		fahren.drehe(90, true);
		fahren.fahre(10, true);
		
		ButtonHelfer.warteAufEnter();
		
		fahren.fahre(10, true);
		fahren.drehe(90, true);
		fahren.fahre(10, true);
		
		ButtonHelfer.warteAufEnter();
		
		Fahren fahren2 = Fahren.AB(12.0, 5.5);

		fahren2.fahre(10, true);
		fahren2.drehe(90, true);
		fahren2.fahre(10, true);
		
		ButtonHelfer.warteAufEnter();
		
		fahren2.fahre(10, true);
		fahren2.drehe(90, true);
		fahren2.fahre(10, true);
	}
}