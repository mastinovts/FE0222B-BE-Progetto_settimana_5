package servizi;

import java.sql.Date;
import java.util.Scanner;

import dati.Auto;
import dati.Infrazione;
import servizi.dao.AutoDAO;
import servizi.dao.InfrazioneDAO;

public class Main {

	public static void main(String[] args) {
		multeApp();

	}

	public static void multeApp() {
		System.out.println(
				"\nApp polizia avviata!\nPremi 1 per inserire dati auto." + "\nPremi 2 per inserire dati infrazione."
						+ "\nPremi 3 per visualizzare tutte le auto" + "\nPremi 4 per cercare le auto dalla targa."
						+ "\nPremi 5 per visualizzare dati infrazioni e auto da targa."
						+ "\nPremi 6 per eliminare infrazione." + "\n#######################################");

		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		switch (x) {
		case 1:
			System.out.println("Inserisci Targa: ");
			scanner.nextLine();
			String targa = scanner.nextLine();
			System.out.println("Inserisci Marca: ");
			String marca = scanner.nextLine();
			System.out.println("Inserisci Modello: ");
			String modello = scanner.nextLine();
			Auto auto = new Auto(targa, marca, modello);
			AutoDAO autoDAO = new AutoDAO();
			autoDAO.inserisciAuto(auto);
			System.out.println("AUTO AGGIUNTA CON SUCCESSO");
			break;

		case 2:
			System.out.println("Inserisci Data nel formato yyyy-mm-dd non dimenticando i trattini: ");
			scanner.nextLine();
			String stringa = scanner.next();
			Date data = Date.valueOf(stringa);
			scanner.nextLine();
			System.out.println("Inserisci Tipo: ");
			String tipo = scanner.nextLine();
			System.out.println("Inserisci Importo: ");
			Double importo = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Inserisci id_auto: ");
			int id_auto = scanner.nextInt();
			Infrazione infrazione = new Infrazione(data, tipo, importo, id_auto);
			InfrazioneDAO infrazioneDAO = new InfrazioneDAO();
			infrazioneDAO.inserisciInfrazione(infrazione);
			System.out.println("INFRAZIONE AGGIUNTA CON SUCCESSO!");
			break;

		case 3:
			AutoDAO autocreata1 = new AutoDAO();
			autocreata1.getAllAuto();
			System.out.println(autocreata1);
			break;
			
		case 4:
			System.out.println("INSERISCI TARGA DA RICERCARE NEL DB: ");
			scanner.nextLine();
			String targaRicercata = scanner.nextLine();
			AutoDAO autoDAOricercata = new AutoDAO();
			Auto autoRicerca = autoDAOricercata.cercaAuto(targaRicercata);
			System.out.println("INFORMAZIONI AUTO:\nTarga: " + autoRicerca.getTarga() + "\nMarca: "
					+ autoRicerca.getMarca() + "\nModello: " + autoRicerca.getModello());
			break;
			
		case 5:
			System.out.println("Inserisci la targa dell'auto");
			String targaInfrazioni = scanner.nextLine();
			InfrazioneDAO infrazioneDAO2 = new InfrazioneDAO();
			infrazioneDAO2.stampaDatiInfrazioneAutoDaTarga(targaInfrazioni);
			
			break;
		case 6:
			System.out.println("INSERISCI ID INFRAZIONE DA ELIMINARE: ");
			int infrazioneDelete = scanner.nextInt();
			InfrazioneDAO infrazioneDAO1 = new InfrazioneDAO();
			infrazioneDAO1.eliminaInfrazione(infrazioneDelete);
			break;
		default:
			System.out.println("Errore, necessario premere un numero da 1 a 6");
			break;
		}

	}
}
