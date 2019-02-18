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
	//类型参数 ，参数不固定
	private T t; //jvm 编译成Object  java泛型其实是语法糖  伪泛型  虚拟机通过类型擦除 将T cast Object,更好的安全性，更好的阅读
	// extends 代表上线为 Number 
	//上限 最高为T 下super T
	//上限表示参数化类型 是该类型和子类型 extends
	//下限表示参数化类型 是该类型和父类型-》Object  super
	//<T extends E>
	//<? extends E>  通配符
	//<T extends E>用于定义类型参数，它声明了一个类型参数T，可放在泛型类定义中类名后面、泛型方法返回值前面。
	//<? extends E>用于实例化类型参数，它用于实例化泛型变量中的类型参数，只是这个具体类型是未知的，只知道它是E或E的某个子类型。
	//类型参数，参数不固定
	//<?>和<? extends E>用于实现更为灵活的读取，它们可以用类型参数的形式替代，但通配符形式更为简洁。 
	//<? super E>用于实现更为灵活的写入和比较，不能被类型参数形式替代。
	//导致误解
	private U u;
	//可接受多个参数
	private static String shard ="罗盛丰";
	private String  name="select";
	/**
	 * 静态内部类与外部类关系不大，静态内部类可以访问外部静态变量,不能访问实例变量，
	 * 当外部类与内部类存在相同的变量，默认访问内部类变量
	 * 静态内部类只能直接访问外贸静态变量
	 * 可以直接new 类名实例化
	 * access$0
	 * @author pet-lsf
	 *
	 */
	public static class inner{
		
		public static String shard="你好";
		public static void main(String[] args) {
			System.out.println(shard);
		}
	   public void print(){
		   System.out.println("ashdashd");
	   }
		
	}
	/**
	 * 成员内部类，不允许静态方法，静态变量，只存在实例变量，方法
	 * 内部方法可以直接访问静态变量和成员变量
	 * 使用new 外部类.new 内部类实例化
	 * Outer$Inner
	 * 如果内部类与外部类关系密切，且操作或依赖外部类实例变量和方法，则可以考虑定义为成员内部类。
	 * 外部类的一些方法的返回值可能是某个接口，为了返回这个接口，外部类方法可能使用内部类实现这个接口，这个内部类可以被设为private，对外完全隐藏。
	 * 对外隐藏方法实现细节 
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
		//参数化类型 
		Class<String> clas=String.class;
		Type type=clas.getSuperclass();
		System.out.println(type);
	}
	public   void compar(){
		/**
		 * ，方法内部类只能在定义的方法内被使用。
		 * 如果方法是实例方法，则除了静态变量和方法，内部类还可以直接访问外部类的实例变量和方法，
		 * 如innerMethod直接访问了外部私有实例变量a。如果方法是静态方法，则方法内部类只能访问外部类的静态变量和方法。
		 * 方法内部类还可以直接访问方法的参数和方法中的局部变量，不过，这些变量必须被声明为final，
		 * 如innerMethod直接访问了方法参数param和局部变量str。
		 * @author pet-lsf
		 *访问方法的变量只能访问final类型
		 */
		final int str=0;
		// 实例化 access$0
		//不能修改局部变量
		//采用对象 堆 数组 对象 属性 修改
		final int mm[]={1,2};
		class ss{
			/**
			 * 可以访问外部类 实例变量，方法,静态变量
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
				 //System.out.println("你好，我是罗盛丰");
				 //一个字符==2个字节 对应utf-16 只能表示0-ffff bmp 对应增补编码 需要2个char 四个字节  前两个被称为高位字节  后两个被称为低位字节
				 //
			 }
		 }
	}
	
	//定义类型参数 H是类型参数，H是Number 的子类
	public <H extends Number> void addAll(H h){
		
	}
	//
	public void addHHHH(Outer<Integer,U> outer ){
		
	}
	//无限定通配符
	public void addAlll(Outer<Integer,?> outer){
		
	}
	//限定通配符
	public void addA(Outer<? extends Number,? extends Comparable<T>> outer){
		
	}
	//限定通配符
	public void addSort(Outer<? extends Number,? extends Comparable<T>> outer){
	}
	public <H extends Comparable<? super H>>  void addS(List<H> t){
		
	};
	public  <H extends Number & Comparable<T> > void addsort(){
		List<Integer> myIntList = new LinkedList<Integer>(); // 1带有类型参数的泛型接口 类型参数为Integer 增加可读性和稳定性(robustness)(鲁棒性)
		//通常称作一个参数化类型a parameterized type)，
		//所有出现形式类型参数(formal type parameter,这里是E)都被替换成实体类型参数(actual type argument) Type 
		//调用类型接口方法，实参替换形式参数  如 Integer 替换形式参数E
		List<String> list=new ArrayList<String>();
		//List<Object> list2=list; 编译不通过  //泛型与多态区别
		String name="罗盛丰";
		Object name1=name;
		//? unknown 不知道，
		List<?> list2=new ArrayList<>();
		List<Object> list3=(List<Object>) list2;
	}
}
