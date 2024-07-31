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
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define PI 3.14159265358979323846

int nvars = 11;
int nobjs = 2;

/**
 * Function for evaluating the DTLZ2 problem.
 */
void evaluate(double* vars, double* objs) {
	int i;
	int j;
	int k = nvars - nobjs + 1;
	double g = 0.0;

	for (i=nvars-k; i<nvars; i++) {
		g += pow(vars[i] - 0.5, 2.0);
	}

	for (i=0; i<nobjs; i++) {
		objs[i] = 1.0 + g;

		for (j=0; j<nobjs-i-1; j++) {
			objs[i] *= cos(0.5*PI*vars[j]);
		}

		if (i != 0) {
			objs[i] *= sin(0.5*PI*vars[nobjs-i-1]);
		}
	}
}

/**
 * This "pure" C version just evaluates random inputs to see the raw
 * performance without any communication overhead.
 */
int main(int argc, char* argv[]) {
	double vars[nvars];
	double objs[nobjs];
	int NFE = 100000;
	
	if (argc > 1) {
		NFE = atoi(argv[1]);
	}
	
	srand(time(NULL));

	for (int i = 0; i < NFE; i++) {
		for (int j = 0; j < nvars; j++) {
			vars[j] = (double)rand()/RAND_MAX; // random value between 0 and 1
		}
		
		evaluate(vars, objs);
	}

	return EXIT_SUCCESS;
}

