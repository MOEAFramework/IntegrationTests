package org.moeaframework.integration;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;

public class PISATests {
	
	@Test
	public void testPISA() {
		NondominatedPopulation result = new Executor()
				.withProblem("UF1")
				.withAlgorithm("hype-pisa")
				.withMaxEvaluations(10000)
				.run();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}

}
