package drivingFrenzy.race;

public interface Section {

	/**
	 * @return The length of the section in <strong>meters</strong>.
	 */
	public int getLength();

	/**
	 * @return -1 if the section is actually slower than the max speed, 0 if
	 *         standard, 1 if faster
	 */
	public int getVariationLevel();

	/**
	 * @return a description of the section.
	 */
	public String getDescription();

	/**
	 * @return The max speed for standard vehicles in <strong>km/h</strong>.
	 */
	public int getTheoreticalMaxSpeed();

	/**
	 * @return The max speed for standard vehicles in <strong>km/h</strong>. Climate
	 *         conditions may affect to it.
	 */
	public int getCurrentMaxSpeed();
}
