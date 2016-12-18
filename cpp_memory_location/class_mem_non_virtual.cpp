/*
 *
 * 用来测试类对象的内存布局
						——————
						｜int bi =  1 ｜
						｜     f()        ｜
						｜     g()	     ｜
						｜     h()	     ｜
				public       ——————  public
	  	——————   				  ——————                                             
		｜int bi1 =  2 ｜				｜int bi2 =  3 ｜
		｜     f()         ｜				｜     f()         ｜
		｜     gg()      ｜				｜     hh()      ｜
		——————  				——————
							public
						——————
						｜int di =  4 ｜
						｜     f()        ｜
						｜     g()	     ｜
						｜     gg()     ｜
						｜     hh()     ｜
						——————
在g＋＋下面：
memory map of derive object:(no virtual devive)
size:28
*******[0]*****
-----D::f()
-----D::g()
-----B::h()
-----D::gg()
-----D::hh()
*******[1]*****
-----1
*******[2]*****
-----2
*******[3]*****
-----D::f()
-----D::g()
-----B::h()
-----D::hh()
*******[4]*****
-----1
*******[5]*****
-----3
*******[6]*****
-----4
************************************************
对于没有虚继承的继承层次，他会保持所有继承机构实体的完整，编译器会按照正常的顺序去构造对象，也就是，先构造基类，在构造派生类，如果派生类有虚函数则将他挂在到第一个基类对象的VPTR中（最顶端的对象，最后一个对象的虚函数指针也是挂在这里），有几条继承层次，会有几个VPTR
*/
#include<iostream>
#include<stdio.h>
#include<stdlib.h>

using namespace std;
/*
	定义一个基类
*/
class B{
public:
	int bi;
	B(){bi = 1;}
	virtual void f(){
		cout<<"B::f()"<<endl;
	}
	virtual void g(){
		cout<<"B::g()"<<endl;
	}
	virtual void h(){
		cout<<"B::h()"<<endl;
	}
};

class B1:public  B{
public:
	int bi1;
	B1(){bi1 = 2;}
	virtual void f(){
		cout<<"B1::f()"<<endl;
	}
	virtual void gg(){
		cout<<"B1::gg()"<<endl;
	}
};

class B2:public   B{
public:
	int bi2;
	B2(){bi2 = 3;}
	virtual void f(){
		cout<<"B2::f()"<<endl;
	}

	virtual void hh(){
		cout<<"B2::hh()"<<endl;
	}
};

class D:public B1,public B2{
public:
	int di;
	D(){di = 4;}
	virtual void f(){
		cout<<"D::f()"<<endl;
	}
	virtual void g(){
		cout<<"D::g()"<<endl;
	}
	virtual void gg(){
		cout<<"D::gg()"<<endl;
	}
	virtual void hh(){
		cout<<"D::hh()"<<endl;
	}

};

typedef void  (*fun)(void);

int main(){

	D d;
	cout<<"************************************************"<<endl;
	cout<<"memory map of derive object:(no virtual devive)"<<endl;

	int **dp = (int **)&d;
	cout<<"size:"<<sizeof(d)<<endl;
	fun fp;

	cout<<"*******[0]*****"<<endl;
	for(int i = 0;i<5;i++){
		cout<<"-----";
		fp = (fun)dp[0][i];
		fp();
	}

	cout<<"*******[1]*****"<<endl;
	cout<<"-----"<<(int)dp[1]<<endl;

	cout<<"*******[2]*****"<<endl;
	cout<<"-----"<<(int)dp[2]<<endl;

	cout<<"*******[3]*****"<<endl;
	for(int i = 0;i<4;i++){
		cout<<"-----";
		fp = (fun)dp[3][i];
		fp();
	}

	cout<<"*******[4]*****"<<endl;
	cout<<"-----"<<(int)dp[4]<<endl;

	cout<<"*******[5]*****"<<endl;
	cout<<"-----"<<(int)dp[5]<<endl;

	cout<<"*******[6]*****"<<endl;
	cout<<"-----"<<(int)dp[6]<<endl;
	cout<<"************************************************"<<endl;
	//cout<<"遵循正常的继承体系"<<endl;
}
