project(log_test)


SET(EXECUTABLE_OUTPUT_PATH ${CMAKE_CURRENT_SOURCE_DIR})

set(TARGET log_test)
add_executable(${TARGET} log.cpp log_test.cpp)
target_link_libraries(${TARGET} pthread rt)