package com.jimmy.permissionx

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import java.util.ArrayList

/**
 * @Description:
 * @Author:         zhangchun
 * @CreateDate:     2024/7/5
 * @Version:        1.0
 */
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            var allGRanted = deniedList.isEmpty()
            callback?.let {
                it(allGRanted, deniedList)
            }
        }

    }
}

