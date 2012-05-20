package jp.mapserver2007.jtmap.db;

import java.sql.SQLException;
import net.java.ao.Query;
import jp.mapserver2007.jtmap.common.JtmapConfigReader;
import jp.mapserver2007.jtmap.db.schema.Apikey;
import jp.mapserver2007.jtmap.db.schema.Apimaster;

/**
 * <pre>
 * データベース問い合わせ処理を実行するクラス
 * </pre>
 * @author mapserver2007
 * @version 0.0.3
 */
public class JtmapExecuteDBAccessor extends JtmapDBAccessor {

	private static final JtmapExecuteDBAccessor dbaccessor = new JtmapExecuteDBAccessor();

	/**
	 * デフォルトコンストラクタは使用禁止
	 */
	private JtmapExecuteDBAccessor() {}

	/**
	 * DBアクセス用のインスタンスを返す
	 * @return
	 */
	public static JtmapExecuteDBAccessor getDBAccessor() {
		return dbaccessor;
	}

	/**
	 * APIキーを取得する
	 * @throws SQLException
	 */
	public String getApiKey() {
		Apikey[] apikey = null;
		try {
			apikey = getEntityManager().find(Apikey.class,
				Query.select("apikey.id, apikey.apikey")
					.join(Apimaster.class, "apikey.api_id = apimaster.id")
					.where("name = ?", JtmapConfigReader.getConfig("jtmap.application.name"))
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apikey.length > 0 ? apikey[0].getApikey() : "";
	}
}
