package core;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

/**
 * Menüobject : Einfaches Menü zur darstellung Verschiedener auswahlen.
 * 
 * @author xxMarkuski
 * @param <TYPE>
 * 
 */
public class Menu<TYPE> {
	public static final String zahlZuText(int zahl, int ziffern) {
		String tmp = String.valueOf(zahl);

		while (tmp.length() < ziffern) {
			tmp = "0" + tmp;
		}

		return tmp;
	}

	public ArrayList<TYPE> objects;

	/**
	 * Konstruiert ein neues Menü( {@link Object#toString()} wird als Name
	 * genutzt)
	 * 
	 * @param objects
	 *            ArrayList der Objekte im Menü
	 */
	public Menu(ArrayList<TYPE> objects) {
		// TODO Letzte Position merken
		this.objects = objects;
	}

	/**
	 * Zeichnet und Überprüft das eigentliche Menü
	 * 
	 * @return Das ausgewählte Objekt
	 */
	public TYPE auswahl() {
		final int size = objects.size();
		final int ziffern = (int) (Math.ceil(Math.log(size + 1)
				/ Math.log(10.0)));
		TYPE seltype = null;
		int currPos = 0;
		int top = 0;
		boolean escapeFrei = Button.ESCAPE.isUp();

		LCD.setAutoRefresh(false);

		while (seltype == null) {
			if (Button.ESCAPE.isDown() && escapeFrei) {
				System.exit(0);
			} else {
				escapeFrei = true;
			}

			LCD.clearDisplay();

			for (int i = 0; i < size; i++) {
				if (i == currPos) {
					for (int j = 0; j < 4; j++) {
						for (int k = j; k < (7 - j); k++) {
							LCD.setPixel(j, k + ((i - top) * LCD.CELL_HEIGHT),
									1);
						}
					}
				} else if (((i - top) == 7) && ((top + 7) != (size - 1))) {
					for (int j = 0; j < 3; j++) {
						for (int k = j; k < (5 - j); k++) {
							LCD.setPixel(k, j + (7 * LCD.CELL_HEIGHT) + 4, 1);
						}
					}
				} else if (((i - top) == 0) && (top > 0)) {
					for (int j = 0; j < 3; j++) {
						for (int k = j; k < (5 - j); k++) {
							LCD.setPixel(k, 3 - j, 1);
						}
					}
				}

				LCD.drawString(zahlZuText(i + 1, ziffern) + " "
						+ objects.get(i).toString(), 1, i - top);
			}

			LCD.refresh();

			Button.waitForAnyPress();

			if (Button.RIGHT.isDown()) {
				if (currPos != (size - 1)) {
					currPos++;
				} else {
					Sound.playTone(700, 500);
				}
			} else if (Button.LEFT.isDown()) {
				if (currPos != 0) {
					currPos--;
				} else {
					Sound.playTone(600, 500);
				}
			} else if (Button.ENTER.isDown()) {
				seltype = objects.get(currPos);
			}

			if (((currPos - top) >= 6) && (top < (size - 8))) {
				top++;
			} else if (((currPos - top) <= 1) && (top > 0)) {
				top--;
			}
		}

		LCD.setAutoRefresh(true);
		LCD.clearDisplay();

		return seltype;
	}
}
