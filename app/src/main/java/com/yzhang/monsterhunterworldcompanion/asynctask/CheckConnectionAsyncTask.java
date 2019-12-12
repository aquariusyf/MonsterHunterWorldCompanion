package com.yzhang.monsterhunterworldcompanion.asynctask;

import android.os.AsyncTask;

public class CheckConnectionAsyncTask extends AsyncTask<Void, Void, Boolean> {

    public AsyncResponse asyncResponse = null;

    @Override
    protected Boolean doInBackground(Void... voids) {
        return asyncResponse.process();
    }

    @Override
    protected void onPostExecute(Boolean isConnected) {
        super.onPostExecute(isConnected);
        asyncResponse.postProcess(isConnected);
    }
}
