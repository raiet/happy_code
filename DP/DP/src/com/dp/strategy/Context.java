package com.test.strategy;
/**
 * 上下文，维护一个策略的引用，
 * 由他负责和客户打交道
 * */
public class Context {
	
	private Strategy stategy;
	
	//构造函数负责初始化具体策略的引用
	public Context(Strategy strategy){
		this.stategy = strategy;
	}
	
	//context对外暴漏的接口，根据具体的策略调用统一的方法
	public void contextInterface(){
		this.stategy.algorithmInterface();
	}
	
	
	public Strategy getStategy() {
		return stategy;
	}

	public void setStategy(Strategy stategy) {
		this.stategy = stategy;
	}
}
