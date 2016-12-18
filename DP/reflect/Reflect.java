package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflect {
	
	//
	public static void testGetClassName() {
		TestClass test = new TestClass();
		// ȡ��ȡ��TestClass�����������
		String testName = test.getClass().getName();

		System.out.println(testName);
	}
	//���ֻ��Class��ķ�ʽ
	public static void testInstance(){
		Class<?> demo1= null;
		Class<?> demo2= null;
		Class<?> demo3= null;
		try {
			demo1 = Class.forName("reflect.TestClass");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		demo2 = new TestClass().getClass();
		demo3 = TestClass.class;
		System.out.println("������"+demo1.getName());
		System.out.println("������"+demo2.getName());
		System.out.println("������"+demo3.getName());
	}
	//ͨ������ʹ��Ĭ�Ϲ��캯��ʵ���������
	public static void testInstanceFromName(){
		User user = null;
		try {
			 user = (User)Class.forName("reflect.User").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setAge(12);
		user.setName("raiet");
		System.out.println(user);
	}
	
	//ʹ���в����Ĺ��캯��ʵ���������
	public static void testInstanceFromNameAndParam(){
		String name = "raiet";
		//1��ȡ��������Ӧ��Class
		Class<?> clazz = null;
		try {
			clazz = Class.forName("reflect.User");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2,ȡ�����еĹ��캯��
		Constructor<?> cons[] = clazz.getConstructors();
		System.out.println("���캯���У�");
		for(Constructor con:cons){
			System.out.println(con);
		}
		//3,������Ӧ�Ĳ�������ʵ��
		User user1 = null;
		User user2 = null;
		try {
			user1 = (User)cons[0].newInstance();
			user2 = (User)cons[1].newInstance("raiet");
		} catch (InstantiationException e) {
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
		
		System.out.println(user1);
		System.out.println(user2);
		
		//Ҳ����ʹ������ķ�ʽ��ʵ��
		try {
			Constructor con= null;
			//ȡ���޲����Ĺ��캯��
			con = clazz.getConstructor(new Class[]{});
			User user = (User) con.newInstance(null);
			System.out.println(user);
			
			//ȡ��ֻ��һ������string���²����Ĺ��캯��
			con = clazz.getConstructor(String.class);
			//���빹�캯���Ĳ���
			user = (User) con.newInstance("raiet");
			System.out.println(user);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ͨ��Class�������ķ���
	public static void testInvokeMethod(){
		System.out.println("test invoke");
		Class<?> user = null;
		try {
			user = Class.forName("reflect.User");
			Method method = user.getMethod("sayHello");
			method.invoke(user.newInstance());
			
			method = user.getMethod("sayHello", String.class,int.class);
			method.invoke(user.newInstance(),"raiet001",25);
			
			method = user.getMethod("add", int.class,int.class);
			Object obj = method.invoke(user.newInstance(), 200,300);
			System.out.println((Integer)obj);
			
			/*
			 * 	��ʵ�����ÿ�δ��붼�ȼ���
			 *  User user = new User();
			 *  user.add(200,300)
			 *  �������ǵ���������ڣ�һ������������ȷ�����ù�ϵ���������ⲿ���룩��
			 *  һ�����ڱ������;����˳������Ϊ
			 * */
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//������������������ݳ�Ա��������
	public static void testGetProperty(){
		Class<?> user = null;
		try {
			user = Class.forName("reflect.User");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Field[] fields = user.getDeclaredFields();
		for(Field field:fields){
			int mo;
			//��ȡȨ�����η�
			mo = field.getModifiers();
			String priv = Modifier.toString(mo);
			//��ȡ��������
			Class<?> type = field.getType();
			System.out.println(priv + " " + type.getName() + " "+field.getName() + ";");
		}
	} 
	
	//�������������Ϣ��������
	//����������еķ��������������η�������ֵ���쳣��סӴͨ��������ƿ���ȡ�����е�method������private�ģ�
	public static void getAllMethods(){
		Class<?> obj = null;
		try {
			obj = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������еķ�����Ϣ");
		Method[] methods = obj.getDeclaredMethods();
		for(Method method : methods){
			System.out.println(method);
		}
		System.out.println("������е��ֶ���Ϣ");
		Field[] fields = obj.getFields();
		for(Field field : fields){
			System.out.println(field);
		}
		System.out.println("������еĹ�������Ϣ");
		Constructor<?>[] cons = obj.getConstructors();
		for(Constructor<?> con : cons){
			System.out.println(con);
		}
		System.out.println("������еĽӿ���Ϣ");
		Class<?>[]  ins = obj.getInterfaces();
		for(Class clazz: ins){
			System.out.println(clazz);
		}
	}
	
	
	public static void main(String[] args) {
		testGetClassName();
		testInstance();
		testInstanceFromName();
		testInstanceFromNameAndParam();
		testInvokeMethod();
		testGetProperty();
		getAllMethods();
	}
}
