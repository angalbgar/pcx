package org.pcx.window;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppGrafica {
    public AppGrafica()
    {
        //creo componentes gráficos
        JFrame frame = new JFrame("ProjectCodeX");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        // Elementos independientes tipo botón
        JButton noticias = new JButton("Noticias");
        JButton practicas = new JButton("Buenas Prácticas");
        JButton funcionalidades = new JButton("Funcionalidades");

        JMenu menu = new JMenu("Funcionalidades");
        JMenuBar b_funcionalidades = new JMenuBar();
        JMenuItem i_password = new JMenuItem("Comprobador Contraseñas");

        //funcionalidad contraseña
        JTextField f1_text = new JTextField();
        JLabel f1_lb = new JLabel("Introduce tu contraseña");
        JLabel f1_result = new JLabel();
        JLabel f1_info = new JLabel();
        p2.setLayout(new BorderLayout());
        menu.add(i_password);

        b_funcionalidades.add(menu);
        ActionListener a_noticias = z ->
        {
            //refresco de componentes
            p2.setVisible(false);
            p2.revalidate();
            p2.repaint();
        };

        ActionListener a_practicas = z ->
        {
            //refresco de componentes
            p2.setVisible(false);
            p2.revalidate();
            p2.repaint();
        };

        ActionListener a_i_password = z ->
        {
            p2.add(f1_text, BorderLayout.NORTH);
            p2.add(f1_info, BorderLayout.CENTER);
            p2.add(f1_result, BorderLayout.SOUTH);
            p2.setVisible(true);


            //Estas líneas se utilizan para recalcular el layout porque al añadir componentes desde un listener se necesita refrescar como tal el frame.
            p2.revalidate();
            p2.repaint();
        };
        noticias.addActionListener(a_noticias);
        practicas.addActionListener(a_practicas);
        i_password.addActionListener(a_i_password);

        //genero layout
        p1.setLayout(new FlowLayout());

        frame.setLayout(new BorderLayout());
        //implemento componentes

        //MENU
        p1.add(noticias);
        p1.add(practicas);
        p1.add(b_funcionalidades);



        frame.add(p1, BorderLayout.NORTH);
        frame.add(p2, BorderLayout.CENTER);
        //configs básicas

        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        p1.setVisible(true);

    }

}
