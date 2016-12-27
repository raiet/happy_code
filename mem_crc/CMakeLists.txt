
project(mem_crc)
set(TARGET mem_crc)
set(SRC_LIST mem_crc.cpp ${PROJECT_SOURCE_DIR}/../common/log/log.cpp)

message("----" + ${PROJECT_SOURCE_DIR})
include_directories(${PROJECT_SOURCE_DIR}/../)

add_executable(${TARGET} ${SRC_LIST})