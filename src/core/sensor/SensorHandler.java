package core.sensor;

import java.util.HashMap;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorConstants;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import core.sensor.interfaces.IDruckSensor;
import core.sensor.interfaces.IDruckSensorUpdate;
import core.sensor.interfaces.IFarbSensor;
import core.sensor.interfaces.IFarbSensorUpdate;
import core.sensor.interfaces.ILichtSensor;
import core.sensor.interfaces.ILichtSensorUpdate;
import core.sensor.interfaces.ISensorHandler;
import core.sensor.interfaces.ISoundSensor;
import core.sensor.interfaces.ISoundSensorUpdate;
import core.sensor.interfaces.IUltraschallSensor;
import core.sensor.interfaces.IUltraschallSensorUpdate;

@SuppressWarnings("deprecation")
public class SensorHandler {
	private HashMap<SensorPort, LightSensor> lichtSensoren;
	private HashMap<SensorPort, TouchSensor> druckSensoren;
	private HashMap<SensorPort, UltrasonicSensor> ultraschallSensoren;
	private HashMap<SensorPort, SoundSensor> soundSensoren;
	private HashMap<SensorPort, ColorSensor> farbSensoren;

	private boolean updateLichtSensoren;
	private boolean updateDruckSensoren;
	private boolean updateUltraschallSensoren;
	private boolean updateSoundSensoren;
	private boolean updateFarbSensoren;

	private final ISensorHandler iSensorHandler;

	/**
	 * Eine Neue Sensor handler instance
	 * 
	 * @param sensorHandler
	 *            Ein ISensorHandler
	 */
	public SensorHandler(ISensorHandler sensorHandler) {
		iSensorHandler = sensorHandler;

		if (iSensorHandler instanceof ILichtSensor) {
			lichtSensoren = new HashMap<SensorPort, LightSensor>();
			final ILichtSensor iLichtSensor = (ILichtSensor) iSensorHandler;

			for (final SensorPort port : iLichtSensor.lichtSensorenPorts()) {
				lichtSensoren.put(port, new LightSensor(port));
			}

			updateLichtSensoren = iLichtSensor instanceof ILichtSensorUpdate;
		}

		if (iSensorHandler instanceof IDruckSensor) {
			druckSensoren = new HashMap<SensorPort, TouchSensor>();
			final IDruckSensor iDruckSensor = (IDruckSensor) iSensorHandler;

			for (final SensorPort port : iDruckSensor.druckSensorenPorts()) {
				druckSensoren.put(port, new TouchSensor(port));
			}

			updateDruckSensoren = iDruckSensor instanceof IDruckSensorUpdate;
		}

		if (iSensorHandler instanceof IUltraschallSensor) {
			ultraschallSensoren = new HashMap<SensorPort, UltrasonicSensor>();
			final IUltraschallSensor iUltraschallSensor = (IUltraschallSensor) iSensorHandler;

			for (final SensorPort port : iUltraschallSensor
					.ultraschallSensorenPorts()) {
				ultraschallSensoren.put(port, new UltrasonicSensor(port));
			}

			updateUltraschallSensoren = iUltraschallSensor instanceof IUltraschallSensorUpdate;
		}

		if (iSensorHandler instanceof ISoundSensor) {
			soundSensoren = new HashMap<SensorPort, SoundSensor>();
			final ISoundSensor iSoundSensor = (ISoundSensor) iSensorHandler;

			for (final SensorPort port : iSoundSensor.soundSensorenPorts()) {
				soundSensoren.put(port, new SoundSensor(port));
			}

			updateSoundSensoren = iSoundSensor instanceof ISoundSensorUpdate;
		}

		if (iSensorHandler instanceof IFarbSensor) {
			farbSensoren = new HashMap<SensorPort, ColorSensor>();
			final IFarbSensor iFarbSensor = (IFarbSensor) iSensorHandler;

			for (final SensorPort port : iFarbSensor.farbSensorenPorts()) {
				farbSensoren.put(port, new ColorSensor(port));
			}

			updateFarbSensoren = iFarbSensor instanceof IFarbSensorUpdate;
		}
	}

	public TouchSensor getDruckSensor(SensorPort port) {
		return druckSensoren.get(port);
	}

	public SensorPort[] getDruckSensorenPorts() {
		return keySetAlsArray(druckSensoren);
	}

	public ColorSensor getFarbSensor(SensorPort port) {
		return farbSensoren.get(port);
	}

	public SensorPort[] getFarbSensorenPorts() {
		return keySetAlsArray(farbSensoren);
	}

	public SensorPort[] getLichtSensorenPorts() {
		return keySetAlsArray(lichtSensoren);
	}

	public LightSensor getLightSensor(SensorPort port) {
		return lichtSensoren.get(port);
	}

	public SoundSensor getSoundSensor(SensorPort port) {
		return soundSensoren.get(port);
	}

	public SensorPort[] getSoundSensorenPorts() {
		return keySetAlsArray(soundSensoren);
	}

	public SensorPort[] getUltraschallenSensorPorts() {
		return keySetAlsArray(ultraschallSensoren);
	}

	public UltrasonicSensor getUltraschallSensor(SensorPort port) {
		return ultraschallSensoren.get(port);
	}

	public void kalibrireFarb(SensorPort... ports) {
		LCD.clear();
		LCD.drawString("Weiß", 0, 0);

		Button.waitForAnyPress();

		for (final SensorPort port : ports) {
			farbSensoren.get(port).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Schwarz", 0, 0);

		Button.waitForAnyPress();

		for (final SensorPort port : ports) {
			farbSensoren.get(port).calibrateLow();
		}
	}

	public void kalibrireLicht(SensorPort... ports) {
		LCD.clear();
		LCD.drawString("Heller", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (final SensorPort port : ports) {
			lichtSensoren.get(port).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Dunkler", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (final SensorPort port : ports) {
			lichtSensoren.get(port).calibrateLow();
		}
	}

	private SensorPort[] keySetAlsArray(
			HashMap<SensorPort, ? extends SensorConstants> map) {
		final SensorPort[] ports = new SensorPort[map.size()];
		int i = 0;

		for (final SensorPort port : SensorPort.PORTS) {
			if (map.get(port) != null) {
				ports[i++] = port;
			}
		}

		return ports;
	}

	/**
	 * Updated die Sensoren
	 */
	public void update() {
		LightSensor lichtSensor;
		TouchSensor druckSensor;
		UltrasonicSensor ultraschallSensor;
		SoundSensor soundSensor;
		ColorSensor farbSensor;

		if (updateLichtSensoren) {
			for (final SensorPort sensorPort : keySetAlsArray(lichtSensoren)) {
				lichtSensor = lichtSensoren.get(sensorPort);

				((ILichtSensorUpdate) iSensorHandler).handleLichtSensorUpdate(
						sensorPort, lichtSensor, lichtSensor.getLightValue(),
						lichtSensor.getFloodlight() != 0);
			}
		}

		if (updateDruckSensoren) {
			for (final SensorPort sensorPort : keySetAlsArray(druckSensoren)) {
				druckSensor = druckSensoren.get(sensorPort);

				if (druckSensor.isPressed()) {
					((IDruckSensorUpdate) iSensorHandler)
							.handleDruckSensorUpdate(sensorPort, druckSensor);
				}
			}
		}

		if (updateUltraschallSensoren) {
			for (final SensorPort sensorPort : keySetAlsArray(ultraschallSensoren)) {
				ultraschallSensor = ultraschallSensoren.get(sensorPort);

				((IUltraschallSensorUpdate) iSensorHandler)
						.handleUltraschallSensorUpdate(sensorPort,
								ultraschallSensor,
								ultraschallSensor.getDistance());
			}
		}

		if (updateSoundSensoren) {
			for (final SensorPort sensorPort : keySetAlsArray(soundSensoren)) {
				soundSensor = soundSensoren.get(sensorPort);

				((ISoundSensorUpdate) iSensorHandler).handleSoundSensorUpdate(
						sensorPort, soundSensor, soundSensor.readValue());
			}
		}

		if (updateFarbSensoren) {
			for (final SensorPort sensorPort : keySetAlsArray(farbSensoren)) {
				farbSensor = farbSensoren.get(sensorPort);

				((IFarbSensorUpdate) iSensorHandler).handleFarbSensorUpdate(
						sensorPort, farbSensor, farbSensor.getColor(),
						farbSensor.getFloodlight());
			}
		}
	}
}
