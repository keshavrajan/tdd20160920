package com.xyzcorp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class DieTest {

	private Random realRandom;
	private Random stubOf3;
	
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("This will run before the entire class");
		
	}

	@Before
	public void setUp() {
		System.out.println("This is before the test");
		realRandom = new Random();
		stubOf3 = new Random() {
			@Override
			public int nextInt(int bound) {
				return 3;
			}
		};
	}

	@Test
	@Category(value = UnitTest.class)
	public void testDefaultIs1WithRandom() {
		Die die = new Die(realRandom);
		assertThat(die.getPips()).isEqualTo(1);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testSimpleRollOf4() {

		// Die is the Subject Under Test
		Die die = new Die(stubOf3);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(4);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testSimpleRollOf2() {
		// Collaborator
		// Stub - Canned Answers
		@SuppressWarnings("serial")
		Random random = new Random() {
			@Override
			public int nextInt(int bound) {
				return 1;
			}
		};

		// Die is the Subject Under Test
		Die die = new Die(random);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(2);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testSimpleRollOf2Twice() {
		// Collaborator
		// Stub - Canned Answers
		@SuppressWarnings("serial")
		Random random = new Random() {
			@Override
			public int nextInt(int bound) {
				return 1;
			}
		};

		// Die is the Subject Under Test
		Die die = new Die(random);
		Die rolledDie = die.roll().roll();
		assertThat(rolledDie.getPips()).isEqualTo(2);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testThatRandomIsNotNull() {
		try {
			new Die(null);
			fail("This line should not be invoked");
		} catch (IllegalArgumentException iae) {
			assertThat(iae).hasMessage("Random is null");
		}
	}

	@Test
	@Category(value = IntegrationTest.class)
	public void testIntegrationTestWithARealRandom() {
		Die die = new Die(realRandom);
		for (int i = 0; i < 1000000; i++) {
			assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);
		}
	}

	@Test
	@Category(value = IntegrationTest.class)
	public void testDistributionWithARealRandomWorksWell() {
		Die die = new Die(realRandom);

		Map<Integer, Long> distribution = new HashMap<Integer, Long>();

		for (int i = 0; i < 1000000; i++) {
			int pips = die.roll().getPips();
			Long value = distribution.get(pips);
			if (value == null) {
				distribution.put(pips, 1L);
			} else {
				distribution.put(pips, value + 1);
			}
		}

		assertThat(distribution.get(6)).isNotNull();
	}

	@Test
	@Category(value = UnitTest.class)
	public void testDistributionWithAMock() {
		// Create the mock
		Random random = createMock(Random.class);

		// Rehearse with the mock
		expect(random.nextInt(6)).andReturn(5).once(); // 0,1,2,3,4

		replay(random);

		// Run the test
		Die die = new Die(random);
		assertThat(die.roll().getPips()).isEqualTo(6);

		// Verify mock
		verify(random);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testBUG3012() {
		// Create the mock
		Random random = createMock(Random.class);

		// Rehearse with the mock
		expect(random.nextInt(6)).andReturn(4).once();

		// Rewind/Replay (applicable only to EasyMock)
		replay(random);

		// Run the test
		Die die = new Die(random);
		assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);

		// Verify mock
		verify(random);
	}

	@Test
	@Category(value = UnitTest.class)
	public void testBUG3012WithAZero() {
		// Create the mock
		Random random = createMock(Random.class);

		// Rehearse with the mock
		expect(random.nextInt(6)).andReturn(0).once();

		// Rewind/Replay (applicable only to EasyMock)
		replay(random);

		// Run the test
		Die die = new Die(random);
		assertThat(die.roll().getPips()).isGreaterThan(0).isLessThan(7);

		// Verify mock
		verify(random);
	}
	
	@After
	public void cleanUp() {
		System.out.println("After test");
		stubOf3 = null;
	}
	
	@AfterClass
	public static void cleanUpAfterClass() {
		System.out.println("After the class");
	}
}