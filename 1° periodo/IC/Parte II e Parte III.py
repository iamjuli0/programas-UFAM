def head(xs):
    return xs[0]
def tail(xs):
    return xs[1:]
    
#PARTE II:
#p35:
def somavet(x,y):
    (a,b) = x
    (c,d) = y
    return (x[0] + y[0], x[1] + y[1])

#p36:
def sumdosecund(n, xs):
    if len(xs) == 0:
        return []
    elif n - head(xs) >= 0:
        return [head(xs)] + sumdosecund(n - head(xs), tail(xs))
    else:
        return []

def sumdo(n):
    if n % 2 != 0:
        return False
    else:
        return sumdosecund(n, list(range(0, n, 2)))

#p37:
def alterna(lista):
    pares = [x for x in lista if x % 2 == 0]
    impares = [y for y in lista if y % 2 != 0]
    xs = []
    
    if len(pares) != len(impares):
        return False
    for i in range(len(pares)):
        k1 = pares[i]
        xs.append(k1)
        k2 = impares[i]
        xs.append(k2)
    
    print(xs)

#p38:
def freq(x,xs):
    return len([y for y in xs if y == x])
    
def intersec(xs,ys):
    zs = xs + ys
    ms = [x for x in zs if freq(x,zs) > 1]
    x = (len(ms)//2)
    return ms[:x]

#p39:
def uni(xs,ys):
    zs = xs + ys
    ms = [x for x in zs if freq(x,zs) == 1]
    return ms + intersec(xs,ys)

#PARTE III:
#p40:
def letraCAPSON(l):
    if 65 <= ord(l) <= 90:
        return True
    else:
        return False
def letraCAPSOFF(l):
    if 97 <= ord(l) <= 122:
        return True
    else:
        return False
def letra(l):
    return letraCAPSON(l) or letraCAPSOFF(l)

def e_palav(cadeia):
    
    xs =[]
    for i in range(0,len(cadeia)):
        k = cadeia[i]
        xs.append(k)
    
    ys = len([x for x in xs if letra(x) == True]) == len(cadeia)
    print(ys)

#p41:
def e_int(cadeia):
    if len(cadeia) == 0:
       return True
       
    else:
        return num(head(cadeia)) and e_int(tail(cadeia))

#p42:
def conjpresente1(verbo):
    radical = verbo[:-2]
    return [ f"Eu {radical}o",
             f"Tu {radical}as",
             f"Ele {radical}a",
             f"Nós {radical}amos",
             f"Vós {radical}ais",
             f"Eles {radical}am"
             ]
def conjpassado1(verbo):
    radical = verbo[:-2]
    return [ f"Eu {radical}ei",
             f"Tu {radical}aste",
             f"Ele {radical}ou",
             f"Nós {radical}amos",
             f"Vós {radical}astes",
             f"Eles {radical}aram"
             ]
def conjfuturo1(verbo):
    radical = verbo[:-2]
    return [ f"Eu {radical}arei",
             f"Tu {radical}arás",
             f"Ele {radical}ará",
             f"Nós {radical}aremos",
             f"Vós {radical}areis",
             f"Eles {radical}arão"
             ]
             
def conjuga(verbo,tempo):
    if tempo == "presente":
        return conjpresente1(verbo)
    elif tempo == "passado":
        return conjpassado1(verbo)
    else:
        return conjfuturo1(verbo)

#p43:
def num(numero):
    if len(numero) == 1:
        return 48 <= ord(head(numero)) <= 57
    return (48 <= ord(head(numero)) <= 57) and num(tail(numero))

def e_float(cadeia):
    
    (x,y) = int_frac(cadeia)
    
    return (y != '0' and num(x) and num(y))

#p44:
def quebra(cadeia: str, y):
    xs =  [k for k in range(len(cadeia)) if ord(cadeia[k]) == ord(y)]
    
    if len(xs) >= 1:
        return [cadeia[:xs[0]], cadeia[(xs[0] + 1) :]]
    else:
        return [cadeia]

def int_frac(cadeia):
    xs = quebra(cadeia, '.')
    
    if len(xs) == 1 or len(xs[1]) == 0:
        return (xs[0], '0')
    else:
        return (xs[0], xs[1])

#p45:
def traduz(numero):
    unidades = ['zero', 'um', 'dois', 'três', 'quatro', 'cinco', 'seis', 'sete', 'oito', 'nove']
    casade10 = ['dez', 'onze', 'doze', 'treze', 'catorze', 'quinze', 'dezesseis', 'dezessete', 'dezoito', 'dezenove']
    dezenas = ['vinte', 'trinta', 'quarenta', 'cinquenta', 'sessenta', 'setenta', 'oitenta', 'noventa']
    
    if numero <= 9:
        return unidades[numero]
    elif 9 < numero <= 19:
        return casade10[numero-10]
    elif 19 < numero <= 99:
        
        numerodafrente = (numero//10) * 10
        numerodetras = numero % 10
        if numerodetras != 0:
            a = dezenas[(numerodafrente-20)//10]
            b = unidades[(numerodetras)]
            return f'{a} e {b}'
        else:
            return dezenas[(numerodafrente-20)//10]