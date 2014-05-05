package core.sensor.interfaces;

import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Drucksensoren
 * implementiert.
 */
public interface IDruckSensor extends ISensorHandler {
	/**
	 * Diese Funktion wird verwendet, um herauszufinden, an welchen Ports
	 * Drucksensoren angeschlossen sind.
	 * 
	 * @return Ein Array das die SensorPorts enthält, an denen Drucksensoren
	 *         sind.
	 */
	public abstract SensorPort[] druckSensorenPorts();
}