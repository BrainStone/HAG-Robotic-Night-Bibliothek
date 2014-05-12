package core.sensor;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class SensorHandler {

	// TODO : ArrayList
	private ArrayList<SensorPort> lichtSensorPort = null;
	private ArrayList<SensorPort> druckSensorPort = null;
	private ArrayList<SensorPort> ultraschallSensorPort = null;
	private ArrayList<SensorPort> soundSensorPort = null;
	private ArrayList<SensorPort> farbSensorPort = null;

	private ArrayList<LightSensor> lichtSensor = null;
	private ArrayList<TouchSensor> druckSensor = null;
	private ArrayList<UltrasonicSensor> ultraschallSensor = null;
	private ArrayList<SoundSensor> soundSensor = null;
	private ArrayList<ColorSensor> farbSensor = null;

	private final ISensorHandler isensorhandler;

	/**
	 * Eine Neue Sensor handler instance
	 * 
	 * @param i
	 *            Ein ISensorHandler
	 */
	@SuppressWarnings("deprecation")
	public SensorHandler(ISensorHandler i) {
		lichtSensorPort = new ArrayList<SensorPort>();
		druckSensorPort = new ArrayList<SensorPort>();
		ultraschallSensorPort = new ArrayList<SensorPort>();
		soundSensorPort = new ArrayList<SensorPort>();
		farbSensorPort = new ArrayList<SensorPort>();
		if (i.getDefaultLichtSensorPort() != null) {
			lichtSensorPort.addAll(i.getDefaultLichtSensorPort());
		}
		if (i.getDefaultDruckSensorPort() != null) {
			druckSensorPort.addAll(i.getDefaultDruckSensorPort());
		}
		if (i.getDefaultUltraschallSensorPort() != null) {
			ultraschallSensorPort.addAll(i.getDefaultUltraschallSensorPort());
		}
		if (i.getDefaultSoundSensorPort() != null) {
			soundSensorPort.addAll(i.getDefaultSoundSensorPort());
		}
		if (i.getDefaultFarbSensorPort() != null) {
			farbSensorPort.addAll(i.getDefaultFarbSensorPort());
		}

		lichtSensor = new ArrayList<LightSensor>();
		druckSensor = new ArrayList<TouchSensor>();
		ultraschallSensor = new ArrayList<UltrasonicSensor>();
		soundSensor = new ArrayList<SoundSensor>();
		farbSensor = new ArrayList<ColorSensor>();

		for (final SensorPort s : lichtSensorPort) {
			lichtSensor.add(new LightSensor(s));
		}
		for (final SensorPort s : druckSensorPort) {
			druckSensor.add(new TouchSensor(s));
		}
		for (final SensorPort s : ultraschallSensorPort) {
			ultraschallSensor.add(new UltrasonicSensor(s));
		}
		for (final SensorPort s : soundSensorPort) {
			soundSensor.add(new SoundSensor(s));
		}
		for (final SensorPort s : farbSensorPort) {
			farbSensor.add(new ColorSensor(s));
		}
		isensorhandler = i;
	}

	public TouchSensor getDruckSensor(SensorPort s) {
		return druckSensor.get(druckSensorPort.indexOf(s));
	}

	public SensorPort[] getDruckSensorPort() {
		return (SensorPort[]) druckSensorPort.toArray();
	}

	public ColorSensor getFarbSensor(SensorPort s) {
		return farbSensor.get(farbSensorPort.indexOf(s));
	}

	public SensorPort[] getFarbSensorPort() {
		return (SensorPort[]) farbSensorPort.toArray();
	}

	public SensorPort[] getLichtSensorPort() {
		return (SensorPort[]) lichtSensorPort.toArray();
	}

	public LightSensor getLightSensor(SensorPort s) {
		return lichtSensor.get(lichtSensorPort.indexOf(s));
	}

	public SensorPort[] getSoundPort() {
		return (SensorPort[]) soundSensorPort.toArray();
	}

	public SoundSensor getSoundSensor(SensorPort s) {
		return soundSensor.get(soundSensorPort.indexOf(s));
	}

	public UltrasonicSensor getUltraschallSensor(SensorPort s) {
		return ultraschallSensor.get(ultraschallSensorPort.indexOf(s));
	}

	public SensorPort[] getUltraschallSensorPort() {
		return (SensorPort[]) ultraschallSensorPort.toArray();
	}

	public void kalibrireFarb(SensorPort s) {
		System.out.println("Die helle Farbe");
		Button.waitForAnyPress();
		farbSensor.get(farbSensorPort.indexOf(s)).calibrateHigh();
		System.out.println("Die dunkle Farbe");
		Button.waitForAnyPress();
		farbSensor.get(farbSensorPort.indexOf(s)).calibrateHigh();
	}

	public void kalibrireLicht(SensorPort s) {
		System.out.println("Das helle Licht");
		Button.waitForAnyPress();
		lichtSensor.get(lichtSensorPort.indexOf(s)).calibrateHigh();
		System.out.println("Das dunkle Licht");
		Button.waitForAnyPress();
		lichtSensor.get(lichtSensorPort.indexOf(s)).calibrateLow();
	}

	@SuppressWarnings("deprecation")
	public void setDruckSensorPort(SensorPort... s) {
		druckSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setFarbSensorPort(SensorPort... s) {
		farbSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setLichtSensorPort(SensorPort... s) {
		lichtSensorPort = new ArrayList<SensorPort>(s);

	}

	@SuppressWarnings("deprecation")
	public void setSoundSensorPort(SensorPort... s) {
		soundSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setUltraschallSensorPort(SensorPort... s) {
		ultraschallSensorPort = new ArrayList<SensorPort>(s);
	}

	/**
	 * Updated die sensoren
	 */
	public void update() {
		for (final LightSensor s : lichtSensor) {
			isensorhandler.licht(lichtSensorPort.get(lichtSensor.indexOf(s)),
					s.getLightValue(), s.getFloodlight());
		}
		for (final TouchSensor s : druckSensor) {
			if (s.isPressed()) {
				isensorhandler
						.druck(druckSensorPort.get(druckSensor.indexOf(s)));
			}
		}
		for (final ColorSensor s : farbSensor) {
			isensorhandler.farbe(farbSensorPort.get(farbSensor.indexOf(s)),
					s.getColor(), s.getFloodlight());
		}
		for (final SoundSensor s : soundSensor) {
			isensorhandler.sound(soundSensorPort.get(soundSensor.indexOf(s)),
					s.readValue());
		}
		for (final UltrasonicSensor s : ultraschallSensor) {
			final int i = s.getDistance();
			// if (i > 255) {
			isensorhandler.ultraschall(
					ultraschallSensorPort.get(ultraschallSensor.indexOf(s)), i);
			// }
		}

	}

}
