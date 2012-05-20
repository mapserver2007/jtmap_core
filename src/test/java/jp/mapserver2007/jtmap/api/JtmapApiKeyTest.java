package jp.mapserver2007.jtmap.api;

import jp.mapserver2007.jtmap.db.JtmapExecuteDBAccessor;

public class JtmapApiKeyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		JtmapExecute jtmap_api = new JtmapExecute();
//		String json = jtmap_api.extract(
//			"http://www.necsoft.com/company/access.html",
//			"7a110b05d6eaadd4b609431640f13c2b4a9479e90e117d2e078f2c88f6c634b5",
//			"callback"
//		);
//		System.out.println(json);

		System.out.println(JtmapExecuteDBAccessor.getDBAccessor().getApiKey());
	}
}