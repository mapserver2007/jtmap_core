package jp.mapserver2007.jtmap.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.oro.text.perl.Perl5Util;
import net.arnx.jsonic.JSON;

/**
 * <pre>
 * JTMAPの共通処理クラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public class JtmapCommon {

	/**
	 * 出力するJSONのインタフェースを整形する
	 * @param jtmap_json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String jsonFormatter(String jtmap_json) {
		Integer id = 1;
		ArrayList<LinkedHashMap<String, String>> formatted_pojo_array = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, ArrayList<ArrayList<LinkedHashMap<String, String>>>> jtmap_pojo
			= (LinkedHashMap<String, ArrayList<ArrayList<LinkedHashMap<String, String>>>>)JSON.decode(jtmap_json);
		for (ArrayList<LinkedHashMap<String, String>> addrs : jtmap_pojo.get("result_select")) {
			for (LinkedHashMap<String, String> addr : addrs) {
				LinkedHashMap<String, String> formatted_pojo_hash = new LinkedHashMap<String, String>();
				formatted_pojo_hash.put("id", (id++).toString());
				formatted_pojo_hash.put("addr", addr.get("text"));
				formatted_pojo_hash.put("lng", addr.get("lng"));
				formatted_pojo_hash.put("lat", addr.get("lat"));
				formatted_pojo_array.add(formatted_pojo_hash);
			}
		}
		return JSON.encode(formatted_pojo_array);
	}

	protected String trim(String content_with_htmltag) {
		Perl5Util util = new Perl5Util();
		String content_without_htmltag = util.substitute("s/<.*?>|\\s//g", content_with_htmltag);
		return content_without_htmltag;
	}

	/**
	 * URLかどうかチェック
	 * @param url
	 * @return
	 */
	protected boolean isURL(String url) {
		Perl5Util util = new Perl5Util();
		boolean is_valid = util.match("/^(https?)(://[-_.!~*'()a-zA-Z0-9;/?:@&=+$,%#]+)$/", url);
		return is_valid;
	}

	/**
	 * 実体参照文字をエスケープする
	 * @param content_with_htmltag
	 * @return
	 */
	protected String escape(String content_with_htmltag) {
		return StringEscapeUtils.escapeHtml(content_with_htmltag);
	}
}
