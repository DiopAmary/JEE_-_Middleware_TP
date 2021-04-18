package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl implements IDAO {

	@Override
	public double getData() {
		double data = 5;
		return data;
	}
}
