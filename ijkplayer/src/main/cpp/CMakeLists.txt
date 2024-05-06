# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)
project("irkplayer")
add_definitions(-DNDEBUG)

set(NATIVE_SOURCE_ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})
set(IJKMEDIA_SOURCE_DIR ${NATIVE_SOURCE_ROOT_DIR}/ijkmedia)
set(IJKPROF_SOURCE_DIR ${NATIVE_SOURCE_ROOT_DIR}/ijkprof)
set(EXTRA_SOURCE_DIR ${NATIVE_SOURCE_ROOT_DIR}/ffmpeg)

include_directories(
        ${EXTRA_SOURCE_DIR}/include
        ${IJKMEDIA_SOURCE_DIR}
        ${IJKPROF_SOURCE_DIR}
        ${IJKMEDIA_SOURCE_DIR}/ijkj4a
        ${IJKMEDIA_SOURCE_DIR}/ijksdl
        ${IJKMEDIA_SOURCE_DIR}/ijksoundtouch
        ${IJKMEDIA_SOURCE_DIR}/ijksoundtouch/include
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/include
        ${IJKMEDIA_SOURCE_DIR}/ijkplayer
)

file(GLOB_RECURSE ijkmedia_files
        ${IJKMEDIA_SOURCE_DIR}/ijkj4a/*.c
        ${IJKMEDIA_SOURCE_DIR}/ijksdl/*.c
        ${IJKMEDIA_SOURCE_DIR}/ijksoundtouch/*.cpp
        ${IJKMEDIA_SOURCE_DIR}/ijkplayer/*.c
        ${IJKMEDIA_SOURCE_DIR}/ijkplayer/*.cpp
        # ijkmedia/ijkyuv
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/compare.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/compare_common.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/compare_posix.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert_argb.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert_from.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert_from_argb.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert_to_argb.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/convert_to_i420.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/cpu_id.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/format_conversion.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/planar_functions.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/rotate.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/rotate_argb.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/rotate_mips.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/row_any.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/row_common.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/row_mips.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/row_posix.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale_argb.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale_common.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale_mips.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale_posix.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/video_common.cc
        #ijkyuv-abi-arm64
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/compare_neon64.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/rotate_neon64.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/row_neon64.cc
        ${IJKMEDIA_SOURCE_DIR}/ijkyuv/source/scale_neon64.cc
        )

file(GLOB_RECURSE ijkprof_files "${IJKPROF_SOURCE_DIR}/*.c")

add_library(
        ijkplayer
        SHARED

        ${ijkmedia_files}
        ${ijkprof_files}
)

target_link_libraries( # Specifies the target library.
        ijkplayer
        ${EXTRA_SOURCE_DIR}/libs/libijkffmpeg.so
        log
        android
        EGL
        GLESv2
        jnigraphics
        OpenSLES
        m z
        )