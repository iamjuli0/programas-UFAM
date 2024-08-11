import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LRU {
	public static void start(String diretorio) {
		
		//Encontrando arquivo .txt usando o caminho do computador
		Path caminho = Paths.get(diretorio);
		try {
			
			//Declaração de variáveis
			byte[] texto = Files.readAllBytes(caminho);
			String entrada = new String(texto);
			System.out.println(entrada);
			
		} catch(Exception erro) {
			System.out.println("Erro ao ler arquivo");
		}
	}

}
