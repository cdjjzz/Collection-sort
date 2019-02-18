package com.utils;

public class EightQueen {
	private static short N=11;
	private short count=0;
	short chess[][]=new short[N][N];
	public EightQueen() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				chess[i][j]=0;
//			}
//		}
//		putQueenAtRow(chess,0);
	}
	
	public void putQueenAtRow(short[][] ch,int row){
		//递出条件
		if(row==N){
			count++;
			System.out.println("第"+count+"结果");
			//打印八皇后结果
			for (int i = 0; i < ch.length; i++) {
				for (int j = 0; j < ch[i].length; j++) {
					System.out.print(ch[i][j]+"\t");
				}
				System.out.println();
			}
			return;
		}
		 
        short[][] chessTemp=chess.clone();
        
        /**
         * 向这一行的每一个位置尝试排放皇后
         * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
         */
        for(int i=0;i<N;i++){
            //摆放这一行的皇后，之前要清掉所有这一行摆放的记录，防止污染棋盘
            for(int j=0;j<N;j++)
                chessTemp[row][j]=0;
            chessTemp[row][i]=1;
            
            if( isSafety( chessTemp,row,i ) ){
                putQueenAtRow(chessTemp,row+1);
            }
        }
	}
	 private static boolean isSafety(short[][] chess,int row,int col) {
	        //判断中上、左上、右上是否安全
	        int step=1;
	        while(row-step>=0){
	            if(chess[row-step][col]==1)                //中上
	                return false;
	            if(col-step>=0 && chess[row-step][col-step]==1)        //左上
	                return false;
	            if(col+step<N && chess[row-step][col+step]==1)        //右上
	                return false;
	            
	            step++;
	        }
	        return true;
	    }
	 public static void main(String[] args) {
		EightQueen eightQueen=new EightQueen();
		eightQueen.BfSerach();
	}
	 /**
	  * bf算法
	  */
	 public void BfSerach(){
		 String sourc="abcdefg";
		 String childSource="abcx";
		 char s[]=sourc.toCharArray();
		 char c[]=childSource.toCharArray();
		 int i=0;
		 int j=0;
		 while(i<s.length&&j<c.length){
			 if(s[i]==c[j]){
				 i++;
				 j++;
			 }else{
				 i=i-j+1;
				 j=0;
			 }
			 if(j==c.length){
				 System.out.println("模式匹配成功");
				 break;
			 }
		 }
	 }
	

  /** KMP算法
     *
     * @param ss 主串
     * @param ps 模式串
     * @return 如果找到，返回在主串中第一个字符出现的下标，否则为-1
     */
    public static int KMP(String ss, String ps) {
        char[] s = ss.toCharArray();
        char[] p = ps.toCharArray();

        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        int[] next = getNext(ps);
        while (i < s.length && j < p.length) {
            //①如果j=-1，或者当前字符匹配成功（即S[i]==P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
                i++;
                j++;
            } else {
                //②如果j!=-1，且当前字符匹配失败（即S[i]!=P[j]），则令i不变，j=next[j]，j右移i-next[j]
                j = next[j];
            }
        }
        return j == p.length ? i - j : -1;
    }

//优化过后的next数组求法
    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[j] == p[k]) {
                //较之前next数组求法，改动在下面4行
                if (p[++j] == p[++k]) {
                    next[j]=next[k];// 当两个字符相等时要跳过
                } else {
                    next[j]=k;//之前只有这一行  递归-分治 思想   
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
    public int[] twoSum(int[] nums, int target) {
        int result[]=new int[]{-1,-1};
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]+nums[i+1]==target){
                result[0]=i;
                result[1]=i+1;
                break;
            }
        }
        return result;
    }
} 
