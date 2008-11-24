package com.ankur.citystate;


/**
 * Provides the City State DAO
 * @author zoom
 */
public class CityStateDAO {

    /**
     * Creates a new instance of CityStateDAO
     */
    public CityStateDAO() {
    }

    /**
     * Provides a list of States
     * @return State Array
     */
    public State[] getStates() {
        //Creates an array of two States
        State[] states = new State[2];

        //Build Texas State Object
        states[0] = new State();
        states[0].setId(1);
        states[0].setName("Texas");
        states[0].setAcronym("TX");
        states[0].setCities(getCitiesByState(states[0].getId()));

        //Build Texas State Object
        states[1] = new State();
        states[1].setId(2);
        states[1].setName("Virginia");
        states[1].setAcronym("VA");
        states[1].setCities(getCitiesByState(states[1].getId()));

        return states;
    }

    /**
     * Provides a list of all Cities based on a State
     * @param id State Id to search by
     * @return City Array
     */
    public City[] getCitiesByState(long id) {

        City[] cities = null;

        if (id == 1) {
            //Creates an array of three Cities
            cities = new City[3];
            //Build Galveston City Object
            cities[0] = new City();
            cities[0].setId(1);
            cities[0].setName("Galveston");
            cities[0].setAcronym("GAL");

            //Build Houston City Object
            cities[1] = new City();
            cities[1].setId(2);
            cities[1].setName("Houston");
            cities[1].setAcronym("HOU");

            //Build Sugarland City Object
            cities[2] = new City();
            cities[2].setId(3);
            cities[2].setName("Sugarland");
            cities[2].setAcronym("SUGL");
        } else if (id == 2) {
            //Creates an array of three Cities
            cities = new City[3];
            //Build Falls Church City Object
            cities[0] = new City();
            cities[0].setId(1);
            cities[0].setName("Falls Church");
            cities[0].setAcronym("FC");

            //Build Arlington City Object
            cities[1] = new City();
            cities[1].setId(2);
            cities[1].setName("Arlington");
            cities[1].setAcronym("ARLING");

            //Build Stafford City Object
            cities[2] = new City();
            cities[2].setId(3);
            cities[2].setName("Stafford");
            cities[2].setAcronym("STAF");
        } else {
            //nothing
        }

        return cities;

    }

}
