package org.liberty.android.fantastischmemo.JacocoInstrument;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;


public class SMSInstrumentedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // write the coverage file to the internal storage, which will not require any permissions
        // see: https://developer.android.com/training/data-storage/files
        // the output dir usually locates at: /data/data/#{app_package_name}/files/coverage.ec
        
        // This api has been deprecated since Q os(Android 10)
        /*
        File coverageFile = new File(context.getFilesDir(), "coverage.ec");
        String coverageFilePath = coverageFile.getAbsolutePath();
        */

        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if(!downloadDir.exists()){
            downloadDir.mkdirs();
        }
        String coverageFileName = "coverage.ec";
        File coverageFilePath = new File(downloadDir + File.separator + coverageFileName);
        Log.i("COV","checking coverage path : " + coverageFilePath);

        FinishListener mListener = new JacocoInstrumentation(coverageFilePath.getAbsolutePath());
        mListener.dumpIntermediateCoverage(coverageFilePath.getAbsolutePath());
    }
}
