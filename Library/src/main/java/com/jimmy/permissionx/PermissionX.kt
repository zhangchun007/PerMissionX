package com.jimmy.permissionx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/7/5
 * @Version:        1.0
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        var fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            var invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }

        //*表示将一个数组转换成可变长度参数传递过去
        fragment.requestNow(callback, *permission)

    }
}