/*
 * rand_test.cpp
 *
 *  Created on: 2016-12-27
 *      Author: raietren
 */
#include "rand_test.h"

int main(int argc, char **argv)
{
	uint32_t max_role_num = 20;
	RDData data(100,max_role_num);
	for (uint32_t i = 0;i< max_role_num;i++)
	{
		uint32_t res = data.GetRandOne();
		printf("%d_",res);
	}
	printf("\n");
	return 0;
}
