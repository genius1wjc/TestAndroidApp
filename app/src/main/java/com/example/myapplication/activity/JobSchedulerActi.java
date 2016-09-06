package com.example.myapplication.activity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myapplication.service.MyJobService;
import com.example.myapplication.R;

public class JobSchedulerActi extends AppCompatActivity {

    private static final int JOB_ID = 0;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        tv = (TextView) findViewById(R.id.textView8);

        ComponentName serviceName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .setMinimumLatency(2000)
                .setPersisted(true) // Persist the job across boot, requires the RECEIVE_BOOT_COMPLETED permission
                .build();

        JobScheduler scheduler = (JobScheduler) this.getSystemService(this.JOB_SCHEDULER_SERVICE);

        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS)
            tv.setText("Succeeded");
        else
            tv.setText("Failed");

    }
}
