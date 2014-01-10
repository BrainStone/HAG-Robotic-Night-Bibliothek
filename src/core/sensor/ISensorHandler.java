package core.sensor;

import lejos.nxt.SensorPort;
import lejos.nxt.ColorSensor.Color;

public interface ISensorHandler {
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
}
