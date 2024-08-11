
public class Relatorio {
    private static int atendimentosOficiais = 0;
    private static int atendimentosSargentos = 0;
    private static int atendimentosCabos = 0;
    private static long tempoTotalAtendimentoOficiais = 0;
    private static long tempoTotalAtendimentoSargentos = 0;
    private static long tempoTotalAtendimentoCabos = 0;
    private static long tempoTotalEsperaOficiais = 0;
    private static long tempoTotalEsperaSargentos = 0;
    private static long tempoTotalEsperaCabos = 0;

    private final int ocupacaoOficiais;
    private final int ocupacaoSargentos;
    private final int ocupacaoCabos;
    private final int ocupacaoLivre;
    private final double comprimentoMedioOficiais;
    private final double comprimentoMedioSargentos;
    private final double comprimentoMedioCabos;

    public Relatorio(int ocupacaoOficiais, int ocupacaoSargentos, int ocupacaoCabos, int ocupacaoLivre, double comprimentoMedioOficiais, double comprimentoMedioSargentos, double comprimentoMedioCabos) {
        this.ocupacaoOficiais = ocupacaoOficiais;
        this.ocupacaoSargentos = ocupacaoSargentos;
        this.ocupacaoCabos = ocupacaoCabos;
        this.ocupacaoLivre = ocupacaoLivre;
        this.comprimentoMedioOficiais = comprimentoMedioOficiais;
        this.comprimentoMedioSargentos = comprimentoMedioSargentos;
        this.comprimentoMedioCabos = comprimentoMedioCabos;
    }

    public static void atualizarAtendimento(int categoria, long tempoAtendimento, long tempoEspera) {
        switch (categoria) {
            case 1:
                atendimentosOficiais++;
                tempoTotalAtendimentoOficiais += tempoAtendimento;
                tempoTotalEsperaOficiais += tempoEspera;
                break;
            case 2:
                atendimentosSargentos++;
                tempoTotalAtendimentoSargentos += tempoAtendimento;
                tempoTotalEsperaSargentos += tempoEspera;
                break;
            case 3:
                atendimentosCabos++;
                tempoTotalAtendimentoCabos += tempoAtendimento;
                tempoTotalEsperaCabos += tempoEspera;
                break;
        }
    }

    public static void imprimirRelatorioFinal() {
        System.out.println("Relatório Final:");
        System.out.printf("Atendimentos Oficiais: %d, Tempo Médio Atendimento: %.2f ms, Tempo Médio Espera: %.2f ms%n",
                atendimentosOficiais, 
                atendimentosOficiais == 0 ? 0 : (double) tempoTotalAtendimentoOficiais / atendimentosOficiais,
                atendimentosOficiais == 0 ? 0 : (double) tempoTotalEsperaOficiais / atendimentosOficiais);
        System.out.printf("Atendimentos Sargentos: %d, Tempo Médio Atendimento: %.2f ms, Tempo Médio Espera: %.2f ms%n",
                atendimentosSargentos,
                atendimentosSargentos == 0 ? 0 : (double) tempoTotalAtendimentoSargentos / atendimentosSargentos,
                atendimentosSargentos == 0 ? 0 : (double) tempoTotalEsperaSargentos / atendimentosSargentos);
        System.out.printf("Atendimentos Cabos: %d, Tempo Médio Atendimento: %.2f ms, Tempo Médio Espera: %.2f ms%n",
                atendimentosCabos,
                atendimentosCabos == 0 ? 0 : (double) tempoTotalAtendimentoCabos / atendimentosCabos,
                atendimentosCabos == 0 ? 0 : (double) tempoTotalEsperaCabos / atendimentosCabos);
    }

    @Override
    public String toString() {
        return String.format("Ocupação: Oficiais: %d, Sargentos: %d, Cabos: %d, Livre: %d | Comprimento Médio: Oficiais: %.2f, Sargentos: %.2f, Cabos: %.2f",
                ocupacaoOficiais, ocupacaoSargentos, ocupacaoCabos, ocupacaoLivre,
                comprimentoMedioOficiais, comprimentoMedioSargentos, comprimentoMedioCabos);
    }
}