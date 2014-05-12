package core.sensor;

import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;

public interface ISensorHandler {
	/**
	 * Wird bei druck auf einen Drucksensor aufgerufen
	 * 
	 * @param s
	 *            Der Port
	 */
	public abstract void druck(SensorPort s);

	/**
	 * Wie bei farbe aufgerufen
	 * 
	 * @param s
	 *            Der Port
	 * @param farbe
	 *            Die farbe
	 * @param FlutLichtFarbe
	 *            Die farbe Des lichts
	 */
	public abstract void farbe(SensorPort s, Color farbe, int FlutLichtFarbe);

	/**
	 * Die Normalen Sensoren
	 * 
	 * @return Die Normalen Sensoren
	 */
	public abstract SensorPort[] getDefaultDruckSensorPort();

	/**
	 * Die Normalen Sensoren
	 * 
	 * @return Die Normalen Sensoren
	 */
	public abstract SensorPort[] getDefaultFarbSensorPort();

	/**
	 * Die Normalen Sensoren
	 * 
	 * @return Die Normalen Sensoren
	 */
	public abstract SensorPort[] getDefaultLichtSensorPort();

	/**
	 * Die Normalen Sensoren
	 * 
	 * @return Die Normalen Sensoren
	 */
	public abstract SensorPort[] getDefaultSoundSensorPort();

	/**
	 * Die Normalen Sensoren
	 * 
	 * @return Die Normalen Sensoren
	 */
	public abstract SensorPort[] getDefaultUltraschallSensorPort();

	/**
	 * Wird bei Licht aufgerufen
	 * 
	 * @param s
	 *            Der Port
	 * @param hellichkeit
	 *            Die Hellichkeit
	 * @param lichtColor
	 *            Die Farbe der Lampe(vom Sensor)
	 */
	public abstract void licht(SensorPort s, int hellichkeit, int lichtColor);

	/**
	 * Wird bei einem ton aufgerufen
	 * 
	 * @param s
	 *            Der Port
	 * @param lautstärke
	 */
	public abstract void sound(SensorPort s, int lautstärke);

	/**
	 * Wird bei ultraschall aufgerufen
	 * 
	 * @param s
	 *            Der Port
	 * @param abstand
	 *            Der Gemessene Abstand
	 */
	public abstract void ultraschall(SensorPort s, int abstand);
}
