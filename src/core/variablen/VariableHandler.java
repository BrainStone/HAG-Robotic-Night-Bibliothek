package core.variablen;

public abstract class VariableHandler<T> {
	
	public static final String INTEGER_VARIABLE_HANDLER_NAME = "int";
	public static final String FLOAT_VARIABLE_HANDLER_NAME = "float";
	public static final String STRING_VARIABLE_HANDLER_NAME = "str";
	
	public VariableHandler() {
		VariableHandlerRegistry.add(this);
	}
	
	/**
	 * Das Objekt als string
	 * @param obj Das Objekt
	 * @return 
	 */
	public abstract String save(T obj);
	/**
	 * Das Objekt
	 * @param t Der String
	 * @return
	 */
	public abstract T read(String t);
	/**
	 * Der name Des VariableHandlers
	 * @return
	 */
	public abstract String name();
}
