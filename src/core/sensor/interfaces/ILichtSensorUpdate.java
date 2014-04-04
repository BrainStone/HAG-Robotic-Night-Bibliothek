package core.sensor.interfaces;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Lichtsensoren, die in
 * regelmäßigen Abständen aktualisiert werden sollen, implementiert.
 */
public interface ILichtSensorUpdate extends ILichtSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Lichtsensor in regelmäßigen
	 * Abständen aufgerufen.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param lichtSensor
	 *            Die Instanz des Lichtsensors
	 */
	public abstract void handleLichtSensorUpdate(SensorPort port,
			LightSensor lichtSensor);
}