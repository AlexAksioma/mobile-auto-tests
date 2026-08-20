package com.mobile.framework.core;

public enum ScrollDirection {
    UP("up"),
    DOWN("down");

    private final String value;

    ScrollDirection(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
