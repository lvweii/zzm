package com.zzm.service;

import com.zzm.dao.IBaseDao;
import com.zzm.hd.dto.Page;
import com.zzm.hd.param.ExCond;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.support.TransactionTemplate;

public class BaseServiceImpl implements IBaseService {

	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 事物管理
	protected TransactionTemplate transaction;

	public TransactionTemplate getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionTemplate transaction) {
		this.transaction = transaction;
	}

	@Override
	public Object getEntity(Class entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.getEntity(entityClass, id);
	}

	@Override
	public List execSql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return baseDao.execSql(sql, params);
	}

	@Override
	public List queryByHQL(String hql, Object[] parameters) {
		return baseDao.queryByHQL(hql, parameters);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public Serializable save(Object entity) {
		return baseDao.save(entity);
	}

	@Override
	public int batchExcute(List entityList, String opType) {
		// TODO Auto-generated method stub
		return baseDao.batchExcute(entityList, opType);
	}

	@Override
	public List find(String hql, Object... parameters) {
		return baseDao.find(hql, parameters);
	}

	@Override
	public List findByProperty(Class entityClass, String[] propertyNames, Object[] parameter) {
		return baseDao.findByProperty(entityClass, propertyNames, parameter);

	}

	@Override
	public List find(String hql, String[] paramNames, Object[] values) {
		return baseDao.find(hql, paramNames, values);
	}

	@Override
	public Page getRecordByPage(Map<String, Object> map, int pageSize, int startIndex) {
		Page page = null;
		// map不为空时执行,为空时返回null
		if (null != map) {
			// 用hql方式执行
			if (null != map.get("hql")) {
				// 获取总数
				int totalCount = baseDao.getCount((String) map.get("hql"), (Object[]) map.get("param"));
				if (startIndex >= totalCount) {
					startIndex -= pageSize;
					if (startIndex < 0) {
						startIndex = 0;
					}
				}
				// 获取分页数据
				page = baseDao.getByPage(totalCount, (String) map.get("hql"), pageSize, startIndex,
						(Object[]) map.get("param"));
			} else if (null != map.get("sql")) {// 用sql方式执行
												// 获取总数
				int totalCount = baseDao.getCountBySql((String) map.get("sql"), (Object[]) map.get("param"));
				if (startIndex >= totalCount) {
					startIndex -= pageSize;
					if (startIndex < 0) {
						startIndex = 0;
					}
				}
				// 获取分页数据
				page = baseDao.getPageBySql(totalCount, (String) map.get("sql"), pageSize, startIndex,
						(Object[]) map.get("param"));
			} else {// 用Criterion方式执行
					// ExCond转换为Criterion
				Criterion[] criterions = baseDao.exCondToCriterion((ExCond[]) map.get("exCond"));
				// 获取总数
				int totalCount = baseDao.getCount((Class) map.get("entity"), criterions);
				if (startIndex >= totalCount) {
					startIndex -= pageSize;
					if (startIndex < 0) {
						startIndex = 0;
					}
				}
				// 获取分页数据
				page = baseDao.getByPage((Class) map.get("entity"), totalCount, criterions, pageSize, startIndex,
						(String[]) map.get("orderProperty"), (boolean[]) map.get("isAsc"));
			}
		}

		return page;
	}

}
