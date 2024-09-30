import scipy.stats as stats

temporada = ['2002/2003', '2003/2004', '2004/2005', '2005/2006', '2006/2007', '2007/2008', '2008/2009', '2009/2010', '2010/2011', 
             '2011/2012', '2012/2013', '2013/2014', '2014/2015', '2015/2016', '2016/2017', '2017/2018', '2018/2019', '2019/2020', 
             '2020/2021', '2021/2022', '2022/2023', '2023/2024']

gols = [5, 6, 9, 12, 23, 42, 26, 33, 53, 60, 56, 51, 61, 51, 43, 44, 28, 37, 36, 24, 17, 50]

media_gols = sum(gols) / len(gols)

gols_esperados = [media_gols] * len(temporadas)
print("Quantidade esperada de gols de Cristiano na temporada:", gols_esperados[0])
chi2, p = stats.chisquare(gols, gols_esperados)

print(f"chi2: {chi2}")
print(f"p-value: {p}")
