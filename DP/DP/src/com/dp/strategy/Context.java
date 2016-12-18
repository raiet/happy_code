package com.test.strategy;
/**
 * �����ģ�ά��һ�����Ե����ã�
 * ��������Ϳͻ��򽻵�
 * */
public class Context {
	
	private Strategy stategy;
	
	//���캯�������ʼ��������Ե�����
	public Context(Strategy strategy){
		this.stategy = strategy;
	}
	
	//context���Ⱪ©�Ľӿڣ����ݾ���Ĳ��Ե���ͳһ�ķ���
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
