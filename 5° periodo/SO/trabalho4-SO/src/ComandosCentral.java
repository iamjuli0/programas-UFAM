import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ComandosCentral {
    private File diretorioAtual;

    public ComandosCentral() {
        this.diretorioAtual = new File(".").getAbsoluteFile();
        Logger.log("Inicializando shell no diretório: " + this.diretorioAtual.getAbsolutePath());
    }

    public void centralComandos(String entrada) {
        Logger.log("Comando recebido: " + entrada);
        
        boolean background = entrada.endsWith("&");
        if (background) {
            entrada = entrada.substring(0, entrada.length() - 1).trim();
        }
        
        String[] comandos = entrada.split("\\|");
        InputStream entradaAnterior = null;

        for (int i = 0; i < comandos.length; i++) {
            String comandoAtual = comandos[i].trim();
            entradaAnterior = executeCommand(comandoAtual, background, entradaAnterior, i == comandos.length - 1);
        }
    }
    
    private InputStream executeCommand(String comando, boolean background, InputStream entradaAnterior, boolean isLastCommand) {
        String[] partes = comando.split("\\s+");
        String nomeComando = partes[0];
        String[] args = Arrays.copyOfRange(partes, 1, partes.length);

        // Verificar qual comando está sendo executado
        switch (nomeComando) {
            case "ls":
                listDirectory();
                break;
            case "cat":
                if (args.length > 0) {
                    printFileContent(args[0]);
                } else {
                    System.out.println("Use: cat <nomeArquivo>");
                }
                break;
            case "pwd":
                printWorkingDirectory();
                break;
            case "cd":
                if (args.length > 0) {
                    changeDirectory(args[0]);
                } else {
                    System.out.println("Use: cd <diretório>");
                }
                break;
            case "cp":
                if (args.length == 2) {
                    copyFile(args[0], args[1]);
                } else {
                    System.out.println("Use: cp <recurso> <destino>");
                }
                break;
            case "mv":
                if (args.length == 2) {
                    moveFile(args[0], args[1]);
                } else {
                    System.out.println("Use: mv <recurso> <destino>");
                }
                break;
            case "rm":
                if (args.length > 0) {
                    removeFile(args[0]);
                } else {
                    System.out.println("Use: rm <nomeArquivo>");
                }
                break;
            case "echo":
                echoCommand(args);
                break;
            case "sleep":
                if (args.length > 0) {
                    sleepCommand(args[0], background);
                } else {
                    System.out.println("Use: sleep <segundos>");
                }
                break;
            case "grep":
                if (args.length == 2) {
                    grepCommand(args[0], args[1]);
                } else {
                    System.out.println("Use: grep <termoBusca> <nomeArquivo>");
                }
                break;
            case "exit":
                exitShell();
                break;
            default:
                return executeExternalCommand(comando, background, entradaAnterior, isLastCommand);
        }
        return null;
    }
    
    private InputStream executeExternalCommand(String comando, boolean background, InputStream entradaAnterior, boolean isLastCommand) {
        PipedOutputStream pipeSaida = new PipedOutputStream();
        PipedInputStream pipeEntrada;
        try {
            pipeEntrada = new PipedInputStream(pipeSaida);
        } catch (IOException e) {
            System.out.println("Erro ao criar pipe");
            Logger.log("Erro ao criar pipe");
            return null;
        }

        Runnable tarefa = () -> {
            try {
                ProcessBuilder construtor = new ProcessBuilder();
                construtor.directory(diretorioAtual);
                if (entradaAnterior != null) {
                    construtor.redirectInput();
                }
                
                construtor.redirectOutput(ProcessBuilder.Redirect.PIPE);
                construtor.redirectError(ProcessBuilder.Redirect.PIPE);
                construtor.command("sh", "-c", comando);

                Process processo = construtor.start();
                
                if (!isLastCommand) {
                    new Thread(() -> {
                        try (InputStream in = processo.getInputStream();
                             OutputStream out = pipeSaida) {
                            byte[] buffer = new byte[1024];
                            int tamanho;
                            while ((tamanho = in.read(buffer)) != -1) {
                                out.write(buffer, 0, tamanho);
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao redirecionar saída do comando: " + comando);
                            Logger.log("Erro ao redirecionar saída do comando: " + comando);
                        }
                    }).start();
                }

                if (!background) {
                    processo.waitFor();
                }
                Logger.log("Comando executado: " + comando);
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao executar o comando: " + comando);
                Logger.log("Erro ao executar o comando: " + comando);
            }
        };

        if (background) {
            new Thread(tarefa).start();
            System.out.println("Comando iniciado em background: " + comando);
            Logger.log("Comando iniciado em background: " + comando);
        } else {
            tarefa.run();
        }

        return pipeEntrada;
    }

    private void listDirectory() {
        File[] files = diretorioAtual.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
            Logger.log("Listagem do diretório: " + diretorioAtual.getAbsolutePath());
        } else {
            System.out.println("Erro ao listar o diretório.");
            Logger.log("Erro ao listar o diretório: " + diretorioAtual.getAbsolutePath());
        }
    }

    private void printFileContent(String nomeArquivo) {
        try {
            System.out.println("----------");
            Files.lines(Paths.get(resolvePath(nomeArquivo))).forEach(System.out::println);
            System.out.println("----------");
            Logger.log("Conteúdo do arquivo " + nomeArquivo + " exibido.");
        } catch (IOException e) {
            System.out.println("Erro de leitura do arquivo: " + nomeArquivo);
            Logger.log("Erro de leitura do arquivo: " + nomeArquivo);
        }
    }

    private void printWorkingDirectory() {
        try {
            System.out.println(diretorioAtual.getCanonicalPath());
            Logger.log("Diretório atual: " + diretorioAtual.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("Erro de retorno do caminho do diretório atual");
            Logger.log("Erro de retorno do caminho do diretório atual: " + e.getMessage());
        }
    }

    private void changeDirectory(String diretorio) {
        File novoDiretorio = new File(resolvePath(diretorio));
        if (novoDiretorio.exists() && novoDiretorio.isDirectory()) {
            try {
                diretorioAtual = novoDiretorio.getCanonicalFile();
                System.out.println("Diretório alterado para: " + diretorioAtual.getCanonicalPath());
                Logger.log("Diretório alterado para: " + diretorioAtual.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("Erro ao trocar o diretório para: " + diretorio);
                Logger.log("Erro ao trocar o diretório para: " + diretorio);
            }
        } else {
            System.out.println("Diretório não encontrado: " + diretorio);
            Logger.log("Diretório não encontrado: " + diretorio);
        }
    }

    private void copyFile(String recurso, String destino) {
        try {
            Files.copy(Paths.get(resolvePath(recurso)), Paths.get(resolvePath(destino)), StandardCopyOption.REPLACE_EXISTING);
            Logger.log("Arquivo copiado de " + recurso + " para " + destino);
        } catch (IOException e) {
            System.out.println("Erro ao copiar o arquivo: " + recurso);
            Logger.log("Erro ao copiar o arquivo: " + recurso + " para " + destino);
        }
    }

    private void moveFile(String source, String destination) {
        copyFile(source, destination);
        removeFile(source);
        Logger.log("Arquivo movido de " + source + " para " + destination);
    }

    private void removeFile(String nomeArquivo) {
        try {
            Files.delete(Paths.get(resolvePath(nomeArquivo)));
            Logger.log("Arquivo removido: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao remover o arquivo: " + nomeArquivo);
            Logger.log("Erro ao remover o arquivo: " + nomeArquivo);
        }
    }
    
    private void echoCommand(String[] args) {
        String mensagem = String.join(" ", args);
        System.out.println(mensagem);
        Logger.log("Echo: " + mensagem);
    }
    
    private void sleepCommand(String segundos, boolean background) {
        Runnable tarefa = () -> {
            try {
                int duracao = Integer.parseInt(segundos);
                Logger.log("Iniciando sleep por " + duracao + " segundos");
                Thread.sleep(duracao * 1000);
                Logger.log("Sleep finalizado após " + duracao + " segundos");
            } catch (NumberFormatException e) {
                System.out.println("Argumento inválido para sleep: " + segundos);
                Logger.log("Erro: argumento inválido para sleep: " + segundos);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrompido");
                Logger.log("Erro: sleep interrompido");
            }
        };

        if (background) {
            new Thread(tarefa).start();
            System.out.println("Sleep iniciado em background por " + segundos + " segundos");
            Logger.log("Sleep iniciado em background por " + segundos + " segundos");
        } else {
            tarefa.run();
        }
    }
    
    private void grepCommand(String termoBusca, String nomeArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(resolvePath(nomeArquivo)))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.contains(termoBusca)) {
                    System.out.println(linha);
                }
            }
            Logger.log("Grep executado em " + nomeArquivo + " com termo " + termoBusca);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + nomeArquivo);
            Logger.log("Erro ao ler o arquivo: " + nomeArquivo);
        }
    }

    private void exitShell() {
        System.out.println("Shell terminado");
        Logger.log("Shell terminado");
        System.exit(0);
    }


    private String resolvePath(String caminho) {
        if (caminho.startsWith("~")) {
            return System.getProperty("user.home") + caminho.substring(1);
        }
        if (Paths.get(caminho).isAbsolute()) {
            return caminho;
        }
        return Paths.get(diretorioAtual.getPath(), caminho).toString();
    }
}
