import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        String url = "jdbc:sqlite:database/meteo.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Sei connesso al database!");
    }

    public static Database getInstance() throws SQLException { // Singleton del database
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void insert(Meteo meteo) {
        try {
            if (!connection.isValid(5)) // Massimo 5 secondi per connettersi
                return;
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database");
            return;
        }

        String query = "INSERT INTO meteo(temperature, windspeed, winddirection, time) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setDouble(1, meteo.current_weather.temperature); // Si usa il metodo set + tipo di variabile per inserire
            stmt.setDouble(2, meteo.current_weather.windspeed);
            stmt.setInt(3, meteo.current_weather.winddirection);
            stmt.setString(4, meteo.current_weather.time);
            int rows = stmt.executeUpdate(); // Per vedere subito se la query ha apportato modifiche
            System.out.println("Query eseguita! Righe inserite: " + rows);
        } catch (SQLException e) {
            System.err.println("Errore nella query...");
        }
    }
}