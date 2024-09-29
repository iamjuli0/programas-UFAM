def pedrap(p):
    (a,b) = p
    k = (0<=a<=6) and (0<=b<=6)
    return k

def maop(xs):
    if len([p for p in xs if pedrap(p) == False]) > 0:
        return False
    else:
        return True

def carrocap(p):
    (a,b) = p
    k = (0<=a<=6) and (0<=b<=6)
    return (a == b) and k
    
def tem_carroca_p(xs):
    if len([p for p in xs if maop(xs) == True and carrocap(p) == True]) > 0:
        return True
    else:
        return False

def tem_carrocas(xs):
    return [p for p in xs if maop(xs) == True and carrocap(p) == True]