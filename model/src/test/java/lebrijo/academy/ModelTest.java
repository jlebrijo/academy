package lebrijo.academy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lebrijo.academy.model.Course;
import lebrijo.academy.model.Level;
import lebrijo.academy.model.Professor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ModelTest {
	private static final Log LOGGER = LogFactory.getLog(ModelTest.class);
	
	EntityManagerFactory emf;
	EntityManager em;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("in-memo");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		loadDDBB();
	}

	private void loadDDBB() {
		Professor juanan = new Professor("Juanan");
		em.persist(juanan);
		Professor shang = new Professor("Shang");
		em.persist(shang);
		Course java = new Course(true, juanan, "Java Basic", 25, Level.Basic);
		em.persist(java);
		Course jsf = new Course(true, shang, "Advanced JSF", 25, Level.Advanced);
		em.persist(jsf);
		// em.getTransaction().commit();
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

	@Test
	public void create_inmemo_ddbb_RW_professors() throws Exception {
		Query q = em.createQuery("from Professor");
		List<Professor> list = q.getResultList();
		for (Professor profe : list) {
			LOGGER.info(profe.getId() + ": " + profe.getName());
		}
		assertThat(list.size(), is(6));

	}

	@Test
	public void testing_relationships() {
		Query q = em.createQuery("from Course c");
		List<Course> courses = q.getResultList();
		for (Course course : courses) {
			LOGGER.info(course.getId() + ": " + course.getTittle());
		}
		assertThat(courses.size(), is(22));

		q = em.createQuery("from Course c where c.professor.name = 'Juanan'");
		Course c = (Course) q.getSingleResult();
		assertThat(c.getProfessor().getName(), is("Juanan"));
	}
	
}
