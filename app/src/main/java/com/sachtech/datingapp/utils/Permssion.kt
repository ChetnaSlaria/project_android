package cool.rishab.gallerydemo

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sachtech.datingapp.app.DatingApp


/**
 * Created by Akash Saggu(R4X) on 12/9/18 at 18:53.
 * akashsaggu@protonmail.com
 * @Version 1 (12/9/18)
 * @Updated on 12/9/18
 */


fun Array<String>.withPermissions(activity: Activity, body: (() -> Unit)? = null) {
    if (checkPermissions(this)) {
        body?.invoke()
    } else {
        val maxCount = 20
        var count = 0
        ActivityCompat.requestPermissions(
            activity,
            this,
            700
        )

        val handler = Handler()
        var runnable: Runnable? = null
        runnable = Runnable {
            if (checkPermissions(this)) {
                body?.invoke()
                handler.removeCallbacksAndMessages(null)
                runnable = null
            }
            if (count > maxCount) {
                handler.removeCallbacksAndMessages(null)
                runnable = null
            }
            count += 1
            handler.postDelayed(runnable, 500)
        }
        handler.postDelayed(runnable, 500)
    }
}

fun <T> T.checkPermissions(permissions: Array<String>): Boolean {
    var allPerm = true
    for (permission: String in permissions) {
        if (ContextCompat.checkSelfPermission(DatingApp.application!!, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            allPerm = false
        }
    }
    return allPerm
}