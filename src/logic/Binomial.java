package logic;

import java.text.DecimalFormat;

import java.util.ArrayList;

public class Binomial {
	public static DecimalFormat df = new DecimalFormat("#.##");
	int n = 0;
	int T = 9;
	double basisW = 100;
	double strike = 102;
	double uFactor = 1.10;
	double sFactor = 0.95;
	double bZins = 1.01;

	public ArrayList<double[]> arrayList = new ArrayList<double[]>();
	public static ArrayList<double[]> call = new ArrayList<double[]>();
	
	public Binomial(int time, double basisW, double strike, double uFactor,
			double sFactor, double bZins) {
		this.T = time;
		this.basisW = basisW;
		this.uFactor = uFactor;
		this.sFactor = sFactor;
		this.bZins = bZins;
	}
	
	public String validityCheck() {
		String result = "true";
		
		if ((T < 0) || (basisW < 0) || (uFactor < 0) || (sFactor < 0) || (bZins < 0)) {
			result = "Negative Zahlen sind nicht erlaubt!";
		}
		else if (this.T <= 0 ) {
			result = "T muss größer als 0 sein";
		}
		else if (this.basisW > this.strike) {
			result = "Basiswert muss kleiner als Ausübungspreis sein";
		}
		else if (this.uFactor < this.sFactor) {
			result = "Upfaktor muss größer als Downfaktor sein";
		}
		else if (this.bZins < this.sFactor || this.bZins > this.uFactor)
			result = "Basiszins muss größer als Downfaktor und kleiner als Upfaktor sein";
		
		return result;
	}
	
	public ArrayList<double[]> aktienPreis() {

		for (int k = 1; this.n < this.T; k++) {
			double[] arr1 = new double[k + 1];
			if (k == 1) {
				arr1[0] = basisW * sFactor;
				arr1[1] = basisW * uFactor;
				arrayList.add(arr1);
				n++;
			} else {
				int p = k;
				double[] sp = arrayList.get(p - 2);
				do {
					if (p != 0) {
						arr1[p] = sp[p - 1] * uFactor;
						p--;
					} else {
						arr1[p] = sp[p] * sFactor;
						p--;
					}
				} while (p >= 0);
				arrayList.add(arr1);
				n++;
			}
			System.out.println("ende der FOR-Schleife");
		}
		System.out.println("in the arrayList: " + toString());
		return arrayList;
	}
	
	public ArrayList<double[]> callOption() {
		int count = 0;
		call = new ArrayList<double[]>();

		for (int k = n; k >= 0; k--) {
			double[] arr1 = new double[k + 1];
		
			if (k == n) {
				int p = k;
				
				System.out.println("in the callOption() -- (k)" + (k));
				System.out.println("in the callOption() -- (p-1)" + (p-1));
				
				double[] sp = this.arrayList.get(p - 1);
				do {
					arr1[p] = sp[p] - strike;
					if (arr1[p] <= 0) {
						arr1[p] = 0;
					}
					p--;
				} while (p >= 0);
				call.add(arr1);
			} else {
				int zahl = 0;
				double[] c = call.get(count);
				
				do {
					arr1[zahl] = (1 / bZins)
							* ((((bZins - sFactor) / (uFactor - sFactor)) * c[zahl + 1]) + (((uFactor - bZins) / (uFactor - sFactor)) * c[zahl]));
					if (arr1[zahl] <= 0) {
						arr1[zahl] = 0;
					}
					zahl++;
				} 
				while (zahl <= k);
				call.add(arr1);
				count++;
			}
		}
		return call;
	}
	
	public String toString() {
		String result = "";
		for (int y = 0; y < arrayList.size(); y++) {
			double[] arr = arrayList.get(y);
			for (int i = 0; i < arr.length; i++) {
				result+= df.format(+arr[i]) + " | ";
				System.out.print(df.format(+arr[i]) + " | ");
			}
			System.out.println(" ");
		}
		return result;
	}
	
	/*public static void main(String[] args) {
//		aktienPreis();
		for (int y = 0; y < arrayList.size(); y++) {
			double[] arr = arrayList.get(y);
			for (int i = 0; i < arr.length; i++) {
				System.out.print(df.format(+arr[i]) + " | ");
			}
			System.out.println(" ");
		}
		
		callOption();
		
		for (int y = 0; y < call.size(); y++) {
			double[] arr = call.get(y);
			System.out.println(" ");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(df.format(+arr[i]) + " | ");
			}
		}
	}*/
}