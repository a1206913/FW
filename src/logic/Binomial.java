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
	double dFactor = 0.95;
	double bZins = 1.01;

	// public ArrayList<double[]> arrayList = new ArrayList<double[]>();
	// public ArrayList<double[]> callArray = new ArrayList<double[]>();

	public Binomial(int time, double basisW, double strike, double uFactor, double dFactor, double bZins) {
		this.T = time;
		this.basisW = basisW;
		this.strike = strike;
		this.uFactor = uFactor;
		this.dFactor = dFactor;
		this.bZins = bZins;
	}

	public String validityCheck() {
		String result = "true";
		System.out.println("basisWer: " + this.basisW);
		System.out.println("strike: " + this.strike);
		if ((T < 0) || (basisW < 0) || (uFactor < 0) || (dFactor < 0) || (bZins < 0)) {
			result = "Negative Zahlen sind nicht erlaubt!";
		} else if (this.T <= 0) {
			result = "T muss größer als 0 sein";
		} else if (this.basisW > this.strike) {
			result = "Basiswert muss kleiner als Ausübungspreis sein";
		} else if (this.uFactor < this.dFactor) {
			result = "Upfaktor muss größer als Downfaktor sein";
		} else if (this.bZins < this.dFactor || this.bZins > this.uFactor)
			result = "Basiszins muss größer als Downfaktor und kleiner als Upfaktor sein";
		return result;
	}

	public ArrayList<double[]> aktienPreis() {
		ArrayList<double[]> arrayList = new ArrayList<double[]>();
		double[] start = new double[1];
		start[0] = basisW;
		arrayList.add(start);
		for (int k = 1; n < T; k++) {
			double[] arr1 = new double[k + 1];
			if (k == 1) {
				arr1[0] = basisW * dFactor;
				arr1[1] = basisW * uFactor;
				arrayList.add(arr1);
				n++;
			} else {
				int p = k;
				double[] sp = arrayList.get(p - 1);
				do {
					if (p != 0) {
						arr1[p] = sp[p - 1] * uFactor;
						p--;
					} else {
						arr1[p] = sp[p] * dFactor;
						p--;
					}
				} while (p >= 0);
				arrayList.add(arr1);
				n++;
			}
		}

		return arrayList;
	}

	public ArrayList<double[]> callOption(ArrayList<double[]> arrayList) {
		int count = 0;
		ArrayList<double[]> callArrayLocal = new ArrayList<double[]>();
		for (int k = n; k >= 0; k--) {
			double[] arr1 = new double[k + 1];
			if (k == n) {
				int p = k;
				double[] sp = arrayList.get(p);
				do {
					arr1[p] = sp[p] - strike;
					if (arr1[p] <= 0) {
						arr1[p] = 0;
					}
					p--;
				} while (p >= 0);
				callArrayLocal.add(arr1);
			} else {
				int zahl = 0;
				double[] c = callArrayLocal.get(count);
				do {
					arr1[zahl] = (1 / bZins)
							* ((((bZins - dFactor) / (uFactor - dFactor)) * c[zahl + 1]) + (((uFactor - bZins) / (uFactor - dFactor)) * c[zahl]));
					if (arr1[zahl] <= 0) {
						arr1[zahl] = 0;
					}
					zahl++;
				} while (zahl <= k);
				callArrayLocal.add(arr1);
				count++;
			}
			System.out.print(df.format(+arr1[k]) + " | ");
		}

		return callArrayLocal;
	}

	public String toString(ArrayList<double[]> arrayList, ArrayList<double[]> callList) {
		String result = " ";
		
		 for (int y = 0; y < arrayList.size(); y++) { 
			 double[] arr = arrayList.get(y); 
			 for (int i = 0; i < arr.length; i++) { 
				 result += df.format(+arr[i]) + " | "; 
				 System.out.print(df.format(+arr[i]) + " | "); 
			 }
			 System.out.println(" "); 
		 }
		  
		 System.out.println(" ");
		 System.out.println("************************************");
		  
		for (int y = 0; y < callList.size(); y++) {
			double[] arr = callList.get(y);
			System.out.println(" ");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(df.format(+arr[i]) + " | ");
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Binomial b = new Binomial(3, 100, 102, 1.10, 0.95, 1.01);
		ArrayList<double[]> aktienList = b.aktienPreis();
		ArrayList<double[]> callList = b.callOption(aktienList);
		System.out.println("************************************");
		b.toString(aktienList, callList);
		
	}
}