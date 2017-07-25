package cn.maoaixi.utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;

public class PageHibernateUtils<T> implements HibernateCallback<List<T>> {
	private String hql;
	private Object[] params;
	private Integer startIndex;
	private Integer pageNum;

	/**
	 * 有参构造函数,设置分页所需各项参数
	 * @param hql
	 * @param params
	 * @param startIndex
	 * @param pageNum
	 */
	public PageHibernateUtils(String hql, Object[] params, Integer startIndex, Integer pageNum) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageNum = pageNum;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException {
		// 执行hql语句
		Query query = session.createQuery(hql);

		// 实际参数
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}

		}

		// 分页
		query.setFirstResult(startIndex);
		query.setMaxResults(pageNum);
		return query.list();
	}

}
