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
		//�ݳ�����
		if(row==N){
			count++;
			System.out.println("��"+count+"���");
			//��ӡ�˻ʺ���
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
         * ����һ�е�ÿһ��λ�ó����ŷŻʺ�
         * Ȼ����״̬�������ȫ�����ִ�еݹ麯���ڷ���һ�лʺ�
         */
        for(int i=0;i<N;i++){
            //�ڷ���һ�еĻʺ�֮ǰҪ���������һ�аڷŵļ�¼����ֹ��Ⱦ����
            for(int j=0;j<N;j++)
                chessTemp[row][j]=0;
            chessTemp[row][i]=1;
            
            if( isSafety( chessTemp,row,i ) ){
                putQueenAtRow(chessTemp,row+1);
            }
        }
	}
	 private static boolean isSafety(short[][] chess,int row,int col) {
	        //�ж����ϡ����ϡ������Ƿ�ȫ
	        int step=1;
	        while(row-step>=0){
	            if(chess[row-step][col]==1)                //����
	                return false;
	            if(col-step>=0 && chess[row-step][col-step]==1)        //����
	                return false;
	            if(col+step<N && chess[row-step][col+step]==1)        //����
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
	  * bf�㷨
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
				 System.out.println("ģʽƥ��ɹ�");
				 break;
			 }
		 }
	 }
	

  /** KMP�㷨
     *
     * @param ss ����
     * @param ps ģʽ��
     * @return ����ҵ��������������е�һ���ַ����ֵ��±꣬����Ϊ-1
     */
    public static int KMP(String ss, String ps) {
        char[] s = ss.toCharArray();
        char[] p = ps.toCharArray();

        int i = 0; // ������λ��
        int j = 0; // ģʽ����λ��
        int[] next = getNext(ps);
        while (i < s.length && j < p.length) {
            //�����j=-1�����ߵ�ǰ�ַ�ƥ��ɹ�����S[i]==P[j]��������i++��j++
            if (j == -1 || s[i] == p[j]) { // ��jΪ-1ʱ��Ҫ�ƶ�����i����ȻjҲҪ��0
                i++;
                j++;
            } else {
                //�����j!=-1���ҵ�ǰ�ַ�ƥ��ʧ�ܣ���S[i]!=P[j]��������i���䣬j=next[j]��j����i-next[j]
                j = next[j];
            }
        }
        return j == p.length ? i - j : -1;
    }

//�Ż������next������
    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            //p[k]��ʾǰ׺��p[j]��ʾ��׺
            if (k == -1 || p[j] == p[k]) {
                //��֮ǰnext�����󷨣��Ķ�������4��
                if (p[++j] == p[++k]) {
                    next[j]=next[k];// �������ַ����ʱҪ����
                } else {
                    next[j]=k;//֮ǰֻ����һ��  �ݹ�-���� ˼��   
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
