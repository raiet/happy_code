/*
 * rand_test.h
 *
 *  Created on: 2016-12-27
 *      Author: raietren
 */

#ifndef RAND_TEST_H_
#define RAND_TEST_H_

#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <stdint.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sstream>
#include <unistd.h>

#include "common/log/log.h"

class RDData
{
public:
	RDData(uint32_t all_money_,uint32_t max_role_num_)
	{
		id = 999;
		min_rand_num = 1;
		all_money = all_money_;
		baoji_rate = 10;
		max_role_num = max_role_num_;
		cur_role_num = 0;
		money_left = all_money;
		srand (time(NULL));
	}
	uint32_t GetRandOne()
	{
		if (cur_role_num  ==  max_role_num)
		{
			return 0;
		}
		uint32_t money = 0;
		if (cur_role_num + 1 ==  max_role_num)
		{
			money = money_left;
			money_left = 0;
			cur_role_num ++;
			return money;
		}
		uint32_t cur_min ,cur_max;
		cur_min = min_rand_num;
		uint32_t need_left = (max_role_num - cur_role_num -1)* min_rand_num;
		uint32_t valid_money_num = money_left - need_left;
		//平均值的两倍，使用均匀分布，就会得到平均值
		uint32_t e_max_num = (money_left / (max_role_num - cur_role_num))*2;
		//随机暴击
		uint32_t rand_num = rand() % 100 + 1;
		if (rand_num < baoji_rate)
		{
			e_max_num *= 3;//随机的区间时正常情况下的三倍
		}
		cur_max = e_max_num > valid_money_num?valid_money_num:e_max_num;

		money = rand() % cur_max + min_rand_num;
		//DEBUG_LOG(0,0,0,"","cur_max = %d,min_rand_num = %d,money = %d",cur_max,min_rand_num,money);
		money_left -= money;
		cur_role_num ++;
		return money;
	}
private:
	uint32_t id;//红包的id
	uint32_t min_rand_num;//每个红包的最小值
	uint32_t baoji_rate; //暴击率 以100为底 ，将剩下的钱都作为池子，产生一个较大的数字
	uint32_t all_money;//初始化时红包有的金额
	uint32_t max_role_num;//最大可以领取的红包个数

	uint32_t cur_role_num;//当前已经领取的红包个数
	uint32_t money_left;//红包剩余金额

};

#endif /* RAND_TEST_H_ */
