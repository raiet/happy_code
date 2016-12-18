package edu.hust.advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//ʵ��InvocationHandler�ӿڣ�����Ŀ�����ִ�����ķ���
public class MyAdvice implements InvocationHandler {
	//Ҫ�����Ŀ�����
	Object target=null;
	//ʹ�ù��캯����Ŀ������ʼ��
	public MyAdvice(Object target) {
		super();
		this.target = target;
	}
	//�����Լ��Ĵ�����
	public void myFun1(Method m){
		System.out.println(m.getName()+" before invoke");
	}
	public void myFun2(Method m){
		System.out.println(m.getName()+" after invoke");
	}
	
	//ʵ��invoke����������������ӿڱ�̣����Ե���ÿ���������ᾭ��invoke�������е��ã����ԾͿ��������������Ƕ���Լ����߼�
	//����Ƕ�̬����ʵ�ֵ�����֮��
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		myFun1(method);
		//ͨ���������Ŀ�����ķ���
		Object obj = method.invoke(target, args);
		myFun1(method);
		
		return obj;
	}

}
