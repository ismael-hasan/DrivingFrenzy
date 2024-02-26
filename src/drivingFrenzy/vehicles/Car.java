package drivingFrenzy.vehicles;

import java.util.Random;

import drivingFrenzy.race.DefaultSection;
import drivingFrenzy.race.Section;

public class Car extends DefaultVehicle {

	public final static int DRIVER_STYLE_STANDARD = 0;
	public final static int DRIVER_STYLE_CONSERVATIVE = 1;
	public final static int DRIVER_STYLE_AGGRESSIVE = 2;

	private final static double DRIVER_STYLE_MIN_MULTIPLIER = 0.8;
	private final static double DRIVER_STYLE_MAX_MULTIPLIER = 1.1;

	private int driverStyle;
	private int currentGear; // 1 to 6
	private int[] minSpeedPerGear;
	private int[] maxSpeedPerGear;

	private Random randomizer;

	/**
	 * @param number
	 * @param driver
	 * @param currentSpeed
	 * @param maxSpeed
	 * @param description
	 * @param driverStyle
	 * @param minSpeedPerGear An array with the minimum speed for each gear. These
	 *                        numbers are checked for consistency.
	 * @param maxSpeedPerGear An array with the maximum speed for each gear. These
	 *                        numbers are checked for consistency.
	 */
	public Car(int number, String driver, int currentSpeed, int maxSpeed, String description, int driverStyle,
			int[] minSpeedPerGear, int[] maxSpeedPerGear) {
		super(number, driver, currentSpeed, maxSpeed, description);
		if (driverStyle != DRIVER_STYLE_STANDARD && driverStyle != DRIVER_STYLE_CONSERVATIVE
				&& driverStyle != DRIVER_STYLE_AGGRESSIVE) {
			System.out.println("Estilo de conducción no válido. " + driver + " será clasificado como standard.");
		} else {
			this.driverStyle = driverStyle;
		}
		this.minSpeedPerGear = minSpeedPerGear;
		this.maxSpeedPerGear = maxSpeedPerGear;
		currentGear = 1;
		randomizer = new Random();
	}

	public int getDriverStyle() {
		return driverStyle;
	}

	public String adaptSpeed(Section nextSection) {
		/*
		 * This method has to: 1) figure out if it needs to accelerate or break. It must
		 * take into account the risk the driver wants. - An aggressive driver will
		 * always accelerate a bit to compensate for the randomization of speed done in
		 * DefaultVehicle.adaptSpeed, unless the variation level of the section is -1. -
		 * A conservative driver will never go over the max speed that a section may
		 * have. - A standard driver will slow down on variation level -1, speed up on
		 * 1, be neutral on 0. 2) Depending on the speed it wants to reach, it will
		 * change gears or accelerate/brake accordingly.
		 * 
		 */
		String result = "";

		int desiredSpeed = nextSection.getTheoreticalMaxSpeed();
		if (nextSection.getVariationLevel() == DefaultSection.SECTION_VARIATION_STANDARD) {
			if (driverStyle == DRIVER_STYLE_CONSERVATIVE) {
				desiredSpeed = Math
						.toIntExact(Math.round(desiredSpeed * randomizer.nextDouble(DRIVER_STYLE_MIN_MULTIPLIER, 1.0)));
			} else if (driverStyle == DRIVER_STYLE_AGGRESSIVE) {
				desiredSpeed = Math
						.toIntExact(Math.round(desiredSpeed * randomizer.nextDouble(1.0, DRIVER_STYLE_MAX_MULTIPLIER)));
			} // if standard section and driver... just try to go to the max.
		} else if (nextSection.getVariationLevel() == DefaultSection.SECTION_VARIATION_EASY) {
			if (driverStyle == DRIVER_STYLE_STANDARD || driverStyle == DRIVER_STYLE_AGGRESSIVE) {
				desiredSpeed = Math
						.toIntExact(Math.round(desiredSpeed * randomizer.nextDouble(1.0, DRIVER_STYLE_MAX_MULTIPLIER)));
			}
		} else if (nextSection.getVariationLevel() == DefaultSection.SECTION_VARIATION_DIFFICULT) {
			if (driverStyle == DRIVER_STYLE_STANDARD || driverStyle == DRIVER_STYLE_CONSERVATIVE) {
				desiredSpeed = Math
						.toIntExact(Math.round(desiredSpeed * randomizer.nextDouble(DRIVER_STYLE_MIN_MULTIPLIER, 1.0)));
			}
		}

		// Now that we have the desired speed, we gear up/down as needed.

		if (getCurrentSpeed() < desiredSpeed) {
			// we have to accelerate or gear up
			if (maxSpeedPerGear[currentGear] > desiredSpeed) {
				// Current gear can get us at the desired speed, so we just change the speed.
				result += "El número " + getNumber() + " acelera desde " + getCurrentSpeed() + " a " + desiredSpeed
						+ ". ";
				setCurrentSpeed(desiredSpeed);
			} else {
				// We have to gear up
				if (currentGear == 6) {
					// We are already in the 6th gear, and the max speed we can reach is lower than
					// the desired, so we just go to the max
					desiredSpeed = maxSpeedPerGear[5];
					result += "El número " + getNumber() + " pone el coche a tope en sexta desde " + getCurrentSpeed()
							+ " a " + desiredSpeed + ". ";
					setCurrentSpeed(desiredSpeed);
				} else {
					// We can get to a larger gear. We check to which one. Remember, gears go 1 to
					// 6, arrays go 0 to 5
					int desiredGear = currentGear;
					while (minSpeedPerGear[desiredGear - 1] < desiredSpeed && desiredGear < 6) {
						desiredGear++;
					}
					desiredGear--;
					setCurrentSpeed(minSpeedPerGear[desiredGear - 1]);
					result += "El número " + getNumber() + " pasa de la marcha " + currentGear + " a " + desiredGear
							+ " y se pone a " + getCurrentSpeed();
					currentGear = desiredGear;
				}
			}
		} else {
			// we have to slow down. Slowing down is easier, since we will slow down to the right speed and set the gear accordingly.
			// first we check which gear we should be in
			// If the car is properly set, and desired speed is always positive, at least we will find a gear that fulfills the condition.
			int desiredGear = currentGear;
			for (int checkedGear = currentGear; checkedGear>=1; checkedGear --) {
				if (minSpeedPerGear[checkedGear-1] >= desiredSpeed && maxSpeedPerGear[checkedGear-1] <= desiredSpeed) {
					desiredGear = currentGear;
					break;
				}
			}
			setCurrentSpeed(desiredSpeed);
			if (desiredGear != currentGear) {
				result += "El número " + getNumber() + " pasa de la marcha " + currentGear + " a " + desiredGear
						+ " y se pone a " + getCurrentSpeed();
				currentGear = desiredGear;
			} else {
				result += "El número " + getNumber() + " se pone a " + getCurrentSpeed();
			}	
		}
		result += super.adaptSpeed(nextSection);
		return result;
	}

}
