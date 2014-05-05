package core.sensor.interfaces;

import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Soundsensoren
 * implementiert.
 */
public interface ISoundSensor extends ISensorHandler {
	/**
	 * Diese Funktion wird verwendet, um herauszufinden, an welchen Ports
	 * Soundsensoren angeschlossen sind.
	 * 
	 * @return Ein Array das die SensorPorts enthält, an denen Soundsensoren
	 *         sind.
	 */
	public abstract SensorPort[] soundSensorenPorts();
}