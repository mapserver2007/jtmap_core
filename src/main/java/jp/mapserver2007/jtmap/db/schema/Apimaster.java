package jp.mapserver2007.jtmap.db.schema;

import net.java.ao.Entity;

public interface Apimaster extends Entity {
	public int getId();
	public void setId(int id);
	public String getName();
	public void setName(String name);
}
