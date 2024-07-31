package org.moeaframework.integration;

import org.junit.Assert;
import org.junit.Test;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;

public class JMetalTests {
	
	@Test
	public void test() {
		NondominatedPopulation result = new Executor()
				.withProblem("UF1")
				.withAlgorithm("AbYSS-JMetal")
				.withMaxEvaluations(10000)
				.run();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
	}

}
