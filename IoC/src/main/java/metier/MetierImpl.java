package metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.DaoImpl;

@Component
public class MetierImpl implements IMetier {
	
	@Autowired
	private DaoImpl dao;
	
	@Override
	public double calcul() {
		double number  = dao.getData();
		return number * 2;
	}

	public void setDao(DaoImpl dao) {
		this.dao = dao;
	}	
}
