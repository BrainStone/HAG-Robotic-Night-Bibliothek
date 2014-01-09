package core.sensor;

import java.util.ArrayList;

import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public abstract class SensorHandler {

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

	@SuppressWarnings("deprecation")
	public SensorHandler() {
		lichtSensorPort = new ArrayList<SensorPort>(getDefaultLichtSensorPort());
		druckSensorPort = new ArrayList<SensorPort>(getDefaultDruckSensorPort());
		ultraschallSensorPort = new ArrayList<SensorPort>(
				getDefaultUltraschallSensorPort());
		soundSensorPort = new ArrayList<SensorPort>(getDefaultSoundSensorPort());
		farbSensorPort = new ArrayList<SensorPort>(getDefaultFarbSensorPort());

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

	public abstract void licht(SensorPort s, int hellichkeit, int lichtColor);

	public abstract void druck(SensorPort s);

	public abstract void ultraschall(SensorPort s, int abstand);

	public abstract void sound(SensorPort s, int lautstärke);

	public abstract void farbe(SensorPort s, Color farbe, int FlutLichtFarbe);

	public abstract SensorPort[] getDefaultLichtSensorPort();

	public abstract SensorPort[] getDefaultDruckSensorPort();

	public abstract SensorPort[] getDefaultUltraschallSensorPort();

	public abstract SensorPort[] getDefaultSoundSensorPort();

	public abstract SensorPort[] getDefaultFarbSensorPort();

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
			this.licht(lichtSensorPort.get(lichtSensor.indexOf(s)),
					s.getLightValue(), s.getFloodlight());
		}
		for (TouchSensor s : druckSensor) {
			if (s.isPressed()) {
				this.druck(druckSensorPort.get(druckSensor.indexOf(s)));
			}
		}
		for (ColorSensor s : farbSensor) {
			this.farbe(farbSensorPort.get(farbSensor.indexOf(s)), s.getColor(),
					s.getFloodlight());
		}
		for (SoundSensor s : soundSensor) {
			this.sound(soundSensorPort.get(soundSensor.indexOf(s)), s.readValue());
		}
		for (UltrasonicSensor s : ultraschallSensor){
			int i =s.getDistance();
			if(i > 255){
				this.ultraschall(ultraschallSensorPort.get(ultraschallSensor.indexOf(s)), s.getDistance());
			}
		}
	}

}
