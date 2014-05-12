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
		final Fahren fahren = Fahren.AB(13.6, 5.6);

		fahren.fahre(10, true);
		fahren.drehe(90, true);
		fahren.fahre(100, true);
		fahren.drehe(360, true);
		fahren.drehe(-180, true);
		fahren.fahre(100, true);
		fahren.drehe(90, true);
		fahren.fahre(-10, true);
	}
}