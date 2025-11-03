public class Meteo {
    public CurrentWeather current_weather;

    // Ho creato una classe interna alla classe perché l'API formattava i dati così
    public static class CurrentWeather {
        public double temperature;
        public double windspeed;
        public int winddirection;
        public String time;
    }

    public void stampa() {
        System.out.println("Temperatura: " + current_weather.temperature + " °C");
        System.out.println("Vento: " + current_weather.windspeed + " km/h");
        System.out.println("Direzione vento: " + current_weather.winddirection + "°");
        System.out.println("Aggiornato alle: " + current_weather.time);
    }
}