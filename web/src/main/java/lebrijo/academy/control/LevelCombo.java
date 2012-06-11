package lebrijo.academy.control;

import java.util.ArrayList;
import java.util.List;

import lebrijo.academy.model.Level;

import org.springframework.stereotype.Component;

@Component
public class LevelCombo {
	private List<Level> list;

	public List<Level> getList() {
		if (list == null) {
			list = new ArrayList<Level>();
			for (Level level : Level.values())
				list.add(level);
		}
		return list;
	}

	public void setList(List<Level> list) {
		this.list = list;
	}

}
