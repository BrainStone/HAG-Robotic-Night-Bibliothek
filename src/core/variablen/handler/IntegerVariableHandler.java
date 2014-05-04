package core.variablen.handler;

import core.variablen.VariableHandler;

public class IntegerVariableHandler extends VariableHandler<Integer> {

	@Override
	public String save(Integer obj) {
		return obj.toString();
	}

	@Override
	public Integer read(String t) {
		return Integer.parseInt(t);
	}
	
	@Override
	public String name() {
		return INTEGER_VARIABLE_HANDLER_NAME;
	}

}
