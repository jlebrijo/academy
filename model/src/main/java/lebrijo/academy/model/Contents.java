package lebrijo.academy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contents implements Serializable {
	@Id
	@GeneratedValue
	private long id;
	private String fileName;
	private byte[] file;
	@OneToOne
	private Course course;

	public Contents() {

	}

	public Contents(String fileName, byte[] file, Course course) {
		super();
		this.fileName = fileName;
		this.file = file;
		this.course = course;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
