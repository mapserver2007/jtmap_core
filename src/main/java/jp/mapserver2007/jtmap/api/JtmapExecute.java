package jp.mapserver2007.jtmap.api;

import jp.mapserver2007.jtmap.db.JtmapExecuteDBAccessor;

/**
 * <pre>
 * JTMAP APIを実行するクラス。
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public class JtmapExecute extends JtmapAbstractExecute {

	/**
	 * APIキーが指定された場合、文字列またはURLから住所・経緯度を取得する
	 * @param url_or_string_with_address
	 * @param apikey
	 * @return
	 */
	@Override
	public String extract(String url_or_string_with_address, String apikey) {
		return apikey != null && apikey.equals(JtmapExecuteDBAccessor.getDBAccessor().getApiKey())
			? extract(url_or_string_with_address) : "{error: 'authorization failed'}";
	}

	/**
	 * APIキーが指定された場合、文字列またはURLから住所・経緯度を取得する
	 * コールバック関数が指定された場合、JSONP形式で返す
	 * @param url_or_string_with_address
	 * @param apikey
	 * @param callback
	 * @return
	 */
	@Override
	public String extract(String url_or_string_with_address, String apikey, String callback) {
		String content_jsonp = extract(url_or_string_with_address, apikey);
		if (callback != null) { content_jsonp = escape(callback) + "(" + content_jsonp + ");"; }
		return content_jsonp;
	}

}
