import numpy as np
import matplotlib.pyplot as plt
import control as ctrl

# Definindo a função de transferência
numerador = [1]  # Coeficientes de s + 2
denominador = [1, 2, 2, 0]  # Coeficientes de s^2 + 10s

G = ctrl.TransferFunction(numerador, denominador)

# Gerando o Root Locus
#real, imag, gain = ctrl.root_locus(G, k=None, Plot=True)
ctrl.root_locus(G)

# Personalizando o gráfico
plt.title('Root Locus de G(s) = 1 / (s(s + 2s + 2))')
plt.xlabel('Parte Real')
plt.ylabel('Parte Imaginária')
plt.grid()
plt.axhline(0, color='black', lw=0.5)
plt.axvline(0, color='black', lw=0.5)
plt.axis('equal')

# Mostrando o gráfico
plt.show()
