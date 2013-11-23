package core;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;

/**
 * Menüobject : Einfaches Menü zur darstellung Verschiedener auswahlen.
 * 
 * @author xxMarkuski
 * @param <TYPE>
 * 
 */
public class Menu<TYPE> {
	public ArrayList<TYPE> objects;

	/**
	 * Konstruiert ein neues Menü( {@link Object#toString()} wird als Name
	 * genutzt)
	 * 
	 * @param objects
	 *            ArrayList der Objekte im Menü
	 */
	public Menu(ArrayList<TYPE> objects) {
		this.objects = objects;
	}

	/**
	 * Zeichnet und Überprüft das eigentliche Menü
	 * 
	 * @return Das ausgewählte Objekt
	 */
	public TYPE auswahl() {
		int size = objects.size();

		TYPE selProg = null;
		int currPos = 0;
		int top = 0;

		while (selProg == null) {
			if (Button.RIGHT.isDown() && currPos != size + 7) {
				currPos++;
			} else if (Button.LEFT.isDown() && currPos != 0) {
				currPos--;
			} else if (Button.ENTER.isDown()) {
				selProg = objects.get(currPos);
			}

			if (currPos - top >= 6 && top != size) {
				top++;
			} else if (currPos - top <= 1 && top != 0) {
				top--;
			}

			for (int i = top; i < top + size -2; i++) {
				String selmark = " ";
				if (i == currPos) {
					selmark = ">";
				} else if (i == top + 7 && top != size) {
					selmark = "V";
				} else if (i == top && top != 0) {
					selmark = "A";
				}
				
				// DEBUG
				// System.out.println("for : " + i + " curr pos " + currPos+ " top " + top);
				LCD.drawString(selmark + objects.get(i - top).toString(), 0, i - top);
			}
		}
		LCD.clearDisplay();
		return selProg;
	}
}
