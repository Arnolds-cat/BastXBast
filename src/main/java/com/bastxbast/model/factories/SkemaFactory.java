package com.bastxbast.model.factories;

import java.util.Arrays;
import java.util.Scanner;

import com.bastxbast.exceptions.SkemaException;
import com.bastxbast.model.Skema;

public class SkemaFactory {
	
	public static Skema getSkema(int nEvents, int nExpected, float share, int initialCash) throws SkemaException{
		
		if(nEvents > 100)
			throw new SkemaException("Number of events must be less than 100");
		if(share<=1)
			throw new SkemaException("Invalid share");
		if(nEvents<=nExpected)
			throw new SkemaException("Invalid number of events");
			
		Skema res = new Skema(nEvents, nExpected, initialCash, share);
		res.setCurrentCash(initialCash);	
		float finalCash = getFinalCash(nEvents, nExpected, share, initialCash);
		res.setFinalCash(finalCash);
		res.setRoundedFinalCash(round(finalCash));
		return res;
	}

	private static int round(float finalCash) {
		
		int rounded = 0;
		
		if(finalCash<=100){
			rounded = (int) Math.floor(0.99*finalCash);
			
		} else if (finalCash <= 301) {
			rounded = (int) Math.floor(0.98*finalCash);
			int rem = rounded%10;
			switch(rem){
			case 0:
			case 1:
			case 2:
				rounded -= rem;
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				rounded = rounded - rem + 5;
				break;
			case 8:
			case 9:
				rounded = rounded - rem + 10;
			}
		} else if (finalCash <= 501){
			rounded = (int) Math.floor(0.97*finalCash);
			rounded = rounded - rounded%10;
		} else if (finalCash <= 1000) {
			rounded = (int) Math.floor(0.96*finalCash);
			rounded = rounded - rounded%10;
		} else if (finalCash <= 3000) {
			rounded = (int) Math.floor(0.95*finalCash);
			rounded = rounded - rounded%100;
		} else if (finalCash <= 5000) {
			rounded = (int) Math.floor(0.93*finalCash);
			rounded = rounded - rounded%100;
		} else if (finalCash <= 10000) {
			rounded = (int) Math.floor(0.90*finalCash);
			rounded = rounded - rounded%100;
		} else {
			rounded = (int) Math.floor(0.85*finalCash);
			rounded = rounded - rounded%1000;
		}
		
		return rounded;
	}

	private static double[][] generateMatrix(int nEvents, int nExpected, float share) {
		double[][] matrix = new double[nEvents][nExpected+1];
		for(int i=0; i<nEvents; i++)
			matrix[i][nExpected] = 1.;
		for(int j=nExpected-1; j>=0; j--)
			for(int i=nEvents+j-nExpected; i>=0; i--){
				if(i-j==nEvents-nExpected){ 
					matrix[i][j]=Math.pow(share, nExpected-j);
				} else {
					matrix[i][j] = share*matrix[i+1][j]*matrix[i+1][j+1]/(matrix[i+1][j]+(share-1)*(matrix[i+1][j+1]));
				}
			}
		return matrix;
	}
	
	private static float getFinalCash(int nEvents, int nExpected, float share, int initialCash) throws SkemaException{
		double[] back = new double[nEvents];
		double[] front = new double[nEvents];
		for(int i=0; i<nEvents; i++){
			front[i] = 1.;
		}
		for(int i = nExpected-1; i>=0; i--){
			int j = nExpected - i;
			back[nEvents-j] = Math.pow(share, j);
			for(int k = nEvents-j - 1; k>=0; k--){
				back[k] = (share*back[k+1]*front[k+1])/(back[k+1]+(share-1)*front[k+1]);
			}
			for(int l=0; l<nEvents; l++){
				front[l] = back[l];
			}
		}
		if(Double.isNaN(back[0]))
			throw new SkemaException("No skema available with this data. Please contact the admin.");
		return (float)(initialCash*back[0]);
	}
	
	public static float getNextBetAmount(int nEvents, int nExpected, float share, float currentCash, int nPlayed, int nWon){
		
		int end = nWon;
		
		double[] back = new double[nEvents+1];
		double[] front = new double[nEvents+1];
		for(int i=0; i<nEvents; i++){
			front[i] = 1.;
		}
		for(int i = nExpected-1; i>=end; i--){
			int j = nExpected - i;
			back[nEvents-j] = Math.pow(share, j);
			for(int k = nEvents-j - 1; k>=0; k--){
				back[k] = (share*back[k+1]*front[k+1])/(back[k+1]+(share-1)*front[k+1]);
			}
			if(i > end){
				for(int l=0; l<nEvents; l++){
					front[l] = back[l];
				}
			}
			
		}
		
		return (float)((1-share*front[nPlayed+1]/(back[nPlayed+1]+(share-1)*front[nPlayed+1]))*currentCash);
	}
	
	public static void main1(String... a) throws SkemaException{
		
		System.out.println(Float.MAX_VALUE);
		int nEvents = 100;
		int nExpected = 55;
		float share = 2.5f;
		int initialCash = 100;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Memoria libera: " + (runtime.freeMemory() / Math.pow(2, 20)));
		double[][] matrix = generateMatrix(nEvents, nExpected, share);
		System.out.println("Masaniello matrice: " + initialCash*matrix[0][0]);
		System.out.println("Masaniello fast: " + getFinalCash(nEvents, nExpected, share, initialCash));
		System.out.println(round((float)(initialCash*matrix[0][0])));
		System.out.println("########## SIMULAZIONE ##################");
		Scanner scanner = new Scanner( System.in );
		int step = 1;
		int nWon = 0;
		int nPlayed = 0;
		float currentCash = initialCash;
		while(step<=20){
			float amount = getNextBetAmount(nEvents, nExpected, share, currentCash, nPlayed, nWon);
			System.out.println("Gioca: " + amount);
			System.out.print( "Scrivi 0 se persa, 1 se vinta:" );
			int res = scanner.nextInt();
			if(res==0){
				currentCash -= amount;
				System.out.println("Nuova cassa:" + currentCash);
				nPlayed++;
			} else if(res==1){
				currentCash += (amount*(share-1));
				System.out.println("Nuova cassa:" + currentCash);
				nPlayed++;
				nWon++;
			} else {
				System.out.println("Errore");
				return;
			}
			step++;
		}
		
		
		System.out.println("Memoria libera: " + (runtime.freeMemory() / Math.pow(2, 20)));
	}

	public static void main(String... a) throws SkemaException{
		
		System.out.println(round(43));
		System.out.println(round(199));
		System.out.println(round(386));
		System.out.println(round(769));
		System.out.println(round(2670));
		System.out.println(round(3800));
		System.out.println(round(6700));
		System.out.println(round(55000));
	}
}
