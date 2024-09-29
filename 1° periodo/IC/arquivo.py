def take(k,xs):
	return [x for x in xs][:k]
def drop(k,xs):
	return [x for x in xs][k:]
def head(xs):
	return xs[0]
def tail(xs):
	return [x for x in xs][1:]
def last(xs):
	return xs[len(xs)-1]
def init(xs):
	return [x for x in xs][:len(xs)-1]

nfs = [(22250349, 8.5, 80), (22250350, 9, 40), (22250351, 4.9, 80), (22250352, 3.5, 50)]

def rfinal(nfs):
	def aprovados(nfs):
        	return [x for x in nfs if x[1] >= 5 and x[2] >= 75]
	def reprovadosN(nfs):
        	return [x for x in nfs if x[1] < 5]
	def reprovadosF(nfs):
        	return [x for x in nfs if x[2] < 75]
	def reprovadosNF(nfs):
		return [x for x in nfs if x[1] < 5 and x[2] < 75]
	return ['Aprovados'] + aprovados(nfs), ['Reprovados por Nota'] + reprovadosN(nfs), ['Reprovados por Falta'] + reprovadosF(nfs), ['Reprovados por Nota e Falta'] + reprovadosNF(nfs)