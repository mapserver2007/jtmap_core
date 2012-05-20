package jp.mapserver2007.jtmap.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * <pre>
 * 文字コードを推定するクラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.2
 */
public class JtmapCharsetDetect {

	/**
	 * 文字列から文字コードを推定して返す
	 * @param content
	 * @return charset
	 */
	public String detectedCharset(String content) {
		String charset = "";
		ByteArrayInputStream stream_content = new ByteArrayInputStream(content.getBytes());
		byte[] buf = new byte[4096];
		int nread;
		UniversalDetector detector = new UniversalDetector(null);
		try {
			while ((nread = stream_content.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		detector.dataEnd();
		charset = detector.getDetectedCharset();
		detector.reset();
		return charset != null ? charset : "";
	}
}
