package programme.test;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import core.sensor.ISensorHandler;
import core.sensor.SensorHandler;
import core.template.programm.Programm;

public class SensorTest extends Programm implements ISensorHandler {

	@Override
	public void druck(SensorPort s) {
		Sound.beep();
		System.out.println("druck");
	}

	@Override
	public void farbe(SensorPort s, Color farbe, int FlutLichtFarbe) {

	}

	@Override
	public SensorPort[] getDefaultDruckSensorPort() {
		final SensorPort[] result = { SensorPort.S1 };
		return result;
		// return null;
	}

	@Override
	public SensorPort[] getDefaultFarbSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultLichtSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultSoundSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultUltraschallSensorPort() {
		final SensorPort[] result = { SensorPort.S4 };
		return result;
	}

	@Override
	public void licht(SensorPort s, int hellichkeit, int lichtColor) {

	}

	@Override
	public String name() {
		return "SensorTest";
	}

	@Override
	public void run() {
		final SensorHandler sh = new SensorHandler(this);
		Button.waitForAnyPress();
		while (Button.ESCAPE.isUp()) {
			// Button.waitForAnyPress();
			sh.update();
		}
	}

	@Override
	public void sound(SensorPort s, int lautstärke) {

	}

	@Override
	public void ultraschall(SensorPort s, int abstand) {
		System.out.println(abstand);

	}

}
