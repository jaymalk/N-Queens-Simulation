make:
	@javac include/*.java
	@mv include/*.class .
	@javac Simulator.java

clean:
	@rm *.class 
