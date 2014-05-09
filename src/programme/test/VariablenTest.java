package programme.test;

import lejos.nxt.Button;
import core.template.programm.Programm;
import core.template.util.Timing;
import core.variablen.Variable;
import core.variablen.VariableHandler;

public class VariablenTest extends Programm {

	@Override
	public String name() {
		return "VariablenTest";
	}

	@Override
	public void run() {
		int i = Button.waitForAnyPress();
		
		if(i == Button.ID_ENTER) {
			Variable.set("s", (int) System.currentTimeMillis(), VariableHandler.INTEGER);
		}
		System.out.println(Variable.get("s", VariableHandler.INTEGER));
		Timing.warte(2000);
	}

}
