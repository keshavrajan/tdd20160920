package com.xyzcorp;

import java.util.function.BiPredicate;

public class CalcStats {

	private final int[] ints;

	public CalcStats(int[] ints) {
		if (ints == null)
			throw new IllegalArgumentException("Array is null");
        this.ints = ints;
	}
	
	//Java 8
	private int findNumber(BiPredicate<Integer, Integer> biPredicate) {
        if (ints.length == 0) throw new IllegalStateException("Array is empty");
        int result = ints[0];
		for (int i = 1 ; i < ints.length; i++) {
			int next = ints[i];
			if (biPredicate.test(next, result)) result = next;
		}
		return result;
	}

	public int getMinimum() {
        return findNumber((next, result) -> next < result);
	}

	public int getMaximum() {
        return findNumber((next, result) -> next > result);
	}

    public int getCount() {
        return ints.length;
    }

    public double getAverage() {
        int sum = 0;
        for (int item: ints) {
            sum += item;
        }
        return ((double) sum) / ints.length;
    }
}
