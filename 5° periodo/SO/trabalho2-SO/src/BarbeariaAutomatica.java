import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class BarbeariaAutomatica {
    private static final Semaphore semaphore = new Semaphore(1);
    private static final Random random = new Random();
    private static final LinkedList<Cliente> filaClientes = new LinkedList<>();
    private static final LinkedList<Cliente> filaOficiais = new LinkedList<>();
    private static final LinkedList<Cliente> filaSargentos = new LinkedList<>();
    private static final LinkedList<Cliente> filaCabos = new LinkedList<>();
    private static final int MAX_CADEIRAS = 20; // Número máximo de cadeiras
    private static volatile boolean barbeariaAberta = true;
    private static volatile boolean entradaFechada = false; // Novo indicador para fechar a entrada de clientes
    private static int count = 0;
    
    private static volatile boolean filaOficiaisOcupada = false;
    private static volatile boolean filaSargentosOcupada = false;
    private static volatile boolean filaCabosOcupada = false;

    private static final List<Relatorio> relatorios = new LinkedList<>(); // Lista de relatórios

    public static void main(String[] args) {
    	
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Caso A (Apenas Zero)");
        System.out.println("2. Caso B (Zero e Dentinho)");
        System.out.println("3. Caso C (Zero, Dentinho e Otto)");
        System.out.println("Digite o número referente ao caso desejado: ");
        int escolha = scan.nextInt();
        scan.close();
        
        // Thread para gerar clientes
        Thread geradorClientes = new Thread(() -> {
            while (barbeariaAberta && !entradaFechada) {
                try {
                    Thread.sleep(100); // Espera 1 segundo para gerar um novo cliente
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
                    cliente.setTempoChegada(System.currentTimeMillis());
                    
                    // Adiciona o cliente à fila com sincronização
                    semaphore.acquire();
                    if (!entradaFechada) {
                        filaClientes.add(cliente);
                    }
                    semaphore.release();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrompida.");
                }
            }
        });

        // Thread para o Sargento Tainha
        Thread sargentoTainha = new Thread(() -> {
            int pausaCount = 0; // Contador de clientes com categoria 0 (pausa)

            while (barbeariaAberta) {
                try {
                    // Dorme por um tempo aleatório entre 1 e 5 segundos
                    int sleepTime = (1000 + random.nextInt(4000));
                    Thread.sleep(sleepTime);

                    // Verifica e adiciona cliente à fila de cadeiras
                    semaphore.acquire();
                    if (!filaClientes.isEmpty()) {
                        Cliente cliente = filaClientes.poll();
                        if (cliente.getCategoria() != 0) {
                            adicionarBarbearia(cliente);
                            pausaCount = 0; // Reseta o contador de pausas
                        } else {
                            System.out.println("Cliente de categoria 0 (pausa). Sargento Tainha volta a dormir.");
                            pausaCount++;
                            if (pausaCount >= 3) {
                                System.out.println("Três clientes de categoria 0 seguidos. Fechando a barbearia.");
                                barbeariaAberta = false;
                                semaphore.release();
                                break;
                            }
                            semaphore.release();
                            sleepTime = (1000 + random.nextInt(5000)); // Gera um novo tempo de sono
                            Thread.sleep(sleepTime); // Dorme novamente pelo novo tempo
                            continue; // Volta ao início do loop
                        }
                    }
                    semaphore.release();

                    // Verifica se a fila está cheia e limpa a fila de clientes
                    if (filaOficiais.size() + filaSargentos.size() + filaCabos.size() >= MAX_CADEIRAS) {
                        System.out.println("Fila cheia. Descartando clientes restantes.");
                        filaClientes.clear();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread do Sargento Tainha interrompida.");
                }
            }
        });

        // Thread para o Recruto Zero
        Thread recrutaZero = new Thread(() -> {
            while (barbeariaAberta) {
                try {
                    atenderCliente("Zero", 1, 2, 3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread do Recruto Zero interrompida.");
                }
            }
        });

        // Thread para o Dentinho
        Thread dentinho = new Thread(() -> {
            while (barbeariaAberta) {
                try {
                    atenderCliente("Dentinho", 2, 1, 3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread do Dentinho interrompida.");
                }
            }
        });

        // Thread para o Otto
        Thread otto = new Thread(() -> {
            while (barbeariaAberta) {
                try {
                    atenderCliente("Otto", 3, 1, 2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread do Otto interrompida.");
                }
            }
        });

        // Thread para o Tenente Escovinha
        Thread tenenteEscovinha = new Thread(() -> {
            while (barbeariaAberta) {
                try {
                    Thread.sleep(3000); // Verifica o estado da barbearia a cada 3 segundos
                    gerarRelatorio();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread do Tenente Escovinha interrompida.");
                }
            }
        });

        geradorClientes.start();
        sargentoTainha.start();
        recrutaZero.start();
        tenenteEscovinha.start();
        
        if (escolha >= 2) {
            dentinho.start();
        }
        if (escolha == 3) {
            otto.start();
        }

        // Aguarda as threads terminarem
        try {
            geradorClientes.join();
            sargentoTainha.join();
            recrutaZero.join();
            if (escolha >= 2) {
                dentinho.join();
            }
            if (escolha == 3) {
                otto.join();
            }
            tenenteEscovinha.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrompida.");
        }
        
        // Imprime o relatório final
        imprimirRelatorioFinal();
        System.out.println("Barbearia fechada com " + count + " clientes atendidos!");
    }
    
    private static void adicionarBarbearia(Cliente cliente) {
        switch (cliente.getCategoria()) {
            case 1:
                filaOficiais.add(cliente);
                System.out.println("Cliente adicionado à fila de oficiais: Categoria " + cliente.getCategoria() + ", Tempo de Serviço " + cliente.getTempoServico() + " ms");
                break;
            case 2:
                filaSargentos.add(cliente);
                System.out.println("Cliente adicionado à fila de sargentos: Categoria " + cliente.getCategoria() + ", Tempo de Serviço " + cliente.getTempoServico() + " ms");
                break;
            case 3:
                filaCabos.add(cliente);
                System.out.println("Cliente adicionado à fila de cabos: Categoria " + cliente.getCategoria() + ", Tempo de Serviço " + cliente.getTempoServico() + " ms");
                break;
            default:
                System.out.println("Cliente de categoria desconhecida.");
                break;
        }
    }

    private static void atenderCliente(String barbeiro, int... prioridades) throws InterruptedException {
        Cliente clienteParaAtender = null;

        while (barbeariaAberta) {
            semaphore.acquire();
            boolean encontrouCliente = false;
            for (int prioridade : prioridades) {
                if (prioridade == 1 && !filaOficiais.isEmpty() && !filaOficiaisOcupada) {
                    filaOficiaisOcupada = true;
                    clienteParaAtender = filaOficiais.poll();
                    encontrouCliente = true;
                    break;
                } else if (prioridade == 2 && !filaSargentos.isEmpty() && !filaSargentosOcupada) {
                    filaSargentosOcupada = true;
                    clienteParaAtender = filaSargentos.poll();
                    encontrouCliente = true;
                    break;
                } else if (prioridade == 3 && !filaCabos.isEmpty() && !filaCabosOcupada) {
                    filaCabosOcupada = true;
                    clienteParaAtender = filaCabos.poll();
                    encontrouCliente = true;
                    break;
                }
            }
            semaphore.release();

            if (encontrouCliente && clienteParaAtender != null) {
                long tempoInicio = System.currentTimeMillis();
                System.out.println("\n---------------------" + barbeiro + " trabalhando-------------------------");
                System.out.println(barbeiro + " atendendo cliente: Categoria " + clienteParaAtender.getCategoria() + ", Tempo de Serviço " + clienteParaAtender.getTempoServico() + " ms");
                System.out.println("------------------------------------------------------------------------\n");
                Thread.sleep(clienteParaAtender.getTempoServico());
                long tempoFim = System.currentTimeMillis();
                long tempoAtendimento = tempoFim - tempoInicio;
                long tempoEspera = tempoInicio - clienteParaAtender.getTempoChegada();
                registrarAtendimento(clienteParaAtender.getCategoria(), tempoAtendimento, tempoEspera);
                System.out.println("\n---------------------" + barbeiro + " terminou----------------------------");
                System.out.println(barbeiro + " terminou de atender cliente: Categoria " + clienteParaAtender.getCategoria() + ", Tempo de Serviço " + clienteParaAtender.getTempoServico() + " ms");
                System.out.println("------------------------------------------------------------------------\n");
                count++;

                semaphore.acquire();
                if (clienteParaAtender.getCategoria() == 1) {
                    filaOficiaisOcupada = false;
                } else if (clienteParaAtender.getCategoria() == 2) {
                    filaSargentosOcupada = false;
                } else if (clienteParaAtender.getCategoria() == 3) {
                    filaCabosOcupada = false;
                }
                semaphore.release();

                clienteParaAtender = null;
            } else {
                Thread.sleep(1000); // Espera um tempo antes de verificar novamente
            }

            if (entradaFechada && filaOficiais.isEmpty() && filaSargentos.isEmpty() && filaCabos.isEmpty() && filaClientes.isEmpty()) {
                barbeariaAberta = false; // Fechar a barbearia quando todas as filas internas estiverem vazias
            }
        }
    }

    private static void gerarRelatorio() {
        int totalClientes = filaOficiais.size() + filaSargentos.size() + filaCabos.size();
        int ocupacaoOficiais = filaOficiais.size();
        int ocupacaoSargentos = filaSargentos.size();
        int ocupacaoCabos = filaCabos.size();
        int ocupacaoLivre = MAX_CADEIRAS - totalClientes;

        Relatorio relatorio = new Relatorio(
                ocupacaoOficiais,
                ocupacaoSargentos,
                ocupacaoCabos,
                ocupacaoLivre,
                calcularComprimentoMedio(filaOficiais),
                calcularComprimentoMedio(filaSargentos),
                calcularComprimentoMedio(filaCabos)
        );

        relatorios.add(relatorio);
//        System.out.println(relatorio);
    }

    private static void registrarAtendimento(int categoria, long tempoAtendimento, long tempoEspera) {
        Relatorio.atualizarAtendimento(categoria, tempoAtendimento, tempoEspera);
    }

    private static double calcularComprimentoMedio(LinkedList<Cliente> fila) {
        return fila.size();
    }

    private static void imprimirRelatorioFinal() {
        Relatorio.imprimirRelatorioFinal();
    }
}