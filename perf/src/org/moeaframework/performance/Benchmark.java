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
package org.moeaframework.performance;

import java.io.IOException;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.spi.AlgorithmFactory;
import org.moeaframework.problem.ProblemException;
import org.moeaframework.problem.DTLZ.DTLZ2;
import org.moeaframework.util.Timing;
import org.moeaframework.util.TypedProperties;
import org.moeaframework.util.format.TableFormat;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder.NSGAIIVariant;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import org.moeaframework.benchmark.NativeC;
import org.moeaframework.benchmark.NativeCDirectMapping;
import org.moeaframework.benchmark.NativeCPP;
import org.moeaframework.benchmark.NativeCPPDirectMapping;
import org.moeaframework.benchmark.NativeFortran;
import org.moeaframework.benchmark.NativeFortranDirectMapping;

public class Benchmark {

	public static int N = 1;
	public static int NFE = 100000;

	public static void run(Problem problem) {
		Timing.startTimer(problem.getName());

		Algorithm algorithm = new NSGAII(problem);
		algorithm.run(NFE);

		Timing.stopTimer(problem.getName());
	}
	
	public static void runPureC() throws IOException, InterruptedException {
		Timing.startTimer("C (pure)");
		
		Process process = new ProcessBuilder("./c/dtlz2_pure.exe", Integer.toString(NFE)).start();
		process.waitFor();
		
		Timing.stopTimer("C (pure)");
	}

	public static void runJava() {
		DTLZ2 problem = new DTLZ2(2);

		Timing.startTimer("Java");

		Algorithm algorithm = new NSGAII(problem);
		algorithm.run(NFE);

		Timing.stopTimer("Java");
	}

	// Construct and run JMetal directly
	public static void runJMetalDirect() {
		org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2 problem =
				new org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2(11, 2);

		Timing.startTimer("JMetal (Direct)");

		org.uma.jmetal.operator.crossover.impl.SBXCrossover crossover = 
				new org.uma.jmetal.operator.crossover.impl.SBXCrossover(1.0, 15.0);

		org.uma.jmetal.operator.mutation.impl.PolynomialMutation mutation =
				new org.uma.jmetal.operator.mutation.impl.PolynomialMutation(1.0 / problem.numberOfVariables(), 20.0);

		org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder<DoubleSolution> builder =
				new org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder<DoubleSolution>(
						problem, crossover, mutation, 100);

		builder.setVariant(NSGAIIVariant.NSGAII);
		builder.setMaxEvaluations(NFE);

		org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII<DoubleSolution> algorithm = builder.build();
		algorithm.run();

		Timing.stopTimer("JMetal (Direct)");
	}

	// Construct and run JMetal using jmetal-plugin
	public static void runJMetalPlugin() {
		DTLZ2 problem = new DTLZ2(2);

		Timing.startTimer("JMetal (Plugin)");

		// this must be set to ensure JMetal runs for the correct number of evaluations!
		TypedProperties properties = new TypedProperties();
		properties.setInt("maxEvaluations", NFE);

		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm("NSGAII-JMetal", properties, problem);
		algorithm.run(NFE);

		Timing.stopTimer("JMetal (Plugin)");
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length >= 1) {
			N = Integer.parseInt(args[0]);
		}

		if (args.length >= 2) {
			NFE = Integer.parseInt(args[1]);
		}

		for (int i = 0; i < N; i++) {
			runPureC();
			runJava();
			
			try (Problem problem = new NativeC()) {
				run(problem);
			}

			try (Problem problem = new NativeCPP()) {
				run(problem);
			}

			try (Problem problem = new NativeFortran()) {
				run(problem);
			}
			
			try (Problem problem = new NativeCDirectMapping()) {
				run(problem);
			}
			
			try (Problem problem = new NativeCPPDirectMapping()) {
				run(problem);
			}
			
			try (Problem problem = new NativeFortranDirectMapping()) {
				run(problem);
			}

			try (Problem problem = new DTLZ2WithCStdio()) {
				run(problem);
			}

			try (Problem problem = new DTLZ2WithCSocket()) {
				run(problem);
			}

			try (Problem problem = new DTLZ2WithPythonStdio()) {
				run(problem);
			}

			try (Problem problem = new DTLZ2WithPythonSocket()) {
				run(problem);
			}

			try (Problem problem = new DTLZ2WithPypyStdio()) {
				run(problem);
			} catch (ProblemException e) {
				// Pypy not available, skip
			}
	
			try (Problem problem = new DTLZ2WithPypySocket()) {
				run(problem);
			} catch (ProblemException e) {
				// Pypy not available, skip
			}

			runJMetalDirect();
			runJMetalPlugin();
		}

		Timing.asTabularData().display(TableFormat.Markdown);
	}

}
