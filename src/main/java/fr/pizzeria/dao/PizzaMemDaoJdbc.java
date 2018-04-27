package fr.pizzeria.dao;

import java.sql.Statement;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaMemDaoJdbc implements IPizzaDaoJdbc{

	@Override
	public List<Pizza> findAllPizzas(Statement s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewPizza(Pizza pizza, Statement s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza, Statement s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePizza(String codePizza, Statement s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pizza findPizzaByCode(String codePizza, Statement s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza, Statement s) {
		// TODO Auto-generated method stub
		return false;
	}

}
