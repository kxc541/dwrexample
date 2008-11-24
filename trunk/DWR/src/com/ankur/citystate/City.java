package com.ankur.citystate;

/**
 * City DTO.
 */
public class City {

	/** The id. */
	public long id;

	/** The name. */
	public String name;

	/** The acronym. */
	public String acronym;

	/**
	 * Creates a new instance of City.
	 */
	public City() {
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

}
