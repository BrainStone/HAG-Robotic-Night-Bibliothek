package core.template.util;

import lejos.nxt.Button;

public class ButtonHelfer {
	public static final void warteAufEnter() {
		warteAufTaste(Button.ID_ENTER);
	}

	public static final void warteAufEsc() {
		warteAufTaste(Button.ID_ESCAPE);
	}

	public static final void warteAufLinks() {
		warteAufTaste(Button.ID_LEFT);
	}

	public static final void warteAufRechts() {
		warteAufTaste(Button.ID_RIGHT);
	}

	public static final void warteAufTaste(int taste) {
		if (taste == -1) {
			while (Button.waitForAnyPress(100) == 0) {
				Sonstiges.überprüfeProgrammStatus();
			}
		} else {
			if ((taste == Button.ID_ENTER) || (taste == Button.ID_ESCAPE)
					|| (taste == Button.ID_LEFT) || (taste == Button.ID_RIGHT)) {
				while (Button.waitForAnyPress(100) != taste) {
					Sonstiges.überprüfeProgrammStatus();
				}
			}
		}
	}
}