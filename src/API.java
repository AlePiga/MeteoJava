import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private HttpClient client = null;

    public API() {
        client = HttpClient.newHttpClient(); // Inizializza il client
    }

    public Meteo getMeteo(double lat, double lon) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon + "&current_weather=true"; // Url dell'API, accetta le variabili latitudine e longitudine passate dalle classi di GeoCoding
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Tutto il codice per la GET cib HttpClient è nel documento che ha creato Costa
            Gson gson = new Gson();
            System.out.println(response.body()); // Stampa del JSON della risposta API
            return gson.fromJson(response.body(), Meteo.class); // Per la deserializzazione basta creare una classe con gli stessi parametri stabiliti dall'API

        } catch (URISyntaxException | IOException | InterruptedException e) { // Più catch messi insieme
            throw new RuntimeException(e);
        }
    }
}
