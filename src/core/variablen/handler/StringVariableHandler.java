package core.variablen.handler;

import core.variablen.VariableHandler;

public class StringVariableHandler extends VariableHandler<String>{

	@Override
	public String save(String obj) {
		return obj;
	}

	@Override
	public String read(String t) {
		return t;
	}
	
	@Override
	public String name() {
		return STRING_VARIABLE_HANDLER_NAME;
	}
}
