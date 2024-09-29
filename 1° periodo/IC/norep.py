def head(x):
    return x[0]
    
def tail(x):
    return x[1:]

def norep(xs):
    if len(xs) == 0: return []
    if head(xs) in tail(xs):
        return norep(tail(xs))
    else:
        return [head(xs)] + norep(tail(xs))