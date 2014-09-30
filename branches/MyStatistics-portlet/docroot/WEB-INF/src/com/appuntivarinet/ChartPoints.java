package com.appuntivarinet;

public class ChartPoints {
	private String x; //date
	private long y; //count
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}
	public ChartPoints(String x, long y) {
		super();
		this.x = x;
		this.y = y;
	}
	public ChartPoints() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
