package com.paikawa.sbtweaks;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Tweaker implements IXposedHookLoadPackage {
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.android.systemui"))
			return;

		findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				TextView tv = (TextView) param.thisObject;
				tv.setTextColor(Color.parseColor("#33b5e5"));
			}
		});
	}
}