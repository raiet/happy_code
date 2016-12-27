/*
 * mem_crc.cpp
 *
 *  Created on: 2016-12-25
 *      Author: raietren
 */

#include "mem_crc.h"

uint16_t DataCheckSum(uint16_t* buf, int len)
{
	printf("\n");
    uint32_t sum = 0;
    uint32_t tmp = 0;
    while(len > 1)
    {
    	printf("%d_",*buf);
        sum += *buf++;
        len -= sizeof(uint16_t);
    };

    if(len)
    {
    	printf("%d_",*(int8_t*)buf);
        sum += *(uint8_t*)buf;
    }

    while(sum >> 16)
    {
        sum = (sum >> 16) + (sum & 0xFFFF);
    }
    printf("\n");
    printf("\n");
    return (uint16_t)~sum;
}

char * GetMmapMem(uint32_t mem_size,bool &is_exist)
{
	int fd;
	struct stat file_stat;
	char *file_buf;
	is_exist = true;

	//新建文件，
	fd = open("test_data.mmap",O_CREAT|O_RDWR,0666); //open file
	if(fd < 0)
	{
		perror("open file fail:");
		close(fd);
		return NULL;
	}
	if(fstat(fd,&file_stat)==-1)
	{
		perror("get file stat fail:");
		close(fd);
		return NULL;
	}
	//如果大小为0，姑且认为是一个新创建的文件吧
	if ( file_stat.st_size == 0)
	{
		//设置新建文件的长度
		ftruncate(fd, mem_size);
		is_exist = false;
	}
	//将文件和内存中的一段空间建立映射
	file_buf =(char *)mmap(NULL,mem_size,PROT_WRITE,MAP_SHARED,fd,SEEK_SET);
	if(file_buf == MAP_FAILED)
	{
		perror("map file_write fail:");
		close(fd);
		munmap(file_buf,mem_size);
		return NULL;
	}
	//映射建立后，文件就可以关闭了，关闭了文件和内存之间的映射关系依然存在。
	close(fd);  //write file map success
	//将内存中的数据同步到文件即磁盘
	if(msync(file_buf,mem_size,MS_SYNC)==-1)
	{
		perror("msync fail:");
		munmap(file_buf,mem_size);
		return NULL;
	}

	return file_buf;
}

int main()
{
	uint32_t all_size = sizeof(RPData);
	DEBUG_LOG(0,0,0,"","all_size:%d",all_size);
	bool exist;
    char* mem = GetMmapMem(all_size, exist);
    if (NULL == mem)
    {
        ERR_LOG(0, 0, 0, "", "load mmap err ");
        return -1;
    }
    RPData *data = (RPData*)mem;
    stringstream sss;
    sss << *data;
    DEBUG_LOG(0,0,0,"","data start ptr:%0x,end ptr = %0x",&(data->seq_id),&(data->crc));
    DEBUG_LOG(0,0,0,"","before data:%s",sss.str().c_str());
    bool result = data->CheckCRC();
    DEBUG_LOG(0,0,0,"","CheckCRC result:%d",result);
    data->last_time = time(NULL);
    data->RefreshCRC();
    data->OutPut();
    stringstream ss;
    ss << *data;
    DEBUG_LOG(0,0,0,""," after data:%s",ss.str().c_str());

}
