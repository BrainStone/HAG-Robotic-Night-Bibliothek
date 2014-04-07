package core.sensor.interfaces;

import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;

/**
 * Dieses Interface wird beim Verwenden eines oder mehrer Farbsensoren, die
 * nicht ausschleißlich manuell ausgelesen werden sollen, implementiert.
 */
public interface IFarbSensorUpdate extends IFarbSensor {
	/**
	 * Diese Funktion wird für jeden registrierten Farbsensor aufgerufen, wenn
	 * die {@link core.sensor.SensorHandler#update()} Funktion aufgerufen wird
	 * und der Sensor gedrückt wurde.
	 * 
	 * @param port
	 *            Der Port an dem der Sensor hängt.
	 * @param farbSensor
	 *            Die Instanz des Soundsensors
	 * @param farbe
	 *            Die aktuell gemessene Farbe
	 * @param flutlichtFarbe
	 *            Die Farbe des Flutlichts
	 */
	public abstract void handleFarbSensorUpdate(SensorPort port,
			ColorSensor farbSensor, Color farbe, int flutlichtFarbe);
}