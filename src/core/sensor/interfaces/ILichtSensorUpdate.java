package core.sensor.interfaces;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Lichtsensoren, die
 * nicht ausschleißlich manuell ausgelesen werden sollen, implementiert.
 */
public interface ILichtSensorUpdate extends ILichtSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Lichtsensor aufgerufen, wenn
	 * die {@link core.sensor.SensorHandler#update()} Funktion aufgerufen wird.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param lichtSensor
	 *            Die Instanz des Lichtsensors
	 * @param helligkeit
	 *            Die aktuell gemessene Helligkeit
	 * @param flutlicht
	 *            Eine Flag, die den Status des Flutlichts beschreibt
	 */
	public abstract void handleLichtSensorUpdate(SensorPort port,
			LightSensor lichtSensor, int hellichkeit, boolean flutlicht);
}