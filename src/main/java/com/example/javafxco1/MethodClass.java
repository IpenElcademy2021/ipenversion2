package com.example.javafxco1;

import javax.swing.*;

public class MethodClass {

    public void Exit(){
        MessageBox("Goodbye", "Exit");
        System.exit(0);
    }
    public static void MessageBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
