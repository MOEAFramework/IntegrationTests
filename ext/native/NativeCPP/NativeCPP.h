#ifndef NativeCPP_H
#define NativeCPP_H

extern "C" {
#ifdef __WIN32__
void __declspec(dllexport) evaluate(double* vars, double* objs, double* constrs);
#else
void evaluate(double* vars, double* objs, double* constrs);
#endif
}

int nvars = 11;
int nobjs = 2;
int nconstrs = 0;

#endif