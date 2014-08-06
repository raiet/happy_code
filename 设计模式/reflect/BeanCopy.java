package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class BeanCopy {
	//一个拷贝java bean的方法，内部使用反射机制实现
	public Object copy(Object obj){
		Object copyObj= null;
		//先取得的Class对象
		Class<?> clazz = obj.getClass();
		try {
			copyObj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//取得所有的属性,注意这里不要使用getFields方法，那样只能取得public的属性
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("*********************************************");
		System.out.println("类中的属性：");
		for(Field field:fields){
			//获取字段的名字，以便后面用来拼接get和set方法
			String fieldName = field.getName();
			//取得第一个字母，并转换为大写，否和普通java bean的名字
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			//拼接get和set方法名
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			try {
				//取得get和set方法
				Method getMethod = clazz.getMethod(getMethodName, null);//get方法没有参数
				Method setMethod = clazz.getMethod(setMethodName, field.getType());
				
				//调用get和set方法，完成赋值
				Object value = getMethod.invoke(obj, null);
				System.out.println(fieldName + " : "+ value);
				setMethod.invoke(copyObj, value);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("*********************************************");
		return copyObj;
	}
	@Test
	public void test(){
		User user = new User();
		user.setAge(10);
		user.setName("haha");
		//BeanCopy beanCopy = new BeanCopy();
		User newUser = (User)new BeanCopy().copy(user);
		System.out.println(newUser);
	}
}
