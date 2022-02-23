/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Misc;

import java.util.List;

/**
 *
 * @author u55369
 */
public class Position {

    private List<Click> clickList;
    private List<Rect> rectList;

    public Position(List<Click> clickList, List<Rect> rectList) {
        this.clickList = clickList;
        this.rectList = rectList;
    }

    public List<Click> getClickList() {
        return clickList;
    }

    public void setClickList(List<Click> clickList) {
        this.clickList = clickList;
    }

    public List<Rect> getRectList() {
        return rectList;
    }

    public void setRectList(List<Rect> rectList) {
        this.rectList = rectList;
    }

}
