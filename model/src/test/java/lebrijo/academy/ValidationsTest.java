package lebrijo.academy;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lebrijo.academy.model.Course;
import lebrijo.academy.model.Level;
import lebrijo.academy.store.Porter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
@Transactional
public class ValidationsTest {
	@Autowired
	private Porter porter;
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void title_should_be_not_null() {
		Course java = new Course(true, null, "", 25, Level.Basic);
		porter.persist(java);
		Set<ConstraintViolation<Course>> constraintViolations = validator
				.validate(java);
		assertEquals(1, constraintViolations.size());
		assertEquals("Title is mandatory", constraintViolations.iterator()
				.next().getMessage());
	}

}
