package org.moeaframework.integration;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;

public class RealWorldBenchmarksTest {
	
	@Test
	public void testRealWorldBenchmarks() {
		NondominatedPopulation result = new Executor()
				.withProblem("GAA")
				.withAlgorithm("NSGAII")
				.withMaxEvaluations(10000)
				.run();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}

}
