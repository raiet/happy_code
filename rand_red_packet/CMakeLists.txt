
project(rand_test)
set(TARGET rand_test)
set(SRC_LIST rand_test.cpp ${PROJECT_SOURCE_DIR}/../common/log/log.cpp)

include_directories(${PROJECT_SOURCE_DIR}/../)

add_executable(${TARGET} ${SRC_LIST})