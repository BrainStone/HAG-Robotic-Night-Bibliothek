package core.sensor;

import lejos.nxt.SensorPort;

public interface ISensorLaoder {

	public abstract SensorPort[] getDefaultLichtSensorPort();

	public abstract SensorPort[] getDefaultDruckSensorPort();

	public abstract SensorPort[] getDefaultUltraschallSensorPort();

	public abstract SensorPort[] getDefaultSoundSensorPort();

	public abstract SensorPort[] getDefaultFarbSensorPort();
	
	
	
	
}
