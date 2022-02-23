/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ust.keysim.ui.misc;

/**
 *
 * @author u55369
 */
public class Rect {

    private String name;
    private int pressX;
    private int pressY;
    private int releaseX;
    private int releaseY;

    public Rect(String name, int pressX, int pressY, int releaseX, int releaseY) {
        this.name = name;
        this.pressX = pressX;
        this.pressY = pressY;
        this.releaseX = releaseX;
        this.releaseY = releaseY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPressX() {
        return pressX;
    }

    public void setPressX(int pressX) {
        this.pressX = pressX;
    }

    public int getPressY() {
        return pressY;
    }

    public void setPressY(int pressY) {
        this.pressY = pressY;
    }

    public int getReleaseX() {
        return releaseX;
    }

    public void setReleaseX(int releaseX) {
        this.releaseX = releaseX;
    }

    public int getReleaseY() {
        return releaseY;
    }

    public void setReleaseY(int releaseY) {
        this.releaseY = releaseY;
    }

    @Override
    public String toString() {
        return "(" + pressX + "," + pressY + ") , (" + releaseX + "," + releaseY + ")";
    }

}
