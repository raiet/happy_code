package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class BeanCopy {
	//һ������java bean�ķ������ڲ�ʹ�÷������ʵ��
	public Object copy(Object obj){
		Object copyObj= null;
		//��ȡ�õ�Class����
		Class<?> clazz = obj.getClass();
		try {
			copyObj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ȡ�����е�����,ע�����ﲻҪʹ��getFields����������ֻ��ȡ��public������
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("*********************************************");
		System.out.println("���е����ԣ�");
		for(Field field:fields){
			//��ȡ�ֶε����֣��Ա��������ƴ��get��set����
			String fieldName = field.getName();
			//ȡ�õ�һ����ĸ����ת��Ϊ��д�������ͨjava bean������
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			//ƴ��get��set������
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			String setMethodName = "set" + firstLetter + fieldName.substring(1);
			try {
				//ȡ��get��set����
				Method getMethod = clazz.getMethod(getMethodName, null);//get����û�в���
				Method setMethod = clazz.getMethod(setMethodName, field.getType());
				
				//����get��set��������ɸ�ֵ
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
