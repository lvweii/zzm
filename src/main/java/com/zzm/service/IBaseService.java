package com.zzm.service;

import com.zzm.hd.dto.Page;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseService {

	/**根据主键查询实体
	 * @param entityClass 实体ClassType
	 * @param id 实体主键
	 * @return 实体
	 */
	public Object getEntity(Class entityClass, Serializable id);
	public List execSql(String sql, Object... params);
	public List queryByHQL(String hql, Object[] parameters);
	void saveOrUpdate(Object entity);
	public Serializable save(Object entity);
	public int batchExcute(List entityList, String opType);
	public List find(String hql, Object... parameters);
	public List findByProperty(Class entityClass, String[] propertyNames, Object[] parameter);
	public List find(String hql, String[] paramNames, Object[] values);
	public Page getRecordByPage(Map<String, Object> map, int pageSize, int startIndex);
}
