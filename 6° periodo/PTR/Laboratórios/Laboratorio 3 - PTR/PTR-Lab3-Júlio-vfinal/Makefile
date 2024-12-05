# Compilador e flags
CC = gcc
CFLAGS = -Iinclude -Wall -Wextra -g

# Diretórios
SRCDIR = src
INCLUDEDIR = include
BUILDDIR = build
TARGET = saida

# Lista de arquivos fonte
SRC = $(wildcard $(SRCDIR)/*.c)
OBJ = $(patsubst $(SRCDIR)/%.c, $(BUILDDIR)/%.o, $(SRC))

# Regra padrão: compilar o programa
$(TARGET): $(OBJ)
	$(CC) $(OBJ) -o $@ -lm

# Regra para compilar arquivos .o a partir de .c
$(BUILDDIR)/%.o: $(SRCDIR)/%.c | $(BUILDDIR)
	$(CC) $(CFLAGS) -c $< -o $@

# Criar diretório build se não existir
$(BUILDDIR):
	mkdir -p $(BUILDDIR)

# Limpar arquivos gerados
clean:
	rm -rf $(BUILDDIR) $(TARGET) saida.txt

# Indica que diretórios não são arquivos
.PHONY: clean
