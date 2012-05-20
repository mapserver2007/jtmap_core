package jp.mapserver2007.jtmap.api;

/**
 * <pre>
 * JTMAP APIのインタフェース。
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public interface JtmapApiInterface {
	/**
	 * 住所抽出APIのURL定義
	 */
	final String EXTRACTOR_URL = "http://api.locosticker.jp/v1/extract_place/";

	/**
	 * 内部エンコーディング定義
	 */
	final String JTMAP_INTERNAL_ENCODING = "UTF-8";

	/**
	 * 文字列またはURLから住所・経緯度を取得する
	 * @param url_or_string_with_address
	 * @param apikey
	 * @param callback
	 * @return
	 */
	String extract(String url_or_string_with_address, String apikey, String callback);
}
