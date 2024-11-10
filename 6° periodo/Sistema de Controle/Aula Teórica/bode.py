import numpy as np
import matplotlib.pyplot as plt

# Definindo a faixa de frequências
frequencias = np.logspace(-1, 2, 100)  # Frequências de 0.1 a 100 rad/s

# Listas para armazenar os resultados de magnitude e fase
magnitude_dB = []
fase_graus = []

# Calcular módulo e fase para cada frequência
for omega in frequencias:
    # Numerador e denominador para G(jw)
    numerador = -omega**2 + 1
    parte_real = -4 * omega**2 + 1
    parte_imaginaria = 10 * omega - omega**3

    # Calcular módulo
    modulo = abs(numerador) / np.sqrt(parte_real**2 + parte_imaginaria**2)
    magnitude_dB.append(20 * np.log10(modulo))  # Converter módulo para dB

    # Calcular fase
    fase_numerador = 0 if numerador > 0 else 180  # Fase do numerador
    fase_denominador = np.degrees(np.arctan2(parte_imaginaria, parte_real))
    fase = fase_numerador - fase_denominador
    fase_graus.append(fase)

# Plotar o diagrama de Bode
plt.figure(figsize=(10, 8))

# Gráfico de magnitude
plt.subplot(2, 1, 1)
plt.semilogx(frequencias, magnitude_dB)  # Frequência em escala logarítmica
plt.title("Diagrama de Bode - Função de Transferência")
plt.xlabel("Frequência (rad/s)")
plt.ylabel("Magnitude (dB)")
plt.grid(which="both", axis="both")

# Gráfico de fase
plt.subplot(2, 1, 2)
plt.semilogx(frequencias, fase_graus)  # Frequência em escala logarítmica
plt.xlabel("Frequência (rad/s)")
plt.ylabel("Fase (graus)")
plt.grid(which="both", axis="both")

# Mostrar o gráfico
plt.tight_layout()
plt.show()
