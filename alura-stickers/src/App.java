import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        for (Map<String, String> filme : listaDeFilmes) {
            var rating = Integer.parseInt(filme.get("imDbRating").substring(0, 1));
            var stars = "";

            for (int i = 0; i < rating; i++) {
                stars += "\u2B50";
            }

            System.out.println(stars);
            System.out.println("\u001b[37;1m \u001b[44;1m" + filme.get("title") + "\u001b[m");
            System.out.println(filme.get("image"));
            System.out.println();
        }
    }
}
