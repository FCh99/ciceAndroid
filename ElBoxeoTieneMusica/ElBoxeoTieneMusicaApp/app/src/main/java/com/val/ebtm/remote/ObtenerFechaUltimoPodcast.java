package com.val.ebtm.remote;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Tarea que recupera la fecha del último Podcast disponible en el servidor
 * Necesario para el servicio de Notificaciones incluído a partir de la 1.1.4
 *
 *
 *
 * @version Disponible desde versión Notario
 * @author Val
 */

//TODO ES necesario sacar todas las uRLS de servicio a un fichero de propiedades o al menos decalrarla como constantes
public class ObtenerFechaUltimoPodcast extends AsyncTask<Void, Integer, String> {

	@Override
	protected String doInBackground(Void... params) {
		
		String fecha_ultimo = null;
		
		try 
			{
			
				Log.d(this.getClass().getCanonicalName(), "Llamado a ObtenerFechaUltimo");
				//URL serverUrl = new URL("http://ebtm-ebtm.rhcloud.com/RadioMarcaServer/ObtenerFechaUltimo");
				//http://172.31.248.80:8080/RadioMarcaServer/


				URL serverUrl = new URL("http://172.31.248.80:8080/RadioMarcaServer/ListarPodcastsDisponibles");
				HttpURLConnection httpCon = (HttpURLConnection) serverUrl.openConnection();

                if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());

                    BufferedReader br = new BufferedReader(isr);

                    fecha_ultimo = br.readLine();

                    br.close();

                    isr.close();
                }
				httpCon.disconnect();
			
			}
			catch (Exception e)
				{
					Log.e(getClass().getCanonicalName(), "Error al Obtener FECHA último podcast",  e);
				}
		
		return fecha_ultimo;
	}

	@Override
	protected void onPostExecute(String result) {

		Log.d(getClass().getCanonicalName(),"Hemos recibido de ObtenerFechaUltimo= "+result);
	}

}
