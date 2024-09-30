import numpy as np
from scipy.stats import chi2_contingency

# Suas listas de dados
elementos = ["Água:", "Terra:", "Fogo:", "Ar:"]
lista_observada = [[7,13,9,7], [11,4,3,3]]

# Execute o teste qui-quadrado
quiquadrado, p, gl, lista_observada = chi2_contingency(lista_observada)

# Impressão de respostas
print("Estatística Qui-Quadrado:", quiquadrado)
print("Valor-p:", p)
print("Graus de Liberdade:", gl)
print("Valores Esperados:")
print("Masculino:")
for i in range(4):
  print(elementos[i], lista_observada[0][i])
print("Feminino:")
for i in range(4):
  print(elementos[i], lista_observada[1][i])
