package jp.mapserver2007.jtmap.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <pre>
 * HTTP関連の処理を実行するクラス。
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public class JtmapHttpAgent {
	private HashMap<String, String> requests = new HashMap<String, String>();
	private String response;
	private String proxy_host;
	private int proxy_port;
	private static final String JTMAP_INTERNAL_ENCODING = "UTF-8";

	/**
	 * コンストラクタ
	 */
	public JtmapHttpAgent() {}

	/**
	 * 指定したURL、Methodに従ってリクエストする
	 * @param url
	 * @param method
	 * @param query
	 */
	public void send(String url, String method) {
		if (method.equals("POST")) {
			postMethod(url);
		}
		else if (method.equals("GET")) {
			getMethod(url);
		}
	}

	/**
	 * POSTリクエスト
	 * @param url
	 */
	private void postMethod(String url) {
		HttpClient httpclient = new HttpClient();
		if (getProxyHost() != null && getProxyPort() != 0) {
			httpclient.getHostConfiguration().setProxy(getProxyHost(), getProxyPort());
		}
		PostMethod httppost = new PostMethod(url);
		Map<String, String> requests = getRequestParameter();
		for (Iterator<Map.Entry<String, String>> it = requests.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, String> entry = it.next();
			httppost.addParameter(entry.getKey(), entry.getValue());
		}
		httppost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + JTMAP_INTERNAL_ENCODING);
		int statusCode;
		try {
			statusCode = httpclient.executeMethod(httppost);
			if (statusCode == 200) {
				StringBuffer strbuf = new StringBuffer();
				String str;
				InputStream response_stream = httppost.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(
					new InputStreamReader(response_stream, JTMAP_INTERNAL_ENCODING)
				);
				while ((str = br.readLine()) != null) {
					strbuf.append(str);
				}
				setResponse(String.valueOf(strbuf));
				response_stream.close();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GETリクエスト
	 * @param url
	 */
	private void getMethod(String url) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(url);
			Document document = parser.getDocument();
			NodeList nodeList = document.getElementsByTagName("body");
			Element element = (Element)nodeList.item(0);
			String renponse_content = element.getTextContent();
			setResponse(renponse_content);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * リクエストパラメータをセットする
	 * @param key
	 * @param value
	 */
	public void setRequestParameter(String key, String value) {
		this.requests.put(key, value);
	}

	/**
	 * リクエストパラメータを取得する
	 * @return
	 */
	public HashMap<String, String> getRequestParameter() {
		return requests;
	}

	/**
	 * レスポンスをセットする
	 * @param response
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * レスポンスを取得する
	 * @return
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Proxyのホスト名をセットする
	 * @param proxy_host
	 */
	public void setProxyHost(String proxy_host) {
		this.proxy_host = proxy_host;
	}

	/**
	 * Proxyのホスト名を取得する
	 * @return
	 */
	public String getProxyHost() {
		return proxy_host;
	}

	/**
	 * Proxyのポート番号をセットする
	 * @param proxy_port
	 */
	public void setProxyPort(int proxy_port) {
		this.proxy_port = proxy_port;
	}

	/**
	 * Proxyのポート番号を取得する
	 * @return
	 */
	public int getProxyPort() {
		return proxy_port;
	}

}
