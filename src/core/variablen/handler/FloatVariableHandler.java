package core.variablen.handler;

import core.variablen.VariableHandler;

public class FloatVariableHandler extends VariableHandler {

	public FloatVariableHandler() {
		super(Float.class);
	}

	@Override
	protected String internSave(Object obj) {
		return obj.toString();
	}

	@Override
	public Object internRead(String t) {
		return Float.parseFloat(t);
	}

}
