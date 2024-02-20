package drivingFrenzy.vehicles;

import drivingFrenzy.race.Section;

public class Kart implements Vehicle {
	
	private int number;
	private String driver;
	private int currentSpeed;
	// We don´t need getters and setters in the gear related variables because they are used just internally.
	private int currentGear;
	private int maxSpeedFirstGear;
	private int minSpeedSecondGear;
	private int maxSpeed; // this is the max speed in second gear. 0 is the min speed in first gear. 
	private String description;
	
	/**
	 * @param number
	 * @param driver
	 * @param currentSpeed
	 * @param maxSpeedFirstGear It must be larger than @minSpeedSecondGear , otherwise it is set a bit larger
	 * @param minSpeedSecondGear
	 * @param maxSpeed
	 * @param description
	 */
	public Kart(int number, String driver, int currentSpeed, int maxSpeedFirstGear, int minSpeedSecondGear, int maxSpeed, String description) {
		super();
		this.number = number;
		this.driver = driver;
		this.currentSpeed = currentSpeed;
		if (maxSpeedFirstGear<=minSpeedSecondGear) {
			this.maxSpeedFirstGear = minSpeedSecondGear+1;
			System.out.println("La velocidad máxima en primera tiene que ser mayor que la velocidad mínima en segunda. Se ha configurado la velocidad máxima en primera just por encima de la velocidad mínima en segunda.");
		} else {
			this.maxSpeedFirstGear = maxSpeedFirstGear;
		}
		
		this.minSpeedSecondGear = minSpeedSecondGear;
		this.maxSpeed = maxSpeed;
		this.description = description;
		currentGear = 1;
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
	public String getDescription() {
		return description;
	}

	@Override
	public int getCurrentSpeed() {
		return currentSpeed;
	}

	@Override
	public int getMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public String adaptSpeed(Section nextSection) {
		String result = "";
		// First gear and next section is faster... Gear up. 
		if (currentGear == 1 & nextSection.getTheoreticalMaxSpeed()>maxSpeedFirstGear) {
			currentGear = 2;
			int nextSpeed = minSpeedSecondGear;
			result += "El número " + number + " mete segunda y se pone a " + nextSpeed + " desde " + currentSpeed + ". ";
			currentSpeed = nextSpeed;
		} else if (currentGear == 2 & nextSection.getTheoreticalMaxSpeed()<=minSpeedSecondGear) {
			currentGear = 1;
			int nextSpeed = nextSection.getTheoreticalMaxSpeed();
			result += "El número " + number + " reduce a primera y se pone a " + nextSpeed + ". ";
			currentSpeed = nextSpeed;
		} else {
			if (nextSection.getTheoreticalMaxSpeed() > currentSpeed) {
				int nextSpeed =  Math.min(maxSpeed, nextSection.getTheoreticalMaxSpeed());;
				result += "El número " + number + " acelera desde " + currentSpeed + " a " + nextSpeed + ". ";
				currentSpeed = nextSpeed;
				if (maxSpeed == currentSpeed) {
					result += driver + " aprieta a fondo, pero su " + description + " está al límite!";
				}
			} else {
				currentSpeed = nextSection.getTheoreticalMaxSpeed();
				result += "El número " + number + " reduce la velocidad a " + currentSpeed;
			}
		}
		
		return result;
	}

}
