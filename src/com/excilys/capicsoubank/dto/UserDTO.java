package com.excilys.capicsoubank.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	private Integer userId;
	private String username;
	private String firstname;
	private String lastname;
	private List<AccountDTO> accounts = new ArrayList<AccountDTO>();

	public static class Builder {
		private final UserDTO userDTO = new UserDTO();

		public Builder userId(Integer userId) {
			userDTO.userId = userId;
			return this;
		}

		/**
		 * Builder de l'attribut username.
		 * 
		 * @param username
		 *            Le username
		 * @return Le Builder
		 */
		public Builder username(String username) {
			userDTO.username = username;
			return this;
		}

		/**
		 * Builder de l'attribut firstname.
		 * 
		 * @param firstname
		 *            Le prénom
		 * @return Le Builder
		 */
		public Builder firstname(String firstname) {
			userDTO.firstname = firstname;
			return this;
		}

		/**
		 * Builder de l'attribut lastname.
		 * 
		 * @param lastname
		 *            Le nom de famille
		 * @return Le Builder
		 */
		public Builder lastname(String lastname) {
			userDTO.lastname = lastname;
			return this;
		}

		public Builder accounts(List<AccountDTO> accounts) {
			userDTO.accounts = accounts;
			return this;
		}

		/**
		 * Construit le User.
		 * 
		 * @return Le User
		 */
		public UserDTO build() {
			return userDTO;
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(AccountDTO accountDTO) {
		accounts.add(accountDTO);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Username : ")
				.append(username).append("\nName : ").append(firstname)
				.append(' ').append(lastname).append("\nAccounts :");
		for (AccountDTO account : accounts)
			sb.append("\n\t").append(account.toString());

		return sb.toString();
	}
}
