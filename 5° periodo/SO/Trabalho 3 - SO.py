from collections import deque

class FIFO:
    def __init__(self, capacidade):
        self.capacidade = capacidade
        self.fila = deque()
        self.paginas = set()
        self.faltasPaginas = 0

    def enqueue(self, processo, pagina):
        if len(self.fila) >= self.capacidade:
            self.dequeue()
        self.fila.append((processo, pagina))
        self.paginas.add((processo, pagina))

    def dequeue(self):
        if self.fila:
            processo, pagina = self.fila.popleft()
            self.paginas.remove((processo, pagina))

    def entradaProcesso(self, processo, pagina):
        if (processo, pagina) == (0, 0):
            return False
        if (processo, pagina) not in self.paginas:
            self.enqueue(processo, pagina)
            self.faltasPaginas += 1
        return True

    def getFaltasPaginas(self):
        return self.faltasPaginas

class LRU:
    def __init__(self, capacidade):
        self.capacidade = capacidade
        self.fila = deque()
        self.paginas = {}
        self.faltasPaginas = 0

    def paginaAcesso(self, processo, pagina):
        if (processo, pagina) in self.paginas:
            self.fila.remove((processo, pagina))
        elif len(self.fila) >= self.capacidade:
            self.removerPagina()
        self.fila.append((processo, pagina))
        self.paginas[(processo, pagina)] = True

    def removerPagina(self):
        if self.fila:
            processo, pagina = self.fila.popleft()
            del self.paginas[(processo, pagina)]

    def entradaProcesso(self, processo, pagina):
        if (processo, pagina) == (0, 0):
            return False
        if (processo, pagina) not in self.paginas:
            self.faltasPaginas += 1
        self.paginaAcesso(processo, pagina)
        return True

    def getFaltasPaginas(self):
        return self.faltasPaginas

class SegundaChance:
    def __init__(self, capacidade):
        self.capacidade = capacidade
        self.fila = deque()
        self.paginas = {}
        self.bitsReferencia = {}
        self.faltasPaginas = 0

    def paginaAcesso(self, processo, pagina):
        if (processo, pagina) in self.paginas:
            self.bitsReferencia[(processo, pagina)] = True
        else:
            if len(self.fila) >= self.capacidade:
                self.removerPagina()
            self.fila.append((processo, pagina))
            self.paginas[(processo, pagina)] = True
            self.bitsReferencia[(processo, pagina)] = False

    def removerPagina(self):
        while self.fila:
            processo, pagina = self.fila.popleft()
            if self.bitsReferencia[(processo, pagina)]:
                self.bitsReferencia[(processo, pagina)] = False
                self.fila.append((processo, pagina))
            else:
                del self.paginas[(processo, pagina)]
                del self.bitsReferencia[(processo, pagina)]
                break

    def entradaProcesso(self, processo, pagina):
        if (processo, pagina) == (0, 0):
            return False
        if (processo, pagina) not in self.paginas:
            self.faltasPaginas += 1
        self.paginaAcesso(processo, pagina)
        return True

    def getFaltasPaginas(self):
        return self.faltasPaginas

def leituraEntrada(caminho):
    entradas = []
    try:
        with open(caminho, 'r', encoding='utf-8') as arquivo:
            data = arquivo.read().strip()
            pares = data.split(';')
            for par in pares:
                par = par.strip()  # Remove espaços em branco ao redor
                if par:  # Verifica se o par não é vazio
                    processo, pagina = map(int, par.split(','))
                    entradas.append((processo, pagina))
    except FileNotFoundError:
        print(f"Arquivo não encontrado: {caminho}")
    except ValueError as e:
        print(f"Erro ao converter valores: {e}")
    except Exception as e:
        print(f"Erro ao ler o arquivo: {e}")
    return entradas

# Solicitar caminho do arquivo ao usuário
caminho = input("Digite o caminho do arquivo: ").strip()

# Ler entradas do arquivo especificado
entradas = leituraEntrada(caminho)

# Capacidade da memória
cap = 8000

if entradas:
    fifo = FIFO(capacidade=cap)
    lru = LRU(capacidade=cap)
    segundaChance = SegundaChance(capacidade=cap)

    print("FIFO:")
    for processo, pagina in entradas:
        if not fifo.entradaProcesso(processo, pagina):
            break
    print(f"Total de faltas de páginas para FIFO: {fifo.getFaltasPaginas()}")

    print("\nLRU:")
    for processo, pagina in entradas:
        if not lru.entradaProcesso(processo, pagina):
            break
    print(f"Total de faltas de páginas para LRU: {lru.getFaltasPaginas()}")

    print("\nSegunda Chance:")
    for processo, pagina in entradas:
        if not segundaChance.entradaProcesso(processo, pagina):
            break
    print(f"Total de faltas de páginas para Segunda Chance: {segundaChance.getFaltasPaginas()}")
else:
    print("Nenhuma entrada foi processada devido a erro na leitura do arquivo.")