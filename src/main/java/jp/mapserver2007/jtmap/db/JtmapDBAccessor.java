package jp.mapserver2007.jtmap.db;

import jp.mapserver2007.jtmap.common.JtmapConfigReader;
import net.java.ao.EntityManager;

/**
 * <pre>
 * データベース問い合わせ処理の定義するクラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.3
 */
public class JtmapDBAccessor {

	private static final EntityManager manager;

	static {
		String host     = JtmapConfigReader.getConfig("jtmap.database.host");
		String port     = JtmapConfigReader.getConfig("jtmap.database.port");
		String id       = JtmapConfigReader.getConfig("jtmap.database.id");
		String password = JtmapConfigReader.getConfig("jtmap.database.password");
		String table    = JtmapConfigReader.getConfig("jtmap.database.table");
		manager = new EntityManager("jdbc:mysql://" + host + ":" + port + "/" + table, id, password);
	}

	/**
	 * ActiveObjects利用インスタンスを返す
	 * @return
	 */
	protected static EntityManager getEntityManager() {
		return manager;
	}
}