package com.pacovides.money.model;

import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Account implements Comparable<Account> {

	/**
	 * The account type can be set during construction or by parent
	 */
	private AccountType type;

	/**
	 * The account name should be unique within a parent account.
	 */
	private final String name;
	private Currency defaultCurrency;
	private String description;
	private Map<String, String> accountProperties;
	private List<String> accountLabels;

	private Account parentAccount;

	/**
	 * SubAccounts are immediate children of this account
	 */
	private SortedSet<Account> subAccounts = new TreeSet<Account>();

	public Account(String name, AccountType type) {
		if (StringUtils.isEmpty(name) || type == null) {
			throw new IllegalArgumentException("Invalid name or type [name:" + name + ", type:" + type + "]");
		}

		this.name = name;
		this.type = type;
	}

	public AccountType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Account getParentAccount() {
		return parentAccount;
	}

	// We disallow external classes to directly set the parent, instead the
	// parent class should be created first and when the child is added the
	// parent will be set.
	private void setParentAccount(Account parentAccount) {
		this.parentAccount = parentAccount;
	}

	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAccountProperties() {
		return accountProperties;
	}

	public void setAccountProperties(Map<String, String> accountProperties) {
		this.accountProperties = accountProperties;
	}

	public List<String> getAccountLabels() {
		return accountLabels;
	}

	public void setAccountLabels(List<String> accountLabels) {
		this.accountLabels = accountLabels;
	}

	/**
	 * Adds the specified account as child of this account. The subAccount must
	 * be of the same type and its name bust be unique amongst its siblings.
	 * 
	 * @param subAccount
	 */
	public void addSubaccount(Account subAccount) {
		if (subAccount == null) {
			// No-op for null subAccounts
			return;
		}

		if (!subAccount.getType().equals(this.getType())) {
			throw new IllegalArgumentException("Subaccount type " + subAccount.getType() + "does not match "
					+ this.getType());
		}

		// We link two-ways for easy navigation
		subAccount.setParentAccount(this);
		subAccounts.add(subAccount);
	}

	public void addSubaccounts(List<Account> additionalAccounts) {
		for (Account additionalAccount : additionalAccounts) {
			addSubaccount(additionalAccount);
		}
	}

	public void removeSubAccount(Account subAccount) {
		subAccounts.remove(subAccount);
	}

	public boolean hasSubAccounts() {
		return !subAccounts.isEmpty();
	}

	public boolean isMainAccount() {
		return parentAccount == null;
	}

	public Collection<Account> getDirectSubAccounts() {
		return subAccounts;
	}

	public Account findSubAccount(String name) {
		for (Account subAccount : subAccounts) {
			if (subAccount.getName().equals(name)) {
				return subAccount;
			} else {
				Account foundAccount = subAccount.findSubAccount(name);
				if (foundAccount != null) {
					return foundAccount;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 * We say one account is equal to another if it has the same name, type and
	 * parent
	 */
	@Override
	public boolean equals(Object object) {

		// check for self-comparison
		if (this == object) {
			return true;
		}

		if (!(object instanceof Account)) {
			return false;
		}

		// now we know the object is an account
		Account thatAccount = (Account) object;

		// Other preconditions enforce type to be non-null.
		// If types are different this is not the same account.
		if (this.getType() != thatAccount.getType()) {
			return false;
		}

		if (this.isMainAccount() != thatAccount.isMainAccount()) {
			return false;
		}

		if (!this.isMainAccount() && !this.getParentAccount().equals(thatAccount.getParentAccount())) {
			return false;
		}

		return this.getName().equals(thatAccount.getName());

	}

	@Override
	public int hashCode() {
		// We use apache hashcodeBuilder with 2 random odd numbers and relevant
		// fields
		return new HashCodeBuilder(15, 101).append(name).append(type).append(parentAccount).toHashCode();
	};

	// Sort based on name
	@Override
	public int compareTo(Account that) {
		return name.compareTo(that.getName());
	}

}
