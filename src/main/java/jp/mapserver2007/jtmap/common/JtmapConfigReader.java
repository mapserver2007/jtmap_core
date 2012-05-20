package jp.mapserver2007.jtmap.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <pre>
 * 設定ファイルを読み込むクラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.1
 */
public class JtmapConfigReader {

	private static final Properties config = new Properties();
	private static final String JTMAP_CONFIG_FILENAME = "jtmap_config.properties";

	static {
		try {
			JtmapConfigReader.getConfig().load(
				JtmapConfigReader.class.getClassLoader().getResourceAsStream(JTMAP_CONFIG_FILENAME)
			);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * デフォルトコンストラクタは使用禁止
	 */
	private JtmapConfigReader() {}

	/**
	 * 設定ファイルオブジェクトのオブジェクトを返す
	 * @return
	 */
	private static Properties getConfig() {
		return config;
	}

	/**
	 * 設定内容を返す
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		return getConfig().getProperty(key);
	}
}
