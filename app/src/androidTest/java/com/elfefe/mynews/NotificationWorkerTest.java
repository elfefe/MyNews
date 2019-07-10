package com.elfefe.mynews;


import android.content.Context;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.impl.utils.SynchronousExecutor;
import androidx.test.runner.*;

import androidx.work.testing.WorkManagerTestInitHelper;

import com.elfefe.mynews.controllers.NotificationWorker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class NotificationWorkerTest {
    @Before
    public void setup() {
        Context context = InstrumentationRegistry.getTargetContext();
        Configuration config = new Configuration.Builder()
            // Set log level to Log.DEBUG to
            // make it easier to see why tests failed
            .setMinimumLoggingLevel(Log.DEBUG)
            // Use a SynchronousExecutor to make it easier to write tests
            .setExecutor(new SynchronousExecutor())
            .build();

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config);
    }

    @Test
    public void testSimpleEchoWorker() throws Exception {
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .build();

        WorkManager workManager = WorkManager.getInstance();

        workManager.enqueue(request).getResult().get();

        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();

        Assert.assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
    }
}
