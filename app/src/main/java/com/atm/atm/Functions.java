package com.atm.atm;

public class Functions {

    String name;
    int icon;

    public Functions() {
    }

    public Functions(String name) {
        this.name = name;
    }

    public Functions(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
