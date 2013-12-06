package core.template.util;

import lejos.nxt.Button;

public class ButtonHelfer {
	public static final void warteAufTaste(int taste) {
		if (taste == -1) {
			while (Button.waitForAnyPress(100) == 0) {
				Sonstiges.überprüfeProgrammStatus();
			}
		} else {
			if (taste != Button.ID_ENTER) {
				while (Button.waitForAnyPress(100) != taste) {
					Sonstiges.überprüfeProgrammStatus();
				}
			}
		}
	}
}