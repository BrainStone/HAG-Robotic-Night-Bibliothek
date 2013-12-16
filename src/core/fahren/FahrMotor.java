package core.fahren;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

public class FahrMotor extends NXTRegulatedMotor {
	public static final FahrMotor A = new FahrMotor(MotorPort.A);
	public static final FahrMotor B = new FahrMotor(MotorPort.B);
	public static final FahrMotor C = new FahrMotor(MotorPort.C);

	public static FahrMotor A(double durchmesser) {
		return new FahrMotor(MotorPort.A, durchmesser);
	}

	public static FahrMotor B(double durchmesser) {
		return new FahrMotor(MotorPort.B, durchmesser);
	}

	public static FahrMotor C(double durchmesser) {
		return new FahrMotor(MotorPort.C, durchmesser);
	}

	/** Durchmesser des Rades in cm */
	private double durchmesser;
	/** Die Richtung der Bewegung */
	private byte richtung;

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

		setBeschleunigung(50);
		setGeschwindigkeit(10);
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
	 *            Gibt an, ob die Funktion darauf wartet, dass der Motor die
	 *            Bewegung beendet hat.
	 */
	public void fahre(double distanz, boolean warte) {
		if (durchmesser < 0.0)
			throw new IllegalStateException(
					"Der Durchmesser und Umfang hat keinen gültigen Wert.");

		rotate((int) (streckeZuRad(distanz) * richtung), !warte);
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
	 * Eine Funktion, die die maximal mögliche Geschwindigkeit zurückgibt.
	 * 
	 * @return Die momentan maximal mögliche Geschwindigkeit in cm/s.
	 */
	public double getMaximaleGeschwindigkeit() {
		return radZuStrecke(getMaxSpeed());
	}

	/**
	 * Entsperrt den Motor. Danach kann man ihn frei drehen.
	 */
	public void motorFrei() {
		suspendRegulation();
	}

	/**
	 * Rechnet Radianten in eine Strecke um.
	 * 
	 * @param rad
	 *            Die umzuwandelnde Rotation in Rad
	 * @return Stecke in cm
	 */
	public double radZuStrecke(double rad) {
		return (Math.toRadians(rad) / 2) * durchmesser;
	}

	/**
	 * Setzt die Beschleunigung auf den Betrag des angegebenen Wertes.
	 * 
	 * @param beschleunigung
	 *            Die Beschleunigung in cm/s².<br>
	 *            Sollte die Beschleunigung negativ sein wird der positive Wert
	 *            verwendet.
	 * @return Sich selbst, um eine Verkettung von ähnlichen Funktionen zu
	 *         ermöglichen.
	 */
	public FahrMotor setBeschleunigung(double beschleunigung) {
		setAcceleration((int) streckeZuRad(Math.abs(beschleunigung)));

		return this;
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
	public FahrMotor setDurchmesser(double durchmesser) {
		if (this.durchmesser < 0.0) {
			this.durchmesser = durchmesser;
		}

		return this;
	}

	/**
	 * Setzt die Geschwindigkeit des Motors auf den angegebenen Wert.<br>
	 * Sollte die Geschwindigkeit negativ sein, wird die Fahrtrichtung
	 * umgekehrt.<br>
	 * Falls die Geschwindigkeit die maximale Geschwindigkeit überschreitet,
	 * wird die Geschwindigkeit auf diese gesetzt.
	 * 
	 * @param geschwindigkeit
	 *            Die Geschwindigkeit in cm/s.<br>
	 *            Ist sie negativ, fährt der Motor rückwärts, ist sie
	 *            betragsmäßig zu groß, wird sie auf die maximale
	 *            Geschwindigkeit gesetzt.
	 * @return Sich selbst, um eine Verkettung von ähnlichen Funktionen zu
	 *         ermöglichen.
	 */
	public FahrMotor setGeschwindigkeit(double geschwindigkeit) {
		richtung = (byte) Math.signum(geschwindigkeit);

		geschwindigkeit = Math.abs(geschwindigkeit);

		if (geschwindigkeit > getMaximaleGeschwindigkeit()) {
			geschwindigkeit = getMaximaleGeschwindigkeit();
		}

		setSpeed((float) streckeZuRad(geschwindigkeit));

		return this;
	}

	/**
	 * Rechnet eine Strecke in Radianten um.
	 * 
	 * @param strecke
	 *            Die umzuwandelnnde Strecke in cm
	 * @return Rotation in Rad
	 */
	public double streckeZuRad(double strecke) {
		return Math.toDegrees((strecke / durchmesser) * 2.0);
	}
}