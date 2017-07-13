package com.scheduler.app.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    public static final int JOB_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scheduleJob();


//        findViewById(R.id.btn_schedule_job).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scheduleJob();
//            }
//        });

    }

    private void scheduleJob() {
        ComponentName serviceName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .setPeriodic(1000)
//                .setOverrideDeadline(400) // Remove comment for faster testing.
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) {
            Toast.makeText(this, R.string.job_scheduled_successfully, Toast.LENGTH_LONG).show();
        }
    }
}
