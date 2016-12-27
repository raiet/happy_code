/*
 * mem_crc.h
 *
 *  Created on: 2016-12-25
 *      Author: raietren
 */

#ifndef MEM_CRC_H_
#define MEM_CRC_H_

#include <stdint.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string>
#include <iostream>
#include <ostream>
#include <sstream>
#include<sys/stat.h>
#include<unistd.h>

#include "common/log/log.h"


using namespace std;

//每个红包最大可以领取的玩家数量
const uint32_t MAX_REWARD_PLAYER_NUMS = 2;
//单进程可以容纳的最大zone个数
const uint32_t MAX_ZONE_NUMS = 10;

uint16_t DataCheckSum(uint16_t* buf, int len);


//#pragma pack (2)

struct RPData
{
	uint32_t seq_id; // 红包序列号

	time_t last_time; // 最后一次修改的时间（可以实现多长时间领完了红包）

	//uint16_t a;
	uint16_t aa;
	uint32_t b;


	char tmp[4];

	uint16_t crc;//校验红包数据的正确性

	friend ostream& operator<< (ostream &os,RPData &data)
	{

	}
	void OutPut()
	{
		int len = sizeof(*this);
		printf("len = %d\n",len);
		uint16_t *mem = (uint16_t *)this;
		for (int i = 0;i<len;i++)
		{
			printf("%d_",*(mem ));
			mem ++;
			i++;
		}
		printf("\n");
	}
	uint32_t GetRealLen()
	{
		uint32_t* start = (uint32_t*)this;
		uint32_t* end = (uint32_t *)&(this->crc);
		uint32_t len = (end - start)*sizeof(uint32_t);
		printf("-----%d\n",len);
		return len;
		//DEBUG_LOG(0,0,0,"","data start ptr:%0x,end ptr = %0x",&(data->seq_id),&(data->crc));
	}
	void RefreshCRC()
	{
		static uint32_t crc_len = sizeof (uint16_t);
		uint32_t len = sizeof(*this) - crc_len;
		len = GetRealLen();
		crc = DataCheckSum((uint16_t*)this,len);
		stringstream ss;
		ss << *this;

		DEBUG_LOG(0,0,0,"","RefreshCRC crc = %u,len = %u,this :%s",crc,len,ss.str().c_str());
	}
	bool CheckCRC()
	{
		static uint32_t crc_len = sizeof (uint16_t);
		uint32_t len = sizeof(*this) - crc_len;
		len = GetRealLen();
		uint16_t tmp_crc = DataCheckSum((uint16_t*)this,len);
		stringstream ss;
		ss << *this;
		DEBUG_LOG(0,0,0,"","tmp_crc = %u ,old crc = %u,len = %u,this :%s",tmp_crc,crc,len,ss.str().c_str());
		return tmp_crc == crc;
	}
};
//#pragma pack ()

char * GetMmapMem(uint32_t mem_size,bool &is_exist);


#endif /* MEM_CRC_H_ */
