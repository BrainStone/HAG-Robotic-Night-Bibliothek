package core.fahren;

import core.template.util.Timing;

public class KurvenFahren extends Fahren {
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
	public static KurvenFahren AB(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.A(durchmesser),
				FahrMotor.B(durchmesser), abstand);
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
	public static KurvenFahren AC(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.A(durchmesser),
				FahrMotor.C(durchmesser), abstand);
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
	public static KurvenFahren BA(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.B(durchmesser),
				FahrMotor.A(durchmesser), abstand);
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
	public static KurvenFahren BC(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.B(durchmesser),
				FahrMotor.C(durchmesser), abstand);
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
	public static KurvenFahren CA(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.C(durchmesser),
				FahrMotor.A(durchmesser), abstand);
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
	public static KurvenFahren CB(double abstand, double durchmesser) {
		return new KurvenFahren(FahrMotor.C(durchmesser),
				FahrMotor.B(durchmesser), abstand);
	}

	private final double halbAbstand;

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
	protected KurvenFahren(FahrMotor rechts, FahrMotor links, double abstand) {
		super(rechts, links, abstand);

		halbAbstand = this.abstand / 2.0;
	}

	public void kurve(double radius, double distanz) {
		kurve(radius, distanz, false);
	}

	public void kurve(double radius, double distanz, boolean warte) {
		final double altGeschwRechts = RECHTS.getGeschwindigkeit();
		final double altGeschwLinks = LINKS.getGeschwindigkeit();
		final double altBeschRechts = RECHTS.getBeschleunigung();
		final double altBeschLinks = LINKS.getBeschleunigung();

		final double faktorRechts = (radius + halbAbstand) / radius;
		final double faktorLinks = (radius - halbAbstand) / radius;

		RECHTS.setGeschwindigkeit(altGeschwRechts * faktorRechts);
		LINKS.setGeschwindigkeit(altGeschwLinks * faktorLinks);
		RECHTS.setBeschleunigung(altBeschRechts * faktorRechts);
		LINKS.setBeschleunigung(altBeschLinks * faktorLinks);

		RECHTS.fahre(distanz * faktorRechts, false);
		LINKS.fahre(distanz * faktorLinks, false);

		if (warte) {
			while (RECHTS.bewegtSich() || LINKS.bewegtSich()) {
				Timing.warte(1);
			}
		}

		RECHTS.setGeschwindigkeit(altGeschwRechts);
		LINKS.setGeschwindigkeit(altGeschwLinks);
		RECHTS.setBeschleunigung(altBeschRechts);
		LINKS.setBeschleunigung(altBeschLinks);
	}
}