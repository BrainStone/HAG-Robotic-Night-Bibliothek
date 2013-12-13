package core.sensor;

import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public abstract class SensorHandler {

	// TODO : ArrayList
	private SensorPort lichtSensorPort = null;
	private SensorPort druckSensorPort = null;
	private SensorPort ultraschallSensorPort = null;
	private SensorPort soundSensorPort = null;
	private SensorPort farbSensorPort = null;
	
	private LightSensor lichtSensor = null;
	
	public SensorHandler() {
		lichtSensorPort = getDefaultLichtSensorPort();
		druckSensorPort = getDefaultDruckSensorPort();
		ultraschallSensorPort = getDefaultUltraschallSensorPort();
		soundSensorPort = getDefaultSoundSensorPort();
		farbSensorPort = getDefaultFarbSensorPort();
	}

	public abstract void licht(SensorPort s, int hellichkeit, boolean lichtan);

	public abstract void druck(SensorPort s);

	public abstract void ultraschall(SensorPort s, int abstand);

	public abstract void sound(SensorPort s, int lautstärke);

	public abstract void farbe(SensorPort s, Color farbe,
			ColorSensor.Color FlutLichtFarbe);

	public abstract SensorPort getDefaultLichtSensorPort();

	public abstract SensorPort getDefaultDruckSensorPort();

	public abstract SensorPort getDefaultUltraschallSensorPort();

	public abstract SensorPort getDefaultSoundSensorPort();

	public abstract SensorPort getDefaultFarbSensorPort();

	public SensorPort getLichtSensorPort() {
		return lichtSensorPort;
	}

	public SensorPort getDruckSensorPort() {
		return druckSensorPort;
	}

	public SensorPort getUltraschallSensorPort() {
		return ultraschallSensorPort;
	}

	public SensorPort getSoundPort() {
		return soundSensorPort;
	}

	public SensorPort getFarbSensorPort() {
		return farbSensorPort;
	}

	public void setLichtSensorPort(SensorPort s) {
		lichtSensorPort = s;
	}

	public void setDruckSensorPort(SensorPort s) {
		druckSensorPort = s;
	}

	public void setUltraschallSensorPort(SensorPort s) {
		ultraschallSensorPort = s;
	}

	public void setSoundSensorPort(SensorPort s) {
		soundSensorPort = s;
	}

	public void setFarbSensorPort(SensorPort s) {
		farbSensorPort = s;
	}
	
	/*public void update(){
		if(lichtSensorPort==null){
			this.lichtSensorPort(lichtSensorPort)
		}
	}*/
	
}
