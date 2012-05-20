package jp.mapserver2007.jtmap.api;

public class JtmapProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JtmapExecute jtmap_api = new JtmapExecute();
//		jtmap_api.setProxyHost("proxygate.nes.nec.co.jp");
//		jtmap_api.setProxyPort(10080);
		String json = jtmap_api.extract(
			"http://www.necsoft.com/company/access.html",
			"7a110b05d6eaadd4b609431640f13c2b4a9479e90e117d2e078f2c88f6c634b5",
			"callback"
		);
		System.out.println(json);
	}

}
