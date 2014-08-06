package com.test.strategy;
/**
 * 策略模式的主要目的：使算法独立于使用它的客户而变化
 * 算法的具体实现对用户透明，用户只要选择相应的算法，就可以使用统一的方式使用这些算法
 * 
 * 在collect中的Comparator比较器就是使用策略模式来实现的
 * 其中：
 * 		Comparator接口是抽象的策略类
 * 
 *      具体的策略有用户根据需求自己实现
 *	
 *	    Collections.sort(list, r)是上下文
 * 
 * */
public class TestStrategy {
	public static void main(String[] args){
		Strategy s1 = new ConcreteStrategyA();
		Strategy s2 = new ConcreteStrategyB();
		
		Context ctx = new Context(s2);
		
		ctx.contextInterface();
		
		
	}
}
