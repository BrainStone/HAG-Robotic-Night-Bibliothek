package core.variablen.handler;

import core.variablen.VariableHandler;

public class FloatVariableHandler extends VariableHandler<Float> {

	@Override
	public String save(Float obj) {
		return obj.toString();
	}

	@Override
	public Float read(String t) {
		return Float.parseFloat(t);
	}

	@Override
	public String name() {
		return FLOAT_VARIABLE_HANDLER_NAME;
	}

}
