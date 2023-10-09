package test.s160419098.anmp.w04.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import test.s160419098.anmp.w04.R

fun createNotificationChannel(
    context: Context,
    priority: Int,
    showBadge: Boolean,
    name: String,
    description: String,
) {
    val channelId = "${context.packageName} - ${context.getString(R.string.app_name)}"

    val channel = NotificationChannel(channelId, name, priority).apply {
        this.description = description
        setShowBadge(showBadge)
    }

    context.getSystemService(NotificationManager::class.java)
        .createNotificationChannel(channel)
}