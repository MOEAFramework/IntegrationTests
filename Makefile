SAMPLES ?= 10
NFE ?= 100000

build:
	mvn dependency:copy-dependencies
	make -C ext/c
	make -C ext/native/NativeC
	make -C ext/native/NativeCPP
	make -C ext/native/NativeFortran
	mvn compile
	
benchmark:
	mvn exec:java -Dbenchmark.samples=$(SAMPLES) -Dbenchmark.nfe=$(NFE)
	
test:
	mvn test

clean:
	make -C ext/c clean
	make -C ext/native/NativeC clean
	make -C ext/native/NativeCPP clean
	make -C ext/native/NativeFortran clean
	rm -rf ext/lib
	mvn clean
