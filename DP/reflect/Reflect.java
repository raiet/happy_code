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
		// 取得取得TestClass类的完整类名
		String testName = test.getClass().getName();

		System.out.println(testName);
	}
	//三种获得Class多的方式
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
		System.out.println("类名："+demo1.getName());
		System.out.println("类名："+demo2.getName());
		System.out.println("类名："+demo3.getName());
	}
	//通过类名使用默认构造函数实例化类对象
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
	
	//使用有参数的构造函数实例化类对象
	public static void testInstanceFromNameAndParam(){
		String name = "raiet";
		//1，取出类名对应的Class
		Class<?> clazz = null;
		try {
			clazz = Class.forName("reflect.User");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2,取出所有的构造函数
		Constructor<?> cons[] = clazz.getConstructors();
		System.out.println("构造函数有：");
		for(Constructor con:cons){
			System.out.println(con);
		}
		//3,根据相应的参数构造实例
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
		
		//也可以使用下面的方式来实现
		try {
			Constructor con= null;
			//取得无参数的构造函数
			con = clazz.getConstructor(new Class[]{});
			User user = (User) con.newInstance(null);
			System.out.println(user);
			
			//取得只有一个带有string类新参数的构造函数
			con = clazz.getConstructor(String.class);
			//传入构造函数的参数
			user = (User) con.newInstance("raiet");
			System.out.println(user);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过Class调用它的方法
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
			 * 	其实上面的每段代码都等价于
			 *  User user = new User();
			 *  user.add(200,300)
			 *  但是他们的区别就在于，一个是在运行期确定调用关系（参数有外部传入），
			 *  一个是在编译器就决定了程序的行为
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
	
	//根据类名获得类中数据成员及其属性
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
			//获取权限修饰符
			mo = field.getModifiers();
			String priv = Modifier.toString(mo);
			//获取属性类型
			Class<?> type = field.getType();
			System.out.println(priv + " " + type.getName() + " "+field.getName() + ";");
		}
	} 
	
	//返回类的所有信息。。。。
	//返回类的所有的方法名，包括修饰符，返回值，异常（住哟通过反射机制可以取出所有的method，包括private的）
	public static void getAllMethods(){
		Class<?> obj = null;
		try {
			obj = Class.forName("java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("输出所有的方法信息");
		Method[] methods = obj.getDeclaredMethods();
		for(Method method : methods){
			System.out.println(method);
		}
		System.out.println("输出所有的字段信息");
		Field[] fields = obj.getFields();
		for(Field field : fields){
			System.out.println(field);
		}
		System.out.println("输出所有的构造器信息");
		Constructor<?>[] cons = obj.getConstructors();
		for(Constructor<?> con : cons){
			System.out.println(con);
		}
		System.out.println("输出所有的接口信息");
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
