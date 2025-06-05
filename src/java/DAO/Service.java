/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Ramon Godoy
 */
public abstract class Service {
	protected String petName;
	protected String ownerName;
	protected double price;

	public Service() {
	}

	public Service(String petName, String ownerName, double price) {
		this.petName = petName;
		this.ownerName = ownerName;
		this.price = price;
	}

	public abstract void performService();

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

