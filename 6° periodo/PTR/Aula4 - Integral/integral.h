#ifndef INTEGRAL_H__
#define INTEGRAL_H__

typedef double function(double);

static inline double integral(function *f, double a, double b){
    double res = ((f(b) + f(a)) * (b - a)) / 2.0;
    return res;
}

#endif //INTEGRAL_H__