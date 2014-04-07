package core.sensor.interfaces;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Ultraschallsensoren,
 * die nicht ausschleißlich manuell ausgelesen werden sollen, implementiert.
 */
public interface IUltraschallSensorUpdate extends IUltraschallSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Ultraschallsensor aufgerufen,
	 * wenn die {@link core.sensor.SensorHandler#update()} Funktion aufgerufen
	 * wird und der Sensor gedrückt wurde.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param ultraschallSensor
	 *            Die Instanz des Ultraschallsensors
	 * @param abstand
	 *            Der aktuell gemessene Abstand
	 */
	public abstract void handleUltraschallSensorUpdate(SensorPort port,
			UltrasonicSensor ultraschallSensor, double abstand);
}
