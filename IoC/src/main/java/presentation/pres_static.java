package presentation;

import dao.DaoImpl;
import dao.IDAO;
import metier.IMetier;
import metier.MetierImpl;

public class pres_static {
	public static void main(String[] args) {
		DaoImpl dao = new DaoImpl();
		MetierImpl metier = new MetierImpl();
		metier.setDao(dao);
		System.out.println(metier.calcul());
	}
}
