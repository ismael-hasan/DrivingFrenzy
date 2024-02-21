package drivingFrenzy.vehicles;

import drivingFrenzy.race.Section;

public class Scooter extends DefaultVehicle {
	
	public Scooter(int number, String driver, int currentSpeed, int maxSpeed, String description) {
		super(number, driver, currentSpeed, maxSpeed, description);
	}

	@Override
	public String adaptSpeed(Section nextSection) {
		String result = "";
		if (nextSection.getTheoreticalMaxSpeed() > getCurrentSpeed()) {
			int nextSpeed =  Math.min(getMaxSpeed(), nextSection.getTheoreticalMaxSpeed());
			result += "El n�mero " + getNumber() + " acelera desde " + getCurrentSpeed() + " a " + nextSpeed + ". ";
			setCurrentSpeed(nextSpeed);
			if (getMaxSpeed() == getCurrentSpeed()) {
				result += getDriver() + " aprieta a fondo, pero su " + getDescription()+ " est� al l�mite!";
			}
		} else {
			setCurrentSpeed(nextSection.getTheoreticalMaxSpeed());
			result += "El n�mero " + getNumber() + " reduce la velocidad a " + getCurrentSpeed();
		}
		result += super.adaptSpeed(nextSection);
		return result;
	}

}
