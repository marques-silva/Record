package com.marques.record;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class LigBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String estado = intent.getStringExtra("state");
        String numero = intent.getStringExtra("incoming_number");
        String mensagem = null;
        //Fazer funcionar o voice_call porem no MIC----------
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);
        //increase Volume
        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), 0);
//------------


        if (estado.equals("RINGING")) {
            // mensagem = "O n�mero "+ numero +" est� chamando.";
            Log.i("script", "RINGING");
        } else if (estado.equals("OFFHOOK")) {//estado Ao atender ou fazer uma chamada
            //mensagem = "Em ligacao.";
            Intent it = new Intent("GRAVAR");
            context.startService(it);

        } else if (estado.equals("IDLE")) {//ao terminar uma liga��o
            // if (numero != null){
            // Log.i("script","IDLE-null");
            // mensagem = "Chamada n�o atendida de "+ numero;
            Intent it = new Intent("GRAVAR");
            context.stopService(it);
            // } else {

            // mensagem = "Chamada atendida, foi terminada.";
            // Intent it= new Intent("GRAVAR");
            //context.stopService(it);
            // }
        }
        //Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();


    }


}
