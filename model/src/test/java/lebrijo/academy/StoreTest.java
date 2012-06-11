package lebrijo.academy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import lebrijo.academy.model.Course;
import lebrijo.academy.model.Level;
import lebrijo.academy.model.Professor;
import lebrijo.academy.store.Finder;
import lebrijo.academy.store.Porter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
@Transactional
public class StoreTest {
	private static final Log LOGGER = LogFactory.getLog(ModelTest.class);
	
	@Autowired
	private Finder finder;
	@Autowired
	private Porter porter;

	@Before
	public void loadDDBB() {
		Professor juanan = new Professor("Juanan");
		porter.persist(juanan);
		Professor shang = new Professor("Shang");
		porter.persist(shang);
		Course java = new Course(true, juanan, "Java Basic", 25, Level.Basic);
		porter.persist(java);
		Course jsf = new Course(true, shang, "Advanced JSF", 25, Level.Advanced);
		porter.persist(jsf);
	}

	@Test
	public void testing_CDI_and_Transaction() {
		List<Professor> list = finder.findAllProfessors();
		for (Professor profe : list) {
			LOGGER.info(profe.getId() + ": " + profe.getName());
		}
		assertThat(list.size(), is(6));
	}

	@Test
	public void deleting_entities() throws Exception {
		Course c = (Course) porter.findById(Course.class, Long.valueOf(1));
		porter.remove(Course.class, c.getId());
		List<Course> list = finder.findAllCourses();
		assertThat(list.size(), is(21));
	}
}
