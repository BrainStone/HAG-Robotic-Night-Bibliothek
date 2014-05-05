package core.sensor.interfaces;

import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Soundsensoren, die
 * nicht ausschleißlich manuell ausgelesen werden sollen, implementiert.
 */
public interface ISoundSensorUpdate extends ISoundSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Soundsensor aufgerufen, wenn
	 * die {@link core.sensor.SensorHandler#update()} Funktion aufgerufen wird
	 * und der Sensor gedrückt wurde.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param soundSensor
	 *            Die Instanz des Soundsensors
	 * @param lautstärke
	 *            Die gemessene Lautstärke
	 */
	public abstract void handleSoundSensorUpdate(SensorPort port,
			SoundSensor soundSensor, double lautstärke);
}