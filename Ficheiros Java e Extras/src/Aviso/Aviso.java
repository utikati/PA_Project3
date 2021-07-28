package Aviso;

import javax.swing.*;
import java.util.Scanner;

/**
 * Classe com os avisos
 * @author Jorge Martins e Rodrigo Duro
 * Classe com os avisos
 */
public class Aviso extends JFrame{
    /**
     * Metodo geral usado para aviso como pop up no programa
     * @param aMensagem corpo da mensagem a demonstrar
     * @param aTitle titulo da janela
     * @param aType tipo de mensagem error para mensagem de tipo error_message e info para information_message
     */
    public static void showMessage(String aMensagem, String aTitle, String aType) {
        if(aType.equals("info")) JOptionPane.showMessageDialog(new JFrame(), aMensagem, aTitle, JOptionPane.INFORMATION_MESSAGE);
        if(aType.equals("error")) JOptionPane.showMessageDialog(new JFrame(), aMensagem, aTitle, JOptionPane.ERROR_MESSAGE);
    }


}
