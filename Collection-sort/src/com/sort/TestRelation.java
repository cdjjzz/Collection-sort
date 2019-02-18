/**
 * ��Ԫ��ϵ�༰�����
 */
package com.sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ĳһ�����ϵĶ�Ԫ��ϵ��
 * �ṩ��ϵ�������ж� ��ϵ������� ���ϵ�ıհ�
 * 1 ��Ԫ��ϵ  R �Ǽ���A�µ��Ӽ���Ԫ��
 * 2 �Է��� �ڹ�ϵ������ ���Խ���ȫΪ1   ��ϵ����{x,x}����R��
 * 3 ���Է��� �ڹ�ϵ������ �����Խ���ȫΪ0
 * 4 �Գ��� ��ϵ������{x,n}={n,x}
 * 5 �ǶԳ��� ��ϵ������{x,n}!={n,x}
 * 6 R�Ǽ���A�µĹ�ϵ R���Է��� �Գƣ����� R����A�ĵȼ۹�ϵ
 * 7 �������󣬼�¼����� �Ĺ�ϵ��������������Щ����ɾͺ���Щ����� ����ͼ ����ͼ
 * 8 �ڽӾ����������������Ĺ�ϵ���γ�һ���ߵ������㣬���ǳ������ڽӡ�
 * ���ڽӾ������һ����¼����֮������ľ����ر�ģ�����������Ǹ�����Ҳ����˵����һ���㹹�ɣ�ֻ��ʼ����յ��غϣ���ô���ǳ�ʼ����յ����ڡ�
 * 9  ����ͼ ��ÿ���߶��з���
 * 10 ���ͼ ����Щ��������Щ�������ͼ
 * 11 ��ͼ�����������ڵ��ͼ���ڵ���ڵ�֮��û�б�
 * 12 ƽ��ͼ������һ��������ͼ
 * 13 ����ͼ������ƽ�бߵ�ͼ��ƽ�б߾�a-b ��b-a �����ڵ�֮���ж�����
 * 14 ��ͼ��������ƽ�бߵ�ͼ
 * 15 (n,m)ͼ:n���ڵ㣬m����
 * 16 ����ͼ�������Ķ�����ͬ��ͼ��Ϊ����ͼ.�����Ķ�����Ϊk��ͼ��Ϊk������ͼ��������һ���ڵ��ж������߾��Ƕ��ٶ� ,�����ֳ����������� һ���ߵ�ʼ���ǳ��ȣ��յ������
 * 17 ��ȫͼ:�������������㶼���ڱ߳�Ϊ��ȫͼ
 * 18 ��ͨͼ������ͨͼ��������ͼG��ƽ��ͼ����G�������������㶼����ͨ�ģ����G����ͨͼ�����򣬳�G�Ƿ���ͨͼ��
 * @author pet-lsf
 *
 */
class BinaryRelation implements Cloneable{
	public static final int AND=1;//��ϵ�Ľ�����
	public static final int OR=2;//��ϵ�Ĳ�����
	public static final int NOT=3;//��ϵ�ķ�����
	public static final int XOR=4;//��ϵ���������
	public static final int SUB=5;//��ϵ�ļ�����
	
	//��������  m*n m=n
	private boolean[][] relation;//��ϵ��0-1����
	private int reflexivity=0;//�Է���
	private int symmetry=0;//�Գ���
	private int irreflexivity=0;//���Է���
	private int antisymmetry=0;//���Գ���
	private int transitivity=0;//������
	//���ϱ�������0Ϊδ֪��1Ϊ�У�-1Ϊ��
	//��Ԫ��ϵ  
	public String toString(){
		StringBuffer str=new StringBuffer();
		for(int i=1;i<relation.length;i++){
			for(int j=1;j<relation.length;j++){
				str.append(relation[i][j]?1+" ":0+" ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	/**
	 * ����ĳһ��ֵ
	 * @param i һά����
	 * @param j ��ά����
	 * @param v ֵ
	 * @return v
	 */
	public boolean setValueOf(int i,int j,boolean v){
		relation[i][j]=v;
		return v;//���Գ���boolean b=setValueof(1,2,true);�������
	}
	/**
	 * ��ȡֵ
	 * @param i һά����
	 * @param j ��ά����
	 * @return ֵ
	 */
	public boolean getValueOf(int i,int j){
		return relation[i][j];
	}
	/**
	 * ��������
	 * @param m ��������
	 * @return ��Ԫ��ϵ��������������󲻷������򷵻�null��
	 */
	public static BinaryRelation getBinaryRelation(boolean[][] m){
		if(m==null)
			return null;
		if(m.length<2)
			return null;
		if(m.length!=m[0].length)
			return null;
		return new BinaryRelation(m);
	}
	/**
	 * ���췽��˽�л�������ֱ�Ӵ�������
	 * @param m ��������
	 */
	private BinaryRelation(boolean[][] m){
		relation=m;
	}
	public Object clone(){
		boolean[][] m=new boolean[relation.length][relation.length];
		for(int i=1;i<relation.length;i++){
			for(int j=1;j<relation.length;j++){
				m[i][j]=relation[i][j];
			}
		}
		BinaryRelation r=new BinaryRelation(m);
		r.antisymmetry=this.antisymmetry;
		r.irreflexivity=this.irreflexivity;
		r.reflexivity=this.reflexivity;
		r.symmetry=this.symmetry;
		r.transitivity=this.transitivity;
		return r;
	}
	/**
	 * ��������
	 */
	/**
	 * �ж��Է���
	 * @return trueΪ�Է���
	 */
	public  boolean isReflexivity(){
		if(reflexivity<0)
			return false;
		else if(reflexivity>0)
			return true;
		for(int i=1;i<relation.length;i++){
			if(relation[i][i]==false){
				reflexivity=-1;
				return false;
			}
		}
		reflexivity=1;
		return true;
	}
	/**
	 * �жϷ��Է���
	 * @return trueΪ���Է���
	 */
	public boolean isIrreflexivity(){
		if(irreflexivity<0)
			return false;
		else if(irreflexivity>0)
			return true;
		for(int i=1;i<relation.length;i++){
			if(relation[i][i]==true){
				irreflexivity=-1;
				return false;
			}
		}
		irreflexivity=1;
		return true;
	}
	/**
	 * �ж϶Գ�����
	 * @return trueΪ�ԳƵ�
	 */
	public boolean isSymmetry(){
		if(symmetry<0)
			return false;
		else if(symmetry>0)
			return true;
		for(int i=1;i<relation.length;i++){
			for(int j=i;j<relation.length;j++){
				if(relation[i][j]!=relation[j][i]){
					symmetry=-1;
					return false;
				}
			}
		}
		symmetry=1;
		return true;
	}
	/**
	 * �жϷ��Գ���
	 * @return trueΪ���ԳƵ�
	 */
	public boolean isAntisymmetry(){
		if(antisymmetry<0)
			return false;
		else if(antisymmetry>0)
			return true;
		for(int i=1;i<relation.length;i++){
			for(int j=i;j<relation.length;j++){
				if(relation[i][j]==relation[j][i]){
					antisymmetry=-1;
					return false;
				}
			}
		}
		antisymmetry=1;
		return true;
	}
	/**
	 * �жϴ�����
	 * @return trueΪ���ݵ�
	 */
	public boolean isTransitivity(){
		if(transitivity<0)
			return false;
		else if(transitivity>0)
			return true;
		BinaryRelation r=composeWith(this,this);
		for(int i=1;i<relation.length;i++){
			for(int j=1;j<relation.length;j++){
				if(r.relation[i][j]){
					if(!this.relation[i][j]){
						transitivity=-1;
						return false;
					}
				}
			}
		}
		transitivity=1;
		return true;
	}
	/**
	 * ��ϵ�ͺϳ�����
	 * @param r1 ��ϵ1
	 * @param r2 ��ϵ2
	 * @return �ϳɺ�Ĺ�ϵ(���޷��ϳɣ��򷵻�null)
	 */
	public static BinaryRelation composeWith(BinaryRelation r1,BinaryRelation r2){
		if(r1.relation.length!=r2.relation.length)
			return null;
		boolean[][] m=new boolean[r1.relation.length][r1.relation.length];
		for(int i=1;i<r1.relation.length;i++){
			for(int j=1;j<r1.relation.length;j++){
				m[i][j]=false;
				for(int k=1;k<r1.relation.length;k++){
					if(r1.relation[i][k] && r2.relation[k][j]){
						m[i][j]=true;
						break;
					}
				}
			}
		}
		return new BinaryRelation(m);
	}
	/**
	 * ��ϵ������ĳһ��ϵ�����㣨���޷������򷵻�null��
	 * @param r ��һ����ϵ���������з����㣬��д�Լ���
	 * @param operation �������㣬��Ӧ����Ĺ������ݳ�Ա
	 * @return �����Ĺ�ϵ����Ӱ��ԭ��ϵ��
	 */
	public BinaryRelation runWith(BinaryRelation r,int operation){
		return runWith(this,r,operation);
	}
	/**
	 * ������ϵ֮������㣨���޷������򷵻�null��
	 * @param r1 ��ϵ1
	 * @param r2 ��ϵ2���������з����㣬����r1��
	 * @param operation �������㣬��Ӧ����Ĺ������ݳ�Ա
	 * @return �����Ĺ�ϵ����Ӱ��ԭ��ϵ��
	 */
	public static BinaryRelation runWith(BinaryRelation r1,BinaryRelation r2,int operation){
		if(r1.relation.length!=r2.relation.length)
			return null;
		boolean[][] m=new boolean[r1.relation.length][r1.relation.length];
		for(int i=1;i<m.length;i++){
			for(int j=1;j<m.length;j++){
				if(operation==AND){
					m[i][j]=r1.relation[i][j] & r2.relation[i][j];
				}
				else if(operation==OR){
					m[i][j]=r1.relation[i][j] | r2.relation[i][j];
				}
				else if(operation==XOR){
					m[i][j]=r1.relation[i][j] ^ r2.relation[i][j];
				}
				else if(operation==SUB){
					if(r1.relation[i][j])
						m[i][j]=r2.relation[i][j];
					else
						m[i][j]=false;
				}
				else if(operation==NOT)
					m[i][j]=!r2.relation[i][j];
				else
					return null;
			}
		}
		return new BinaryRelation(m);
	}
	/**
	 * ��һ��ϵ���Է��հ�
	 * @param r ��ϵ
	 * @return �Է��հ�����Ӱ��ԭ��ϵ��
	 */
	public static BinaryRelation reflexiveClosure(BinaryRelation r){
		BinaryRelation r2=(BinaryRelation)r.clone();
		if(r.reflexivity>0)
			return r2;
		for(int i=1;i<r2.relation.length;i++)
			r2.relation[i][i]=true;
		r2.reflexivity=1;
		return r2;
	}
	/**
	 * ���Լ����Է��հ�
	 * @return �Է��հ�����Ӱ���Լ���
	 */
	public BinaryRelation reflexiveClosure(){
		return reflexiveClosure(this);
	}
	/**
	 * ��һ����ϵ�ĶԳƱհ�
	 * @param r ��ϵ
	 * @return �ԳƱհ�(��Ӱ��ԭ��ϵ)
	 */
	public static BinaryRelation symmetricClosure(BinaryRelation r){
		BinaryRelation r2=(BinaryRelation)r.clone();
		if(r.symmetry>0)
			return r2;
		for(int i=1;i<r2.relation.length;i++){
			for(int j=i;j<r2.relation.length;j++){
				if(r2.relation[i][j] || r2.relation[j][i])
					r2.relation[i][j]=r2.relation[j][i]=true;
			}
		}
		r2.symmetry=1;
		return r2;
	}
	/**
	 * ���Լ��ĶԳƱհ�
	 * @return �ԳƱհ�����Ӱ���Լ���
	 */
	public BinaryRelation symmetricClosure(){
		return symmetricClosure(this);
	}
	/**
	 * ��һ����ϵ�Ĵ��ݱհ�
	 * @param r ��ϵ
	 * @return ���ݱհ�(��Ӱ��ԭ��ϵ)
	 */
	public static BinaryRelation transitiveClosure(BinaryRelation r){
		BinaryRelation r2=(BinaryRelation)r.clone();
		if(r.transitivity>0)
			return r2;
		for(int i=1;i<r2.relation.length;i++){
			for(int j=1;j<r2.relation.length;j++){
				if(r2.relation[j][i]){
					for(int k=1;k<r2.relation.length;k++)
						r2.relation[j][k]=r2.relation[j][k] | r2.relation[i][k];
				}
			}
		}
		r2.transitivity=1;
		return r2;
	}
	/**
	 * ���Լ��Ĵ��ݱհ�
	 * @return ���ݱհ�����Ӱ���Լ���
	 */
	public BinaryRelation transitiveClosure(){
		return transitiveClosure(this);
	}
}
/**
 * ������
 */
public class TestRelation {
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("�������ϵ����Ľ���");
			int n=Integer.parseInt(br.readLine());
			boolean[][] m=new boolean[n+1][n+1];
			boolean[][] m2=new boolean[n+1][n+1];
			System.out.println("�������һ����ϵ����ĸ���Ԫ��ֵ");
			for(int i=1;i<=n;i++){
				StringTokenizer str=new StringTokenizer(br.readLine()," ");
				for(int j=1;j<=n;j++)
					m[i][j]=Integer.parseInt(str.nextToken())==1;
			}
			System.out.println("������ڶ�����ϵ����ĸ���Ԫ��ֵ");
			for(int i=1;i<=n;i++){
				StringTokenizer str=new StringTokenizer(br.readLine()," ");
				for(int j=1;j<=n;j++)
					m2[i][j]=Integer.parseInt(str.nextToken())==1;
			}
			BinaryRelation r=BinaryRelation.getBinaryRelation(m);
			System.out.println("r���Է���");
			System.out.println(r.isReflexivity());
			System.out.println("r�ĶԳ���");
			System.out.println(r.isSymmetry());
			System.out.println("r�Ĵ�����");
			System.out.println(r.isTransitivity());
			BinaryRelation r2=BinaryRelation.getBinaryRelation(m2);
			System.out.println("r���Է��հ���");
			System.out.println(r.reflexiveClosure());
			System.out.println("r�ĶԳƱհ���");
			System.out.println(r.symmetricClosure());
			System.out.println("r�Ĵ��ݱհ���");
			System.out.println(r.transitiveClosure());
			System.out.println("r2�Ƿ��ԳƵģ�");
			System.out.println(r2.isAntisymmetry());
			System.out.println("r��r2�Ĳ���ϵ��");
			System.out.println(r.runWith(r2,BinaryRelation.OR));
		}catch(Exception e){
			System.out.println("�����쳣���������˳�");
		}
	}
}
