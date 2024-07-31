package org.moeaframework.benchmark;

import org.moeaframework.core.FrameworkException;
import org.moeaframework.core.spi.RegisteredProblemProvider;

public class NativeCProvider extends RegisteredProblemProvider {

	public NativeCProvider() {
		super();
		
		register("NativeC", NativeC::new, null);
		register("NativeCDirectMapping", NativeCDirectMapping::new, null);
		registerDiagnosticToolProblems(getRegisteredProblems());
	}
	
}
