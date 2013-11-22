package core;

import lejos.nxt.LCD;
import core.loader.ProgrammLoader;

public class Main {
	/**
	 * Programmeinstiegspunkt.<br>
	 * Diese Funktion sollte nicht von Hand aufgerufen werden.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		int anzProgramme = ProgrammLoader.Programme.size();
		
		int topPos = 0;
		int programm = 0;
		
		int i, top;
		
		for(i = 0; i < 8; i++) {
			top = i + topPos;
			
			if(top < anzProgramme) {
				LCD.drawString(ProgrammLoader.Programme.get(top).name(), 0, i);
			}
		}
	}
}