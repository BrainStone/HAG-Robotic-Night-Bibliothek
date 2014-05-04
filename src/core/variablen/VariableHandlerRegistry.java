package core.variablen;

import java.util.HashMap;
@SuppressWarnings("deprecation")

public class VariableHandlerRegistry {
	private static HashMap<String, VariableHandler<?>> array;
	
	/**
	 * Fügt ein VariableHandler hinzu
	 * @param element
	 */
	public static void add(VariableHandler<?> element){
		array.put(element.name(), element);
	}
	/**
	 * Löscht ein VariableHandler
	 * @param name
	 */
	public static void remove(String name) {
		array.remove(name);
	}
	
	/**
	 * Gibt ein VariableHandler zurück
	 * @param name
	 * @return
	 */
	public static VariableHandler<?> get(String name){
		return array.get(name);
	}
	
}
