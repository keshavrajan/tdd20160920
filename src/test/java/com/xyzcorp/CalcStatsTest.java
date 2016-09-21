package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.data.Offset;
import org.junit.Test;

public class CalcStatsTest {

	// Think of the simplest thing I can do.
	// Minimum Value?

	@Test
	public void testMinimumWithArrayOfOneElement() {
		// 0. Do I want to put this in a pre-existing class?
		// 1. Do I want make an instantiation?
		// CalcStats calcStats = new CalcStats();
		// calcStats.getMinimum(new int[]{1});
		// 2. Do you want make an instantiation with array as member var?
		// CalcStats calcStats = new CalcStats(new int[] { 1 });
		// calcStats.getMinimum();
		// 3. Do I want to run this as a static method?
		// CalcStats.getMinimum(new int[]{1});

		CalcStats calcStats = new CalcStats(new int[] { 1 });
		assertThat(calcStats.getMinimum()).isEqualTo(1);
	}

	@Test
	public void testMinimumWithArrayOfOneElementDifferentNumber() {
		CalcStats calcStats = new CalcStats(new int[] { 4 });
		assertThat(calcStats.getMinimum()).isEqualTo(4);
	}

	@Test
	public void testMinimumWithArrayOfOneNegativeElement() {
		CalcStats calcStats = new CalcStats(new int[] { -54 });
		assertThat(calcStats.getMinimum()).isEqualTo(-54);
	}

	@Test
	public void testCalcStatsCannotReceiveANull() {
		try {
			new CalcStats(null);
			fail("We should not get this far");
		} catch (IllegalArgumentException e) {
			assertThat(e).hasMessage("Array is null");
		}
	}

	@Test
	public void testMinimumWithArrayOfTwoPositiveElement() {
		CalcStats calcStats = new CalcStats(new int[] { 10, 4 });
		assertThat(calcStats.getMinimum()).isEqualTo(4);
	}

	@Test
	public void testMinimumWithArrayOfMultipleAndNegativeElements() {
		CalcStats calcStats = new CalcStats(new int[] { 10, -4, -11, 22, -3, 4 });
		assertThat(calcStats.getMinimum()).isEqualTo(-11);
	}

	@Test
	public void testMinimumWithAnEmptyArray() {
        CalcStats calcStats = new CalcStats(new int[] {});
		try {
            calcStats.getMinimum();
			fail("We should not get this far");
		} catch (IllegalStateException e) {
			assertThat(e).hasMessage("Array is empty");
		}
	}

	@Test
	public void testMinimumWithArrayOfThreeElementsWithOneBeingMaxValuePlusOne() {
		CalcStats calcStats = new CalcStats(new int[] { 10, Integer.MAX_VALUE + 1, 4 });
		assertThat(calcStats.getMinimum()).isEqualTo(Integer.MAX_VALUE + 1);
	}

	@Test
	public void testMaximumWithArrayOfOneElement() {
		CalcStats calcStats = new CalcStats(new int[] { 1 });
		assertThat(calcStats.getMaximum()).isEqualTo(1);
	}

	@Test
	public void testMaximumWithArrayOfOneElementDifferentNumber() {
		CalcStats calcStats = new CalcStats(new int[] { 4 });
		assertThat(calcStats.getMaximum()).isEqualTo(4);
	}

	@Test
	public void testMaximumWithArrayOfOneNegativeElement() {
		CalcStats calcStats = new CalcStats(new int[] { -54 });
		assertThat(calcStats.getMaximum()).isEqualTo(-54);
	}

	@Test
	public void testMaximumWithArrayOfTwoPositiveElement() {
		CalcStats calcStats = new CalcStats(new int[] { 4, 10 });
		assertThat(calcStats.getMaximum()).isEqualTo(10);
	}

	@Test
	public void testMaximumWithArrayOfMultipleAndNegativeElements() {
		CalcStats calcStats = new CalcStats(new int[] { 10, -4, -11, 22, -3, 4 });
		assertThat(calcStats.getMaximum()).isEqualTo(22);
	}

    @Test
    public void testMaximumWithAnEmptyArray() {
        CalcStats calcStats = new CalcStats(new int[] {});
        try {
            calcStats.getMaximum();
            fail("We should not get this far");
        } catch (IllegalStateException e) {
            assertThat(e).hasMessage("Array is empty");
        }
    }

	@Test
	public void testMaximumWithArrayOfThreeElementsWithOneBeingMinValueMinusOne() {
		CalcStats calcStats = new CalcStats(new int[] { 10, Integer.MIN_VALUE - 1, 4 });
		assertThat(calcStats.getMaximum()).isEqualTo(Integer.MIN_VALUE - 1);
	}

	@Test
	public void testMAX_VALUEAndMIN_VALUETheory() {
		assertThat(Integer.MAX_VALUE + 1).isEqualTo(Integer.MIN_VALUE);
		assertThat(Integer.MIN_VALUE - 1).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
    public void testNumberOfElementsForOneItem() {
        CalcStats calcStats = new CalcStats(new int[] { -54 });
        assertThat(calcStats.getCount()).isEqualTo(1);
    }

    @Test
    public void testNumberOfElementsForTwoItem() {
        CalcStats calcStats = new CalcStats(new int[] { -54, 65});
        assertThat(calcStats.getCount()).isEqualTo(2);
    }

    @Test
    public void testNumberOfElementsForNoItems() {
        CalcStats calcStats = new CalcStats(new int[]{});
        assertThat(calcStats.getCount()).isEqualTo(0);
    }

    @Test
    public void testAverageWithOneElement() {
        CalcStats calcStats = new CalcStats(new int[] { 54 });
        assertThat(calcStats.getAverage()).isEqualTo(54);
    }

    @Test
    public void testAverageWithTwoElements() {
        CalcStats calcStats = new CalcStats(new int[] { 89, 100 });
        assertThat(calcStats.getAverage()).isEqualTo(94.5);
    }

    @Test
    public void testAverageWithThreeElements() {
        CalcStats calcStats = new CalcStats(new int[] { 90, 90, 100 });
        assertThat(calcStats.getAverage()).isEqualTo(93.3333, Offset.offset(.0001));
    }

}
