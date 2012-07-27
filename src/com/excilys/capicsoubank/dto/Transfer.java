package com.excilys.capicsoubank.dto;

public class Transfer {

	private Integer accountSourceId;
	private Integer accountDestinationId;
	private long amount;

	/**
	 * Builder de la classe transfer.
	 * 
	 */
	public static class Builder {
		private final Transfer transfer = new Transfer();

		/**
		 * Builder pour l'attribut accountDestinationId.
		 * 
		 * @param accountDestinationId
		 *            Id du compte destinataire
		 * @return Le Builder
		 */
		public Builder accountDestinationId(Integer accountDestinationId) {
			transfer.accountDestinationId = accountDestinationId;
			return this;
		}

		/**
		 * Builder pour l'attribut accountSourceId.
		 * 
		 * @param accountSourceId
		 *            Id du compte source
		 * @return Le Builder
		 */
		public Builder accountSourceId(Integer accountSourceId) {
			transfer.accountSourceId = accountSourceId;
			return this;
		}

		/**
		 * Builder pour l'attribut amount.
		 * 
		 * @param amount
		 *            montant du transfer
		 * @return Le Builder
		 */
		public Builder amount(long amount) {
			transfer.amount = amount;
			return this;
		}

		public Transfer build() {
			return transfer;
		}
	}

	public Integer getAccountSourceId() {
		return accountSourceId;
	}

	public void setAccountSourceId(Integer accountSourceId) {
		this.accountSourceId = accountSourceId;
	}

	public Integer getAccountDestinationId() {
		return accountDestinationId;
	}

	public void setAccountDestinationId(Integer accountDestinationId) {
		this.accountDestinationId = accountDestinationId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
}
