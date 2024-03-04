package drivingFrenzy.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import drivingFrenzy.race.Section;
import drivingFrenzy.race.StandardIndoorSection;
import drivingFrenzy.race.StandardOutdoorSection;
import drivingFrenzy.race.Track;
import drivingFrenzy.vehicles.Car;
import drivingFrenzy.vehicles.Kart;
import drivingFrenzy.vehicles.Scooter;
import drivingFrenzy.vehicles.Vehicle;

/**
 * @author ismael This is the Control Center. It is the entry point of the
 *         application, where the races are created, vehicles are added...
 */
public class ControlCenter {

	private final static String USAGE = "El programa genera un número aleatorio de vehículos y secciones de pista y realiza la carrera. \n"
			+ "El programa automáticamente para tras imprimir una línea, esperando a que el usuario pulse enter para continuar.";

	private final static int SECONDS_REPORT_LIVE_RACE = 30;

	/**
	 * It creates simple race with StandarIndoorSection sections and Scooters, with
	 * initial random stats
	 * 
	 * @param minSections
	 * @param maxSections
	 * @param minVehicles
	 * @param maxVehicles
	 * @param minVehicleSpeed
	 * @param maxVehicleSpeed
	 * @param minSectionLenght
	 * @param maxSectionLength
	 * @param minSectionSpeed
	 * @param maxSectionSpeed
	 * @throws IOException
	 * 
	 */

	private static void simpleRandomRace(int minSections, int maxSections, int minVehicles, int maxVehicles,
			int minVehicleSpeed, int maxVehicleSpeed, int minSectionLenght, int maxSectionLength, int minSectionSpeed,
			int maxSectionSpeed) throws IOException {
		// We will need a variable to return random numbers to generate the initial
		// conditions.
		Random random = new Random();

		// first, we randomly decide on the race conditions.

		int numberOfSections = random.nextInt(minSections, maxSections + 1);
		int numberOfVehicles = random.nextInt(minVehicles, maxVehicles + 1);

		// next, we create the track.
		Section[] sections = new Section[numberOfSections];

		for (int i = 0; i < numberOfSections; i++) {
			sections[i] = new StandardIndoorSection(random.nextInt(minSectionLenght, maxSectionLength + 1),
					"una recta sencilla", random.nextInt(minSectionSpeed, maxSectionSpeed + 1));
		}

		Track track = new Track(sections);

		// Next, we create some vehicles
		Vehicle[] vehicles = new Vehicle[numberOfVehicles];

		for (int i = 0; i < numberOfVehicles; i++) {
			vehicles[i] = new Scooter(i, "un conductor anónimo", 0, random.nextInt(minVehicleSpeed, maxVehicleSpeed),
					"scooter");
		}

		start(track, vehicles, false);
	}

	/**
	 * Creates a race with 5 sections and 3 scooters
	 * 
	 * @throws IOException
	 * 
	 */
	private static void defaultRace() throws IOException {
		// We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of
		// length; you can choose the max speed.
		Section[] sections = new Section[5];
		sections[0] = new StandardIndoorSection(1000, "recta inicial", 100);
		sections[1] = new StandardIndoorSection(1000, "primera curva", 50);
		sections[2] = new StandardIndoorSection(1000, "recta media", 80);
		sections[3] = new StandardIndoorSection(1000, "segunda curva muy cerrada", 50);
		sections[4] = new StandardIndoorSection(1000, "recta final", 100);
		Vehicle[] vehicles = new Vehicle[3];
		vehicles[0] = new Scooter(0, "Valentino", 0, 70, "Ariic Gemma");
		vehicles[1] = new Scooter(1, "Marc", 0, 87, "Daelim Besbi");
		vehicles[2] = new Scooter(2, "Dani", 0, 95, "Honda Forza");

		Track track = new Track(sections);
		start(track, vehicles, false);
	}

	private static void testRace() throws IOException {
		// We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of
		// length; you can choose the max speed.
		Section[] sections = new Section[5];
		sections[0] = new StandardIndoorSection(1000, "recta inicial", 100);
		sections[1] = new StandardIndoorSection(1000, "primera curva", 50);
		sections[2] = new StandardIndoorSection(1000, "recta media", 80);
		sections[3] = new StandardIndoorSection(1000, "segunda curva muy cerrada", 50);
		sections[4] = new StandardIndoorSection(1000, "recta final", 100);
		Vehicle[] vehicles = new Vehicle[1];
		vehicles[0] = new Scooter(0, "Valentino", 0, 70, "Ariic Gemma");
		
		Track track = new Track(sections);
		start(track, vehicles, true);
	}
	
	private static void kartsRace() throws IOException {
		// We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of
		// length; you can choose the max speed.
		Section[] sections = new Section[6];

		sections[0] = new StandardIndoorSection(100, "salida", 20);
		sections[1] = new StandardIndoorSection(1000, "recta inicial", 100);
		sections[2] = new StandardIndoorSection(1000, "primera curva", 50);
		sections[3] = new StandardIndoorSection(1000, "recta media", 120);
		sections[4] = new StandardIndoorSection(1000, "segunda curva muy cerrada", 30);
		sections[5] = new StandardIndoorSection(1000, "recta final", 100);
		Vehicle[] vehicles = new Vehicle[3];
		vehicles[0] = new Kart(0, "Carlos", 0, 50, 40, 100, "kart rojo");
		vehicles[1] = new Kart(1, "Carlos Jr", 0, 30, 25, 80, "kart rojo");
		vehicles[2] = new Kart(2, "Fernando", 0, 60, 55, 110, "kart rojo");

		Track track = new Track(sections);
		start(track, vehicles, false);
	}

	private static void kartsAndScootersRace() throws IOException {
		// We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of
		// length; you can choose the max speed.
		Section[] sections = new Section[6];

		sections[0] = new StandardIndoorSection(100, "salida", 20);
		sections[1] = new StandardIndoorSection(1000, "recta inicial", 100);
		sections[2] = new StandardIndoorSection(1000, "primera curva", 50);
		sections[3] = new StandardIndoorSection(1000, "recta media", 120);
		sections[4] = new StandardIndoorSection(1000, "segunda curva muy cerrada", 30);
		sections[5] = new StandardIndoorSection(1000, "recta final", 100);
		Vehicle[] vehicles = new Vehicle[6];
		vehicles[0] = new Kart(0, "Carlos", 0, 50, 40, 100, "kart rojo");
		vehicles[1] = new Kart(1, "Carlos Jr", 0, 30, 25, 80, "kart rojo");
		vehicles[2] = new Kart(2, "Fernando", 0, 60, 55, 110, "kart rojo");
		vehicles[3] = new Scooter(3, "Valentino", 0, 70, "Ariic Gemma");
		vehicles[4] = new Scooter(4, "Marc", 0, 87, "Daelim Besbi");
		vehicles[5] = new Scooter(5, "Dani", 0, 95, "Honda Forza");

		Track track = new Track(sections);
		start(track, vehicles, false);
	}

	/**
	 * It combines indoor and outdoor.
	 * 
	 * @throws IOException
	 */
	private static void kartsAndScootersMixedRace() throws IOException {
		// We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of
		// length; you can choose the max speed.
		Section[] sections = new Section[10];

		sections[0] = new StandardIndoorSection(100, "salida desde el pabellón", 20);
		sections[1] = new StandardIndoorSection(1000, "recta inicial", 100);
		sections[2] = new StandardIndoorSection(1000, "primera curva de salida al exterior", 50);
		sections[3] = new StandardOutdoorSection(1000, "recta exterior muy soleada", 120, 1.1);
		sections[4] = new StandardOutdoorSection(500, "segunda curva muy cerrada, todavía soleada", 30, 1.1);
		sections[5] = new StandardOutdoorSection(2000, "recta de vuelta al pabellón", 100, 1.1);
		sections[6] = new StandardOutdoorSection(500, "curva de entrada al pabellón, alguien ha derramado aceite", 50,
				0.8);
		sections[7] = new StandardIndoorSection(1000, "recta media", 120);
		sections[8] = new StandardIndoorSection(1000, "tercera curva muy cerrada", 30);
		sections[9] = new StandardIndoorSection(1000, "recta final", 100);
		Vehicle[] vehicles = new Vehicle[6];
		vehicles[0] = new Kart(0, "Carlos", 0, 50, 40, 100, "kart rojo");
		vehicles[1] = new Kart(1, "Carlos Jr", 0, 30, 25, 80, "kart rojo");
		vehicles[2] = new Kart(2, "Fernando", 0, 60, 55, 110, "kart rojo");
		vehicles[3] = new Scooter(3, "Valentino", 0, 70, "Ariic Gemma");
		vehicles[4] = new Scooter(4, "Marc", 0, 87, "Daelim Besbi");
		vehicles[5] = new Scooter(5, "Dani", 0, 95, "Honda Forza");

		Track track = new Track(sections);
		start(track, vehicles, false);
	}

	/**
	 * It combines indoor and outdoor.
	 * 
	 * @throws IOException
	 */
	private static void scootersLongRace() throws IOException {
		Vehicle[] vehicles = new Vehicle[3];
		vehicles[0] = new Scooter(3, "Valentino", 0, 70, "Ariic Gemma");
		vehicles[1] = new Scooter(4, "Marc", 0, 87, "Daelim Besbi");
		vehicles[2] = new Scooter(5, "Dani", 0, 95, "Honda Forza");

		Track track = Utils.createLongTrack();
		start(track, vehicles, false);
	}

	/**
	 * Helper method to create gears for the cars
	 * 
	 * @param maxSpeed
	 * @return the array of min speed per gears; null if values are not valid.
	 */
	private static int[] createMinSpeedGears(int maxSpeed, int numberOfGears) {
		Random randomizer = new Random();
		if (numberOfGears <= 1 || maxSpeed < 30) {
			return null;
		}
		int[] result = new int[numberOfGears];
		result[0] = 0;
		int speedIncreaser = maxSpeed / (numberOfGears);
		for (int i = 1; i < numberOfGears; i++) {
			int thisSpeed = result[i - 1] + speedIncreaser;
			int speedModifier = randomizer.nextInt(maxSpeed / (numberOfGears * 10));
			thisSpeed += speedModifier * (randomizer.nextBoolean() ? -1 : 1);
			result[i] = thisSpeed;
		}
		return result;
	}

	private static int[] createMaxSpeedGears(int maxSpeed, int[] minSpeedGears) {
		Random randomizer = new Random();
		if (maxSpeed < 30) {
			return null;
		}
		int numberOfGears = minSpeedGears.length;
		int[] result = new int[numberOfGears];
		result[numberOfGears - 1] = maxSpeed;
		for (int i = 0; i < numberOfGears - 1; i++) {
			int minSpeedNextGear = minSpeedGears[i + 1];
			result[i] = minSpeedNextGear + randomizer.nextInt(maxSpeed / 10);
		}
		return result;
	}

	/**
	 * It combines indoor and outdoor.
	 * 
	 * @throws IOException
	 */
	private static void carsLongRace(boolean isLive) throws IOException {
		Vehicle[] vehicles = new Vehicle[3];
		int maxSpeed = 340;
		int[] min = createMinSpeedGears(maxSpeed, 6);
		int[] max = createMaxSpeedGears(maxSpeed, min);
		vehicles[0] = new Car(0, "Verstappen", 0, maxSpeed, "Red Bull", Car.DRIVER_STYLE_AGGRESSIVE, min, max);

		maxSpeed = 333;
		min = createMinSpeedGears(maxSpeed, 6);
		max = createMaxSpeedGears(maxSpeed, min);
		vehicles[1] = new Car(0, "Hamilton", 0, maxSpeed, "Mercedes", Car.DRIVER_STYLE_STANDARD, min, max);

		maxSpeed = 337;
		min = createMinSpeedGears(maxSpeed, 6);
		max = createMaxSpeedGears(maxSpeed, min);
		vehicles[2] = new Car(0, "LeClerc", 0, maxSpeed, "Ferrari", Car.DRIVER_STYLE_CONSERVATIVE, min, max);

		Track track = Utils.createCustomTrack(50000, 1000, 5000, 70, 350);
		start(track, vehicles, isLive);
	}

	/**
	 * It will start a race for the proposed track with the proposed vehicles.
	 * <b>isLive</b> is used to decide if all drivers compete one each time, or if
	 * all driver compete at the same time.
	 * 
	 * @param track
	 * @param vehicles
	 * @param isLive
	 * @throws IOException
	 * 
	 * 
	 */
	private static void start(Track track, Vehicle[] vehicles, boolean isLive) throws IOException {

		// If we want to simulate the race running live, we can just store the speed per
		// section for each vehicle. Later, we will use those speeds. We are introducing
		// maps to do this; if they are not learned yet in the course, try to figure out
		// how to save the data to be used later. We want to store for each vehicle what
		// was its speed per section.
		Map<Vehicle, Map<Section, Integer>> vehicleToSectionToSpeed = new HashMap<>();

		// We will track the total time per vehicle in an array matching positions.
		// However, this should be done differently, with proper Java Objects.
		double[] times = new double[vehicles.length];
		// We will store, for each vehicle, in which section it was disqualified. Null
		// if it was not disqualified.
		Section[] disqualifiedVehicles = new Section[vehicles.length];
		Arrays.fill(disqualifiedVehicles, null);

		// NOW WE START THE RACE!!!! We have to get the times for each vehicle per
		// section, and then the total time.

		nextComment("Bienvenidos a la carrera de hoy.");
		nextComment("Comencemos con alguna información sobre la pista:");
		nextComment(track.getDescription());
		nextComment("Tenemos hoy " + vehicles.length + " competidores: ");
		for (Vehicle vehicle : vehicles) {
			nextComment("Con el número " + vehicle.getNumber() + " tenemos un " + vehicle.getDescription()
					+ " pilotado por " + vehicle.getDriver() + ". Este vehículo alcanza una velocidad máxima de "
					+ vehicle.getMaxSpeed() + " km/h");
		}
		nextComment("Comienza la carrera!");

		for (int i = 0; i < vehicles.length; i++) {

			Vehicle vehicle = vehicles[i];
			Map<Section, Integer> sectionToSpeed = new HashMap<Section, Integer>();
			vehicleToSectionToSpeed.put(vehicle, sectionToSpeed);
			// for each vehicle, we want to track its total time.
			int currentSectionPosition = 1;
			double totalTime = 0;
//			if (!isLive) 
				nextComment("El siguiente piloto es " + vehicle.getDriver() + " con el número " + vehicle.getNumber()
						+ ". Se prepara para salir!");
			for (Section section : track.getSections()) {
//				if (!isLive)
					nextComment("\tEl siguiente tramo es el número " + currentSectionPosition + ", "
							+ section.getDescription() + " de " + section.getLength()
							+ " metros de longitud, con una velocidad máxima permitida de "
							+ section.getTheoreticalMaxSpeed() + "km/h");
				// The driver modifies the speed based on the section about to enter
				String action = vehicle.adaptSpeed(section);

				double secondsThisSection = section.getLength() / (vehicle.getCurrentSpeed() * 1d / 1000 * 3600);
				sectionToSpeed.put(section, vehicle.getCurrentSpeed());
//				if (!isLive)
					nextComment("\t" + action);
				if (vehicle.getCurrentSpeed() <= section.getCurrentMaxSpeed()) {
//					if (!isLive)
						nextComment("\tPasa la sección en " + secondsThisSection + " segundos.");
					totalTime += secondsThisSection;
//					if (!isLive)
						nextComment("\tSu tiempo total tras el tramo " + currentSectionPosition + " es de "
								+ Utils.timeTo2Decimals(totalTime) + " segundos");
					currentSectionPosition++;
				} else {
					// too fast!
//					if (!isLive)
						nextComment("\t¡Se ha pasado de velocidad y se sale de la pista! " + vehicle.getDriver()
								+ " queda descalificado...");
					disqualifiedVehicles[i] = section;
					break;
				}

			}
			if (disqualifiedVehicles[i] == null) {
				if (!isLive)
					nextComment("\tFinaliza el recorrido! Su tiempo total es de " + Utils.timeTo2Decimals(totalTime)
							+ " segundos.");
				times[i] = totalTime;
			} else {
				times[i] = Integer.MAX_VALUE;
			}
		}

		// MODIFY THIS to show the results sorted by total time.
		if (!isLive) {
			nextComment("Y acaba la carrera! La clasificación es: ");
			// We have to sort both arrays at the same time. We will not use any complex
			// algorithms, but instead, we will iterate the array of times, get the current
			// minimum, print it. As a trick to avoid repeated elements, we will have a
			// third array of "already processed positions"
			boolean[] alreadyProcessedTime = new boolean[vehicles.length];
			for (int vehiclesLoop = 0; vehiclesLoop < vehicles.length; vehiclesLoop++) {
				alreadyProcessedTime[vehiclesLoop] = false;
			}
			// We do an external loop to select the current minimum time as many times as
			// vehicles. Current position will also be the classification.
			for (int currentPosition = 0; currentPosition < vehicles.length; currentPosition++) {
				// In this loop, we iterate through the times we still have to process.
				double currentShorterTime = Double.MAX_VALUE; // A trick is to initialize it to its max value, so we
																// know
																// any time in the array of times will be smaller
				int currentShorterTimePosition = -1;
				for (int vehiclesLoop = 0; vehiclesLoop < vehicles.length; vehiclesLoop++) {
					if (alreadyProcessedTime[vehiclesLoop]) {
						// it means we already processed the time of this vehicle, so we ignore it (do
						// nothing).
					} else {
						if (times[vehiclesLoop] < currentShorterTime) {
							// the time of this vehicle is the smaller so far
							currentShorterTime = times[vehiclesLoop];
							currentShorterTimePosition = vehiclesLoop;
						}
					}
				}
				// Now we know the position of the next car. Disqualified cars are at the end
				// with the max time, and we need to process them differently. Also, we have to
				// store that we
				// already processed it.
				Vehicle nextVehicle = vehicles[currentShorterTimePosition];
				Double nextVehicleTime = times[currentShorterTimePosition];
				alreadyProcessedTime[currentShorterTimePosition] = true;
				if (disqualifiedVehicles[currentShorterTimePosition] == null) {
					nextComment("\t" + nextVehicle.getDriver() + " con el número " + nextVehicle.getNumber()
							+ " queda en posición " + (currentPosition + 1) + " con un tiempo de "
							+ Utils.timeTo2Decimals(nextVehicleTime) + " segundos.");
				} else {
					nextComment("\t" + nextVehicle.getDriver() + " con el número " + nextVehicle.getNumber()
							+ " quedó descalificado en "
							+ disqualifiedVehicles[currentShorterTimePosition].getDescription());
				}
			}
			// Note that we have better ways to sort this. The following code would
			// automatically sort it. By default, TreeMap sorts all its entries according to
			// their natural ordering
			/*
			 * TreeMap<Double, Vehicle> positions = new TreeMap<>(); for (int i = 0; i <
			 * vehicles.length; i++) { positions.put(times[i], vehicles[i]); } for
			 * (Entry<Double, Vehicle> currentVehicle: positions.entrySet()) {
			 * nextComment("\t" + currentVehicle.getValue().getDriver() + " con el número "
			 * + currentVehicle.getValue().getNumber() + " tiene un tiempo de " +
			 * timeTo2Decimals(currentVehicle.getKey()) + " segundos."); }
			 */
		} else {
			// we simulate a real race. We will check how many meters each car makes each 10
			// seconds to speak about what is happening in the race. To do this, we will be
			// using Lists instead of arrays, since they are dynamic and it is easier. In
			// any case, this could be done by getting the total time for each car and
			// compute how many times we have to report the time at the desired intervals.

			// This map will have, for each vehicle, the list of times at the desired
			// interval. If the car goes off-road y adds a -1 to the list to indicate it.
			Map<Vehicle, List<Integer>> vehicleToMetersPerInterval = new HashMap<>();

			for (int i = 0; i < vehicles.length; i++) {
				Vehicle currentVehicle = vehicles[i];
				List<Integer> metersEachIntervalSeconds = new ArrayList<Integer>();
				vehicleToMetersPerInterval.put(currentVehicle, metersEachIntervalSeconds);
				int totalMeters = 0;
				double totalTime = 0;
				for (Section section : track.getSections()) {
					if (disqualifiedVehicles[i] == section) {
						metersEachIntervalSeconds.add(-1);
						break;
					}
					int speedThisSection = vehicleToSectionToSpeed.get(currentVehicle).get(section);
					double timeThisSection = (section.getLength() * 1d / (speedThisSection * 1000)) * 3600;

					int currentMeters = totalMeters;
					double currentTime = totalTime;
					double nextIntervalSecondsTime = Math.ceil(totalTime / SECONDS_REPORT_LIVE_RACE) * SECONDS_REPORT_LIVE_RACE;
					while (nextIntervalSecondsTime < totalTime + timeThisSection) {
						currentMeters += ((nextIntervalSecondsTime - currentTime) / 3600) * speedThisSection * 1000;
						currentTime = nextIntervalSecondsTime;
						nextIntervalSecondsTime += SECONDS_REPORT_LIVE_RACE;
						metersEachIntervalSeconds.add(currentMeters);
					}
					totalMeters += section.getLength();
					totalTime += timeThisSection;
				}
			}
			System.out.println();

		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(USAGE);
		// simpleRandomRace(50, 100, 2, 5, 40, 150, 500, 2000, 70, 150);
//		 defaultRace();
		testRace();
//		 kartsRace();
//		kartsAndScootersRace();
//		kartsAndScootersMixedRace();
//		scootersLongRace();
//		carsLongRace(true);
	}

	/*
	 * This method is just used as a convenience method to print a comment and await
	 * for the user to click enter to go to the next comment.
	 */
	private static void nextComment(String comment) throws IOException {
		System.out.println(comment);
//		System.in.read();
	}
}
