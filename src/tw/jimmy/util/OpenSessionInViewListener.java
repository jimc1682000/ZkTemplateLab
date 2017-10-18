package tw.jimmy.util;

import java.util.List;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;

public class OpenSessionInViewListener implements ExecutionInit, ExecutionCleanup {

	@Override
	public void init(Execution exec, Execution parent) {
		if (parent == null) {
			// the root execution of a servlet request
			HibernateTools.getSessionFactory().getCurrentSession().beginTransaction();
		}
	}

	private void rollback(Execution exec, Throwable ex) {
		try {
			if (HibernateTools.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
				ex.printStackTrace();
				HibernateTools.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
		} catch (Throwable rbEx) {
			ex.printStackTrace();
		}
	}

	@Override
	public void cleanup(Execution exec, Execution parent, List<Throwable> errs) throws Exception {
		if (parent == null) { // the root execution of a servlet request
			if (errs == null || errs.isEmpty()) {
				HibernateTools.getSessionFactory().getCurrentSession().getTransaction().commit();
			} else {
				final Throwable ex = (Throwable) errs.get(0);
				rollback(exec, ex);
			}
		}
	}
}
