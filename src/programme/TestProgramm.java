package programme;

import core.template.programm.Programm;
import core.template.util.Timing;

public class TestProgramm extends Programm {
	@Override
	public void run() {
		Timing.warte(1000);
	}
}