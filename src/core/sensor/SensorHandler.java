package core.sensor;

import java.util.HashMap;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import core.sensor.interfaces.IDruckSensor;
import core.sensor.interfaces.IFarbSensor;
import core.sensor.interfaces.ILichtSensor;
import core.sensor.interfaces.ISensorHandler;
import core.sensor.interfaces.ISoundSensor;
import core.sensor.interfaces.IUltraschallSensor;

@SuppressWarnings("deprecation")
public class SensorHandler {
	private HashMap<SensorPort, LightSensor> lichtSensoren;
	private HashMap<SensorPort, TouchSensor> druckSensoren;
	private HashMap<SensorPort, UltrasonicSensor> ultraschallSensoren;
	private HashMap<SensorPort, SoundSensor> soundSensoren;
	private HashMap<SensorPort, ColorSensor> farbSensoren;

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
			ILichtSensor iLichtSensor = (ILichtSensor) iSensorHandler;

			for (SensorPort port : iLichtSensor.lichtSensorenPorts()) {
				lichtSensoren.put(port, new LightSensor(port));
			}
		}

		if (iSensorHandler instanceof IDruckSensor) {
			IDruckSensor iDruckSensor = (IDruckSensor) iSensorHandler;

			for (SensorPort port : iDruckSensor.druckSensorenPorts()) {
				druckSensoren.put(port, new TouchSensor(port));
			}
		}

		if (iSensorHandler instanceof IUltraschallSensor) {
			IUltraschallSensor iUltraschallSensor = (IUltraschallSensor) iSensorHandler;

			for (SensorPort port : iUltraschallSensor
					.ultraschallSensorenPorts()) {
				ultraschallSensoren.put(port, new UltrasonicSensor(port));
			}
		}

		if (iSensorHandler instanceof ISoundSensor) {
			ISoundSensor iSoundSensor = (ISoundSensor) iSensorHandler;

			for (SensorPort port : iSoundSensor.soundSensorenPorts()) {
				soundSensoren.put(port, new SoundSensor(port));
			}
		}

		if (iSensorHandler instanceof IFarbSensor) {
			IFarbSensor iFarbSensor = (IFarbSensor) iSensorHandler;

			for (SensorPort port : iFarbSensor.farbSensorenPorts()) {
				farbSensoren.put(port, new ColorSensor(port));
			}
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
	 * Updated die sensoren
	 */
	public void update() {
		// for (LightSensor s : lichtSensor) {
		// iSensorHandler.licht(lichtSensorPort.get(lichtSensor.indexOf(s)),
		// s.getLightValue(), s.getFloodlight());
		// }
		// for (TouchSensor s : druckSensor) {
		// if (s.isPressed()) {
		// iSensorHandler
		// .druck(druckSensorPort.get(druckSensor.indexOf(s)));
		// }
		// }
		// for (ColorSensor s : farbSensor) {
		// iSensorHandler.farbe(farbSensorPort.get(farbSensor.indexOf(s)),
		// s.getColor(), s.getFloodlight());
		// }
		// for (SoundSensor s : soundSensor) {
		// iSensorHandler.sound(soundSensorPort.get(soundSensor.indexOf(s)),
		// s.readValue());
		// }
		// for (UltrasonicSensor s : ultraschallSensor) {
		// int i = s.getDistance();
		// //if (i > 255) {
		// iSensorHandler
		// .ultraschall(ultraschallSensorPort
		// .get(ultraschallSensor.indexOf(s)), i);
		// //}
		// }
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
