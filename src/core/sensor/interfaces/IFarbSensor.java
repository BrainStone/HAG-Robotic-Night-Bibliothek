package core.sensor.interfaces;

import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Farbsensoren
 * implementiert.
 */
public interface IFarbSensor extends ISensorHandler {
	/**
	 * Diese Funktion wird verwendet, um herauszufinden, an welchen Ports
	 * Farbsensoren angeschlossen sind.
	 * 
	 * @return Ein Array das die SensorPorts enthält, an denen Farbsensoren
	 *         sind.
	 */
	public abstract SensorPort[] farbSensorenPorts();
}