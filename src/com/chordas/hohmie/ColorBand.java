package com.chordas.hohmie;

import android.graphics.Color;


public class ColorBand {
	
	private int color;
	private int sigFig;
	private int index;
	private double multiplier;
	private double tolerance;
	
	public ColorBand(){
		
	}
	public ColorBand(int color, double multiplier, double tolerance, int sigFig, int index){
		this.color = color;
		this.index = index;
		this.multiplier = multiplier;
		this.tolerance = tolerance;
		this.sigFig = sigFig;
	}
	
	public void setFields(int color, double multiplier, double tolerance, int sigFig, int index){
		this.color = color;
		this.sigFig = sigFig;
		this.multiplier = multiplier;
		this.tolerance = tolerance;
		this.index = index;
	}
	
	public void setSigFig(int sigFig){
		this.sigFig = sigFig;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	
	public int getColor() {
		return color;
	}

	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public double getTolerance() {
		return tolerance;
	}
	

}
