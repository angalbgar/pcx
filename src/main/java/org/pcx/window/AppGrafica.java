package org.pcx.window;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.pcx.utilities.*;
import java.io.*;
import java.awt.Font;
import java.awt.FontFormatException;

import static javax.swing.SwingConstants.CENTER;

public class AppGrafica {
    public AppGrafica()
    {
        //creo componentes gráficos
        JFrame frame = new JFrame("ProjectCodeX");
        JPanel inicio = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        JMenu menu = new JMenu("Funcionalidades");
        JMenuBar b_funcionalidades = new JMenuBar();
        JMenuItem i_principal = new JMenuItem("Inicio");
        JMenuItem i_password = new JMenuItem("Comprobador Contraseñas");
        b_funcionalidades.add(menu);
        menu.add(i_principal);
        menu.add(i_password);
        //funcionalidad contraseña
        JPasswordField f1_text = new JPasswordField();
        JLabel f1_lb = new JLabel("Introduce tu contraseña");
        JLabel f1_result = new JLabel();

        JLabel f1_info = new JLabel("<html>Bienvenido/a a la funcionalidad de Comprobador de Contraseñas. Introduce una contraseña para comprobar si esta es segura o no</html>");

        /*
        Investigando me di cuenta que si una label tiene mucho texto, no hará un salto de linea automático dependiendo del tamaño del frame.
        Vi que hay una solución, si pones el texto  como etiquetas html, java interpretará código html y hará que el texto se ajuste de forma dinámica, es por ello que lo he usado así.
        */

        //  MOVER  LO IMPLEMENTACION DE COMPONENTES DE LOS LISTENERS Y GESTIONAR UNICAMENTE LA VISIBILIDAD DE ESTA FORMA
        /*
        *
        * ActionListener a_i_inicio = z ->
{
    p2.setVisible(false);

    inicio.setVisible(true);

    frame.revalidate();
    frame.repaint();
};
        *
        *
        * */



        ActionListener a_i_inicio = z ->
        {
            //se pone a false todos los paneles para hacer visible unicamente el que está seleccionado.
            p2.setVisible(false);

            inicio.add(f1_lb);
            inicio.setVisible(true);
            inicio.revalidate();
            inicio.repaint();
        };

        ActionListener a_i_password = z ->
        {
            //se pone a false todos los paneles para hacer visible unicamente el que está seleccionado.
            inicio.setVisible(false);

            p2.add(f1_info);
            p2.add(f1_lb);
            p2.add(f1_text);
            p2.add(f1_result);
            p2.setVisible(true);

            //Estas líneas se utilizan para recalcular el layout porque al añadir componentes desde un listener se necesita refrescar como tal el frame.
            p2.revalidate();
            p2.repaint();
        };

        ActionListener a_jpf_password = z ->
        {
            String password = String.valueOf(f1_text.getPassword());
            System.out.println(password);
            f1_result.setText("Se ha generado un reporte en la ruta del proyecto como resultado.txt");
            HBIP comprobador1 = new HBIP(password);
        };

        i_password.addActionListener(a_i_password);
        f1_text.addActionListener(a_jpf_password);
        //LAYOUTS
        p1.setLayout(new FlowLayout());
        inicio.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(4,1));

        //IMPLEMENTO COMPONENTES
        inicio.add(f1_lb);
        //MENU
        p1.add(b_funcionalidades);


        //IMPORTO AL FRAME
        frame.add(p1, BorderLayout.NORTH);
        frame.add(p2, BorderLayout.CENTER);
        frame.add(inicio, BorderLayout.SOUTH);


        //configs básicas
        p1.setBackground(new Color(14, 85, 92));
        frame.setSize(1500,1500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        p1.setVisible(true);
        inicio.setVisible(true);

        // ESTILOS

        //voy a cargar una fuente predeterminada que estoy utilizando también en la web del blog.
        //para ello uso la clase font especificando en los parámetros el archivo y el tamaño
        f1_text.setBackground(new Color(87, 180, 244));
        f1_result.setBackground(new Color(182, 243, 248));
        f1_info.setBackground(new Color(182, 243, 248));
        f1_lb.setBackground(new Color(182, 243, 248));

        // estas lineas se ponen para que se pinte el background porque una label por defecto no lo hace
        f1_result.setOpaque(true);
        f1_info.setOpaque(true);
        f1_lb.setOpaque(true);


        //creo la fuente
        Font fuente_etiquetas = null;

        try {
            fuente_etiquetas = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("./fuentes/sanva_font.ttf")
            ).deriveFont(Font.BOLD,24f);
        }
        catch (IOException  e) {System.out.println(e);}
        catch (FontFormatException e){System.out.println(e );}

        Font fuente_jpf = null;

        try {
            fuente_jpf = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("./fuentes/sanva_font.ttf")
            ).deriveFont(Font.BOLD,50f);
        }
        catch (IOException  e) {System.out.println(e);}
        catch (FontFormatException e){System.out.println(e );}


        //asigno la fuente
        f1_lb.setFont(fuente_etiquetas);
        f1_info.setFont(fuente_etiquetas);
        f1_result.setFont(fuente_etiquetas);

        menu.setOpaque(true);
        menu.setFont(fuente_etiquetas);
        menu.setForeground(new Color (198, 189, 189));
        menu.setBackground(new Color(5, 97, 161));

        i_principal.setOpaque(true);
        i_principal.setForeground(new Color(5, 97, 161));
        i_principal.setFont(fuente_etiquetas);

        i_password.setOpaque(true);
        i_password.setForeground(new Color(5, 97, 161));
        i_password.setFont(fuente_etiquetas);


        f1_info.setHorizontalAlignment(SwingConstants.LEFT);
        f1_info.setVerticalAlignment(SwingConstants.CENTER);

        f1_result.setHorizontalAlignment(SwingConstants.LEFT);
        f1_result.setVerticalAlignment(SwingConstants.CENTER);

        f1_lb.setHorizontalAlignment(CENTER);
        f1_lb.setVerticalAlignment(CENTER);

        f1_text.setFont(fuente_jpf);

    }

}
