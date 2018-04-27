package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDaoJdbc;

public class CommitService extends MenuService{

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		
	}
	
	public void executeUC(Scanner scanner, PizzaMemDaoJdbc dao) {
		dao.commit();
	}

}
