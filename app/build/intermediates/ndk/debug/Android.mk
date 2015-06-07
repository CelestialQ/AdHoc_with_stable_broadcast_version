LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := jnitask
LOCAL_SRC_FILES := \
	C:\Users\Q\StudioProjects\AdHoc_with_stable_broadcast_version\app\src\main\jni\adhoc_setup_AdHocNative.c \
	C:\Users\Q\StudioProjects\AdHoc_with_stable_broadcast_version\app\src\main\jni\Android.mk \

LOCAL_C_INCLUDES += C:\Users\Q\StudioProjects\AdHoc_with_stable_broadcast_version\app\src\main\jni
LOCAL_C_INCLUDES += C:\Users\Q\StudioProjects\AdHoc_with_stable_broadcast_version\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
