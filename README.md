# Integration and Performance Testing

This repository contains integration tests and performance benchmarks for the [MOEA Framework](https://github.com/MOEAFramework/MOEAFramework).
The integration tests are intended to test staged Maven artifacts before being released for general consumption.

## Usage

While these tests are typically run automatically when updating `pom.xml`, they can be run manually with:

```bash
make build
make test            # Run integration tests found under src/test/java
make benchmark       # Run benchmarks found under src/main/java
```
