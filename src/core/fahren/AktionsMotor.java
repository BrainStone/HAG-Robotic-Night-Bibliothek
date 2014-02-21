package core.fahren;

import lejos.nxt.MotorPort;
import lejos.nxt.TachoMotorPort;

public class AktionsMotor extends Motor {
	public static final AktionsMotor A = new AktionsMotor(MotorPort.A);
	public static final AktionsMotor B = new AktionsMotor(MotorPort.B);
	public static final AktionsMotor C = new AktionsMotor(MotorPort.C);
	
	// DOCME
	public AktionsMotor(TachoMotorPort port) {
		super(port);
	}
}