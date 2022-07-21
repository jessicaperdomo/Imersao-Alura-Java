import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP e buscar os top250filmes.
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient(); //Java sabe que é HttpClient - possível trocar por var
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Separar dados que queremos (Titulo, imagem, nota). Parciar os dados.
        JsonParser Parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = Parser.parse(body);
        //System.out.println(listaDeFilmes.size()); //tamanho da lista separada por filmes.
        //System.out.println(listaDeFilmes.get(0)); //Lista na posicao 0.

        //Exibir e manipular os dados.
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[34mTitulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[34mImagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[34mNota: \u001b[m" + filme.get("imDbRating"));
            System.out.println();
        }
    }
}
