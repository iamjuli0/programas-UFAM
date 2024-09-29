def conta(c):
    if c <= 5:
        print('Valor a ser pago será de 50 reais pelos ' + str(c) + 'GBs')
    elif 5 < c <= 10:
        x = 50 + 10*(c-5)
        print('Valor a ser pago será de ' + str(x) + ' reais pelos ' + str(c) + 'GBs')
    elif c > 10:
        y = 5*c
        print('Valor a ser pago será de ' + str(y) + ' reais pelos ' + str(c) + 'GBs')