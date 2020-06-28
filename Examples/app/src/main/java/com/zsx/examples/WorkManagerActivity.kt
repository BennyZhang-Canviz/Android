package com.zsx.examples

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*


class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this, Observer {
                if(it.state == WorkInfo.State.SUCCEEDED){
                    Log.e("upload image tag", "Upload completed" );
                }
                if(it.state == WorkInfo.State.RUNNING){
                    Log.e("upload image tag", "Uploading many many images..." );
                }
            })
    }
}

class UploadWorker(appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Do the work here--in this case, upload the images.

        uploadImages()

        // Indicate whether the task finished successfully with the Result
        return Result.success()
    }

    private fun uploadImages() {
        Log.d("upload image tag","I uploaded many many images.")
    }


}
