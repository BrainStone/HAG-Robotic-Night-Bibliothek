package core.variablen;

import core.variablen.handler.FloatVariableHandler;
import core.variablen.handler.IntegerVariableHandler;
import core.variablen.handler.StringVariableHandler;

public abstract class VariableHandler {

	public static final VariableHandler INTEGER = new IntegerVariableHandler();
	public static final VariableHandler FLOAT = new FloatVariableHandler();
	public static final VariableHandler STRING = new StringVariableHandler();
	
	
	protected final Class<?> type;

	public VariableHandler(Class<?> type) {
		this.type = type;
	}

	/**
	 * Das Objekt als string
	 * 
	 * @param value
	 *            Das Objekt
	 * @return
	 */
	public final String save(Object value) {
		if (type.isInstance(value)) {
			return internSave(value);
		}
		return null;
	}

	protected abstract String internSave(Object value);

	/**
	 * Das Objekt
	 * 
	 * @param t
	 *            Der String
	 * @return
	 */
	public final Object read(String t) {
		return internRead(t);
	}
	
	protected abstract Object internRead(String t);

}
