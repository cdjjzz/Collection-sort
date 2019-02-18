package com.utils;

import java.util.Stack;

public class Center {
	
	static Stack<String> czf=new Stack<String>();
	static Stack<String> maths=new Stack<String>();
	
	void toAfter(String word){
	   if(word==null)return;
	   int i=0;
	   while(i<word.length()){
		switch (word.charAt(i)) {
		case '+':
		case '-':
			while(!czf.isEmpty()&&!czf.peek().equals("("))
				maths.push(czf.pop());
			czf.push(word.charAt(i)+"");
			i++;
			break;
		case '*':
		case '/':
			while(!czf.isEmpty()&&(czf.peek().equals("*")||czf.peek().equals("/")))
				maths.push(czf.pop());
			czf.push(word.charAt(i)+"");
			i++;
			break;
		case '(':
			  czf.push("(");
			  i++;
			break;
		case ')':
			while(!czf.isEmpty()&&!czf.peek().equals("("))
				maths.push(czf.pop());
			czf.pop();
			i++;
			break;
		default:
			maths.push(word.charAt(i)+"");
			i++;
			if(i<word.length()){
				while(word.charAt(i)>='0'&&word.charAt(i)<='9'){
					maths.push(maths.pop()+word.charAt(i));
					i++;
				}
			}
			break;
		}
		   
	   }
	   while(!czf.isEmpty())
		   maths.push(czf.pop());
	   
	}
	
	void calcAfter(){
		Stack<String> stack=Center.maths;
		Stack<Integer> values=new Stack<Integer>(); 
		int i=0;
		int x=0;
		while(i<stack.size()){
			String p=stack.get(i);
			i++;
			switch (p) {
			case "+":
				x=values.pop();
				values.push(x+values.pop());
				break;
			case "-":
				x=values.pop();
				values.push(values.pop()-x);
				break;
			case "*":
				x=values.pop();
				values.push(values.pop()*x);
				break;
			case "/":
				x=values.pop();
				values.push(values.pop()/x);
				break;
			default:
				values.push(Integer.valueOf(p));
				break;
			}
		}
		if(!values.isEmpty())
		System.out.println(values.pop());
	}
	
	void toBefore(String word){
		if(word==null)return;
		int i=word.length()-1;
		while(i>=0){
			switch (word.charAt(i)) {
			case '+':
			case '-':
				while(!czf.isEmpty()&&(czf.peek().equals("*")||czf.peek().equals("/")))
					maths.push(czf.pop());
				czf.push(word.charAt(i)+"");
				i--;
				break;
			case '*':
			case '/':
				   czf.push(word.charAt(i)+"");
				i--;
				break;
			case '(':
				while(!czf.isEmpty()&&!czf.peek().equals(")"))
					maths.push(czf.pop());
				 czf.pop();
				  i--;
				break;
			case ')':
				//Ñ¹Èë
				czf.push(")");
				i--;
				break;
			default:
				maths.push(word.charAt(i)+"");
				i--;
				while(i>=0&&word.charAt(i)>='0'&&word.charAt(i)<='9'){
					maths.push(word.charAt(i)+maths.pop());
					i--;
				}
				break;
			}
		}
		 while(!czf.isEmpty())
			   maths.push(czf.pop());
	}
	void calcBefore(){
		Stack<String> stack=Center.maths;
		Stack<Integer> values=new Stack<Integer>(); 
		int i=0;
		int x=0;
		while(i<stack.size()){
			String p=stack.get(i);
			i++;
			switch (p) {
			case "+":
				x=values.pop();
				values.push(x+values.pop());
				break;
			case "-":
				x=values.pop();
				values.push(x-values.pop());
				break;
			case "*":
				x=values.pop();
				values.push(x*values.pop());
				break;
			case "/":
				x=values.pop();
				values.push(x/values.pop());
				break;
			default:
				values.push(Integer.valueOf(p));
				break;
			}
		}
		if(!values.isEmpty())
		System.out.println(values.pop());
	}
	public static void main(String[] args) {
		Center center=new Center();
		center.toAfter("1+((2+3)*4)-5");
		 center.calcAfter();
		 /* System.out.println("------------------------------");
		center.toBefore("12*6/(5+9-10/2)-5");
		 Stack<String> stack1=Center.maths;
		 while(!stack1.isEmpty()){
			 System.out.println(stack1.pop());
		 }*/
		/*center.removeAll("absdasdwdedgsadhasdds","a");
		center.removeAllElement("absdasdwdedgsadhasdds","a");*/
	}
	void removeAll(String word,String key){
		if(word==null)return;
		int i=word.indexOf(key);
		if(i==-1)return;
		char words[]=word.toCharArray();
		for(int j=i+1;j<words.length;j++){
			if(!String.valueOf(words[j]).equals(key)){
				words[i++]=words[j];
			}
		}
		System.out.println(new String(words,0,i));
	}
	
	void removeAllElement(String word,String key){
		if(word==null)return;
		int i=word.indexOf(key);
		if(i==-1)return;
		char words[]=word.toCharArray();
		int k=1;
		for(int j=i+1;j<words.length;j++){
			if(!String.valueOf(words[j]).equals(key)){
				words[j-k]=words[j];
			}else{
				k++;
			}
		}
		System.out.println(new String(words,0,words.length-k));
	}

}
