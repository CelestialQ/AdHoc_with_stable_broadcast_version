#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/system_properties.h>


#include "adhoc_setup_AdHocNative.h"

#define PROPERTY_KEY_MAX   32
#define PROPERTY_VALUE_MAX  92

JNIEXPORT jint JNICALL Java_adhoc_setup_AdHocNative_runCommand
  (JNIEnv * env, jclass class, jstring command)
{
	const char *commandString;
  	commandString = (*env)->GetStringUTFChars(env, command, 0);
  	int exitcode = system(commandString); 
	(*env)->ReleaseStringUTFChars(env, command, commandString);  
	return (jint)exitcode;
}

JNIEXPORT jstring JNICALL Java_adhoc_setup_AdHocNative_getProp
  (JNIEnv * env, jclass class, jstring name)
{
	const char *nameString;
  	nameString = (*env)->GetStringUTFChars(env, name, 0);

  	char value[PROPERTY_VALUE_MAX];
  	char *default_value;
  	jstring jstrOutput;
  
  	default_value = "undefined";
  	property_get(nameString, value, default_value);

  	jstrOutput = (*env)->NewStringUTF(env, value);

  	(*env)->ReleaseStringUTFChars(env, name, nameString);  

  	return jstrOutput;
}

int property_get(const char *key, char *value, const char *default_value)
{
    int len;

    len = __system_property_get(key, value);
    if(len > 0) {
        return len;
    }

    if(default_value) {
        len = strlen(default_value);
        memcpy(value, default_value, len + 1);
    }
    return len;
}
