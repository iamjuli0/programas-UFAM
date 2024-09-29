def xp(n):
    return (0 <= n <= 100 and n % 3 == 0 and n % 5 == 0)

def quad(x):
    return x*x
def raiz(x):
    return x**(1/2)
def mod(x):
    if x < 0:
        return -x
    else:
        return x


def amarelo(x1,y1,x2,y2):
    atotal = (x2-x1)*(y1-y2)
    d = mod(y1-y2)
    ladoq = d / raiz(2)
    raio = ladoq/2
    areaa = 3.14*quad(raio)
    areaq = quad(ladoq)
    areav = atotal - areaq
    
    amarelo = atotal - areav - areaa
    
    return amarelo

amarelo(2,8,10,4)
