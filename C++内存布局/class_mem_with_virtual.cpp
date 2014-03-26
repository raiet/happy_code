/*
	���������������ڴ沼��
						������������
						��int bi =  1 ��
						��     f()        ��
						��     g()	     ��
						��     h()	     ��
				virtual       ������������  virtual
	  	������������   				  ������������                                             
		��int bi1 =  2 ��				��int bi2 =  3 ��
		��     f()         ��				��     f()         ��
		��     gg()      ��				��     hh()      ��
		������������  				������������
							public
						������������
						��int di =  4 ��
						��     f()        ��
						��     g()	     ��
						��     gg()     ��
						��     hh()     ��
						������������
��g�������棺������̳���ϵ���棬������ʵ���Ƕ����������ŵģ������麯����Ҳ�ǵ�����ŵģ������κμ̳�ʵ����麯�����У�������̳е���ʵ���е��麯��������ע�⣬�����������麯���ǳ����ڶ����еĵ�һ��vptr��ָ���麯�����У���һ�����ࣩ������������Լ�û��VPTR
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
	����һ������
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
	//cout<<"˵���� ������̳���ϵ���棬������ʵ���Ƕ����������ŵģ������麯����Ҳ�ǵ�����ŵģ������κμ̳�ʵ����麯�����У�������̳е���ʵ���е��麯��������ע�⣬�����������麯���ǳ����ڶ����еĵ�һ��vptr��ָ���麯�����У�����������Լ�û��VPTR"<<endl;

}
