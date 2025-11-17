package com.example.threadhandlerandprogressbar

import android.app.Application
import android.media.MediaPlayer
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WorkViewModel(private val app: Application) : AndroidViewModel(app) {

    private val handlerThread = HandlerThread("VM-Work").apply { start() }
    private val worker = Handler(handlerThread.looper)
    private val mainHandler = Handler(app.mainLooper) // 主執行緒 Handler

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> = _progress

    private val _status = MutableLiveData("Idle")
    val status: LiveData<String> = _status

    private var mediaPlayer: MediaPlayer? = null
    @Volatile private var running = false

    fun start() {
        if (running) return
        running = true
        _status.postValue("Preparing…")
        _progress.postValue(0)

        // ✅ 音樂播放在主線程啟動，避免卡住
        startMusic()

        worker.post {
            try {
                Thread.sleep(2000) // 模擬準備階段
                _status.postValue("Working…")

                for (i in 1..100) {
                    if (!running) break
                    Thread.sleep(100) // 模擬工作負載
                    _progress.postValue(i)
                }

                _status.postValue(if (running) "背景工作結束！" else "Canceled")
            } catch (_: InterruptedException) {
                _status.postValue("Canceled")
            } finally {
                running = false
                // ✅ 工作結束或中斷都要在主執行緒釋放音樂
                stopMusic()
            }
        }
    }

    fun cancel() {
        running = false
        _status.postValue("Canceled…")
        stopMusic()
    }

    override fun onCleared() {
        running = false
        stopMusic()
        handlerThread.quitSafely()
        super.onCleared()
    }

    // --- 🎧 音樂控制 ---
    private fun startMusic() {
        mainHandler.post {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(app, R.raw.background_music)?.apply {
                    isLooping = true
                    setVolume(1.0f, 1.0f)
                }
            }
            mediaPlayer?.start()
        }
    }

    private fun stopMusic() {
        mainHandler.post {
            mediaPlayer?.let {
                try {
                    if (it.isPlaying) it.stop()
                } catch (_: Exception) {}
                it.release()
            }
            mediaPlayer = null
        }
    }
}
