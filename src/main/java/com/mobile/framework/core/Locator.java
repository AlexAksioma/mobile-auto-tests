package com.mobile.framework.core;

public final class Locator {

    private final String androidXPath;
    private final String iosXPath;

    private Locator(String androidXPath, String iosXPath) {
        this.androidXPath = androidXPath;
        this.iosXPath = iosXPath;
    }

    public static Locator of(String androidXPath, String iosXPath) {
        return new Locator(androidXPath, iosXPath);
    }

    public static Locator same(String xpath) {
        return new Locator(xpath, xpath);
    }

    public String xpath() {
        return Platform.current().isAndroid() ? androidXPath : iosXPath;
    }

    public String androidXPath() { return androidXPath; }
    public String iosXPath()     { return iosXPath; }

    public Locator child(Locator child) {
        return new Locator(
                this.androidXPath + child.androidXPath,
                this.iosXPath + child.iosXPath
        );
    }

    @Override
    public String toString() {
        return xpath();
    }
}
