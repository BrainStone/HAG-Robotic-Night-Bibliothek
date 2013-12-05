package core.fahren;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

public class FahrMotoren extends NXTRegulatedMotor {
	public static final FahrMotoren AB(int durchmesser) {
		return new FahrMotoren(MotorPort.A, MotorPort.B, durchmesser);
	}

	public static final FahrMotoren AC(int durchmesser) {
		return new FahrMotoren(MotorPort.A, MotorPort.C, durchmesser);
	}

	public static final FahrMotoren BC(int durchmesser) {
		return new FahrMotoren(MotorPort.B, MotorPort.C, durchmesser);
	}

	private final NXTRegulatedMotor Links;
	private int durchmesserReifen;

	public FahrMotoren(final TachoMotorPort Links, final TachoMotorPort Rechts,
			final int durchmesserReifen) {
		super(Rechts);
		this.Links = new NXTRegulatedMotor(Links);
		this.durchmesserReifen = durchmesserReifen;

	}

	public synchronized void drehen(int grad, float speed) {
		this.drehen(grad, speed, false);
	}

	public synchronized void drehen(int grad, float speed,
			boolean immediateReturn) {
		this.setSpeed(speed);
		if (grad > 180) {
			super.rotate(grad, true);
			Links.rotate(-grad, immediateReturn);
		} else {
			super.rotate(-grad, true);
			Links.rotate(grad, immediateReturn);
		}
	}

	public synchronized void gradeaus(final int cm, float speed) {
		this.gradeaus(cm, speed, false);
	}

	public synchronized void gradeaus(final int cm, float speed,
			final boolean immediateReturn) {
		this.setSpeed(speed);
		final int i = Math.round(((cm * 360) / durchmesserReifen)
				* (float) Math.PI);
		this.rotate(i, immediateReturn);
	}

	public int getDurchmesser() {
		return durchmesserReifen;
	}

	public void setDurchmesser(int durchmesser) {
		this.durchmesserReifen = durchmesser;
	}

	public void endMove() {
		this.stop();
		this.gradeaus(0, 0);
	}

	@Override
	public void backward() {
		super.backward();
		Links.backward();
	}

	@Override
	public void flt() {
		super.flt(true);
		Links.flt();
	}

	@Override
	public void flt(final boolean immediateReturn) {
		super.flt(true);
		Links.flt(immediateReturn);
	}

	@Override
	public void forward() {
		super.forward();
		Links.forward();
	}

	@Deprecated
	@Override
	public void lock(final int power) {
		super.lock(power);
		Links.lock(power);
	}

	@Override
	public void rotateTo(final int limitAngle, final boolean immediateReturn) {
		super.rotateTo(limitAngle, true);
		Links.rotateTo(limitAngle, immediateReturn);
	}

	@Override
	public void setAcceleration(final int acceleration) {
		super.setAcceleration(acceleration);
		Links.setAcceleration(acceleration);
	}

	@Override
	public void setSpeed(final float speed) {
		super.setSpeed(speed);
		Links.setSpeed(speed);
	}

	@Override
	public void setSpeed(final int speed) {
		super.setSpeed(speed);
		Links.setSpeed(speed);
	}

	@Override
	public void setStallThreshold(final int error, final int time) {
		super.setStallThreshold(error, time);
		Links.setStallThreshold(error, time);
	}

	@Override
	public void stop() {
		super.stop();
		Links.stop();
	}

	@Override
	public void stop(final boolean immediateReturn) {
		super.stop(true);
		Links.stop(immediateReturn);
	}
}
