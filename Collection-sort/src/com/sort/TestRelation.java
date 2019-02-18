/**
 * 二元关系类及其测试
 */
package com.sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 某一集合上的二元关系类
 * 提供关系的性质判断 关系间的运算 求关系的闭包
 * 1 二元关系  R 是集合A下的子集或元素
 * 2 自反性 在关系矩阵中 主对角线全为1   关系矩阵{x,x}存在R中
 * 3 反自反性 在关系矩阵中 ，主对角线全为0
 * 4 对称性 关系矩阵中{x,n}={n,x}
 * 5 非对称性 关系矩阵中{x,n}!={n,x}
 * 6 R是集合A下的关系 R是自反， 对称，传递 R就是A的等价关系
 * 7 关联矩阵，记录点与边 的关系，关联，边由那些点组成就和那些点关联 无向图 有向图
 * 8 邻接矩阵，用于描述点与点的关系，形成一条边的两个点，我们称它们邻接。
 * 而邻接矩阵就是一个记录两点之间边数的矩阵。特别的：如果这条边是个环，也就是说它由一个点构成，只是始点和终点重合，那么我们称始点和终点相邻。
 * 9  有向图 ：每条边都有方向
 * 10 混合图 ：有些边无向，有些边有向的图
 * 11 零图：仅含孤立节点的图，节点与节点之间没有边
 * 12 平凡图：仅含一个结点的零图
 * 13 多重图：含有平行边的图，平行边就a-b ，b-a 两个节点之间有多条边
 * 14 线图：不含有平行边的图
 * 15 (n,m)图:n个节点，m条边
 * 16 正则图：各结点的度数相同的图称为正则图.各结点的度数均为k的图称为k次正则图。度数，一个节点有多少条边就是多少度 ,度数又出度与入度组成 一条边的始点是出度，终点是入度
 * 17 完全图:各个点与其他点都存在边称为完全图
 * 18 连通图，非连通图：若无向图G是平凡图或是G中任意两个顶点都是连通的，则称G是连通图；否则，称G是非连通图。
 * @author pet-lsf
 *
 */
class BinaryRelation implements Cloneable{
	public static final int AND=1;//关系的交运算
	public static final int OR=2;//关系的并运算
	public static final int NOT=3;//关系的非运算
	public static final int XOR=4;//关系的异或运算
	public static final int SUB=5;//关系的减运算
	
	//布尔方阵  m*n m=n
	private boolean[][] relation;//关系的0-1矩阵
	private int reflexivity=0;//自反性
	private int symmetry=0;//对称性
	private int irreflexivity=0;//反自反性
	private int antisymmetry=0;//反对称性
	private int transitivity=0;//传递性
	//以上变量定义0为未知，1为有，-1为无
	//二元关系  
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
	 * 设置某一个值
	 * @param i 一维索引
	 * @param j 二维索引
	 * @param v 值
	 * @return v
	 */
	public boolean setValueOf(int i,int j,boolean v){
		relation[i][j]=v;
		return v;//可以出现boolean b=setValueof(1,2,true);这种语句
	}
	/**
	 * 获取值
	 * @param i 一维索引
	 * @param j 二维索引
	 * @return 值
	 */
	public boolean getValueOf(int i,int j){
		return relation[i][j];
	}
	/**
	 * 创建对象
	 * @param m 布尔方阵
	 * @return 二元关系对象（如果布尔方阵不符条件则返回null）
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
	 * 构造方法私有化（不能直接创建对象）
	 * @param m 布尔方阵
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
	 * 布尔方阵
	 */
	/**
	 * 判断自反性
	 * @return true为自反的
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
	 * 判断反自反性
	 * @return true为反自反的
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
	 * 判断对称性性
	 * @return true为对称的
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
	 * 判断反对称性
	 * @return true为反对称的
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
	 * 判断传递性
	 * @return true为传递的
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
	 * 关系和合成运算
	 * @param r1 关系1
	 * @param r2 关系2
	 * @return 合成后的关系(若无法合成，则返回null)
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
	 * 关系自身与某一关系的运算（若无法计算则返回null）
	 * @param r 另一个关系（如果想进行非运算，填写自己）
	 * @param operation 哪种运算，对应该类的公有数据成员
	 * @return 运算后的关系（不影响原关系）
	 */
	public BinaryRelation runWith(BinaryRelation r,int operation){
		return runWith(this,r,operation);
	}
	/**
	 * 两个关系之间的运算（若无法计算则返回null）
	 * @param r1 关系1
	 * @param r2 关系2（如果想进行非运算，请填r1）
	 * @param operation 哪种运算，对应该类的公有数据成员
	 * @return 运算后的关系（不影响原关系）
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
	 * 求一关系的自反闭包
	 * @param r 关系
	 * @return 自反闭包（不影响原关系）
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
	 * 求自己的自反闭包
	 * @return 自反闭包（不影响自己）
	 */
	public BinaryRelation reflexiveClosure(){
		return reflexiveClosure(this);
	}
	/**
	 * 求一个关系的对称闭包
	 * @param r 关系
	 * @return 对称闭包(不影响原关系)
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
	 * 求自己的对称闭包
	 * @return 对称闭包（不影响自己）
	 */
	public BinaryRelation symmetricClosure(){
		return symmetricClosure(this);
	}
	/**
	 * 求一个关系的传递闭包
	 * @param r 关系
	 * @return 传递闭包(不影响原关系)
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
	 * 求自己的传递闭包
	 * @return 传递闭包（不影响自己）
	 */
	public BinaryRelation transitiveClosure(){
		return transitiveClosure(this);
	}
}
/**
 * 测试类
 */
public class TestRelation {
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("请输入关系矩阵的阶数");
			int n=Integer.parseInt(br.readLine());
			boolean[][] m=new boolean[n+1][n+1];
			boolean[][] m2=new boolean[n+1][n+1];
			System.out.println("请输入第一个关系矩阵的各个元素值");
			for(int i=1;i<=n;i++){
				StringTokenizer str=new StringTokenizer(br.readLine()," ");
				for(int j=1;j<=n;j++)
					m[i][j]=Integer.parseInt(str.nextToken())==1;
			}
			System.out.println("请输入第二个关系矩阵的各个元素值");
			for(int i=1;i<=n;i++){
				StringTokenizer str=new StringTokenizer(br.readLine()," ");
				for(int j=1;j<=n;j++)
					m2[i][j]=Integer.parseInt(str.nextToken())==1;
			}
			BinaryRelation r=BinaryRelation.getBinaryRelation(m);
			System.out.println("r的自反性");
			System.out.println(r.isReflexivity());
			System.out.println("r的对称性");
			System.out.println(r.isSymmetry());
			System.out.println("r的传递性");
			System.out.println(r.isTransitivity());
			BinaryRelation r2=BinaryRelation.getBinaryRelation(m2);
			System.out.println("r的自反闭包是");
			System.out.println(r.reflexiveClosure());
			System.out.println("r的对称闭包是");
			System.out.println(r.symmetricClosure());
			System.out.println("r的传递闭包是");
			System.out.println(r.transitiveClosure());
			System.out.println("r2是反对称的？");
			System.out.println(r2.isAntisymmetry());
			System.out.println("r和r2的并关系是");
			System.out.println(r.runWith(r2,BinaryRelation.OR));
		}catch(Exception e){
			System.out.println("出现异常，程序被迫退出");
		}
	}
}
