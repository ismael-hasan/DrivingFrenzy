package drivingFrenzy.race;

public abstract class DefaultSection implements Section {
	
	public final static int SECTION_VARIATION_STANDARD = 0;
	public final static int SECTION_VARIATION_DIFFICULT = -1;
	public final static int SECTION_VARIATION_EASY = 1;
	
	private int length;
	private String description;
	private int theoreticalMaxSpeed;
	
	
	public DefaultSection(int length, String description, int theoreticalMaxSpeed) {
		super();
		this.length = length;
		this.description = description;
		this.theoreticalMaxSpeed = theoreticalMaxSpeed;
	}
	
	public int getLength() {
		return length;
	}

	public String getDescription() {
		return description;
	}

	public int getTheoreticalMaxSpeed() {
		return theoreticalMaxSpeed;
	}

	public int getVariationLevel() {
		return SECTION_VARIATION_STANDARD;
	}
	
	public int getCurrentMaxSpeed() {
		return theoreticalMaxSpeed;
	}
}
