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

## License

Copyright 2009-2024 David Hadka and other contributors.  All rights reserved.

The MOEA Framework is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or (at your
option) any later version.

The MOEA Framework is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
License for more details.

You should have received a copy of the GNU Lesser General Public License
along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
