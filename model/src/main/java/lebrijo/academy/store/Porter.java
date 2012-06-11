package lebrijo.academy.store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Porter {

	@PersistenceContext
	private transient EntityManager em;

	public void persist(Object o) {
		em.persist(o);
	}

	public void modify(Object o) {
		em.merge(o);
	}

	public <T> void remove(Class<T> classType, Object idDetached) {
		Object managed = findById(classType, idDetached);
		em.remove(managed);
	}

	public <T> Object findById(Class<T> classType, Object id) {
		return em.find(classType, id);
	}
}
