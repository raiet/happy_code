/*
����һ������n�����������ʽ�Ļ��ξ���

1	2	3	4	
12	13	14	5	
11	16	15	6	
10	9	8	7	

��չ������Ǹ���һ����ά���飬���ջ��������
˼·��һ���ģ���ʱ���Զ����ĸ���������ʾ��ά����ı߽�
north(�ϱ߽�)��east(�ֱ߽�)��south(�±߽�)��west(��߽�)����ÿ�������ϱ�����ʱ�������Щ�߽�Ϳ��԰��ջ��������
*/
#include<iostream>

using namespace std;

#define M 4
int a[M][M];

void show(){
	for(int i =0;i<M;i++){
		for(int j = 0;j<M;j++){
			cout<<a[i][j]<<"\t";
		}
		cout<<endl;
	}
}
void spinout(){
	int i=0;
	int j =0;
	int direct=1;
	int num =1;
	while(num<=M*M){
		switch(direct){
		case 1://������
			while(a[i][j]==0&&j<M){
				a[i][j] = num;
				num++;
				j++;
			}
			i++;
			j--;
			direct = 2;
			break;
		case 2://���ϵ���
			while(a[i][j]==0&&i<M){
				a[i][j] = num;
				i++;
				num++;
			}
			i--;
			j--;
			direct = 3;
			break;
		case 3://���ҵ���
			while(a[i][j]==0&&j>=0){
				a[i][j] = num;
				j--;
				num++;
			}
			j++;
			i--;
			direct = 4;
			break;
		case 4://���µ���
			while(a[i][j] == 0){//��Ϊ��һ���Ѿ��Ե�һ�ų�ʼ���ˣ�����ֻҪ���a[i][j]�Ƿ�Ϊ0���Ϳ��Ծ�������
				a[i][j] = num;
				i--;
				num++;
			}
			i++;
			j++;
			direct = 1;
			break;
		}
	}
}

int main(){
	
	spinout();
	show();
}

