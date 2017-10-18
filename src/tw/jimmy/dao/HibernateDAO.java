package tw.jimmy.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

public abstract class HibernateDAO {
	public int execProcedureByNativeSql(String cmd, Map<String, Object> condMap, Session session) {
		Query query = session.createSQLQuery(cmd);
		setAllParam(condMap, query);
		return query.executeUpdate();
	}

	public List<?> queryResultSetListByNativeSql(String cmd, Map<String, Object> condMap, Session session) {
		Query query = session.createSQLQuery(cmd);
		setAllParam(condMap, query);
		return query.list();
	}

	public Object getRefreshedObject(Object obj, Session session) {
		session.refresh(obj);
		session.flush();
		return obj;
	}

	public Object getObjectWithKey(Class<?> clz, String str, Session session) {
		Object obj = session.get(clz, str);
		session.flush();
		return obj;
	}

	public Object getObjectWithKey(Class<?> clz, Long l, Session session) {
		Object obj = session.get(clz, l);
		session.flush();
		return obj;
	}

	public Object save(Object obj, Session session) {
		session.save(obj);
		session.flush();
		return obj;
	}

	public Collection<?> save(Collection<?> col, Session session) {
		for (Object obj : col) {
			save(obj, session);
		}
		return col;
	}

	public Object saveOrUpdate(Object obj, Session session) {
		session.saveOrUpdate(obj);
		session.flush();
		return obj;
	}

	public Collection<?> saveOrUpdate(Collection<?> col, Session session) {
		for (Object obj : col) {
			saveOrUpdate(obj, session);
		}
		return col;
	}

	public Object update(Object obj, Session session) {
		session.update(obj);
		session.flush();
		return obj;
	}

	public Collection<?> update(Collection<?> col, Session session) {
		for (Object obj : col) {
			update(obj, session);
		}
		return col;
	}

	public void delete(Object obj, Session session) {
		session.delete(obj);
		session.flush();
	}

	public Collection<?> delete(Collection<?> col, Session session) {
		for (Object obj : col) {
			delete(obj, session);
		}
		return col;
	}

	public List<?> queryAllList(String queryStr, Session session) {
		String cmd = "from " + queryStr; // ex: from Teacher
		Query query = session.createQuery(cmd);
		List<?> queryList = query.list();
		return queryList;
	}

	public List<?> queryAllListWithConditions(String queryStr, Map<String, Object> condMap, Session session) {
		String cmd = "from " + queryStr; // ex: from Student where grade = :grade
		Query query = session.createQuery(cmd);
		// query.setParameter("grade", "1");
		setAllParam(condMap, query);
		List<?> queryList = query.list();
		return queryList;
	}

	public List<?> queryAllListUsingNamedQuery(String queryStr, Session session) {
		return queryAllListUsingNamedQuery(queryStr, null, session);
	}

	public List<?> queryAllListUsingNamedQuery(String queryStr, Map<String, Object> condMap, Session session) {
		Query query = session.getNamedQuery(queryStr);
		setAllParam(condMap, query);
		List<?> queryList = query.list();
		return queryList;
	}

	public void setAllParam(Map<String, Object> condMap, Query query) {
		if (condMap != null) {
			for (Map.Entry<String, Object> entry : condMap.entrySet()) {
				query.setParameter((String) entry.getKey(), (Object) entry.getValue());
			}
		}
	}

	public Object getObjWithConditions(String queryStr, Map<String, Object> condMap, Session session) {
		String cmd = "from " + queryStr; // ex: from Teacher
		Query query = session.createQuery(cmd);
		setAllParam(condMap, query);
		Object queryObj = query.uniqueResult();
		return queryObj;
	}

	public Object getObjUsingNamedQuery(String queryStr, Map<String, Object> condMap, Session session) {
		Query query = session.getNamedQuery(queryStr);
		setAllParam(condMap, query);
		Object queryObj = query.uniqueResult();
		return queryObj;
	}
}
