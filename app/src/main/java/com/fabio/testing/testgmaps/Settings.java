package com.fabio.testing.testgmaps;

import android.content.SharedPreferences;


public class Settings {

    private static String username;
    private static String password;
    private static String serverProtocol;
    private static String serverAddress;
    private static float zoomLevel;
    private static int serverPort;


    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getServerProtocol() {
        return serverProtocol;
    }

    public static String getServerAddress() {
        return serverAddress;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static float getZoomLevel() {return zoomLevel; }

    public static void setUsername(String username) {
        Settings.username = username;
    }

    public static void setPassword(String password) {
        Settings.password = password;
    }

    public static void setServerProtocol(String serverProtocol) {
        Settings.serverProtocol = serverProtocol;
    }

    public static void setServerAddress(String serverAddress) {
        Settings.serverAddress = serverAddress;
    }

    public static void setServerPort(int serverPort) {
        Settings.serverPort = serverPort;
    }

    public static void setZoomLevel(float zoomLevel) {
        Settings.zoomLevel = zoomLevel;
    }
}