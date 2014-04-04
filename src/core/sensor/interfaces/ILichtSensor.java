package core.sensor.interfaces;

import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Lichtsensoren
 * implementiert.
 */
public interface ILichtSensor extends ISensorHandler {
	/**
	 * Diese Funktion wird verwendet, um herauszufinden, an welchen Ports
	 * Lichtsensoren angeschlossen sind.
	 * 
	 * @return Ein Array das die SensorPorts enthält, an denen Lichtsensoren
	 *         sind.
	 */
	public abstract SensorPort[] lichtSensorenPorts();
}