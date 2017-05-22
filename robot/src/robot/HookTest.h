#ifndef _Included_HookTest
#define _Included_HookTest

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_HookTest_registerHook(JNIEnv * env, jobject
obj);

JNIEXPORT void JNICALL Java_HookTest_unRegisterHook(JNIEnv * env,
jobject obj);

#ifdef __cplusplus
}
#endif

#endif /* _Included_HookTest */
