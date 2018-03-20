package com.example.lyd.likeclicktest;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * @Description: 第二步  新建ClickService继承AccessibilityService
 * @Author:LYD
 * @ParameterName: create at 2018/3/20 14:06
 */

public class ClickService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        performClick();
    }

    /**
     * 执行点击
     */
    private void performClick() {
        AccessibilityNodeInfo nodeInfo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            nodeInfo = this.getRootInActiveWindow();
        }
        AccessibilityNodeInfo targetNode = null;

        //通过名字获取
        //targetNode = findNodeInfoByText(nodeInfo,"7");

        //通过id获取
        //targetNode = findNodeInfoById(nodeInfo, "com.miui.calculator:id/btn_7");
        if (targetNode != null && targetNode.isClickable()) {
            targetNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    @Override
    public void onInterrupt() {

    }


    /**
     * 通过id查找
     *
     * @param nodeInfo
     * @param resId
     * @return
     */
    public static AccessibilityNodeInfo findNodeInfoById(AccessibilityNodeInfo nodeInfo, String resId) {
        List<AccessibilityNodeInfo> list = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
        }
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过文本查找
     *
     * @param nodeInfo
     * @param text
     * @return
     */
    public static AccessibilityNodeInfo findNodeInfoByText(AccessibilityNodeInfo nodeInfo, String text) {
        List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


}
