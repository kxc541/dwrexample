/**
 *
 */
package com.ankur.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * The Class People.
 *
 * @author ankur
 */
public class People {

	/** The people. */
    private Set<Person> people = new HashSet<Person>();


	/**
	 */
	public People() {
		super();
		people.add(new Person(1,"Ankur","Address",111F));
		people.add(new Person(2,"Ankur","Address",22.2F));
		people.add(new Person(3,"Ankur","Address",3F));
		people.add(new Person(4,"Ankur","Address",4F));
		people.add(new Person(5,"Ankur","Address",5F));
	}

	/**
	 * Gets the all people.
	 *
	 * @return the all people
	 */
	public Set<Person> getAllPeople() {
        return people;
    }

    /**
     * Sets the person.
     *
     * @param person the new person
     */
    public void setPerson(Person person) {
        if (person.getId() == -1) {
            person.setId(getNextId());
        }

        people.remove(person);
        people.add(person);
    }

    /**
     * Delete person.
     *
     * @param person the person
     */
    public void deletePerson(Person person) {
        people.remove(person);
    }

    /**
     * @return Person
     */
    public int getNextId() {
    	for (Iterator<Person> iterator = people.iterator(); iterator.hasNext();) {
    		return (iterator.next()).getId();
		}
    	return 0;
    }



}
