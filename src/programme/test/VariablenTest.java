package programme.test;

import lejos.nxt.Button;
import core.template.programm.Programm;
import core.variablen.Variable;
import core.variablen.VariableHandler;

public class VariablenTest extends Programm {

	@Override
	public String name() {
		return "VariablenTest";
	}

	@Override
	public void run() {
		Variable var = new Variable("test");

		int i = Button.waitForAnyPress();

		if (i == Button.ID_ENTER) {
			var.write("Hello, im saved", "s",
					VariableHandler.STRING_VARIABLE_HANDLER_NAME);
			var.write(3.14F, "pi", VariableHandler.FLOAT_VARIABLE_HANDLER_NAME);
			var.write(42, "answer",
					VariableHandler.INTEGER_VARIABLE_HANDLER_NAME);
		}

		System.out.println("s : " + var.get("s"));
		System.out.println("pi : " + var.get("pi"));
		System.out.println("answer : " + var.get("answer"));

		var.write("Hello,im diffrent", "s",
				VariableHandler.STRING_VARIABLE_HANDLER_NAME);
		var.write(3.1415F, "pi", VariableHandler.FLOAT_VARIABLE_HANDLER_NAME);
		var.write(42 * 2, "answer",
				VariableHandler.INTEGER_VARIABLE_HANDLER_NAME);

		System.out.println("s : " + var.get("s"));
		System.out.println("pi : " + var.get("pi"));
		System.out.println("answer : " + var.get("answer"));
	}

}
