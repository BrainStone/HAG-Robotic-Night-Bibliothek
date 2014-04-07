package core.sensor;

import java.util.HashMap;
import java.util.Map.Entry;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
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

	private ISensorHandler iSensorHandler;

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
			ILichtSensor iLichtSensor = (ILichtSensor) iSensorHandler;

			for (SensorPort port : iLichtSensor.lichtSensorenPorts()) {
				lichtSensoren.put(port, new LightSensor(port));
			}

			updateLichtSensoren = iLichtSensor instanceof ILichtSensorUpdate;
		}

		if (iSensorHandler instanceof IDruckSensor) {
			druckSensoren = new HashMap<SensorPort, TouchSensor>();
			IDruckSensor iDruckSensor = (IDruckSensor) iSensorHandler;

			for (SensorPort port : iDruckSensor.druckSensorenPorts()) {
				druckSensoren.put(port, new TouchSensor(port));
			}

			updateDruckSensoren = iDruckSensor instanceof IDruckSensorUpdate;
		}

		if (iSensorHandler instanceof IUltraschallSensor) {
			ultraschallSensoren = new HashMap<SensorPort, UltrasonicSensor>();
			IUltraschallSensor iUltraschallSensor = (IUltraschallSensor) iSensorHandler;

			for (SensorPort port : iUltraschallSensor
					.ultraschallSensorenPorts()) {
				ultraschallSensoren.put(port, new UltrasonicSensor(port));
			}

			updateUltraschallSensoren = iUltraschallSensor instanceof IUltraschallSensorUpdate;
		}

		if (iSensorHandler instanceof ISoundSensor) {
			soundSensoren = new HashMap<SensorPort, SoundSensor>();
			ISoundSensor iSoundSensor = (ISoundSensor) iSensorHandler;

			for (SensorPort port : iSoundSensor.soundSensorenPorts()) {
				soundSensoren.put(port, new SoundSensor(port));
			}

			updateSoundSensoren = iSoundSensor instanceof ISoundSensorUpdate;
		}

		if (iSensorHandler instanceof IFarbSensor) {
			farbSensoren = new HashMap<SensorPort, ColorSensor>();
			IFarbSensor iFarbSensor = (IFarbSensor) iSensorHandler;

			for (SensorPort port : iFarbSensor.farbSensorenPorts()) {
				farbSensoren.put(port, new ColorSensor(port));
			}

			updateFarbSensoren = iFarbSensor instanceof IFarbSensorUpdate;
		}
	}

	public SensorPort[] getLichtSensorenPorts() {
		return (SensorPort[]) lichtSensoren.keySet().toArray();
	}

	public SensorPort[] getDruckSensorenPorts() {
		return (SensorPort[]) druckSensoren.keySet().toArray();
	}

	public SensorPort[] getUltraschallenSensorPorts() {
		return (SensorPort[]) ultraschallSensoren.keySet().toArray();
	}

	public SensorPort[] getSoundSensorenPorts() {
		return (SensorPort[]) soundSensoren.keySet().toArray();
	}

	public SensorPort[] getFarbSensorenPorts() {
		return (SensorPort[]) farbSensoren.keySet().toArray();
	}

	public LightSensor getLightSensor(SensorPort port) {
		return lichtSensoren.get(port);
	}

	public UltrasonicSensor getUltraschallSensor(SensorPort port) {
		return ultraschallSensoren.get(port);
	}

	public SoundSensor getSoundSensor(SensorPort port) {
		return soundSensoren.get(port);
	}

	public TouchSensor getDruckSensor(SensorPort port) {
		return druckSensoren.get(port);
	}

	public ColorSensor getFarbSensor(SensorPort port) {
		return farbSensoren.get(port);
	}

	/**
	 * Updated die Sensoren
	 */
	public void update() {
		SensorPort port;
		LightSensor lichtSensor;
		TouchSensor druckSensor;
		UltrasonicSensor ultraschallSensor;
		SoundSensor soundSensor;
		ColorSensor farbSensor;

		if (updateLichtSensoren) {
			for (Entry<SensorPort, LightSensor> sensor : lichtSensoren
					.entrySet()) {
				port = sensor.getKey();
				lichtSensor = sensor.getValue();

				((ILichtSensorUpdate) iSensorHandler).handleLichtSensorUpdate(
						port, lichtSensor, lichtSensor.getLightValue(),
						lichtSensor.getFloodlight() != 0);
			}
		}

		if (updateDruckSensoren) {
			for (Entry<SensorPort, TouchSensor> sensor : druckSensoren
					.entrySet()) {
				port = sensor.getKey();
				druckSensor = sensor.getValue();

				if (druckSensor.isPressed()) {
					((IDruckSensorUpdate) iSensorHandler)
							.handleDruckSensorUpdate(port, druckSensor);
				}
			}
		}

		if (updateUltraschallSensoren) {
			for (Entry<SensorPort, UltrasonicSensor> sensor : ultraschallSensoren
					.entrySet()) {
				port = sensor.getKey();
				ultraschallSensor = sensor.getValue();

				((IUltraschallSensorUpdate) iSensorHandler)
						.handleUltraschallSensorUpdate(port, ultraschallSensor,
								ultraschallSensor.getDistance());
			}
		}

		if (updateSoundSensoren) {
			for (Entry<SensorPort, SoundSensor> sensor : soundSensoren
					.entrySet()) {
				port = sensor.getKey();
				soundSensor = sensor.getValue();

				((ISoundSensorUpdate) iSensorHandler).handleSoundSensorUpdate(
						port, soundSensor, soundSensor.readValue());
			}
		}

		if (updateFarbSensoren) {
			for (Entry<SensorPort, ColorSensor> sensor : farbSensoren
					.entrySet()) {
				port = sensor.getKey();
				farbSensor = sensor.getValue();

				((IFarbSensorUpdate) iSensorHandler).handleFarbSensorUpdate(
						port, farbSensor, farbSensor.getColor(),
						farbSensor.getFloodlight());
			}
		}
	}

	public void kalibrireLicht(SensorPort... ports) {
		LCD.clear();
		LCD.drawString("Heller", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (SensorPort port : ports) {
			lichtSensoren.get(port).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Dunkler", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (SensorPort port : ports) {
			lichtSensoren.get(port).calibrateLow();
		}
	}

	public void kalibrireFarb(SensorPort... ports) {
		LCD.clear();
		LCD.drawString("Weiß", 0, 0);

		Button.waitForAnyPress();

		for (SensorPort port : ports) {
			farbSensoren.get(port).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Schwarz", 0, 0);

		Button.waitForAnyPress();

		for (SensorPort port : ports) {
			farbSensoren.get(port).calibrateLow();
		}
	}
}
