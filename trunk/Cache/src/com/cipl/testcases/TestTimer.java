package com.cipl.testcases;


////////////////////////////////////////////////////////////////////////////////
// TestTimer: $Source$
// 
// Created : 15 apr. 2008 by ankur
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2008
////////////////////////////////////////////////////////////////////////////////


/**
 * Simple utilities class for benchmarking unit tests. Use is a follows:
 *     TestTimer t = new TestTimer("My task");
 * 	   // Do something
 *     t.done();

 * @author jfsmart
 */
public class TestTimer {

	private long startTime;
	private String message; 

	/**
	 * Initiate a timer
	 * @param message 
	 */
	public TestTimer(String message) {
		startTime = System.currentTimeMillis();
		this.message = message;
	}
	
	/**
	 * Reset the timer for another timing session.
	 *
	 */
	public void reset() {
		startTime = System.currentTimeMillis();		
	}
	
	/**
	 * End the timing session and output the results.
	 */
	public void done() {

		System.out.println(message 
						+ " : "
				   		+ (System.currentTimeMillis() - startTime) 
				   		+ " ms.");		
	}
}
