package com.ankur.citystate;

/**
 * State DTO.
 */
public class State {

	/** The id. */
	public long id;

	/** The name. */
	public String name;

	/** The acronym. */
	public String acronym;

	/** The cities. */
	public City[] cities;

	/**
	 * Creates a new instance of State.
	 */
	public State() {
	}

	// Getters
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the acronym.
	 *
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Gets the cities.
	 *
	 * @return the cities
	 */
	public City[] getCities() {
		return cities;
	}

	// Setters
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the acronym.
	 *
	 * @param acronym
	 *            the new acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * Sets the cities.
	 *
	 * @param cities
	 *            the new cities
	 */
	public void setCities(City[] cities) {
		this.cities = cities;
	}

}
