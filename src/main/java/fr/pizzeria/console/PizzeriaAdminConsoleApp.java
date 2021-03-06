package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDaoJdbc;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {

		/* Initialisation du TDB de pizzas */
		PizzaMemDaoJdbc dao = new PizzaMemDaoJdbc();

		/* Initialisation du scanner */
		Scanner scanner = new Scanner(System.in);
		int choix = 0;

		/* Structure de r�p�tition de la boucle principale */
		do {
			println("***** Pizzeria Administration *****");
			println("1. Lister les pizzas ");
			println("2. Ajouter une nouvelle pizza ");
			println("3. Mettre à jour une pizza ");
			println("4. Supprimer une pizza ");
			println("5. Initialiser la base de données ");
			println("6. Commit ");
			println("99. Sortir ");

			System.out.print("Veuillez choisir une option:");
			choix = scanner.nextInt();

			MenuService service = MenuServiceFactory.getInstance(choix);
			service.executeUC(scanner, dao);

		} while (choix != 99);

		scanner.close();
		dao.close();
	}

	private static void println(String msg) {
		System.out.println(msg);
	}
}
