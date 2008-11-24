/**
 *
 */
package com.ankur.pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ankur
 */
public class CarsFacade {

	static List<String> list = new ArrayList<String>();
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	static {
		list.add("Nivea");
		list.add("121212");
		list.add("1000");
		list.add("Rebbok");
		list.add("12333333");
		list.add("20000");
		map.put("brand", list);
		map.put("model", list);
		map.put("serialNo", list);


	}

	/**
	 * @param attribute
	 * @param currentPage
	 * @return List
	 */
	public Map<String, List<String>> sort(String attribute, int currentPage) {
		return map;
	}

	/**
	 * @param newPage
	 * @return List
	 */
	public Map<String, List<String>> goToPage(int newPage) {
		return map;
	}

}
