#p06: terminado
def pedrap(p):
    (a,b) = p
    k = (0<=a<=6) and (0<=b<=6)
    return k
    
def pontos(xs):
    return sum([a+b for (a,b) in xs if pedrap((a,b)) == True])
    
#p07: terminado
def garagem(xs):
    k = pontos(xs)
    if k % 5 == 0:
        return k
    else:
        return []

#p08: terminado
def pedra_igual_p(p1,p2):
    (a,b) = p1
    (c,d) = p2
    if (a,b) == (c,d) or (b,a) == (c,d):
        return True
    else:
        return False

#p09: terminado
def ocorre_pedra_p(p,xs):
    (a,b) = p
    ys = [x for x in xs if x == p and pedrap(p)]
    if len(ys) != 0:
        return True
    else:
        return False
    
    
#p10: terminado
def ocorre_valor_p(k,xs):
    ys = [(a,b) for (a,b) in xs if (a == k or b == k) and 0 <= k <= 6]
    if len(ys) != 0:
        return True
    else:
        return False
        
#p11: terminado
def ocorre_pedra(k,xs):
    return [(a,b) for (a,b) in xs if (a == k or b == k)]

#p12: terminado
def soma_f(x,p):
    (a,b) = p
    return (a+b) + x
    
def pedra_maior(xs):
    mp = (0, 0)
    for p in xs:
        if soma_f(0, p) > soma_f(0, mp):
            mp = p
        elif soma_f(0, p) < soma_f(0, mp):
            mp = mp
        else:
            mp = p
    return mp
    
#p13: terminado
def ocorre_valor_q(k,xs):
    return len([(a,b) for (a,b) in xs if (a == k or b == k)])
    
#p14: terminado
def ocorre_carroca_q(xs):
    return len([(a,b) for (a,b) in xs if a == b])
    
#p15: terminado
def tira_maior(xs):
    return [(a,b) for (a,b) in xs if ((a,b) != pedra_maior(xs))]

#p16: terminado
def teste(k,p):
    (a,b) = p
    return (a == k or a == k or b == k or b == k)
    
def pedras_encaixe(k,xs):
    return [p for p in xs if teste(k,p)]

def ordertuple(p):
    (a,b) = p
    if a > b:
        return (b,a)
    elif a < b:
        return (a,b)
    else:
        return p

def lista_sem_maior(pedra_maior,xs):
    return [
        p
        for p in xs
        if ordertuple(p) != ordertuple(pedra_maior)]

def tira_maior_v(k,xs):
    maiorpedra = pedra_maior(pedras_encaixe(k,xs))
    return lista_sem_maior(maiorpedra,xs)