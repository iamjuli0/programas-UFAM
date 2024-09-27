import java.awt.Point;
import java.util.*;

/**
 * Classe de exemplo para a implementação de um Jogador para o Jogo Snake.
 * Nesta implementação, a próxima direção da cobra é escolhida aleatoriamente
 * entre as direções possíveis (que não geram colisões).
 * <p>
 * Use esta classe como base inicial para a sua solução do jogo. Basicamente
 * você precisará reimplementar o método {@code getDirecao}.
 * 
 * @author Horácio
 */

public class SnakeJogador {
    private Snake jogo;
    
    /**
     * Cria um novo jogador para o jogo passado como parâmetro.
     * @param jogo jogo snake.
     */
    public SnakeJogador(Snake jogo) {
        this.jogo = jogo;
    }

    /**
     * Executado pelo método {@link Snake#inicia()} a cada 'tick' do jogo para
     * perguntar qual a próxima direção da cobra ('C'ima, 'D'ireita, 'B'aixo,
     * 'E'squerda ou 'N'enhum).
     * 
     * @return a próxima direção da cobra.
     */
    
    private class Caminho {
        Point point;
        Caminho parent;
        int custo;
        int heuristic;

        Caminho(Point point, Caminho parent, int custo, int heuristic) {
            this.point = point;
            this.parent = parent;
            this.custo = custo;
            this.heuristic = heuristic;
        }
    }
    
    public int calculaHeuristica(Point pontoAtual) {
        Point comida = jogo.getComida();
        return Math.abs(pontoAtual.x - comida.x) + Math.abs(pontoAtual.y - comida.y);
    }

    public char getDirecao() {
        PriorityQueue<Caminho> fronteira = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic + n.custo));
        Map<Point, Caminho> explorados = new HashMap<>();
        Point cabeca = jogo.getSegmentos().getFirst();
        Caminho inicio = new Caminho(cabeca, null, 0, calculaHeuristica(cabeca));
        fronteira.add(inicio);
        explorados.put(cabeca, inicio);

        while (!fronteira.isEmpty()) {
            Caminho atual = fronteira.poll();

            if (atual.point.equals(jogo.getComida())) {
                return recuperaPrimeiroMovimento(atual);
            }

            for (Point vizinho : getVizinhos(atual.point)) {
                if (!jogo.isCelulaLivre(vizinho.x, vizinho.y) || explorados.containsKey(vizinho)) continue;
                Caminho novoNode = new Caminho(vizinho, atual, atual.custo + 1, calculaHeuristica(vizinho));
                explorados.put(vizinho, novoNode);
                fronteira.add(novoNode);
            }
        }

        return 'N';  // Nenhum movimento possível
    }



    private char recuperaPrimeiroMovimento(Caminho node) {
        Caminho atual = node;
        Caminho antigo = node.parent;

        while (antigo.parent != null) {
            atual = antigo;
            antigo = antigo.parent;
        }

        return qualDirecao(antigo.point, atual.point);
    }

    private List<Point> getVizinhos(Point point) {
        List<Point> vizinhos = new ArrayList<>();
        vizinhos.add(new Point(point.x, point.y - 1));  // Cima
        vizinhos.add(new Point(point.x + 1, point.y)); // Direita
        vizinhos.add(new Point(point.x, point.y + 1)); // Baixo
        vizinhos.add(new Point(point.x - 1, point.y)); // Esquerda
        return vizinhos;
    }

    private char qualDirecao(Point p_inicial, Point p_final) {
        if (p_final.x == p_inicial.x + 1) return 'D';
        if (p_final.x == p_inicial.x - 1) return 'E';
        if (p_final.y == p_inicial.y + 1) return 'B';
        if (p_final.y == p_inicial.y - 1) return 'C';
        return 'N';
    }

        
//        ArrayList<Character> possiveisDirecoes = new ArrayList<Character>(4);
//        Point cabeca = jogo.getSegmentos().getFirst();
//        
//        // Adiciona as possíveis direções na lista
//        if (jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C'); // Cima
//        if (jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D'); // Direita
//        if (jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B'); // Baixo
//        if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E'); // Esquerda
//        
//        // Não existe mais nenhuma direção disponível
//        if (possiveisDirecoes.size() == 0) return 'N';
//        
//        // Pega um número aleatório entre 0 e o tamanho da lista e retorna a direção
//        int posicao = new Random().nextInt(possiveisDirecoes.size());
//        return possiveisDirecoes.get(posicao);
//    }
    
}
