#P27:
def lista_de_jogadas(xs):
    def tail(xs):
        return xs[1:]
    
    if len(xs) <= 1:
        return True
    else:
        return xs[0][1] == xs[1][0] and lista_de_jogadas(tail(xs))

#P28:
def mesa2p(formato1,formato2):
    a = (formato1[0][0] == formato2[1][0][0])
    b = (formato1[1][0] == formato2[2][0][0])
    c = (formato1[2][0] == formato2[3][0][0])
    d = (formato1[3][0] == formato2[4][0][0])
    
    return (a and b and c and d)
    
#P29:
def marca_ponto_2(formato2):
    def valor(x):
        if x[0] == x[1]:
            return 2*(x[0])
        else:
            return x[0]
    
    a = (formato2[1][0])
    b = (formato2[2][0])
    c = (formato2[3][0])
    d = (formato2[4][0])
    
    xs = [valor(a),valor(b),valor(c),valor(d)]
    
    return (xs[0]+xs[1]+xs[2]+xs[3])
    
#P30:
def faz_jogada_2(p,formato2,k):
    (a,b) = p
    
    if not (0 <= k <= 3):
        return False
    elif p[1] == formato2[k+1][0][0]:
        x = formato2[k+1]
        x.insert(0,p)
        
        return formato2
    else:
        return False
        
#P31:
def pedra_de_ponto(formato1):
    
    if len(pedras_de_ponto(formato1)) != 0:
        return pedras_de_ponto(formato1)[0]
    else:
        return []

#P32:
def pedras_de_ponto(formato1):
    def pedraspossiveis():
        return [(a,b) for a in range(7) for b in range(a,7)]
    
    a = formato1[0][0]
    b = formato1[1][0]
    c = formato1[2][0]
    d = formato1[3][0]
    
    
    p_q_encaixam = [(x,y) for (x,y) in pedraspossiveis() if ((x == a) or (y == a)) or ((x == b) or (y == b)) or ((x == c) or (y == c)) or ((x == d) or (y == d))]
    
    p_q_pontuam0 = [(x,y) for (x,y) in p_q_encaixam 
    if (x == a or y == a) and (((x+b+c+d) % 5 == 0) or ((y+b+c+d) % 5 == 0))]
    p_q_pontuam1 = [(x,y) for (x,y) in p_q_encaixam
    if (x == b or y == b) and (((a+x+c+d) % 5 == 0) or ((a+y+c+d) % 5 == 0))]
    p_q_pontuam2 = [(x,y) for (x,y) in p_q_encaixam
    if (x == c or y == c) and (((a+b+x+d) % 5 == 0) or ((a+b+y+d) % 5 == 0))]
    p_q_pontuam3 = [(x,y) for (x,y) in p_q_encaixam
    if (x == d or y == d) and (((a+b+c+x) % 5 == 0) or ((a+b+c+y) % 5 == 0))]
    
    return p_q_pontuam0 + p_q_pontuam1 + p_q_pontuam2 + p_q_pontuam3
    
#P33:
def head(xs):
	return xs[0]
def tail(xs):
	return [x for x in xs][1:]
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
def find_indexs(lado_a, mesa):
    indece = []
    for ind in range(0, len(mesa)):
        if len(mesa[ind]) != 0 and lado_a == mesa[ind][0]:
            indece.append(ind)
    return indece
def find_indexs_auxiliador(lado_a, mesa, lista_idxs):
    if len(lista_idxs) == 0:
        return []
    elif (
        len(mesa[head(lista_idxs)]) != 0
        and lado_a == mesa[head(lista_idxs)][0]
    ):
        return [head(lista_idxs)] + find_indexs_auxiliador(
            lado_a, mesa, tail(lista_idxs)
        )
    else:
        return find_indexs_auxiliador(lado_a, mesa, tail(lista_idxs))
    
def pedra_de_maior_ponto(formato1):

    p_q_encaixam = pedras_de_ponto(formato1)

    pontapedra = mapea_pedra(p_q_encaixam,formato1)

    valor1 = pontapedra[0]

    pedra, _, _= valor1

    return pedra
    
#P34:
def pedras_fora_p(formato2,p):
    
    (a,b) = p
    a = formato2[1]
    b = formato2[2]
    c = formato2[3]
    d = formato2[4]
    
    xs = []
    ys = [len(a),len(b),len(c),len(d)]
    
    for i in range(ys[0]):
        k0 = (a[i] == p)
        xs.append(k0)
        
    for i in range(ys[1]):
        k1 = (b[i] == p)
        xs.append(k1)
        
    for i in range(ys[2]):
        k2 = (c[i] == p)
        xs.append(k2)
        
    for i in range(ys[3]):
        k3 = (d[i] == p)
        xs.append(k3)
    
    zs = [x for x in xs if x == True]
    
    if len(zs) == 0:
        return True
    else:
        return False