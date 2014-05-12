package programme.test;

import lejos.nxt.MotorPort;
import core.fahren.Motor;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;

public class AnschlussTest extends Programm {
	@Override
	public String name() {
		return "AnschlussTest";
	}

	@Override
	public void run() {
		System.out.println(Motor.A.zählstand());
		Motor.A.zählstandZurücksetzten();
		System.out.println(Motor.A.zählstand());

		System.out.println("A " + Motor.isAngeschlossen(MotorPort.A));
		System.out.println("B " + Motor.isAngeschlossen(MotorPort.B));
		System.out.println("C " + Motor.isAngeschlossen(MotorPort.C));

		ButtonHelfer.warteAufEnter();

		while (true) {
			System.out.println(Motor.A.zählstand());
		}
	}
}