package org.moeaframework.integration;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.moeaframework.algorithm.Algorithm;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.population.NondominatedPopulation;
import org.moeaframework.core.spi.ProblemFactory;
import org.moeaframework.problem.Problem;

public class RealWorldBenchmarksTest {
	
	@Test
	@Ignore
	public void testRealWorldBenchmarks() {
		Problem problem = ProblemFactory.getInstance().getProblem("GAA");

		Algorithm algorithm = new NSGAII(problem);
		algorithm.run(10000);

		NondominatedPopulation result = algorithm.getResult();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}

}
