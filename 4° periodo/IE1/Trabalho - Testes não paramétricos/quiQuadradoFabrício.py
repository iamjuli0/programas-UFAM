from scipy.stats import mannwhitneyu

homens = [4, 4, 5, 5, 3, 3, 2, 1, 4, 5, 2]
mulheres = [4, 5, 5, 5, 4, 3, 3, 5, 2, 1, 4]

estatistica, p_valor = mannwhitneyu(mulheres, homens)

print("Estatística do teste de Mann-Whitney:", estatistica)
print("Significância:", p_valor)

if p_valor < 0.05:
  print("Há uma diferença significativa entre as avaliações entre homens e mulheres.")
else:
  print("Tanto homens quanto mulheres possuem avaliações semelhantes")
