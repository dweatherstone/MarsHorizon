package uk.co.weatherstone.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

import uk.co.weatherstone.build.MarsHorizon;

public class EasyTest {

	public static void main(String[] args) {
		// MarsHorizon mh = new MarsHorizon();
		// mh.addTurns(3, 4, 2);
		// System.out.println(mh.getTurns());
		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		testMap.put(1, "Hello");
		testMap.put(2, "Goodbye");
		int key;
		try {
			key = Collections.max(testMap.keySet());
		} catch (NoSuchElementException ex) {
			key = 0;
		}
		key++;
		System.out.println(key);
	}

}
