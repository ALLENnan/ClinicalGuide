package com.allen.guide.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

public class IntentUtil {
    public static void startActivity(Context context, Class<?> cls, String key, Serializable value) {
        Intent intent = new Intent(context, cls);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
