package core.sensor.interfaces;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Drucksensoren, die
 * nicht ausschleißlich manuell ausgelesen werden sollen, implementiert.
 */
public interface IDruckSensorUpdate extends IDruckSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Drucksensor aufgerufen, wenn
	 * die {@link core.sensor.SensorHandler#update()} Funktion aufgerufen wird
	 * und der Sensor gedrückt wurde.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param druckSensor
	 *            Die Instanz des Drucksensors
	 */
	public abstract void handleDruckSensorUpdate(SensorPort port,
			TouchSensor druckSensor);
}
