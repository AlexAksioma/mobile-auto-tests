package com.mobile.framework.core;

public enum Platform {
    ANDROID,
    IOS;

    private static Platform current;

    public static Platform current() {
        if (current == null) {
            String env = System.getProperty("platform", System.getenv().getOrDefault("PLATFORM", "ANDROID"));
            current = Platform.valueOf(env.toUpperCase());
        }
        return current;
    }

    public static void setCurrent(Platform platform) {
        current = platform;
    }

    public boolean isAndroid() { return this == ANDROID; }
    public boolean isIos()     { return this == IOS; }
}
