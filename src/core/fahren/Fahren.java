package core.fahren;

import core.template.util.Timing;

/**
 * Diese Klasse ist ein Wrapperklasse zum einfachen Ansteuern der Fahrmotoren.
 * 
 * @author Yannick Schinko
 * 
 */
public class Fahren {
	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an A und dem
	 * linken an B.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren AB(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.A(durchmesser), FahrMotor.B(durchmesser),
				abstand);
	}

	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an A und dem
	 * linken an C.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren AC(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.A(durchmesser), FahrMotor.C(durchmesser),
				abstand);
	}

	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an B und dem
	 * linken an A.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren BA(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.B(durchmesser), FahrMotor.A(durchmesser),
				abstand);
	}

	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an B und dem
	 * linken an C.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren BC(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.B(durchmesser), FahrMotor.C(durchmesser),
				abstand);
	}

	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an C und dem
	 * linken an A.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren CA(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.C(durchmesser), FahrMotor.A(durchmesser),
				abstand);
	}

	/**
	 * Diese Funktion erzeugt ein Objekt mit dem rechten Motor an C und dem
	 * linken an B.
	 * 
	 * @param abstand
	 *            Der Radabstand in cm
	 * @param durchmesser
	 *            Der Durchmesser der Räder in cm
	 * @return Eine voreingestellte Instanz dieser Klasse
	 */
	public static Fahren CB(double abstand, double durchmesser) {
		return new Fahren(FahrMotor.C(durchmesser), FahrMotor.B(durchmesser),
				abstand);
	}

	public final FahrMotor RECHTS, LINKS;

	private final double abstand;

	/**
	 * Initiert das Objekt und die Motoren.
	 * 
	 * @param rechts
	 *            Rechter Motor
	 * @param links
	 *            linker Motor
	 * @param abstand
	 *            Radabstand in cm
	 */
	private Fahren(FahrMotor rechts, FahrMotor links, double abstand) {
		RECHTS = rechts;
		LINKS = links;
		this.abstand = abstand;

		setGeschwindigkeit(Math.min(RECHTS.getMaximaleGeschwindigkeit(),
				LINKS.getMaximaleGeschwindigkeit()));
	}

	/**
	 * Dreht den Robotor um seine Achse. Die Funktion wartet nicht, dass die
	 * Bewegung beendet ist.
	 * 
	 * @param grad
	 *            Anzahl an Grad der Drehung nach rechts.<br>
	 *            <small>Negative Werte nach links.</small>
	 */
	public void drehe(double grad) {
		drehe(grad, false);
	}

	/**
	 * Dreht den Robotor um seine Achse.
	 * 
	 * @param grad
	 *            Anzahl an Grad der Drehung nach rechts.<br>
	 *            <small>Negative Werte nach links.</small>
	 * @param warte
	 *            Gibt an, ob die Funktion darauf wartet, dass der Motor die
	 *            Bewegung beendet hat.
	 */
	public void drehe(double grad, boolean warte) {
		final double strecke = Math.toRadians(grad) * (abstand / 2.0);

		RECHTS.fahre(strecke);
		LINKS.fahre(-strecke);

		if (warte) {
			while (RECHTS.isMoving() || LINKS.isMoving()) {
				Timing.warte(1);
			}
		}
	}

	/**
	 * Lässt die Motoren eine gewisse Distanz fahren. Die Funktion wartet nicht,
	 * dass die Bewegung beendet ist.
	 * 
	 * @param distanz
	 *            Die Distanz, die die Motoren zurücklegen soll, in cm.
	 */
	public void fahre(double distanz) {
		fahre(distanz, false);
	}

	/**
	 * Lässt die Motoren eine gewisse Distanz fahren.
	 * 
	 * @param distanz
	 *            Die Distanz, die die Motoren zurücklegen soll, in cm.
	 * @param warte
	 *            Gibt an, ob die Funktion darauf wartet, dass der Motor die
	 *            Bewegung beendet hat.
	 */
	public void fahre(double distanz, boolean warte) {
		RECHTS.fahre(distanz);
		LINKS.fahre(distanz);

		if (warte) {
			while (RECHTS.isMoving() || LINKS.isMoving()) {
				Timing.warte(1);
			}
		}
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
	public Fahren setBeschleunigung(double beschleunigung) {
		RECHTS.setBeschleunigung(beschleunigung);
		LINKS.setBeschleunigung(beschleunigung);

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
	 *            Ist sie negativ, fahren die Motoren rückwärts, ist sie
	 *            betragsmäßig zu groß, wird sie auf die maximale
	 *            Geschwindigkeit gesetzt.
	 * @return Sich selbst, um eine Verkettung von ähnlichen Funktionen zu
	 *         ermöglichen.
	 */
	public Fahren setGeschwindigkeit(double geschwindigkeit) {
		RECHTS.setGeschwindigkeit(geschwindigkeit);
		LINKS.setGeschwindigkeit(geschwindigkeit);

		return this;
	}
}