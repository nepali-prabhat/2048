package com.application;
//this is for app store type of game.
import java.util.Random;

public class Random_generator {
	public static int[][] two_face_generator(int a) { //fills 2 values in our main array
		int x[][] = new int[a][a];
		int place[] = place(a);
		
		for(int i = 0;  i<4; i=i+2) {
			if(Math.random()*3<2) {
				x[place[i]][place[i+1]] = 2;
			}else {
				x[place[i]][place[i+1]] = 4;
			}
			
		}
		return x;
	}
	public static int[] place(int a) { //return 2 different place in array 
		 int place[] = new int[a]; //(0,1)(2,3)
		 Random rand = new Random();
		 do {
			 place[0]=rand.nextInt(a);
			 place[1]=rand.nextInt(a);
			 place[2]=rand.nextInt(a);
			 place[3]=rand.nextInt(a);
		 }while(place[0] == place[2] && place[1]==place[3]);
		 
		return place;
	}
	public static int[] newplace(int a) {
		int place[] = new int [a];
		 Random rand = new Random();
		 place[0]=rand.nextInt(a);
		 place[1]=rand.nextInt(a);
		return place;
	}
	public static void newvalue(int x[][], int a){
		int place[];
		do {
			place=place(a);
		}while(x[place[0]][place[1]] != 0);
			if(Math.random()*3<2) {
				x[place[0]][place[1]] = 2;
			}else {
				x[place[0]][place[1]] = 4;
			}
	}
}
