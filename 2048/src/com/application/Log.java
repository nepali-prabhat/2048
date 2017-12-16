package com.application;
//this class gives score and highest number
public class Log {
	public int get_highest_no(int x[][]) {
		int temp_no = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				temp_no = Math.max(temp_no, x[i][j]);
			}
		}
	return temp_no;
	}
}