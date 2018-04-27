package fr.pizzeria.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.model.Pizza;

public class PizzaMemDaoJdbcTest {
	
	PizzaMemDaoJdbc p;
	
	@Before
	public void setUp()
	{
		this.p = new PizzaMemDaoJdbc();
	}
	
	@Test
	public void testFindAllPizzas()
	{
		assertTrue(p.findAllPizzas().size() == 8);
	}
	
	@Test
	public void testSaveNewPizza()
	{
		int oldlength = p.findAllPizzas().size();
		Pizza pi = new Pizza("TES", "Test", 11.11);
		p.saveNewPizza(pi);
		
		assertTrue(p.findAllPizzas().size() == oldlength+1);
	}
	
	@Test
	public void testDeletePizza()
	{
		int oldlength = p.findAllPizzas().size();
		p.deletePizza("PEP");
		
		assertTrue(p.findAllPizzas().size() == oldlength-1);
	}
	
	@Test
	public void testUpdatePizza()
	{
		Pizza pi = new Pizza("TEST", "Test update", 12.12);
		p.updatePizza("TES", pi);
		
		int lastIndex = p.findAllPizzas().size() - 1;
		
		assertEquals("TEST", p.findAllPizzas().get(lastIndex).getCode());
		assertEquals("Test update", p.findAllPizzas().get(lastIndex).getLibelle());
		assertTrue(p.findAllPizzas().get(lastIndex).getPrix() == 12.12);
	}
	
	@Test
	public void testFindPizzaByCode()
	{
		assertTrue(p.findPizzaByCode("MAR") instanceof Pizza);
	}
	
	@Test
	public void testPizzaExists()
	{
		assertTrue(p.pizzaExists("MAR") == true);
	}
	
	@After
	public void tearDown()
	{
		this.p.close();
		this.p=null;
	}
}
