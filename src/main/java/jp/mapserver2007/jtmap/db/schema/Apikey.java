package jp.mapserver2007.jtmap.db.schema;

import java.sql.Date;
import net.java.ao.Entity;

public interface Apikey extends Entity {
	public int getId();
	public String getDomain();
	public Date getDate();
	public String getApikey();
	public int getApiId();

	public void setId(int id);
	public void setDomain(String domain);
	public void setDate(Date date);
	public void setApikey(String apikey);
	public void setApiId(int api_id);
}

