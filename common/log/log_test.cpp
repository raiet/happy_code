#include "log.h"

using namespace std;

void TestLog(int a,int b)
{
    LOG("input a= %d, b = %d",a,b);
}

int main(int argc, char *argv[])
{
    //test log
    LOG("hello ward");
    LOG("%s-%d","char type",123456);  
    
    TestLog(10,20);

    return 0; 
}