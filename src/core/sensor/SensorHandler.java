package core.sensor;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
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

	private ISensorHandler isensorhandler;

	@SuppressWarnings("deprecation")
	public SensorHandler(ISensorLaoder i) {
		lichtSensorPort = new ArrayList<SensorPort>();
		druckSensorPort = new ArrayList<SensorPort>();
		ultraschallSensorPort = new ArrayList<SensorPort>();
		soundSensorPort = new ArrayList<SensorPort>();
		farbSensorPort = new ArrayList<SensorPort>();
		if (i.getDefaultLichtSensorPort() != null)
			lichtSensorPort.addAll(i.getDefaultLichtSensorPort());
		if (i.getDefaultDruckSensorPort() != null)
			lichtSensorPort.addAll(i.getDefaultDruckSensorPort());
		if (i.getDefaultUltraschallSensorPort() != null)
			lichtSensorPort.addAll(i.getDefaultUltraschallSensorPort());
		if (i.getDefaultSoundSensorPort() != null)
			lichtSensorPort.addAll(i.getDefaultSoundSensorPort());
		if (i.getDefaultFarbSensorPort() != null)
			lichtSensorPort.addAll(i.getDefaultFarbSensorPort());

		lichtSensor = new ArrayList<LightSensor>();
		druckSensor = new ArrayList<TouchSensor>();
		ultraschallSensor = new ArrayList<UltrasonicSensor>();
		soundSensor = new ArrayList<SoundSensor>();
		farbSensor = new ArrayList<ColorSensor>();

		for (SensorPort s : lichtSensorPort) {
			lichtSensor.add(new LightSensor(s));
		}
		for (SensorPort s : druckSensorPort) {
			druckSensor.add(new TouchSensor(s));
		}
		for (SensorPort s : ultraschallSensorPort) {
			ultraschallSensor.add(new UltrasonicSensor(s));
		}
		for (SensorPort s : soundSensorPort) {
			soundSensor.add(new SoundSensor(s));
		}
		for (SensorPort s : farbSensorPort) {
			farbSensor.add(new ColorSensor(s));
		}
	}

	public SensorHandler(ISensorHandler i) {
		this((ISensorLaoder) i);
		isensorhandler = i;
	}

	public SensorPort[] getLichtSensorPort() {
		return (SensorPort[]) lichtSensorPort.toArray();
	}

	public SensorPort[] getDruckSensorPort() {
		return (SensorPort[]) druckSensorPort.toArray();
	}

	public SensorPort[] getUltraschallSensorPort() {
		return (SensorPort[]) ultraschallSensorPort.toArray();
	}

	public SensorPort[] getSoundPort() {
		return (SensorPort[]) soundSensorPort.toArray();
	}

	public SensorPort[] getFarbSensorPort() {
		return (SensorPort[]) farbSensorPort.toArray();
	}

	public LightSensor getLightSensor(SensorPort s) {
		return lichtSensor.get(lichtSensorPort.indexOf(s));
	}

	public UltrasonicSensor getUltraschallSensor(SensorPort s) {
		return ultraschallSensor.get(ultraschallSensorPort.indexOf(s));
	}

	public SoundSensor getSoundSensor(SensorPort s) {
		return soundSensor.get(soundSensorPort.indexOf(s));
	}

	public TouchSensor getDruckSensor(SensorPort s) {
		return druckSensor.get(druckSensorPort.indexOf(s));
	}

	public ColorSensor getFarbSensor(SensorPort s) {
		return farbSensor.get(farbSensorPort.indexOf(s));
	}

	@SuppressWarnings("deprecation")
	public void setLichtSensorPort(SensorPort... s) {
		lichtSensorPort = new ArrayList<SensorPort>(s);

	}

	@SuppressWarnings("deprecation")
	public void setDruckSensorPort(SensorPort... s) {
		druckSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setUltraschallSensorPort(SensorPort... s) {
		ultraschallSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setSoundSensorPort(SensorPort... s) {
		soundSensorPort = new ArrayList<SensorPort>(s);
	}

	@SuppressWarnings("deprecation")
	public void setFarbSensorPort(SensorPort... s) {
		farbSensorPort = new ArrayList<SensorPort>(s);
	}

	public void update() {
		for (LightSensor s : lichtSensor) {
			isensorhandler.licht(lichtSensorPort.get(lichtSensor.indexOf(s)),
					s.getLightValue(), s.getFloodlight());
		}
		for (TouchSensor s : druckSensor) {
			if (s.isPressed()) {
				isensorhandler
						.druck(druckSensorPort.get(druckSensor.indexOf(s)));
			}
		}
		for (ColorSensor s : farbSensor) {
			isensorhandler.farbe(farbSensorPort.get(farbSensor.indexOf(s)),
					s.getColor(), s.getFloodlight());
		}
		for (SoundSensor s : soundSensor) {
			isensorhandler.sound(soundSensorPort.get(soundSensor.indexOf(s)),
					s.readValue());
		}
		for (UltrasonicSensor s : ultraschallSensor) {
			int i = s.getDistance();
			if (i > 255) {
				isensorhandler
						.ultraschall(ultraschallSensorPort
								.get(ultraschallSensor.indexOf(s)), s
								.getDistance());
			}
		}
	}

	public void kalibrireLicht(SensorPort... sensoren) {
		LCD.clear();
		LCD.drawString("Heller", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (SensorPort sensor : sensoren) {
			lichtSensor.get(lichtSensorPort.indexOf(sensor)).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Dunkler", 0, 0);
		LCD.drawString("Untergrund", 0, 1);

		Button.waitForAnyPress();

		for (SensorPort sensor : sensoren) {
			lichtSensor.get(lichtSensorPort.indexOf(sensor)).calibrateLow();
		}
	}

	public void kalibrireFarb(SensorPort... sensoren) {
		LCD.clear();
		LCD.drawString("Weiß", 0, 0);

		Button.waitForAnyPress();

		for (SensorPort sensor : sensoren) {
			lichtSensor.get(farbSensorPort.indexOf(sensor)).calibrateHigh();
		}

		LCD.clear();
		LCD.drawString("Schwarz", 0, 0);

		Button.waitForAnyPress();

		for (SensorPort sensor : sensoren) {
			lichtSensor.get(farbSensorPort.indexOf(sensor)).calibrateLow();
		}
	}
}
