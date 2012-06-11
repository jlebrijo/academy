package lebrijo.academy.control;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lebrijo.academy.model.Professor;
import lebrijo.academy.store.Finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorCombo implements Converter {
	@Autowired
	private Finder finder;

	private List<Professor> list;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		for (Professor p : list)
			if (p.getId() == Long.valueOf(value))
				return p;
		return new Professor();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null)
			return "";
		return String.valueOf(((Professor) value).getId());
	}

	public List<Professor> getList() {
		if (list == null)
			list = finder.findAllProfessors();
		return list;
	}

	public void setList(List<Professor> list) {
		this.list = list;
	}

}
