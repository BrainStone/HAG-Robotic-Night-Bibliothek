package programme.test;

import core.fahren.KurvenFahren;
import core.template.programm.Programm;

public class FahrTest2 extends Programm {
	@Override
	public String name() {
		return "FahrTest2";
	}

	@Override
	public void run() {
		final KurvenFahren fahren = KurvenFahren.AB(13.6, 5.6);

		fahren.kurve(10, 31.415926535897932384626433832795, true);
		fahren.kurve(20, 62.83185307179586476925286766559, true);
		fahren.kurve(10, 31.415926535897932384626433832795, true);
		fahren.drehe(180);
	}
}