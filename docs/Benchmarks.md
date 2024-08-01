# Benchmarks

All benchmarks are based on the 2-D DTLZ2 problem with `100,000` function evaluations (NFE) and `10` seeds.
See [Timings](docs/Timings.md) for collected timing data.

We currently support the following benchmarks:

1. **Pure C** - Implemented entirely in C, without any integration with the MOEA Framework.  This provides a baseline measure for the
   fastest possible evaluation times.
3. **Pure Java** - Implemented entirely in Java.  This is how we typically recommend developing problems using the
   MOEA Framework.
4. **JNA Interface Mapping** - Calls a natively-compiled C, C++, or Fortran shared library using JNA.
5. **JNA Direct Mapping** - Calls a natively-compiled C, C++, or Fortran shared library using JNA's direct mapping feature, which should have
   less overhead than the interface mapping.
6. **External Stdio / Socket** - Interfaces with a C or Python program using `ExternalProblem`, communicating either over standard I/O or
   sockets.
8. **JMetal** - Benchmarks the equivalent JMetal implementation (Direct) or using our JMetal-Plugin.
