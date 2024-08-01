build:
	mvn dependency:copy-dependencies
	mkdir -p ext/lib
	cp target/dependency/*.jar ext/lib
	make -C ext/c
	make -C ext/native/NativeC
	make -C ext/native/NativeCPP
	make -C ext/native/NativeFortran
	
benchmark:
	mvn exec:java
	
test:
	mvn test

clean:
	make -C ext/c clean
	make -C ext/native/NativeC clean
	make -C ext/native/NativeCPP clean
	make -C ext/native/NativeFortran clean
	rm -rf ext/lib
