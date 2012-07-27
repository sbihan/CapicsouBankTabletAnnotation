package com.excilys.capicsoubank.dto;

public class AccountDTO {
	private Integer accountId;
	private String label;
	private long balance;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	/**
	 * Builder de la classe Account.
	 * 
	 * @author "Grégory Coutant"
	 * 
	 */
	public static class Builder {
		private final AccountDTO accountDTO = new AccountDTO();

		/**
		 * Builder pour l'attribut accountId.
		 * 
		 * @param accountId
		 *            L'ID du compte
		 * @return Le Builder
		 */
		public Builder accountId(Integer accountId) {
			accountDTO.accountId = accountId;
			return this;
		}

		/**
		 * Builder pour l'attribut label.
		 * 
		 * @param label
		 *            Le label du compte
		 * @return Le Builder
		 */
		public Builder label(String label) {
			accountDTO.label = label;
			return this;
		}

		/**
		 * Builder pour l'attribut balance.
		 * 
		 * @param balance
		 *            Le solde du compte
		 * @return Le Builder
		 */
		public Builder balance(long balance) {
			accountDTO.balance = balance;
			return this;
		}

		/**
		 * Construit le compte.
		 * 
		 * @return Le compte
		 */
		public AccountDTO build() {
			return accountDTO;
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append("N�").append(accountId).append('\t')
				.append(label).append('\t').append(balance / 100).append(',')
				.append(balance % 100).toString();
	}
}
