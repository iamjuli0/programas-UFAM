def loun(x):
    if (65 <= ord(x) <= 90) or (97 <= ord(x) <= 122):
        return 'L'
    elif (48 <= ord(x) <= 57):
        return 'N'
    else:
        return 'X'
    
def placa(x):
    xs = list(x)
    
    carro = ['L','L','L','N','L','N','N']
    moto = ['L','L','L','N','N','L','N']
    p = [loun(xs[i]) for i in range(len(x))]
    
    if p == carro:
        return 'Carro'
    elif p == moto:
        return 'Moto'
    else:
        return 'Nada'