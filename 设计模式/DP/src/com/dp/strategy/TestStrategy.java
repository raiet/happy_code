package com.test.strategy;
/**
 * ����ģʽ����ҪĿ�ģ�ʹ�㷨������ʹ�����Ŀͻ����仯
 * �㷨�ľ���ʵ�ֶ��û�͸�����û�ֻҪѡ����Ӧ���㷨���Ϳ���ʹ��ͳһ�ķ�ʽʹ����Щ�㷨
 * 
 * ��collect�е�Comparator�Ƚ�������ʹ�ò���ģʽ��ʵ�ֵ�
 * ���У�
 * 		Comparator�ӿ��ǳ���Ĳ�����
 * 
 *      ����Ĳ������û����������Լ�ʵ��
 *	
 *	    Collections.sort(list, r)��������
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
