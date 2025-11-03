import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Scanner per leggere l'input dell'utente
        System.out.print("Inserisci una città: ");
        String citta = scanner.nextLine();

        /* IGNORARE, GeoCoding*/ Geocoding geo = new Geocoding();
        /* IGNORARE, GeoCoding*/ Coordinate coord = geo.getCoordinate(citta);
        /* IGNORARE, GeoCoding*/
        /* IGNORARE, GeoCoding*/ if (coord == null) {
        /* IGNORARE, GeoCoding*/     System.out.println("Città non trovata!");
        /* IGNORARE, GeoCoding*/     return;
        /* IGNORARE, GeoCoding*/ }

        API api = new API(); // Inizializzo l'API
        Meteo meteo = api.getMeteo(coord.latitude, coord.longitude); // Viene creato un oggetto Meteo passate latitudine e longitudine
        meteo.stampa();

        Database db = null;

        try{
            db = Database.getInstance();
            db.insert(meteo);
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database!");
            System.exit(-1);
        }
    }
}
