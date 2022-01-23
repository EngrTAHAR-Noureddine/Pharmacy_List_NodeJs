
package com.example.pharmacylist.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.pharmacylist.controller.ConsultationController
import com.example.pharmacylist.controller.ConsultationController.cancelWorkManager
import com.example.pharmacylist.controller.ConsultationController.getListOfUnsendingConsultation
import com.example.pharmacylist.controller.ConsultationController.sendUnsendConsultation


class MyWorkManager(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        getListOfUnsendingConsultation(applicationContext)
        if(!ConsultationController.unsendingConsultationsList.value.isNullOrEmpty()){
            sendUnsendConsultation(applicationContext)
            return Result.success()
        }else { cancelWorkManager()
                return Result.failure()}
    }

}
