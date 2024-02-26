package drivingFrenzy.race;

/**
 * @author ismael This type of section is affected by climate or some
 *         effects.
 */
public class StandardOutdoorSection extends DefaultSection {

	private double speedMultiplier;

	public StandardOutdoorSection(int length, String description, int theoreticalMaxSpeed, double speedMultiplier) {
		super(length, description, theoreticalMaxSpeed);
		this.speedMultiplier = speedMultiplier;
	}

	@Override
	public int getCurrentMaxSpeed() {
		return Math.toIntExact(Math.round(getTheoreticalMaxSpeed() * speedMultiplier));
	}
	
	@Override
	public int getVariationLevel() {
		if (speedMultiplier<1) {
			return DefaultSection.SECTION_VARIATION_DIFFICULT;
		} else if (speedMultiplier>1) {
			return DefaultSection.SECTION_VARIATION_EASY;
		} else {
			return DefaultSection.SECTION_VARIATION_STANDARD;
		}
	}

}
