package fr.pizzeria.services;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDaoJdbc;

public class CommitService extends MenuService{
	
	private static final Logger LOG = LoggerFactory.getLogger(CommitService.class);

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) {
		LOG.debug("Pas de commit");
	}
	
	public void executeUC(Scanner scanner, PizzaMemDaoJdbc dao) {
		LOG.debug("Commit executeUC");
		dao.commit();
	}

}
