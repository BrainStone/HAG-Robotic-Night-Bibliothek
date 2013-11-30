package fahren;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

public class FahrMotoren extends NXTRegulatedMotor {
	private final NXTRegulatedMotor Links;

	private final int durchmesserReifen;

	public FahrMotoren(final TachoMotorPort Links, final TachoMotorPort Rechts,
			final int durchmesserReifen) {
		super(Rechts);
		this.Links = new NXTRegulatedMotor(Links);
		this.durchmesserReifen = durchmesserReifen;

	}

	public synchronized void drehen(final int grad) {
		if (grad > 180) {
			super.rotate(grad, false);
			Links.rotate(-grad, false);
		} else {
			super.rotate(-grad, false);
			Links.rotate(grad, false);
		}
	}

	public synchronized void gradeaus(final int cm,
			final boolean immediateReturn) {
		final int i = Math.round(((cm * 360) / durchmesserReifen) * (float) Math.PI);
		this.rotate(i, immediateReturn);
	}
	
	public void endMove(){
		this.stop();
		this.rotate(0, true);
	}
	
	@Override
	public void backward() {
		super.backward();
		Links.backward();
	}

	@Override
	public void flt() {
		super.flt();
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
