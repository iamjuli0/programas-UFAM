def orx(a,b):
    if (a == True and b == False) or (a == False and b == True):
        return True
    else:
        return False

def pag(a,b,c):
    if ((a == b + c) or (b == a + c) or (c == a + b)) or ((a == b*c) or (b == a*c) or (c == a*b)):
        return True
    else:
        return False
        
def pgp(vl,i):
	if i >= 60:
		return str(0.6*vl) + " reais"
	elif 10 < i < 60:
		return str(vl) + " reais"
	elif 2 <= i <= 10:
		return str(0.5*vl) + " reais"
	elif 0 < i < 2:
		return str(0.1*vl) + " reais"