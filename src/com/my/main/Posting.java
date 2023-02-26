package com.my.main;

public class Posting implements Comparable<Posting> {
	private String code;
	private String sum;
	private String date;
	private Status status;
	private int numAct;
	private int numMistch;
	private String warehouse;
	
	

	public Status getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public Posting() {
		this.status = Status.ARBITRAGE;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setNumAct(int numAct) {
		this.numAct = numAct;
	}

	public void setNumMistch(int numMistch) {
		this.numMistch = numMistch;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Номер отправления: ").append(code).append(System.lineSeparator()).append("Сумма отправления: ")
				.append(sum).append(System.lineSeparator()).append("Дата отправления: ").append(date)
				.append(System.lineSeparator()).append("Статус: ").append(status.getName())
				.append(System.lineSeparator()).append("Номер в акте: ");

		if (numAct != 0) {
			sb.append(numAct);
		}

		sb.append(System.lineSeparator()).append("Номер в акте о расхождениях: ");

		if (numMistch != 0) {
			sb.append(numMistch);
		}

		sb.append(System.lineSeparator()).append("Склад: ").append(warehouse);
		return sb.toString();
	}

	@Override
	public int compareTo(Posting o) {
		// TODO Auto-generated method stub
		return this.status.compareTo(o.status);
	}
}
