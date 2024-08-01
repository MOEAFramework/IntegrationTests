/* Copyright 2009-2024 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework;

import java.io.IOException;
import java.util.List;

import org.moeaframework.benchmark.Benchmark;
import org.moeaframework.benchmark.JMetalDirectBenchmark;
import org.moeaframework.benchmark.JMetalPluginBenchmark;
import org.moeaframework.benchmark.PureCBenchmark;
import org.moeaframework.problem.DTLZ2WithCSocket;
import org.moeaframework.problem.DTLZ2WithCStdio;
import org.moeaframework.problem.DTLZ2WithPypySocket;
import org.moeaframework.problem.DTLZ2WithPypyStdio;
import org.moeaframework.problem.DTLZ2WithPythonSocket;
import org.moeaframework.problem.DTLZ2WithPythonStdio;
import org.moeaframework.problem.DTLZ.DTLZ2;
import org.moeaframework.util.Timing;
import org.moeaframework.util.format.TableFormat;

public class Benchmarks {

	public static int N = 1;
	public static int NFE = 10000;
	public static List<Benchmark> BENCHMARKS;
	
	static {
		BENCHMARKS = List.of(
			new PureCBenchmark(),
			Benchmark.of("Java", DTLZ2.class, 2),
			Benchmark.of("C (JNA Interface Mapping)", "org.moeaframework.benchmark.NativeC"),
			Benchmark.of("C (JNA Direct Mapping)", "org.moeaframework.benchmark.NativeCDirectMapping"),
			Benchmark.of("C++ (JNA Interface Mapping)", "org.moeaframework.benchmark.NativeCPP"),
			Benchmark.of("C++ (JNA Direct Mapping)", "org.moeaframework.benchmark.NativeCPPDirectMapping"),
			Benchmark.of("Fortran (JNA Interface Mapping)", "org.moeaframework.benchmark.NativeFortran"),
			Benchmark.of("Fortran (JNA Direct Mapping)", "org.moeaframework.benchmark.NativeFortranDirectMapping"),
			Benchmark.of("C (External Stdio)", DTLZ2WithCStdio.class),
			Benchmark.of("C (External Socket)", DTLZ2WithCSocket.class),
			Benchmark.of("Python (External Stdio)", DTLZ2WithPythonStdio.class),
			Benchmark.of("Python (External Socket)", DTLZ2WithPythonSocket.class),
			Benchmark.of("Pypy (External Stdio)", DTLZ2WithPypyStdio.class),
			Benchmark.of("Pypy (External Socket)", DTLZ2WithPypySocket.class),
			new JMetalDirectBenchmark(),
			new JMetalPluginBenchmark());
	}

	public static void run(Benchmark benchmark) throws IOException, InterruptedException {
		Timing.startTimer(benchmark.getName());
		benchmark.run(NFE);
		Timing.stopTimer(benchmark.getName());
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length >= 1) {
			N = Integer.parseInt(args[0]);
		}

		if (args.length >= 2) {
			NFE = Integer.parseInt(args[1]);
		}

		for (int i = 0; i < N; i++) {
			for (Benchmark benchmark : BENCHMARKS) {
				run(benchmark);
			}
		}

		Timing.asTabularData().display(TableFormat.Markdown);
	}

}
