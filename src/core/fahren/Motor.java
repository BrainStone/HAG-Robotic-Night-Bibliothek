package core.fahren;

import java.util.HashMap;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

@SuppressWarnings("deprecation")
public class Motor {
	protected static final HashMap<TachoMotorPort, NXTRegulatedMotor> motorMap = new HashMap<TachoMotorPort, NXTRegulatedMotor>(3);
	static
	{
		motorMap.put(MotorPort.A, new NXTRegulatedMotor(MotorPort.A));
		motorMap.put(MotorPort.B, new NXTRegulatedMotor(MotorPort.B));
		motorMap.put(MotorPort.C, new NXTRegulatedMotor(MotorPort.C));
	}
	
	public static final Motor A = new Motor(MotorPort.A);
	public static final Motor B = new Motor(MotorPort.B);
	public static final Motor C = new Motor(MotorPort.C);
	
	// DOCME
	public Motor(TachoMotorPort port) {
		motor = motorMap.get(port);
		this.port = port;
	}
	
	protected final NXTRegulatedMotor motor;
	protected final TachoMotorPort port;
	
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
	 */
	public void motorFrei() {
		motor.flt();
	}
}