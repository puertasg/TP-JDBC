package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class InitialisationPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao daoBdd) {
		
		IPizzaDao daoListe = new PizzaMemDao();
		
		for (Pizza p : daoListe.findAllPizzas()) {
			daoBdd.saveNewPizza(p);
		}
	}
}