package core.sensor.interfaces;

import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Ultraschallsensoren
 * implementiert.
 */
public interface IUltraschallSensor extends ISensorHandler {
	/**
	 * Diese Funktion wird verwendet, um herauszufinden, an welchen Ports
	 * Ultraschallsensoren angeschlossen sind.
	 * 
	 * @return Ein Array das die SensorPorts enthält, an denen
	 *         Ultraschallsensoren sind.
	 */
	public abstract SensorPort[] ultraschallSensorenPorts();
}