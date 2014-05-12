package programme.test;

import core.template.programm.Programm;
import core.template.util.Timing;

public class TestProgramm extends Programm {
	@Override
	public String name() {
		return "Testprogramm";
	}

	@Override
	public void run() {
		System.out.println("Start");

		Timing.warte(1000);

		System.out.println("Ende");
	}
}