package jp.mapserver2007.jtmap.api;

import jp.mapserver2007.jtmap.common.JtmapCommon;
import jp.mapserver2007.jtmap.common.JtmapHttpAgent;

/**
 * <pre>
 * 住所抽出を行う抽象クラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public class JtmapAbstractExecute extends JtmapCommon implements JtmapApiInterface {

	/**
	 * 文字列またはURLから住所・経緯度を取得する
	 * @param url_or_string_with_address
	 * @return
	 */
	public final String extract(String url_or_string_with_address) {
		JtmapHttpAgent httpagent = new JtmapHttpAgent();
		String content_with_address = null;
		String content_json = "[]";

		if (url_or_string_with_address != null) {
			if (isURL(url_or_string_with_address)) {
				// 住所・地名を含む文字列を取得
				String url = url_or_string_with_address;
				httpagent.send(url, "GET");
				content_with_address = trim(httpagent.getResponse());
			}
			else {
				content_with_address = url_or_string_with_address;
			}
			// 住所・地名を抽出する
			httpagent.setRequestParameter("text", content_with_address);
			httpagent.send(EXTRACTOR_URL, "POST");
			content_json = jsonFormatter(httpagent.getResponse());
		}

		return content_json;
	}

	/**
	 * APIキーが指定された場合、文字列またはURLから住所・経緯度を取得する
	 * @param url_or_string_with_address
	 * @param apikey
	 * @return
	 */
	public String extract(String url_or_string_with_address, String apikey) {
		return extract(url_or_string_with_address);
	}

	/**
	 * APIキーが指定された場合、文字列またはURLから住所・経緯度を取得する
	 * コールバック関数が指定された場合、JSONP形式で返す
	 * @param url_or_string_with_address
	 * @param apikey
	 * @param callback
	 * @return
	 */
	public String extract(String url_or_string_with_address, String apikey, String callback) {
		return extract(url_or_string_with_address);
	}

}
