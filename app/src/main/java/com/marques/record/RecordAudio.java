package com.marques.record;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.media.AudioManager;
import android.content.Context;

public class RecordAudio {
    private static String mFileName = null;
    private static final String LOG_TAG = "script";
    private MediaRecorder mRecorder = null;

    public RecordAudio() {
        try {
            getDirFromSDCard();
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();//retorna /mnt/sdcard
            mFileName += "/Rec/REC" + getDate() + ".3gp";
            //mFileName = "mnt/sdcard/audiorecordtest.3gp";
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("script", "erro RecordAudio()" + e);
        }
    }

    private File getDirFromSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdcard = Environment.getExternalStorageDirectory().getAbsoluteFile();
            //File dir = new File(sdcard, "APP_NAME" + File.separator + "PASTA_1");
            File dir = new File(sdcard, "Rec");
            if (!dir.exists())
                dir.mkdirs();
            return dir;
        } else {
            Log.i("script", "getDirFromSDCard()");
            return null;
        }
    }


    private void startRecording() {
        mRecorder = new MediaRecorder();

        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); //tipo de gracao MIC ou VOICE_CALL
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//formato da grava��o = .3gp
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//encoder
        mRecorder.setOutputFile(mFileName);//path ="/mnt/sdcard/NOME_DO_ARQUIVO.3GP
        try {
            //preparando o mediaRecorder
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed" + e);
        }
        //gravando
        mRecorder.start();
    }

    // private void onRecord(boolean start) {
    public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HHmmss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

}
