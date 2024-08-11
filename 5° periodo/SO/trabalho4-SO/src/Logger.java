import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter formatado = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String mensagem) {
        String tempo = LocalDateTime.now().format(formatado);
        String mensagemLog = String.format("[%s] %s", tempo, mensagem);

        try (FileWriter filew = new FileWriter(LOG_FILE, true);
            BufferedWriter bufferw = new BufferedWriter(filew);
            PrintWriter saida = new PrintWriter(bufferw)) {
        	saida.println(mensagemLog);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}
