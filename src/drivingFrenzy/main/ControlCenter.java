package drivingFrenzy.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import drivingFrenzy.race.Section;
import drivingFrenzy.race.StandardIndoorSection;
import drivingFrenzy.race.StandardOutdoorSection;
import drivingFrenzy.race.Track;
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

	/**
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
	 *
	 *                     This method creates simple race with StandarIndoorSection
	 *                     sections and Scooters, with initial random stats
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

		start(track, vehicles);
	}

	/**
	 * @throws IOException
	 * 
	 *                     Creates a race with 5 sections and 3 scooters
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
		start(track, vehicles);
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
		start(track, vehicles);
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
		start(track, vehicles);
	}

	/**
	 * @throws IOException It combines indoor and outdoor.
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
		start(track, vehicles);
	}

	/**
	 * This method receives a track and a list of cars and it starts a race, showing
	 * the results in command line.
	 * 
	 * @throws IOException
	 */
	private static void start(Track track, Vehicle[] vehicles) throws IOException {
		// At the end, who won the race? We should re-order the results. TODO.

		// We will track the total time per vehicle in an array matching positions.
		// However, this should be done differently, with proper Java Objects.
		double[] times = new double[vehicles.length];
		// We will store, for each vehicle, in which section it was disqualified. Null
		// if it was not disqualified.
		Section[] disqualifiedVehicles = new Section[vehicles.length];
		Arrays.fill(disqualifiedVehicles, null);

		// NOW WE START THE RACE!!!! We have to get the times for each vehicle per
		// section, and then the total time.
		nextComment(
				"Bienvenidos a la carrera simple en línea recta indoor. Hoy tenemos algunas scooters tratando de realizar el trayecto lo más rápido posible!");
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
			// for each vehicle, we want to track its total time.
			int currentSectionPosition = 1;
			double totalTime = 0;
			nextComment("El siguiente piloto es " + vehicle.getDriver() + " con el número " + vehicle.getNumber()
					+ ". Se prepara para salir!");
			for (Section section : track.getSections()) {
				nextComment("\tEl siguiente tramo es el número " + currentSectionPosition + ", "
						+ section.getDescription() + " de " + section.getLength()
						+ " metros de longitud, con una velocidad máxima permitida de "
						+ section.getTheoreticalMaxSpeed() + "km/h");
				// The driver modifies the speed based on the section about to enter
				String action = vehicle.adaptSpeed(section);

				double secondsThisSection = section.getLength() / (vehicle.getCurrentSpeed() * 1d / 1000 * 3600);
				nextComment("\t" + action);
				if (vehicle.getCurrentSpeed() <= section.getCurrentMaxSpeed()) {
					nextComment("\tPasa la sección en " + secondsThisSection + " segundos.");
					totalTime += secondsThisSection;
					nextComment("\tSu tiempo total tras el tramo " + currentSectionPosition + " es de "
							+ timeTo2Decimals(totalTime) + " segundos");
					currentSectionPosition++;
				} else {
					// too fast!
					nextComment("\t¡Se ha pasado de velocidad y se sale de la pista! " + vehicle.getDriver()
							+ " queda descalificado...");
					disqualifiedVehicles[i] = section;
					break;
				}

			}
			if (disqualifiedVehicles[i] == null) {
				nextComment(
						"\tFinaliza el recorrido! Su tiempo total es de " + timeTo2Decimals(totalTime) + " segundos.");
				times[i] = totalTime;
			} else {
				times[i] = Integer.MAX_VALUE;
			}
		}

		// MODIFY THIS to show the results sorted by total time.
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
			double currentShorterTime = Double.MAX_VALUE; // A trick is to initialize it to its max value, so we know
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
						+ timeTo2Decimals(nextVehicleTime) + " segundos.");
			} else {
				nextComment("\t" + nextVehicle.getDriver() + " con el número " + nextVehicle.getNumber()
				+ " quedó descalificado en " + disqualifiedVehicles[currentShorterTimePosition].getDescription());
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
	}

	private static String timeTo2Decimals(double time) {
		return 0.01 * Math.round(time * 100) + "";
	}

	public static void main(String[] args) throws IOException {
		System.out.println(USAGE);
		// simpleRandomRace(50, 100, 2, 5, 40, 150, 500, 2000, 70, 150);
		// defaultRace();
		// kartsRace();
		kartsAndScootersRace();
//		kartsAndScootersMixedRace();
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
