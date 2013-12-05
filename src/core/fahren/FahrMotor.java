package core.fahren;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

public class FahrMotor extends NXTRegulatedMotor {
	public static final FahrMotor A = new FahrMotor(MotorPort.A);
	public static final FahrMotor B = new FahrMotor(MotorPort.B);
	public static final FahrMotor C = new FahrMotor(MotorPort.C);

	/** Durchmesser des Rades in cm */
	private double durchmesser;
	private double umfang;

	/**
	 * Privater Konstruktor. Wird nur intern verwendet. Er setzt den durchmesser
	 * auf -1, d.h. man kann ihn verändern.
	 * 
	 * @param port
	 *            Der Port des Motors ({@link MotorPort#A}, {@link MotorPort#B}
	 *            oder {@link MotorPort#C}).
	 */
	private FahrMotor(TachoMotorPort port) {
		this(port, -1.0);
	}

	/**
	 * Dieser Motor erzeugt einen neuen Fahrmotor am angegebenen Port. Der Motor
	 * verwendet den angebenen Durchmesser, um Distanzen zu berechnen.
	 * 
	 * @param port
	 *            Der Port des Motors ({@link MotorPort#A}, {@link MotorPort#B}
	 *            oder {@link MotorPort#C}).
	 * @param durchmesser
	 *            Der Durchmesser des Rades in cm.<br>
	 *            <i>Ist er <code>< 0</code>, kann man ihn noch nachträglich mit
	 *            {@link FahrMot#setDurchmesser(int durchmesser)} ändern.</i>
	 */
	private FahrMotor(TachoMotorPort port, double durchmesser) {
		super(port);

		this.durchmesser = durchmesser;
		umfang = this.durchmesser * Math.PI;
	}

	/**
	 * Gibt den Durchmesser zurück.
	 * 
	 * @return Diese Funktion gibt nur den angegebenen Durchmesser des Rades in
	 *         cm zurück.<br>
	 *         Ist er <code>< 0</code>, dann kann man den Wert noch ändern.
	 */
	public double getDurchmesser() {
		return durchmesser;
	}

	/**
	 * Gibt den Umfang zurück.
	 * 
	 * @return Diese Funktion gibt nur den berechneten Umfang des Rades in cm
	 *         zurück.<br>
	 *         Ist er <code>< 0</code>, dann kann man den Wert noch ändern.
	 */
	public double getUmfang() {
		return umfang;
	}

	/**
	 * Mit dieser Funktion kann man nachträglich den Durchmesser des Rades
	 * einstellen.<br>
	 * Dies wird aber nur gemacht, falls der Duchmesser <code>< 0</code> ist.
	 * Sonst wird die Änderung einfach ignoriert.
	 * 
	 * @param durchmesser
	 *            Der Durchmesser, mit dem der Motor rechnen soll, in cm.
	 * @return Sich selbst, um eine Verkettung von ähnlichen Funktionen zu
	 *         ermöglichen.
	 */
	public FahrMotor setDurchmesser(float durchmesser) {
		if (this.durchmesser < 0.0) {
			this.durchmesser = durchmesser;
			umfang = this.durchmesser * Math.PI;
		}

		return this;
	}

	/**
	 * Lässt den Motor eine gewisse Distanz fahren. Die Funktion wartet nicht,
	 * dass die Bewegung beendet ist.
	 * 
	 * @param distanz
	 *            Die Distanz, die der Motor zurücklegen soll, in cm.
	 */
	public void fahre(double distanz) {
		fahre(distanz, false);
	}

	/**
	 * Lässt den Motor eine gewisse Distanz fahren.
	 * 
	 * @param distanz
	 *            Die Distanz, die der Motor zurücklegen soll, in cm.
	 * @param warte
	 *            Gibt an, ob die Funktion darauf wartet, dass der Motot die
	 *            Bewegung beendet hat.
	 */
	public void fahre(double distanz, boolean warte) {
		if (durchmesser < 0.0) {
			throw new IllegalStateException(
					"Der Durchmesser und Umfang hat keinen gültigen Wert.");
		}

		rotate((int) Math.round(Math.toDegrees(distanz / umfang)), !warte);
	}
}