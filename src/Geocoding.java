/* IGNORARE, GeoCoding */ import com.google.gson.Gson;
/* IGNORARE, GeoCoding */ import java.io.IOException;
/* IGNORARE, GeoCoding */ import java.net.URI;
/* IGNORARE, GeoCoding */ import java.net.URISyntaxException;
/* IGNORARE, GeoCoding */ import java.net.http.HttpClient;
/* IGNORARE, GeoCoding */ import java.net.http.HttpRequest;
/* IGNORARE, GeoCoding */ import java.net.http.HttpResponse;
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */ public class Geocoding {
/* IGNORARE, GeoCoding */     private static final String BASE_URL = "https://geocoding-api.open-meteo.com/v1/search?name=";
/* IGNORARE, GeoCoding */     private HttpClient client;
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */     public Geocoding() {
/* IGNORARE, GeoCoding */         client = HttpClient.newHttpClient();
/* IGNORARE, GeoCoding */     }
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */     public Coordinate getCoordinate(String citta) {
/* IGNORARE, GeoCoding */         try {
/* IGNORARE, GeoCoding */             HttpRequest request = HttpRequest.newBuilder()
/* IGNORARE, GeoCoding */                     .GET()
/* IGNORARE, GeoCoding */                     .uri(new URI(BASE_URL + citta))
/* IGNORARE, GeoCoding */                     .build();
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */             HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
/* IGNORARE, GeoCoding */             Gson gson = new Gson();
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */             GeocodingResponse data = gson.fromJson(response.body(), GeocodingResponse.class);
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */             if (data.results == null || data.results.length == 0) {
/* IGNORARE, GeoCoding */                 System.err.println("Città non trovata");
/* IGNORARE, GeoCoding */                 return null;
/* IGNORARE, GeoCoding */             }
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */             double lat = data.results[0].latitude;
/* IGNORARE, GeoCoding */             double lon = data.results[0].longitude;
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */             return new Coordinate(lat, lon);
/* IGNORARE, GeoCoding */
/* IGNORARE, GeoCoding */         } catch (URISyntaxException | IOException | InterruptedException e) {
/* IGNORARE, GeoCoding */             throw new RuntimeException(e);
/* IGNORARE, GeoCoding */         }
/* IGNORARE, GeoCoding */     }
/* IGNORARE, GeoCoding */ }