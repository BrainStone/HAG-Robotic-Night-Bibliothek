package core.fahren;

import lejos.nxt.MotorPort;
import lejos.nxt.TachoMotorPort;

public class FahrMotor extends Motor {
	public static final FahrMotor A = new FahrMotor(MotorPort.A);
	public static final FahrMotor B = new FahrMotor(MotorPort.B);
	public static final FahrMotor C = new FahrMotor(MotorPort.C);

	// DOCME
	public static FahrMotor A(double durchmesser) {
		A.durchmesser = durchmesser;
		A.richtung = 1;

		A.setBeschleunigung(50);
		A.setGeschwindigkeit(10);

		return A;
	}

	// DOCME
	public static FahrMotor B(double durchmesser) {
		B.durchmesser = durchmesser;
		B.richtung = 1;

		B.setBeschleunigung(50);
		B.setGeschwindigkeit(10);

		return B;
	}

	// DOCME
	public static FahrMotor C(double durchmesser) {
		C.durchmesser = durchmesser;
		C.richtung = 1;

		C.setBeschleunigung(50);
		C.setGeschwindigkeit(10);

		return C;
	}

	/** Durchmesser des Rades in cm */
	private double durchmesser;
	/** Die Richtung der Bewegung */
	private byte richtung;

	// DOCME
	public FahrMotor(TachoMotorPort port) {
		super(port);

		durchmesser = -1.0;
		richtung = 1;
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

		motor.rotate((int) (streckeZuGrad(distanz) * richtung), !warte);
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
		return gradZuStrecke(motor.getMaxSpeed());
	}

	/**
	 * Rechnet Grad in eine Strecke um.
	 * 
	 * @param rad
	 *            Die umzuwandelnde Rotation in Grad
	 * @return Stecke in cm
	 */
	public double gradZuStrecke(double grad) {
		return (Math.toRadians(grad) / 2) * durchmesser;
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
		motor.setAcceleration((int) streckeZuGrad(Math.abs(beschleunigung)));

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

		motor.setSpeed((float) streckeZuGrad(geschwindigkeit));

		return this;
	}

	/**
	 * Rechnet eine Strecke in Grad um.
	 * 
	 * @param strecke
	 *            Die umzuwandelnnde Strecke in cm
	 * @return Rotation in Grad
	 */
	public double streckeZuGrad(double strecke) {
		return Math.toDegrees(strecke / (durchmesser / 2.0));
	}

	/**
	 * Gibt den Zählstand des Motors in cm zurück.
	 * 
	 * @return Zählstand in cm
	 * @see lejos.nxt.NXTRegulatedMotor#getTachoCount()
	 */
	public double zählstandStrecke() {
		return gradZuStrecke(motor.getTachoCount());
	}
}