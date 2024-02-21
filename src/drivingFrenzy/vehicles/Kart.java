package drivingFrenzy.vehicles;

import drivingFrenzy.race.Section;

public class Kart extends DefaultVehicle {

	// We don´t need getters and setters in the gear related variables because they
	// are used just internally.
	private int currentGear;
	private int maxSpeedFirstGear;
	private int minSpeedSecondGear;

	/**
	 * @param number
	 * @param driver
	 * @param currentSpeed
	 * @param maxSpeedFirstGear  It must be larger than @minSpeedSecondGear ,
	 *                           otherwise it is set a bit larger
	 * @param minSpeedSecondGear
	 * @param maxSpeed
	 * @param description
	 */
	public Kart(int number, String driver, int currentSpeed, int maxSpeedFirstGear, int minSpeedSecondGear,
			int maxSpeed, String description) {
		super(number, driver, currentSpeed, maxSpeed, description);
		if (maxSpeedFirstGear <= minSpeedSecondGear) {
			this.maxSpeedFirstGear = minSpeedSecondGear + 1;
			System.out.println(
					"La velocidad máxima en primera tiene que ser mayor que la velocidad mínima en segunda. Se ha configurado la velocidad máxima en primera just por encima de la velocidad mínima en segunda.");
		} else {
			this.maxSpeedFirstGear = maxSpeedFirstGear;
		}

		this.minSpeedSecondGear = minSpeedSecondGear;
		currentGear = 1;
	}

	@Override
	public String adaptSpeed(Section nextSection) {
		String result = "";
		// First gear and next section is faster... Gear up.
		if (currentGear == 1 & nextSection.getTheoreticalMaxSpeed() > maxSpeedFirstGear) {
			currentGear = 2;
			int nextSpeed = minSpeedSecondGear;
			result += "El número " + getNumber() + " mete segunda y se pone a " + nextSpeed + " desde "
					+ getCurrentSpeed() + ". ";
			setCurrentSpeed(nextSpeed);
		} else if (currentGear == 2 & nextSection.getTheoreticalMaxSpeed() <= minSpeedSecondGear) {
			currentGear = 1;
			int nextSpeed = nextSection.getTheoreticalMaxSpeed();
			result += "El número " + getNumber() + " reduce a primera y se pone a " + nextSpeed + ". ";
			setCurrentSpeed(nextSpeed);
		} else {
			if (nextSection.getTheoreticalMaxSpeed() > getCurrentSpeed()) {
				int nextSpeed = Math.min(getMaxSpeed(), nextSection.getTheoreticalMaxSpeed());
				;
				result += "El número " + getNumber() + " acelera desde " + getCurrentSpeed() + " a " + nextSpeed + ". ";
				setCurrentSpeed(nextSpeed);
				if (getMaxSpeed() == getCurrentSpeed()) {
					result += getDriver() + " aprieta a fondo, pero su " + getDescription() + " está al límite!";
				}
			} else {
				setCurrentSpeed(nextSection.getTheoreticalMaxSpeed());
				result += "El número " + getNumber() + " reduce la velocidad a " + getCurrentSpeed();
			}
		}
		result += super.adaptSpeed(nextSection);

		return result;
	}

}
