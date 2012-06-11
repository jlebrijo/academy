package lebrijo.academy.store;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lebrijo.academy.model.Course;
import lebrijo.academy.model.Professor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class Finder {

	@PersistenceContext
	private transient EntityManager em;

	public List<Professor> findAllProfessors() {
		Query q = em.createQuery("from Professor p order by p.name");
		return q.getResultList();
	}

	public List<Course> findAllCourses() {
		Query q = em.createQuery("from Course c order by c.tittle");
		return q.getResultList();
	}

}
