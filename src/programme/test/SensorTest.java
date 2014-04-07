package programme.test;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import core.sensor.SensorHandler;
import core.sensor.interfaces.IDruckSensorUpdate;
import core.sensor.interfaces.IUltraschallSensorUpdate;
import core.template.programm.Programm;

public class SensorTest extends Programm implements IUltraschallSensorUpdate,
		IDruckSensorUpdate {

	@Override
	public String name() {
		return "SensorTest";
	}

	@Override
	public void run() {
		SensorHandler sh = new SensorHandler(this);
		Button.waitForAnyPress();
		while (Button.ESCAPE.isUp()) {
			// Button.waitForAnyPress();1
			sh.update();
		}
	}

	@Override
	public SensorPort[] ultraschallSensorenPorts() {
		return new SensorPort[] { SensorPort.S4 };
	}

	@Override
	public SensorPort[] druckSensorenPorts() {
		return new SensorPort[] { SensorPort.S1 };
	}

	@Override
	public void handleDruckSensorUpdate(SensorPort port, TouchSensor druckSensor) {
		Sound.beep();
		System.out.println("druck");
	}

	@Override
	public void handleUltraschallSensorUpdate(SensorPort port,
			UltrasonicSensor ultraschallSensor, double abstand) {
		System.out.println(abstand);
	}
}
