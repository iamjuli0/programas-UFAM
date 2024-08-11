import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import firwin, freqz

# Parâmetros do filtro
fs = 60000  # Frequência de amostragem (Hz)
cutoff_low = 12000  # Frequência de corte baixa (Hz)
cutoff_high = 16000  # Frequência de corte alta (Hz)
numtaps = 101  # Número de coeficientes do filtro (ordem do filtro + 1)

# Projeto do filtro FIR passa-banda
fir_coeff = firwin(numtaps, [cutoff_low, cutoff_high], pass_zero=False, window='boxcar', fs=fs)

# Resposta em frequência do filtro
w, h = freqz(fir_coeff, worN=8000)

# Plotar a resposta em frequência
plt.figure(figsize=(10, 6))
plt.plot(0.5 * fs * w / np.pi, np.abs(h), 'b')
plt.axhline(1.0, color='r', linestyle='--')  # Linha de ganho unitário
plt.title('Resposta em Frequência do Filtro FIR Passa-Banda')
plt.xlabel('Frequência (Hz)')
plt.ylabel('Ganho')
plt.grid()
plt.show()
