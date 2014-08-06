package reflect;

public class User{
	private String name;
	private int age;
	//在提供了有参的构造函数的时候，尽量显示的给出无参的
	public User(){
		
	}
	//这里提供了一个有参数的构造函数
	public User(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {		
		return "NAME :" + this.name + " AGE: " + this.age;
	}
	public void sayHello(String name,int age){
		System.out.println("hello:"+"NAME :" + name + " AGE: " + age);
	}
	public void sayHello(){
		System.out.println("hello nothing");
	}
	public int add(int a,int b){
		return a+b;
	}
}
