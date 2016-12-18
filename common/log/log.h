#ifndef HAPPY_CODE_LOG_H_
#define HAPPY_CODE_LOG_H_

#include <stdio.h>
#include <stdarg.h>
#include <time.h>
#include <unistd.h>
#include <string>
#include <sstream>
#include <vector>
#include <set>
#include <map>
#include <typeinfo>
#include <iostream>


const int kMaxLogSize = 1024;
const int out_fd = 2;
const bool full_file_name = 0;

#define LOG(args...) comm_log(getpid(), __FILE__, __LINE__,__FUNCTION__ ,args)


void comm_log(unsigned long pid,
                const char *file,
                unsigned int line,
                const char * fun_name,
                const char *fmt, ...);

#endif