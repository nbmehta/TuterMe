package ae.tutorme.utils;

import java.util.HashMap;

public class Helpers {
	public static HashMap<String, String> returnSingleMessage(String msg) {
		HashMap<String, String> map = new HashMap<>();
		map.put("message", msg);
		return map;
	}
}
