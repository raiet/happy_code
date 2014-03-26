/*
	用来测试类对象的内存布局
						——————
						｜int bi =  1 ｜
						｜     f()        ｜
						｜     g()	     ｜
						｜     h()	     ｜
				virtual       ——————  virtual
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
在g＋＋下面：在有虚继承体系下面，单独的实体是独立抽出来存放的，他的虚函数表也是单独存放的，其他任何继承实体的虚函数表中，都不会继承单独实体中的虚函数。但是注意，派生类对像的虚函数是承载在对象中的第一个vptr所指的虚函数表中（第一个基类），派生类对象自己没有VPTR
************************************************
memory map of derive object:(with virtual devive)
size:28
*******[0]*****
-----D::f()
-----D::gg()
-----D::g()
-----D::hh()
*******[1]*****
-----2
*******[2]*****
-----D::f()
-----D::hh()
*******[3]*****
-----3
*******[4]*****
-----4
*******[5]*****
-----D::f()
-----D::g()
-----B::h()
*******[6]*****
-----1

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
/*virtual*/
class B1:public virtual B{
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
/*virtual*/
class B2:public virtual  B{
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

int main(){	cout<<"************************************************"<<endl;
	cout<<"memory map of derive object:(with virtual devive)"<<endl;
	D d;
	int **dp = (int **)&d;
	cout<<"size:"<<sizeof(d)<<endl;
	fun fp;

	cout<<"*******[0]*****"<<endl;
	for(int i = 0;i<4;i++){
		cout<<"-----";
		fp = (fun)dp[0][i];
		fp();
	}
	
	cout<<"*******[1]*****"<<endl;
	cout<<"-----"<<(int)dp[1]<<endl;

	cout<<"*******[2]*****"<<endl;
	for(int i = 0;i<2;i++){
		cout<<"-----";
		fp = (fun)dp[2][i];
		fp();
	}
	
	cout<<"*******[3]*****"<<endl;
	cout<<"-----"<<(int)dp[3]<<endl;

	cout<<"*******[4]*****"<<endl;
	cout<<"-----"<<(int)dp[4]<<endl;

	cout<<"*******[5]*****"<<endl;
	for(int i = 0;i<3;i++){
		cout<<"-----";
		fp = (fun)dp[5][i];
		fp();
	}

	cout<<"*******[6]*****"<<endl;
	cout<<"-----"<<(int)dp[6]<<endl;
	cout<<"************************************************"<<endl;
	//cout<<"说明： 在有虚继承体系下面，单独的实体是独立抽出来存放的，他的虚函数表也是单独存放的，其他任何继承实体的虚函数表中，都不会继承当独实体中的虚函数。但是注意，派生类对像的虚函数是承载在对象中的第一个vptr所指的虚函数表中，派生类对象自己没有VPTR"<<endl;

}
