/*
 *
 * ���������������ڴ沼��
						������������
						��int bi =  1 ��
						��     f()        ��
						��     g()	     ��
						��     h()	     ��
				public       ������������  public
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
��g�������棺
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
����û����̳еļ̳в�Σ����ᱣ�����м̳л���ʵ����������������ᰴ��������˳��ȥ�������Ҳ���ǣ��ȹ�����࣬�ڹ��������࣬������������麯���������ڵ���һ����������VPTR�У���˵Ķ������һ��������麯��ָ��Ҳ�ǹ���������м����̳в�Σ����м���VPTR
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
	//cout<<"��ѭ�����ļ̳���ϵ"<<endl;
}
