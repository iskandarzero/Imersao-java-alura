import java.io.InputStream;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        var http = new ClienteHTTP();
        String body = http.buscaDados(url);
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        var extratorIMDB = new ExtratorIMDB();
        var conteudos = extratorIMDB.extraiConteudos(body);
        
        for (int i = 0; i < 10; i++) {
            Conteudo conteudo = conteudos.get(i);

            var nomeDoArquivo = conteudo.getTitulo() + ".png";
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
