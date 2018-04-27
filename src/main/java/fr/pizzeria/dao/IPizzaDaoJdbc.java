package fr.pizzeria.dao;

import java.sql.Statement;
import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDaoJdbc {
	List<Pizza> findAllPizzas(Statement s);
	void saveNewPizza(Pizza pizza, Statement s);
	void updatePizza(String codePizza, Pizza pizza, Statement s);
	void deletePizza(String codePizza, Statement s);
	Pizza findPizzaByCode(String codePizza, Statement s);
	boolean pizzaExists(String codePizza, Statement s);
}
