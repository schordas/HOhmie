package com.chordas.hohmie;

import android.graphics.Color;

public class BandSpecs {
	private int [] colorArray = {Color.BLACK,Color.parseColor("#964B00"),Color.RED,
			Color.parseColor("#FFA500"),Color.YELLOW,Color.GREEN, Color.BLUE, 
			Color.parseColor("#EE82EE"),Color.GRAY, Color.WHITE,
			Color.parseColor("#CFB53B"), Color.parseColor("silver")}; 
	
	private int [] sigFigArray = {0,1,2,3,4,5,6,7,8,9,-1,-1,-1};
	
	private double [] multiplierArray = {1,10,100, 1000, 10000, 100000, 1000000, 10000000,
			100000000, 1000000000, .1, .01, -1};
	private double [] toleranceArray = {-1, .01, .02, -1, .05, .005, .025, .001,
			.0005, -1, .05, .01, .2};
	
	public double getTolerance(int index){
		return toleranceArray[index];
	}
	
	public double getMultiplier(int index){
		return multiplierArray[index];
	}
	public int getSigFig(int index){
		return sigFigArray[index];
	}
	public int getColor(int index){
		return colorArray[index];
	}
}
