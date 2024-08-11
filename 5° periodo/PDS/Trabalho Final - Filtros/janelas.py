import numpy as np
import matplotlib.pyplot as plt

# Definir o número de pontos
N = 51
n = np.arange(N)

# Gerar as diferentes janelas
rectangular = np.ones(N)
triangular = np.bartlett(N)
hanning = np.hanning(N)
hamming = np.hamming(N)
blackman = np.blackman(N)

# Plotar as janelas
plt.figure(figsize=(10, 8))

plt.subplot(5, 1, 1)
plt.plot(n, rectangular, label='Retangular')
plt.title('Janela Retangular')
plt.xlabel('Amostras')
plt.ylabel('Amplitude')

plt.subplot(5, 1, 2)
plt.plot(n, triangular, label='Triangular')
plt.title('Janela Triangular')
plt.xlabel('Amostras')
plt.ylabel('Amplitude')

plt.subplot(5, 1, 3)
plt.plot(n, hanning, label='Hanning')
plt.title('Janela Hanning')
plt.xlabel('Amostras')
plt.ylabel('Amplitude')

plt.subplot(5, 1, 4)
plt.plot(n, hamming, label='Hamming')
plt.title('Janela Hamming')
plt.xlabel('Amostras')
plt.ylabel('Amplitude')

plt.subplot(5, 1, 5)
plt.plot(n, blackman, label='Blackman')
plt.title('Janela Blackman')
plt.xlabel('Amostras')
plt.ylabel('Amplitude')

plt.tight_layout()
plt.show()