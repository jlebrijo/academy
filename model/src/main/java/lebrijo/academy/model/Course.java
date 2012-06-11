package lebrijo.academy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Course implements Serializable {
	private static final long serialVersionUID = 144391029345293166L;
	
	@Id
	@GeneratedValue
	private long id;
	private boolean active;
	@ManyToOne
	private Professor professor;
	@NotEmpty(message="Title is mandatory")
	@Length(max=50)
	private String tittle;
	@Min(5)
	@Max(75)
	private int hours;
	@Enumerated(EnumType.STRING)
	private Level level;
	@OneToOne(mappedBy="course",cascade=CascadeType.ALL)
	private Contents contents;

	public Course() {
	}

	public Course(boolean active, Professor professor, String tittle,
			int hours, Level level) {
		super();
		this.active = active;
		this.professor = professor;
		this.tittle = tittle;
		this.hours = hours;
		this.level = level;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Contents getContents() {
		return contents;
	}

	public void setContents(Contents contents) {
		this.contents = contents;
	}

}
