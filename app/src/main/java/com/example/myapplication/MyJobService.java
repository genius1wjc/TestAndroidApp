package com.example.myapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

/**
 * Created by jiechao on 9/5/16.
 */
public class MyJobService extends JobService {

    private UpdateAppsAsyncTask task = new UpdateAppsAsyncTask();

    @Override
    public boolean onStartJob(JobParameters params) {
        // Note: this is preformed on the main thread.

        int jobId = params.getJobId();

        task.execute(params);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // This will get called when your parameters are no longer being met. For example, wifi is turned off.
        // Note: return true to reschedule this job.

        return task.stopJob(params);
    }

    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {

        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {

            // Do updating and stopping logical here.
            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {
            for (JobParameters params : result) {
                if (!hasJobBeenStopped(params)) {
                    jobFinished(params, false); // Call this method when the job is done
                }
            }
        }

        private boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return false;
        }

        public boolean stopJob(JobParameters params) {
            // Logic for stopping a job. return true if job should be rescheduled.
            return true;
        }

    }
}
