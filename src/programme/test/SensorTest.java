package programme.test;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import core.sensor.ISensorHandler;
import core.sensor.SensorHandler;
import core.template.programm.Programm;

public class SensorTest extends Programm implements ISensorHandler {

	@Override
	public String name() {
		return "SensorTest";
	}

	@Override
	public void run() {
		SensorHandler sh = new SensorHandler(this);
		Button.waitForAnyPress();
		while (Button.ESCAPE.isUp()) {
			Button.waitForAnyPress();
			sh.update();
		}
	}

	@Override
	public void druck(SensorPort s) {

	}

	@Override
	public void ultraschall(SensorPort s, int abstand) {

	}

	@Override
	public void sound(SensorPort s, int lautstärke) {

	}

	@Override
	public void farbe(SensorPort s, Color farbe, int FlutLichtFarbe) {

	}

	@Override
	public void licht(SensorPort s, int hellichkeit, int lichtColor) {

	}

	@Override
	public SensorPort[] getDefaultLichtSensorPort() {
		SensorPort[] result = { SensorPort.S1 };
		return result;
	}

	@Override
	public SensorPort[] getDefaultDruckSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultUltraschallSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultSoundSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultFarbSensorPort() {
		return null;
	}

}
