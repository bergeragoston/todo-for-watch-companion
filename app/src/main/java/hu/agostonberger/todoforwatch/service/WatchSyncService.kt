package hu.agostonberger.todoforwatch.service

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import com.samsung.android.sdk.accessory.SASocket
import android.widget.Toast
import com.samsung.android.sdk.accessory.SAAgent
import com.samsung.android.sdk.accessory.SAPeerAgent
import com.samsung.android.sdk.SsdkUnsupportedException
import com.samsung.android.sdk.accessory.SA
import hu.agostonberger.todoforwatch.MainActivity
import org.slf4j.LoggerFactory


class WatchSyncService : SAAgent(TAG, WatchSyncServiceSocket::class.java) {

    companion object {
        private val CHANNEL_ID = 110
        private val log = LoggerFactory.getLogger(WatchSyncService::class.java)
    }

    private var syncServiceSocket: WatchSyncServiceSocket? = null


    override fun onBind(intent: Intent?): IBinder? {
        log.info("onBind called")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val accessory = SA()
        try {
            accessory.initialize(this)
        } catch (e: SsdkUnsupportedException) {
            log.error("Samsung SDK not supported!", e)
        } catch (e: Exception) {
            log.error("Error on create", e)
            stopSelf()
        }

    }

    override fun onServiceConnectionResponse(
        peerAgent: SAPeerAgent?,
        socket: SASocket?,
        result: Int
    ) {
        if (result == CONNECTION_SUCCESS) {
            if (socket != null) {
                syncServiceSocket = socket as WatchSyncServiceSocket?
                Toast.makeText(
                    baseContext, "Gear connection is established",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (result == CONNECTION_ALREADY_EXIST) {
            Toast.makeText(
                baseContext, "Gear connection already exists",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class WatchSyncServiceSocket : SASocket("syncSocket") {
        override fun onError(channelId: Int, errorMessage: String?, errorCode: Int) {
            log.info("onError called, channelId: {}, errorCode: {}, errorMessage: {}", channelId, errorCode, errorMessage)
        }

        override fun onReceive(channelId: Int, data: ByteArray?) {
            log.info("onReceive called, channelId: {}", channelId)
        }

        override fun onServiceConnectionLost(reason: Int) {
            log.info("Service connection lost, reason: {}", reason)
            close()
        }
    }
}