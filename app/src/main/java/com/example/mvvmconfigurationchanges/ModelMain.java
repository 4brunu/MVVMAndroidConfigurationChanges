package com.example.mvvmconfigurationchanges;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ModelMain {

    interface ListOfDataCallback {
        void onResult(ArrayList<String> strings);
    }

    private void simulateWork() {

        try {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public void getListOfData(ListOfDataCallback listOfDataCallback) {

        simulateWork();

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            strings.add(""+i);
        }

        listOfDataCallback.onResult(strings);

    }

    interface SendDataToServerCallback {
        void onResult(String serverResponse);
    }

    public void sendDataToServer(SendDataToServerCallback sendDataToServerCallback) {

        simulateWork();

        sendDataToServerCallback.onResult("Success!!!");

    }

}
