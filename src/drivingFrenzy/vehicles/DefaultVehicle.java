package drivingFrenzy.vehicles;

import java.util.Random;

import drivingFrenzy.race.Section;

public abstract class DefaultVehicle implements Vehicle {
	private int number;
	private String driver;
	private int currentSpeed;
	private int maxSpeed;
	private String description;
	private Random randomizer;
	
	public DefaultVehicle(int number, String driver, int currentSpeed, int maxSpeed, String description) {
		super();
		this.number = number;
		this.driver = driver;
		this.currentSpeed = currentSpeed;
		this.maxSpeed = maxSpeed;
		this.description = description;
		randomizer = new Random(System.currentTimeMillis());
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public String getDriver() {
		return driver;
	}

	@Override
	public int getCurrentSpeed() {
		return currentSpeed;
	}
	
	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String adaptSpeed(Section section) {
		int multiplier = randomizer.nextInt(80,101);
		if (multiplier!=100) {
			currentSpeed = currentSpeed * multiplier/100;
			return driver + " no lo ha hecho perfecto y su velocidad se reduce a " + currentSpeed;
		} else {
			return "";
		}
	}
	
	

}
