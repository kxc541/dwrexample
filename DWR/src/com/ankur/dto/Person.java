/**
 *
 */
package com.ankur.dto;

import java.lang.reflect.Method;

/**
 * @author ankur
 *
 */
public class Person
{
    private int id;
    private String name;
    private String address;
    private float salary;
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 *
	 */
	public Person() {
		super();
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the salary
	 */
	public float getSalary() {
		return this.salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
		result = prime * result + this.id;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + Float.floatToIntBits(this.salary);
		return result;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final Person other = (Person) obj;
		if (this.address == null) {
			if (other.address != null) return false;
		}
		else if (!this.address.equals(other.address)) return false;
		if (this.id != other.id) return false;
		if (this.name == null) {
			if (other.name != null) return false;
		}
		else if (!this.name.equals(other.name)) return false;
		if (Float.floatToIntBits(this.salary) != Float.floatToIntBits(other.salary)) return false;
		return true;
	}

	/**
	 * @return String
	 */
	@Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	Method[] ms = this.getClass().getMethods();
	String mName = null;
	for (Method element : ms) {
	    mName = element.getName();
	    if (mName.startsWith("get")) {
		try {
		    buffer.append("\n\t" + mName + " = "
			    + element.invoke(this, (Object[]) null));
		} catch (Exception e) {
			/**NO Need for showing this error to user*/
		}
	    }
	}
	return buffer.toString();
    }
	/**
	 * @param id
	 * @param name
	 * @param address
	 * @param salary
	 */
	public Person(int id, String name, String address, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.salary = salary;
	}

    // Getters, setters, equals and toString omitted
}
