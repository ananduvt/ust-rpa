/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ust.keysim.ui.misc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextArea;

/**
 *
 * @author u55369
 */
public class CustomOutputStream extends OutputStream {

    private JTextArea textArea;
    private PrintStream defaultStream;

    public CustomOutputStream(JTextArea textArea, PrintStream defaultStream) {
        this.textArea = textArea;
        this.defaultStream = defaultStream;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
        defaultStream.print(String.valueOf((char) b));

    }
}
