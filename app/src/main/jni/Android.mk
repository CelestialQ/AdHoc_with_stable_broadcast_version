LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE    := jnitask
LOCAL_SRC_FILES := adhoc_setup_AdHocNative.c
LOCAL_ALLOW_UNDEFINED_SYMBOLS := true
LOCAL_STATIC_LIBRARIES := libcutils libc
include $(BUILD_SHARED_LIBRARY)
