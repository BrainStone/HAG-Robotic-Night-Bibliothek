package core.loader;

import core.variablen.handler.FloatVariableHandler;
import core.variablen.handler.IntegerVariableHandler;
import core.variablen.handler.StringVariableHandler;

public final class VariableHandlerLoader {
	
	static{
		
		//XXX  Hier neue Instanzen der VariabelnHandler erstellen
		
		new FloatVariableHandler();
		new IntegerVariableHandler();
		new StringVariableHandler();
	}
}
