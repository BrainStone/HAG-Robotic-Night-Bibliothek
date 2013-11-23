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

		TYPE seltype = null;
		int currPos = 0;
		int top = 0;

		while (seltype == null) {
			if (Button.RIGHT.isDown() && currPos != size - 1) {
				currPos++;
			} else if (Button.LEFT.isDown() && currPos != 0) {
				currPos--;
			} else if (Button.ENTER.isDown()) {
				seltype = objects.get(currPos);
			}

			if (currPos-top >= 6 && top < size-8) {
				top++;
			} else if (currPos-top <= 1 && top > 0) {
				top--;
			}

			for (int i = 0; i < size; i++) {
				String selmark = " ";
				if (i == currPos) {
					selmark = ">";
				} else if (i-top == 7 && top+7 != size -1) {
					selmark = "V";
				} else if (i-top == 0 && top > 0) {
					selmark = "A";
				}
				
				selmark = selmark +  i;
				
				// DEBUG
				//System.out.println("for : " + i + " curr pos " + currPos+ " top " + top + " size " + size);
				LCD.drawString(selmark + objects.get(i).toString(), 0, i - top);
			}
		}
		LCD.clearDisplay();
		return seltype;
	}
}
