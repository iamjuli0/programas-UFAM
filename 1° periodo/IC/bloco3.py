#p17:
def elem(xs):
    if (len(xs) == 2) and (0 <= xs[0] <= 6) and (0 <= xs[1] <= 6) and (xs[0] == xs[1]):
        return (xs[0])
    elif (len(xs) == 1) and (0 <= xs[0] <= 6):
        return (xs[0])
    else:
        return (xs[0])
def mesap(x):
    (a,b,c,d) = x
    
    if (0 <= elem(a) <= 6) and (0 <= elem(b) <= 6) and (0 <= elem(c) <= 6) and (0 <= elem(d) <= 6):
        return True
    else:
        return False

#p18:
def carroca_m_p(x):
    (a,b,c,d) = x
    
    if (len(a) == 2) or (len(b) == 2) or (len(c) == 2) or (len(d) == 2):
        return True
    else:
        return False
        
#p19:
def pontos_marcados(x):
    (a,b,c,d) = x
    y = [k for k in x if len(k) == 2]
    z = [k for k in x if len(k) != 2]
    if carroca_m_p(x) == True:
        if len(y) == 4:
            r1 = 2*(elem(y[0]) + elem(y[1]) + elem(y[2]) + elem(y[3]))
            return r1 % 5 == 0
        elif len(y) == 3:
            r2 = 2*(elem(y[0]) + elem(y[1]) + elem(y[2])) + elem(z[0])
            return r2 % 5 == 0
        elif len(y) == 2:
            r3 = 2*(elem(y[0]) + elem(y[1])) + elem(z[0]) + elem(z[1])
            return r3 % 5 == 0
        elif len(y) == 1:
            r4 = 2*(elem(y[0])) + elem(z[0]) + elem(z[1]) + elem(z[2])
            return r4 % 5 == 0
        
    elif (elem(a) + elem(b) + elem(c) + elem(d)) % 5 == 0:
        return True
    else:
        return False
        
#p20:
def pode_jogar_p(p1,x):
    (a,b) = p1
    (c,d,e,f) = x
    if a == elem(c) or a == elem(d) or a == elem(e) or a == elem(f) :
        return True
    elif b == elem(c) or b == elem(d) or b == elem(e) or b == elem(f):
        return True
    else:
        return False
        
#p21:
def find_indexs(lado_a, mesa):
    indece = []
    for ind in range(0, len(mesa)):
        if len(mesa[ind]) != 0 and lado_a == mesa[ind][0]:
            indece.append(ind)
    return indece
    
def find_to_point(indexs, pedra, mesa):
    if len(indexs) == 0:
        return False

    return pontos_marcados(
        joga_pedra(pedra, mesa, head(indexs))
    ) or find_to_point(tail(indexs), pedra, mesa)
    
def marca_ponto_p(pedra, mesa):
    indexs = find_indexs(pedra[0], mesa) + find_indexs(pedra[1], mesa)
    return find_to_point(indexs, pedra, mesa)
    
#p22:
def pontos_marcados_v(mesa):
    if multcinco(sum_pontas(mesa)) == sum_pontas(mesa):
        return sum_pontas(mesa)
    else:
        return 0


def maior_ponto(pedra, mesa):

    indexs = find_indexs(pedra[0], mesa) + find_indexs(pedra[1], mesa)

    maior_ponto = 0
    maior_ponto_index = 0

    for inx in indexs:
        if maior_ponto <= pontos_marcados_v(joga_pedra(pedra, mesa, inx)):
            maior_ponto = pontos_marcados_v(joga_pedra(pedra, mesa, inx))
            maior_ponto_index = inx

    return maior_ponto_index

#p23:
def ponta_que_entra(pedra, ponta):
    if pedra[0] == ponta[0] and pedra[0] != pedra[1]:
        return [pedra[1]]
    elif pedra[0] == ponta[0] and pedra[0] == pedra[1]:
        return [pedra[0], pedra[1]]
    else:
        return [pedra[0]]
        
def joga_pedra(pedra, mesa, index):

    valores_anteriores = mesa[:index]
    if len(mesa) != index + 1:
        valores_posteriores = mesa[(index + 1) :]
    else:
        valores_posteriores = ()

    return (
        valores_anteriores
        + (ponta_que_entra(pedra, mesa[index]),)
        + valores_posteriores
    )

#p24:
def head(xs):
	return xs[0]
	
def tail(xs):
	return [x for x in xs][1:]

def jogap(mao_do_jogador, mesa):
    if len(mao_do_jogador) == 0:
        return False

    return len(
        find_indexs(head(mao_do_jogador)[0], mesa)
        + find_indexs(head(mao_do_jogador)[1], mesa)
    ) != 0 or jogap(tail(mao_do_jogador), mesa)

#p25:
def suma(values):
    if len(values) == 0:
        return 0

    return head(values) + suma(tail(values))

def sum_pontas(pontas):
    if len(pontas) == 0:
        return 0
    return suma(pontas[0]) + sum_pontas(pontas[1:])

def m_mais_ponto(pedra, mesa):

    indexs = find_indexs(pedra[0], mesa) + find_indexs(pedra[1], mesa)

    maior_ponto = 0
    maior_ponto_index = 0

    for inx in indexs:
        if maior_ponto <= sum_pontas(joga_pedra(pedra, mesa, inx)):
            maior_ponto = sum_pontas(joga_pedra(pedra, mesa, inx))
            maior_ponto_index = inx

    return (maior_ponto, maior_ponto_index)

def mapea_pedra(mao_do_jogador, mesa):

    mior_ponto = 0

    pedras = []

    for pedra in mao_do_jogador:
        m_ponto, m_ponto_index = m_mais_ponto(pedra, mesa)
        if m_ponto > mior_ponto:
            mior_ponto = m_ponto
            pedras = [(pedra, m_ponto, m_ponto_index)]
        elif m_ponto == mior_ponto:
            pedras.append((pedra, m_ponto, m_ponto_index))

    return pedras

def jogada(xs,x):
    return len(mapea_pedra(xs,x))
    
#p26:

def faz_jogada(mao_do_jogador, mesa):
    pedra, _, m_ponto_index = mapea_pedra(mao_do_jogador, mesa)[0]
    return joga_pedra(pedra, mesa, m_ponto_index)