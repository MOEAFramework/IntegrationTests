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
package org.moeaframework.benchmark;

import java.io.IOException;

import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder.NSGAIIVariant;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class JMetalDirectBenchmark implements Benchmark {

	@Override
	public String getName() {
		return "JMetal (Direct)";
	}

	@Override
	public void run(int NFE) throws IOException, InterruptedException {
		DTLZ2 problem = new DTLZ2(11, 2);

		SBXCrossover crossover = new SBXCrossover(1.0, 15.0);
		PolynomialMutation mutation = new PolynomialMutation(1.0 / problem.numberOfVariables(), 20.0);

		NSGAIIBuilder<DoubleSolution> builder = new NSGAIIBuilder<>(problem, crossover, mutation, 100);
		builder.setVariant(NSGAIIVariant.NSGAII);
		builder.setMaxEvaluations(NFE);

		NSGAII<DoubleSolution> algorithm = builder.build();
		algorithm.run();
	}

}
