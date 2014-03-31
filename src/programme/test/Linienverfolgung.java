package programme.test;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import core.fahren.FahrMotor;
import core.sensor.ISensorHandler;
import core.sensor.SensorHandler;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;
import core.template.util.Sonstiges;

public class Linienverfolgung extends Programm implements ISensorHandler {
	private static final SensorPort links = SensorPort.S1;
	private static final SensorPort rechts = SensorPort.S2;

	@Override
	public SensorPort[] getDefaultDruckSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultFarbSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultLichtSensorPort() {
		return new SensorPort[] { links, rechts };
	}

	@Override
	public SensorPort[] getDefaultSoundSensorPort() {
		return null;
	}

	@Override
	public SensorPort[] getDefaultUltraschallSensorPort() {
		return null;
	}

	@Override
	public String name() {
		return "Linienverfolgung";
	}

	@Override
	public void run() {
		final SensorHandler handler = new SensorHandler(this);
		final FahrMotor mLinks = FahrMotor.A(5.6);
		final FahrMotor mRechts = FahrMotor.B(5.6);
		final double speedFactor = 2.0;

		handler.kalibrireLicht(links, rechts);

		LCD.clear();
		LCD.refresh();
		LCD.setAutoRefresh(false);

		ButtonHelfer.warteAufEnter();

		boolean enterDown = Button.ENTER.isDown();
		int sensorLinks, sensorRechts;

		mLinks.setBeschleunigung(FahrMotor.defaultBeschleunigung * speedFactor);
		mRechts.setBeschleunigung(FahrMotor.defaultBeschleunigung * speedFactor);

		while (!Button.ENTER.isDown() || enterDown) {
			sensorLinks = handler.getLightSensor(links).getLightValue();
			sensorRechts = handler.getLightSensor(rechts).getLightValue();

			if (sensorLinks < 10) {
				sensorLinks = 0;
			}
			if (sensorRechts < 10) {
				sensorRechts = 0;
			}

			mRechts.setGeschwindigkeit((FahrMotor.defaultGeschwindigkeit
					* speedFactor * sensorLinks) / 100.0);
			mRechts.getMotor().forward();

			mLinks.setGeschwindigkeit((FahrMotor.defaultGeschwindigkeit
					* speedFactor * sensorRechts) / 100.0);
			mLinks.getMotor().forward();

			LCD.drawString("                     ", 0, 0);
			LCD.drawString("                     ", 0, 1);
			LCD.drawString(Integer.toString(sensorLinks), 0, 0);
			LCD.drawString(Integer.toString(sensorRechts), 0, 1);
			LCD.refresh();

			if (enterDown) {
				enterDown = Button.ENTER.isDown();
			}

			Sonstiges.überprüfeProgrammStatus();
		}

		LCD.setAutoRefresh(true);
		mLinks.getMotor().stop(true);
		mRechts.getMotor().stop();
	}

	@Override
	public void licht(SensorPort s, int hellichkeit, int lichtColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void druck(SensorPort s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ultraschall(SensorPort s, int abstand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sound(SensorPort s, int lautstärke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void farbe(SensorPort s, Color farbe, int FlutLichtFarbe) {
		// TODO Auto-generated method stub
		
	}
}