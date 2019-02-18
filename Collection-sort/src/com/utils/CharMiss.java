package com.utils;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class CharMiss {
	private HashSet<String> wordS=new HashSet<String>();
	private int row;//行
	private int col;//列
	private char[][] wordSet;
	//构造
	public CharMiss(int row,int col) {
		this.row=row;
		this.col=col;
		init();
		calc();
	}
	public CharMiss() {
		this.row=4;
		this.col=4;
		init();
		calc();
	}
	void init() {
		ThreadLocalRandom localRandom=ThreadLocalRandom.current();
		wordSet=new char[row][col];
		for (int i = 0; i < wordSet.length; i++) {
			for (int j = 0; j < wordSet[i].length; j++) {
				 wordSet[i][j]=(char)localRandom.nextInt(97,122);
				 System.out.print(wordSet[i][j]+"\t");
			}
			System.out.println();
		}
	}
	void calc(){
		V[] vs=V.values();
		StringBuffer text=new StringBuffer();
		int colrun=0;
		for (V v:vs) {
			switch (v) {
			case EAST://left-right
				  for (int i = 0; i <row; i++) {
						for (int j = 0; j <col; j++) {
							text.append(wordSet[i][j]);
							wordS.add(text.toString());
						}
						//清空
						text.setLength(0);
					}
				break;
			case SOUTHEAST:
				for(int i=0;i<row;){
					int temp=i;
					int j=colrun;
					for (;j <col;j++) {
 						text.append(wordSet[temp][j]);
						wordS.add(text.toString());
						if(i!=row-1){
							temp++;
						}else{
							text.setLength(0);
							if(j==col-1){
								temp++;
								break;
							}
						}
						if(temp==row){
							break;
						}
						
					}
					colrun++;
					if(i==(temp-1)){
						i++;
						colrun=0;
					}
					text.setLength(0);
				}
				colrun=0;
				break;
			case SOUTH:
				for (int i = 0; i <col; i++) {
					for (int j = 0; j <row; j++) {
						text.append(wordSet[j][i]);
						wordS.add(text.toString());
					}
					//清空
					text.setLength(0);
				}
				break;
			case SOUTHWEST:
				//西南
				colrun=col;
				for(int i=0;i<row;){
					int temp=i;
					int j=colrun;
					for (j=colrun-1;j>=0;j--) {
 						text.append(wordSet[temp][j]);
						wordS.add(text.toString());
						if(i!=row-1){
							temp++;
						}else{
							text.setLength(0);
							if(j==0){
								temp++;
								break;
							}
						}
						if(temp==row){
							break;
						}
						
					}
					colrun--;
					if(i==(temp-1)){
						i++;
						colrun=col;
					}
					text.setLength(0);
				}
				colrun=0;
				break;
			case WEST://right-left
					for (int i =0;i<row;i++) {
						for (int j = col-1; j>0; j--) {
							text.append(wordSet[i][j]);
							wordS.add(text.toString());
						}
						//清空
						text.setLength(0);
					}
				break;
			case NORTHWEST:
				colrun=col-1;
				for(int i=row-1;i>=0;){
					int temp=i;
					int j=colrun;
					for (;j>=0;j--) {
 						text.append(wordSet[temp][j]);
						wordS.add(text.toString());
						if(i!=0){
							temp--;
						}else{
							text.setLength(0);
							if(j==0){
								temp--;
								break;
							}
						}
						if(temp==-1){
							break;
						}
						
					}
					colrun--;
					if(i==(temp+1)){
						i--;
						colrun=col-1;
					}
					text.setLength(0);
				}
				colrun=0;
				break;
			case NORTH:
				for (int i =0; i<col; i++) {
					for (int j = row-1; j>0; j--) {
						text.append(wordSet[j][i]);
						wordS.add(text.toString());
					}
					//清空
					text.setLength(0);
				}
				break;
			case NORTHEAST:
				for(int i=row-1;i>=0;){
					int temp=i;
					int j=colrun;
					for (;j<col;j++) {
 						text.append(wordSet[temp][j]);
 						System.out.println(text.toString()+"----NORTHEAST");
						wordS.add(text.toString());
						if(i!=0){
							temp--;
						}else{
							text.setLength(0);
							if(j==col-1){
								temp--;
								break;
							}
						}
						if(temp==-1){
							break;
						}
						
					}
					colrun++;
					if(i==(temp+1)){
						i--;
						colrun=0;
					}
					text.setLength(0);
				}
				colrun=0;
				break;
			}
		}
	}
	public HashSet<String> getWordS() {
		return wordS;
	}
	public static void main(String[] args) {
		CharMiss charMiss=new CharMiss(4, 4);
		HashSet<String> words=charMiss.getWordS();
//		for(String word:words){
//			System.out.println(word);
//		}
		
	}
	//方向
	private enum V{
		EAST,SOUTHEAST,SOUTH,SOUTHWEST,WEST,NORTHWEST,NORTH,NORTHEAST;
		private String value;

		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
}
