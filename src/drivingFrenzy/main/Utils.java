package drivingFrenzy.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import drivingFrenzy.race.Section;
import drivingFrenzy.race.StandardIndoorSection;
import drivingFrenzy.race.StandardOutdoorSection;
import drivingFrenzy.race.Track;

public class Utils {

	/**
	 * It creates a track of at least 50km and random speed 40-200. On purpose we are
	 * not making it configurable and it relies on another method, but it could
	 * receive input parameters.
	 */
	public static Track createLongTrack() {
		return createCustomTrack(50000, 500, 2000, 40, 200);
	}

	/**
	 * It creates a track with the proposed characteristics
	 * @param totalLength
	 * @param minSectionLength
	 * @param maxSectionLength
	 * @param minSpeed
	 * @param maxSpeed
	 * @return
	 * 
	 */
	public static Track createCustomTrack(int totalLength, int minSectionLength, int maxSectionLength, int minSpeed,
			int maxSpeed) {
		String[] normalSectionsDescription = { "una recta de pabellón", "una curva de pabellón" };
		String[] normalOutdoorSections = { "una recta exterior", "una curva exterior" };
		String[] difficultOutdoorSections = { "una recta exterior con aceite", "una curva exterior con polvo" };
		String[] easyOutdoorSections = { "una recta exterior muy segura", "una curva exterior muy peraltada" };

		Random random = new Random(System.currentTimeMillis());
		List<Section> sections = new ArrayList<Section>();
		int totalLengthInMeters = 0;
		while (totalLengthInMeters < totalLength) {
			// we create the next section randomly.
			int typeOfSection = random.nextInt(0, 4);
			int length = random.nextInt(minSectionLength, maxSectionLength);
			int theoreticalMaxSpeed = random.nextInt(minSpeed, maxSpeed + 1);
			Section newSection;
			if (typeOfSection == 0) {
				// normal indoor
				newSection = new StandardIndoorSection(length,
						normalSectionsDescription[random.nextInt(0, normalSectionsDescription.length)],
						theoreticalMaxSpeed);
			} else if (typeOfSection == 1) {
				// normal outdoor
				newSection = new StandardOutdoorSection(length,
						normalOutdoorSections[random.nextInt(0, normalOutdoorSections.length)], theoreticalMaxSpeed,
						1.0);
			} else if (typeOfSection == 2) {
				// difficult outdoor
				newSection = new StandardOutdoorSection(length,
						difficultOutdoorSections[random.nextInt(0, difficultOutdoorSections.length)],
						theoreticalMaxSpeed, random.nextDouble(0.8, 1.0));
			} else if (typeOfSection == 3) {
				// easy outdoor
				newSection = new StandardOutdoorSection(length,
						easyOutdoorSections[random.nextInt(0, easyOutdoorSections.length)], theoreticalMaxSpeed,
						random.nextDouble(1.0, 1.1));
			} else {
				// unreachable due to the way in which typeOfSection is created
				newSection = null;
			}
			sections.add(newSection);
			totalLengthInMeters += length;
		}

		Section[] fingerprint = new Section[] {};
		Track result = new Track(sections.toArray(fingerprint));

		return result;
	}

	public static String timeTo2Decimals(double time) {
		return 0.01 * Math.round(time * 100) + "";
	}


}
