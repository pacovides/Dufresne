package com.pacovides.money.model;

import static org.junit.Assert.fail;

import org.junit.Test;

public class AccountTest {

	/**
	 * Very simple test to assert that we cannot create null types or names in
	 * an account
	 */
	@Test
	public void testNameTypeConstructor() {
		try {
			new Account(null, AccountType.ASSET);
			fail("Expected name not null exception");
		} catch (IllegalArgumentException e) {
			// good, this is expected
		}

		try {
			new Account("some name", null);
			fail("Expected type not null exception");
		} catch (IllegalArgumentException e) {
			// good, this is expected
		}

		// This should not fail
		new Account("Valid name", AccountType.ASSET);

	}

}
