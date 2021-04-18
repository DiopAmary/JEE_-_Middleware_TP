package presentation;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

import dao.IDAO;
import metier.IMetier;

public class pres_dynamique {
	public static void main(String[] args){
		try {
			Scanner scanner = new Scanner(new File("config.txt"));
			String daoClassName = scanner.next();
			Class cdaoClass = Class.forName(daoClassName);
			IDAO dao =(IDAO) cdaoClass.newInstance();
			
			String metierClassName = scanner.next();
			Class cmetietClass = Class.forName(metierClassName);
			IMetier metier = (IMetier) cmetietClass.newInstance();
			
			Method method = cmetietClass.getMethod("setDao", IDAO.class);
			method.invoke(metier, dao);
			
			System.out.println(metier.calcul());
		}catch(Exception exp){
			exp.printStackTrace();
		}
	}
}
