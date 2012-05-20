package jp.mapserver2007.jtmap.api;

public class JtmapStringExtractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JtmapExecute jtmap_api = new JtmapExecute();
		String json = jtmap_api.extract(
			"ここは東京都千代田区神田錦町2-2です",
			"7a110b05d6eaadd4b609431640f13c2b4a9479e90e117d2e078f2c88f6c634b5",
			"callback"
		);
		System.out.println(json);
	}
}
