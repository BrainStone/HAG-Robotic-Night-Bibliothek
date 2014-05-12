package core.variablen.handler;

import core.variablen.VariableHandler;

public class StringVariableHandler extends VariableHandler{

	public StringVariableHandler() {
		super(String.class);
	}

	@Override
	protected String internSave(Object obj) {
		return (String) obj;
	}

	@Override
	protected String internRead(String t) {
		return t;
	}
	
}
