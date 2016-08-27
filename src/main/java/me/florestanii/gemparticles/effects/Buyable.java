package me.florestanii.gemparticles.effects;

public abstract class Buyable {

	protected int cost;
	
	public Buyable(int cost) {
		this.cost = cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
}
