package com.inner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class Outer<T extends Number,U> {
	//���Ͳ��� ���������̶�
	private T t; //jvm �����Object  java������ʵ���﷨��  α����  �����ͨ�����Ͳ��� ��T cast Object,���õİ�ȫ�ԣ����õ��Ķ�
	// extends ��������Ϊ Number 
	//���� ���ΪT ��super T
	//���ޱ�ʾ���������� �Ǹ����ͺ������� extends
	//���ޱ�ʾ���������� �Ǹ����ͺ͸�����-��Object  super
	//<T extends E>
	//<? extends E>  ͨ���
	//<T extends E>���ڶ������Ͳ�������������һ�����Ͳ���T���ɷ��ڷ����ඨ�����������桢���ͷ�������ֵǰ�档
	//<? extends E>����ʵ�������Ͳ�����������ʵ�������ͱ����е����Ͳ�����ֻ���������������δ֪�ģ�ֻ֪������E��E��ĳ�������͡�
	//���Ͳ������������̶�
	//<?>��<? extends E>����ʵ�ָ�Ϊ���Ķ�ȡ�����ǿ��������Ͳ�������ʽ�������ͨ�����ʽ��Ϊ��ࡣ 
	//<? super E>����ʵ�ָ�Ϊ����д��ͱȽϣ����ܱ����Ͳ�����ʽ�����
	//�������
	private U u;
	//�ɽ��ܶ������
	private static String shard ="��ʢ��";
	private String  name="select";
	/**
	 * ��̬�ڲ������ⲿ���ϵ���󣬾�̬�ڲ�����Է����ⲿ��̬����,���ܷ���ʵ��������
	 * ���ⲿ�����ڲ��������ͬ�ı�����Ĭ�Ϸ����ڲ������
	 * ��̬�ڲ���ֻ��ֱ�ӷ�����ó��̬����
	 * ����ֱ��new ����ʵ����
	 * access$0
	 * @author pet-lsf
	 *
	 */
	public static class inner{
		
		public static String shard="���";
		public static void main(String[] args) {
			System.out.println(shard);
		}
	   public void print(){
		   System.out.println("ashdashd");
	   }
		
	}
	/**
	 * ��Ա�ڲ��࣬������̬��������̬������ֻ����ʵ������������
	 * �ڲ���������ֱ�ӷ��ʾ�̬�����ͳ�Ա����
	 * ʹ��new �ⲿ��.new �ڲ���ʵ����
	 * Outer$Inner
	 * ����ڲ������ⲿ���ϵ���У��Ҳ����������ⲿ��ʵ�������ͷ���������Կ��Ƕ���Ϊ��Ա�ڲ��ࡣ
	 * �ⲿ���һЩ�����ķ���ֵ������ĳ���ӿڣ�Ϊ�˷�������ӿڣ��ⲿ�෽������ʹ���ڲ���ʵ������ӿڣ�����ڲ�����Ա���Ϊprivate��������ȫ���ء�
	 * �������ط���ʵ��ϸ�� 
	 * @author pet-lsf
	 *
	 */
	public class inner1{
		public void print(){
			System.out.println(shard);
			System.out.println(name);
		}
	}
	public static void main(String[] args) {
		//List<String> las[]=new ArrayList<String>[10];
		//���������� 
		Class<String> clas=String.class;
		Type type=clas.getSuperclass();
		System.out.println(type);
	}
	public   void compar(){
		/**
		 * �������ڲ���ֻ���ڶ���ķ����ڱ�ʹ�á�
		 * ���������ʵ������������˾�̬�����ͷ������ڲ��໹����ֱ�ӷ����ⲿ���ʵ�������ͷ�����
		 * ��innerMethodֱ�ӷ������ⲿ˽��ʵ������a����������Ǿ�̬�������򷽷��ڲ���ֻ�ܷ����ⲿ��ľ�̬�����ͷ�����
		 * �����ڲ��໹����ֱ�ӷ��ʷ����Ĳ����ͷ����еľֲ���������������Щ�������뱻����Ϊfinal��
		 * ��innerMethodֱ�ӷ����˷�������param�;ֲ�����str��
		 * @author pet-lsf
		 *���ʷ����ı���ֻ�ܷ���final����
		 */
		final int str=0;
		// ʵ���� access$0
		//�����޸ľֲ�����
		//���ö��� �� ���� ���� ���� �޸�
		final int mm[]={1,2};
		class ss{
			/**
			 * ���Է����ⲿ�� ʵ������������,��̬����
			 */
		   public  void print(){
			   System.out.println(name);
			   System.out.println(shard);
			   System.out.println(str);
			   mm[0]=10;
		   }
		}
		ss ss=new ss();
		ss.print();
		System.out.println(mm[0]);
	}
	public static void compar11(){
		  class ss1{
			 public void init(){
				 //System.out.println("��ã�������ʢ��");
				 //һ���ַ�==2���ֽ� ��Ӧutf-16 ֻ�ܱ�ʾ0-ffff bmp ��Ӧ�������� ��Ҫ2��char �ĸ��ֽ�  ǰ��������Ϊ��λ�ֽ�  ����������Ϊ��λ�ֽ�
				 //
			 }
		 }
	}
	
	//�������Ͳ��� H�����Ͳ�����H��Number ������
	public <H extends Number> void addAll(H h){
		
	}
	//
	public void addHHHH(Outer<Integer,U> outer ){
		
	}
	//���޶�ͨ���
	public void addAlll(Outer<Integer,?> outer){
		
	}
	//�޶�ͨ���
	public void addA(Outer<? extends Number,? extends Comparable<T>> outer){
		
	}
	//�޶�ͨ���
	public void addSort(Outer<? extends Number,? extends Comparable<T>> outer){
	}
	public <H extends Comparable<? super H>>  void addS(List<H> t){
		
	};
	public  <H extends Number & Comparable<T> > void addsort(){
		List<Integer> myIntList = new LinkedList<Integer>(); // 1�������Ͳ����ķ��ͽӿ� ���Ͳ���ΪInteger ���ӿɶ��Ժ��ȶ���(robustness)(³����)
		//ͨ������һ������������a parameterized type)��
		//���г�����ʽ���Ͳ���(formal type parameter,������E)�����滻��ʵ�����Ͳ���(actual type argument) Type 
		//�������ͽӿڷ�����ʵ���滻��ʽ����  �� Integer �滻��ʽ����E
		List<String> list=new ArrayList<String>();
		//List<Object> list2=list; ���벻ͨ��  //�������̬����
		String name="��ʢ��";
		Object name1=name;
		//? unknown ��֪����
		List<?> list2=new ArrayList<>();
		List<Object> list3=(List<Object>) list2;
	}
}
