package programme;

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

		Timing.warte(/* 10 */00);

		System.out.println("Ende");
	}
}