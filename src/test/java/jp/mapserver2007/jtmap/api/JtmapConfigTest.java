package jp.mapserver2007.jtmap.api;

import jp.mapserver2007.jtmap.common.JtmapConfigReader;

public class JtmapConfigTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test = JtmapConfigReader.getConfig("jtmap.database.host");
		System.out.println(test);
	}

}
