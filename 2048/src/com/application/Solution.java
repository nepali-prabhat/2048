package com.application;

import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	public static int a = 4;
	public static int score;
	public static void main(String args[]) {
		Log obj = new Log();
		Scanner scan = new Scanner(System.in);
		int x[][] = new int[a][a];
		String statuS;
		x = Random_generator.two_face_generator(a);
		move.prinT(x, "WELCOME TO THE GAME \n ");
		do {
			System.out.println(" W=up \t D=right \n S=down \t A=left");
			System.out.println("Enter Your Move or Press \'e\' to exit.\n");
			statuS=scan.next().toLowerCase();
			if(statuS.equals("e")) {
				break;
			}
				if(statuS.equals("s")) {
					x=move.downM(x);
					move.prinT(x, "Moved Down");
					System.out.println("SCORE:"+score);
				}else if(statuS.equals("w")) {
					x=move.upM(x);
					System.out.println("SCORE:"+score);
				}else if(statuS.equals("d")) {
					x=move.rightM(x);
					System.out.println("SCORE:"+score);
				}else if(statuS.equals("a")) {
					x=move.leftM(x);
					System.out.println("SCORE:"+score);
				}else {
					System.out.println("Invalid Input");
				}

		}while(true);
		System.out.println("SCORE:"+score);
		System.out.println("HIGHEST NO REACHED\t"+obj.get_highest_no(x));
		scan.close();
	}
}

class random {
	public static int[][] generator(int a) {
		int x[][] = new int[a][a];
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				double ran = Math.random() * 4;
				if (ran > 2) {
					x[i][j] = 4;
				} else
					x[i][j] = 2;
			}
		}
		return x;
	}

	public static int[][] statusGenerator(int a) {
		int x[][] = new int[a][a];
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				x[i][j] = 0;
			}
		}
		return x;
	}
}

class move {
	public static void prinT(int x[][], String message) {
		Log obj = new Log();
		System.out.println(message);
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if(x[i][j]==0) {
					System.out.print("- \t");
				}else
				System.out.print(x[i][j] + "\t");
			}
			System.out.println();
			System.out.println();
		}
	}
	public static int[][] downM(int x[][]) {
		x = Shift.shiftdown(x);
		int a = x.length;
		int seg1[], seg2[] = new int[a];
		int comp[];
		int status[][];
		int dummy = a;
		for(int i = 0; i<(a-1) ; i++) {
			status =  random.statusGenerator(a);
			seg1 = segregate.segR(x, a, --dummy); // 1  // 2
			seg2 = segregate.segR(x, a, --dummy); //0   //1
			++dummy;
			comp = check.checkseg(seg1, seg2, a);
			seg1 = add.adder(comp, a, seg1, seg2);
			x = add.addUpadte(x, seg1);
			status = Status.statusUpdate(status, comp, seg2[0]);
			x=Transform.columnDown(x, status, seg2[0]);
		}
		Random_generator.newvalue(x, a);
		return x;
	}
	public static int[][] rightM(int x[][]) {
		x = Transform.anticlk90(x);
		x = downM(x);
		for(int i = 1; i<=3 ; i++) {
			x = Transform.anticlk90(x);
		}
		prinT(x, "Right");
		return x;
	}
	public static int[][] leftM(int x[][]){
		for(int i = 1; i<=3 ; i++) {
			x = Transform.anticlk90(x);
		}
		x = downM(x);
		x = Transform.anticlk90(x);
		prinT(x, "Left");
		return x;
	}
	public static int[][] upM(int x[][]){
		for(int i = 1; i<=2 ; i++) {
			x = Transform.anticlk90(x);
		}
		x = downM(x);
		for(int i = 0; i<2 ; i++) {
			x = Transform.anticlk90(x);
		}
		prinT(x, "Up");
		return x;
	}
}

class segregate {
	// a is size of square q is row to segregate
	public static int[] segR(int x[][], int a, int q) {
		int seg1[] = new int[a + 1];
		seg1[0] = q;
		for (int j = 0; j < a; j++) {
			seg1[j + 1] = x[q][j];
		}
		return seg1;
	}
	public static int[] segC(int x[][], int a, int q) {
		int seg1[] = new int[a + 1];
		seg1[0] = q;
		for (int i = 0; i < a; i++) {
			seg1[i + 1] = x[i][q];
		}
		return seg1;
	}
}

class check {
	public static int[] checkseg(int x[], int y[], int a) {
		int stats[] = new int[a];
		for (int i = 1; i <= a; i++) {
			if (x[i] == y[i]) {
				stats[i - 1] = 1;
			} else
				stats[i - 1] = 0;
		}
		return stats;
	}
}

class add {
	
	public static int[] adder(int comp[], int a, int seg1[], int seg2[]) { // add the similar value.		
		for (int i = 0; i < comp.length; i++) {
			if (comp[i] == 1) {
				seg1[i + 1] += seg2[i + 1];
				Solution.score += seg1[i+1];
			}
		}
		return seg1;
	}

	public static int[][] addUpadte(int x[][], int seg1[]) { // replaces the main array
		for (int i = 0; i < (seg1.length - 1); i++) {
			x[seg1[0]][i] = seg1[i + 1];
		}
		return x;
	}

}

class Status {
	public static int[][] statusUpdate(int stat[][], int comp[], int row) {
		for (int j = 0; j < comp.length; j++) {
			stat[row][j] = comp[j];
		}
		return stat;
	}
}

class Transform {
	public static int[][] columnDown(int x[][], int status[][], int row){
		int dum;
		if(row!=0) {
			for(int i = 0; i<x.length; i++) {
				if(status[row][i]==1){
					dum= row;
					while(dum>=0) {
						if(dum>0) {
							x[dum][i] = x[dum-1][i];
						}else {
							x[dum][i]=0;
						}
						dum--;
					}
				}
			}
		}else {
			for(int i = 0; i<x.length; i++) {
				if(status[row][i]==1){
					x[row][i]=0;
				}
			}
		}
		return x;
	}
	public static int[][] anticlk90(int x[][]){
		int xPrime[][]  = random.statusGenerator((x.length));
		int seg1[]= new int[x.length];
		int pointer = x.length;
		for(int i=0;i<x.length;i++) {	//0
			seg1=segregate.segC(x, x.length, i); //0 2444
			for(int j = 0; j<x.length;j++) {  //1
				xPrime[i][j]=seg1[pointer];  //4
				pointer--; //3
			}
			pointer = x.length;
		}
		return xPrime;
	}
	
}
class Shift{
	 static int[][] shiftdown(int x[][] ){
		int b = x.length;
		int column[];
		for(int i = 0; i<b; i++) {
			column = segregate.segC(x,b ,i);
			column = sort(column);
			x = addUpdateC(x,column,i);
		}
		return x;
	 }
	private static int[] sort(int column[]) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 1; i<column.length; i++) {
			if(column[i]==0) {
				arr.add(0);
			}
		}
		for(int i = 1; i<column.length; i++) {
			if(column[i]!=0) {
				arr.add(column[i]);
			}
		}
		column = toIntArray(arr);
		return column;
	}
	private static int[] toIntArray(ArrayList<Integer> arr) {
		int[] column = new int[arr.size()];
		for(int i=0; i<arr.size();i++) {
			column[i] = arr.get(i);
		}
		return column;
	}
	private static int[][] addUpdateC (int x[][], int temp[],int index){
		for(int i=0; i < temp.length; i++) {
			x[i][index]=temp[i];
		}
		
		return x;
	}
}























