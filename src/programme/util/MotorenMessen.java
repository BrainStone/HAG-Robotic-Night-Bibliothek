package programme.util;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import core.fahren.FahrMotor;
import core.template.programm.Programm;
import core.template.util.Sonstiges;

public class MotorenMessen extends Programm {

	private final FahrMotor[] motoren;

	public MotorenMessen(FahrMotor... motoren) {
		this.motoren = motoren;
	}

	@Override
	public String name() {
		return "MotorenMessen";
	}

	@Override
	public void run() {
		for (final FahrMotor motor : motoren) {
			try {
				motor.zählstandZurücksetzten();
			} catch (final Exception e) {
			} finally {
				motor.motorFrei();
			}
		}

		boolean enterDown = Button.ENTER.isDown();

		while (!Button.ENTER.isDown() || enterDown) {
			for (int i = 0; i < motoren.length; i++) {
				LCD.drawString(Double.toString(motoren[i].zählstandStrecke()),
						0, i);
			}

			if (enterDown) {
				enterDown = Button.ENTER.isDown();
			}

			Sonstiges.überprüfeProgrammStatus();
		}
	}

}
