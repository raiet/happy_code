package edu.hust.advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//实现InvocationHandler接口，代理目标对象执行它的方法
public class MyAdvice implements InvocationHandler {
	//要代理的目标对象
	Object target=null;
	//使用构造函数将目标对象初始化
	public MyAdvice(Object target) {
		super();
		this.target = target;
	}
	//定义自己的代理方法
	public void myFun1(Method m){
		System.out.println(m.getName()+" before invoke");
	}
	public void myFun2(Method m){
		System.out.println(m.getName()+" after invoke");
	}
	
	//实现invoke方法，由于是面向接口编程，所以调用每个方法都会经由invoke方法进行调用，所以就可以在这个方法中嵌入自己的逻辑
	//这就是动态代理实现的巧妙之处
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		myFun1(method);
		//通过反射调用目标对象的方法
		Object obj = method.invoke(target, args);
		myFun1(method);
		
		return obj;
	}

}
