package com.yzhang.monsterhunterworldcompanion.asynctask;

public interface AsyncResponse {

    Boolean process();
    void postProcess(Boolean isConnected);

}
