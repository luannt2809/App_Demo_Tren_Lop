package com.example.app_demo_tren_lop;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketManager {
    private static final SocketManager INSTANCE = new SocketManager();

    public  static SocketManager getInstance(){
        return INSTANCE;
    }

    private Socket mSocket;
    private SocketManager(){
        try {
            mSocket = IO.socket("http://10.24.26.228:3001");
        } catch (URISyntaxException e) {}
    }

    public void connect() {
        mSocket.connect();
    }

    public Socket getmSocket() {
        return mSocket;
    }

    public void on(String event, Emitter.Listener fn) {
        mSocket.on(event, fn);
    }

    public void emit(String event, Object ...args){
        mSocket.emit(event, args);
    }
}
