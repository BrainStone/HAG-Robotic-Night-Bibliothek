package core.fahren;

import java.util.HashMap;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

@SuppressWarnings("deprecation")
public class Motor {
	protected static final HashMap<TachoMotorPort, NXTRegulatedMotor> motorMap = new HashMap<TachoMotorPort, NXTRegulatedMotor>(
			3);
	static {
		motorMap.put(MotorPort.A, new NXTRegulatedMotor(MotorPort.A));
		motorMap.put(MotorPort.B, new NXTRegulatedMotor(MotorPort.B));
		motorMap.put(MotorPort.C, new NXTRegulatedMotor(MotorPort.C));
	}

	public static final Motor A = new Motor(MotorPort.A);
	public static final Motor B = new Motor(MotorPort.B);
	public static final Motor C = new Motor(MotorPort.C);

	protected final NXTRegulatedMotor motor;

	protected final TachoMotorPort port;

	// DOCME
	public Motor(TachoMotorPort port) {
		motor = motorMap.get(port);
		this.port = port;
	}

	/**
	 * Gibt true zurück, wenn sich der Motor bewegt.<br>
	 * Es wird geprüft, ob der Motor versucht sich zu bewegen und ob er nicht
	 * blockiert ist.
	 * 
	 * @return true, wenn sich der Motor bewegt, sonst false.
	 * @see lejos.nxt.NXTRegulatedMotor#isMoving()
	 * @see lejos.nxt.NXTRegulatedMotor#isStalled()
	 */
	public boolean bewegtSich() {
		return motor.isMoving() && !motor.isStalled();
	}

	// DOCME
	public NXTRegulatedMotor getMotor() {
		return motor;
	}

	// DOCME
	public TachoMotorPort getPort() {
		return port;
	}

	/**
	 * Entsperrt den Motor. Danach kann man ihn frei drehen.
	 * 
	 * @see lejos.nxt.NXTRegulatedMotor#suspendRegulation()
	 */
	public void motorFrei() {
		motor.flt();
	}

	/**
	 * Gibt den Zählstand des Motors in Grad zurück.
	 * 
	 * @return Zählstand in Grad
	 * @see lejos.nxt.NXTRegulatedMotor#getTachoCount()
	 */
	public int zählstand() {
		return motor.getTachoCount();
	}

	/**
	 * Setzt den Zählstand des Motors auf 0.
	 * 
	 * @see lejos.nxt.NXTRegulatedMotor#resetTachoCount()
	 */
	public void zählstandZurücksetzten() {
		motor.resetTachoCount();
	}
}