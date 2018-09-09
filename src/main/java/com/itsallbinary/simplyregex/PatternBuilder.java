package com.itsallbinary.simplyregex;

public interface PatternBuilder<T> {
	
	

	T build(PatternAccumulator accumulator);

}
