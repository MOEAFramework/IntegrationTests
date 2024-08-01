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
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.moeaframework.core.FrameworkException;
import org.moeaframework.core.Problem;

public interface Benchmark {
	
	String getName();
	
	void run(int NFE) throws IOException, InterruptedException;
	
	public static Benchmark of(String name, Class<? extends Problem> problemType, Object... args) {
		return new AbstractBenchmark(name, () -> {
			try {
				return ConstructorUtils.invokeConstructor(problemType, args);
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
					| InstantiationException e) {
				throw new FrameworkException(e);
			}
		});
	}
	
	public static Benchmark of(String name, String className, Object... args) {
		return new AbstractBenchmark(name, () -> {
			try {
				Class<?> problemType = Class.forName(className);
				return (Problem)ConstructorUtils.invokeConstructor(problemType, args);
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
					| InstantiationException | ClassNotFoundException e) {
				throw new FrameworkException(e);
			}
		});
	}

}
