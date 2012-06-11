package lebrijo.academy.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lebrijo.academy.model.Contents;
import lebrijo.academy.model.Course;
import lebrijo.academy.store.Finder;
import lebrijo.academy.store.Porter;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class Catalog implements Serializable {
	private static final long serialVersionUID = -8097596356708724241L;

	@ManagedProperty(value = "#{finder}")
	private transient Finder finder;
	@ManagedProperty(value = "#{porter}")
	private transient Porter porter;
	private List<Course> courses;
	private Course editedCourse;

	@PostConstruct
	public void loadCourses() {
		setCourses(finder.findAllCourses());
	}

	public void save() {
		if (editedCourseIsNew()) {
			porter.persist(editedCourse);
			courses.add(0, editedCourse);
		} else {
			porter.modify(editedCourse);
		}
	}

	private boolean editedCourseIsNew() {
		return editedCourse.getId() == 0;
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		editedCourse.setContents(new Contents(event.getFile().getFileName(),
				event.getFile().getContents(), editedCourse));
	}

	public StreamedContent getStreamedContents() {
		InputStream stream = new ByteArrayInputStream(editedCourse
				.getContents().getFile());
		String nombreFichero = editedCourse.getContents().getFileName();
		return new DefaultStreamedContent(stream, "application/"
				+ nombreFichero.substring(nombreFichero.indexOf('.') + 1),
				nombreFichero);
	}

	public void newCourse() {
		editedCourse = new Course();
	}

	public void delete() {
		porter.remove(Course.class, editedCourse.getId());
		courses.remove(editedCourse);
		newCourse();
	}

	/* Getters & Setters */
	public void setFinder(Finder finder) {
		this.finder = finder;
	}

	public void setPorter(Porter porter) {
		this.porter = porter;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getEditedCourse() {
		return editedCourse;
	}

	public void setEditedCourse(Course editedCourse) {
		this.editedCourse = editedCourse;
	}

}
