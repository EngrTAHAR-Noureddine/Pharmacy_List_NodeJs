
package com.example.pharmacylist.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.pharmacylist.controller.ConsultationController.sendUnsendConsultation
import com.google.common.util.concurrent.ListenableFuture


class MyWorkManager(ctx: Context, params: WorkerParameters) : ListenableWorker(ctx, params) {

    @SuppressLint("RestrictedApi")
    val future: SettableFuture<Result> = SettableFuture.create()

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        sendUnsendConsultation(applicationContext)
        future.set(Result.success())
        return future
    }

}
