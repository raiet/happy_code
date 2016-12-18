/*
输入一个整数n，输出如下形式的回形矩阵

1	2	3	4	
12	13	14	5	
11	16	15	6	
10	9	8	7	

扩展：如果是给定一个二维数组，按照回形输出。
思路是一样的，这时可以定义四个变量，表示二维数组的边界
north(上边界)，east(又边界)，south(下边界)，west(左边界)，在每个方向上遍历的时候更新这些边界就可以按照回形输出了
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
		case 1://从左到右
			while(a[i][j]==0&&j<M){
				a[i][j] = num;
				num++;
				j++;
			}
			i++;
			j--;
			direct = 2;
			break;
		case 2://从上到下
			while(a[i][j]==0&&i<M){
				a[i][j] = num;
				i++;
				num++;
			}
			i--;
			j--;
			direct = 3;
			break;
		case 3://从右到左
			while(a[i][j]==0&&j>=0){
				a[i][j] = num;
				j--;
				num++;
			}
			j++;
			i--;
			direct = 4;
			break;
		case 4://从下到上
			while(a[i][j] == 0){//因为第一次已经对第一排初始化了，所以只要检测a[i][j]是否为0，就可以决定结束
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

