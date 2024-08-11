import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class GeradorClientes {
    private static final Random random = new Random();
    private static final LinkedList<Cliente> filaClientes = new LinkedList<>();

    public static void main(String[] args) {
    	// Imprime o diretório de trabalho atual
        String currentDir = System.getProperty("user.dir");
        System.out.println("Diretório de trabalho atual: " + currentDir);
    	
        try (FileWriter writer = new FileWriter("clientes.txt")) {
            for (int i = 0; i < 1000; i++) {
                Cliente cliente = new Cliente();
                int categoria = random.nextInt(4);
                cliente.setCategoria(categoria);

                int tempoServico = 0;
                switch (categoria) {
                    case 1: // Oficial
                        tempoServico = (4000 + random.nextInt(2000)); // 4-6 segundos
                        break;
                    case 2: // Sargento
                        tempoServico = (2000 + random.nextInt(2000)); // 2-4 segundos
                        break;
                    case 3: // Cabo
                        tempoServico = (1000 + random.nextInt(2000)); // 1-3 segundos
                        break;
                    default: // Pausa
                        tempoServico = 0;
                        break;
                }
                cliente.setTempoServico(tempoServico);

                // Adiciona o cliente à fila
                filaClientes.add(cliente);
                
                String texto = String.format("%d,%d\n", cliente.getCategoria(), cliente.getTempoServico());

                // Escreve as informações do cliente no arquivo
                writer.write(texto);
            }
            System.out.println("Arquivo 'clientes.txt' gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
