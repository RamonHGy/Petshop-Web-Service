/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Ramon Godoy
 */
public class Bath extends Service implements ISchedulable {
	private int id, duration;
	private String service;

	public Bath() {
	}

	public Bath(int id, String petName, String ownerName, String service, int duration, double price) {
		super(petName, ownerName, price);
		this.duration = duration;
		this.id = id;
		this.service = service;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public void performService() {
		System.out.println("Banho realizado para " + petName + ", " + "Tutor(a): " + ownerName + ", " + "Duração: "
				+ duration + " min." + ", " + "R$ " + price);
	}

	@Override
	public void schedule(String date, String time) {
		// TODO Auto-generated method stub
		System.out.println("Banho agendado para " + petName + " em " + date + " às " + time + ".");
	}

}
