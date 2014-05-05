package programme.test;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import core.fahren.FahrMotor;
import core.sensor.SensorHandler;
import core.sensor.interfaces.ILichtSensor;
import core.template.programm.Programm;
import core.template.util.ButtonHelfer;
import core.template.util.Sonstiges;

public class Linienverfolgung extends Programm implements ILichtSensor {
	private static final SensorPort links = SensorPort.S1;
	private static final SensorPort rechts = SensorPort.S2;

	@Override
	public SensorPort[] lichtSensorenPorts() {
		return new SensorPort[] { links, rechts };
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
}