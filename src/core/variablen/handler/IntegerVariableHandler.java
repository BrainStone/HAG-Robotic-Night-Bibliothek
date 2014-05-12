package core.variablen.handler;

import core.variablen.VariableHandler;

public class IntegerVariableHandler extends VariableHandler {

	public IntegerVariableHandler() {
		super(Integer.class);
	}

	@Override
	protected String internSave(Object obj) {
		return obj.toString();
	}

	@Override
	protected Integer internRead(String t) {
		return Integer.parseInt(t);
	}
}
