#include <stdio.h>
#include <stdarg.h>
#include <time.h>
#include <sys/time.h>
#include <unistd.h>
#include <string>
#include <libgen.h>
#include "log.h"

void comm_log(unsigned long pid,
                const char *file,
                unsigned int line,
                const char *fun_name,
                const char *fmt, ...)
{
        if (file == NULL) return;

        struct timeval t;
        gettimeofday(&t, NULL);

        struct tm tm;
        time_t now = time (NULL);
        localtime_r(&now, &tm);
        char log_buf [kMaxLogSize];
        char file_name[256] = {0};
        snprintf(file_name,sizeof(file_name),file);
        int ret = snprintf(log_buf, sizeof(log_buf), "%04d-%02d-%02d %02d:%02d:%02d.%06ld|%s:%d|%s()|%lu|",
                        tm.tm_year + 1900, tm.tm_mon + 1, tm.tm_mday, tm.tm_hour, tm.tm_min,
                        tm.tm_sec,t.tv_usec, full_file_name?file_name:basename(file_name), line, fun_name,/*pid*/0L);
        if (ret < 0)
        {
            write(out_fd, "snprintf err",13 );  
            return ;
        }
        unsigned int offset_ = ret;

        va_list ap;
        va_start(ap, fmt);
        ret = vsnprintf(log_buf + offset_, (sizeof(log_buf) - offset_ - 1), fmt, ap);
        va_end(ap);

        offset_ += ret;

        if(offset_ < sizeof(log_buf) - 1)
                log_buf[offset_] = '\n';
        else
                log_buf[offset_ - 1] = '\n';

        write(out_fd, log_buf, offset_ + 1);
        fsync(out_fd);
}