package org.pcx.window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import org.pcx.utilities.*;
import java.io.*;
import java.awt.Font;

import org.pcx.utilities.Fuente;

import static javax.swing.SwingConstants.CENTER;
/**
* Aplicación gráfica que instancia un prototipo de aplicación
* */
public class AppGrafica {
    private File f;
    private boolean contador_hash = true;
    private boolean contador_jfc = true;
    /**
    * Instanciador de la aplicación gráfica prototipada
    * */
    public AppGrafica()
    {
        //CREACIÓN DE COMPONENTES GRÁFICOS

        //CREO EL CARDLAYOUT PARA CAMBIAR PANELES ENTRE MENÚS Y EL CONTENEDOR PRINCIPAL QUE CONTENDRÁ EL RESTO DE PANELES
        CardLayout cardLayout = new CardLayout();
        JPanel contenedor = new JPanel(cardLayout);

        //CREO EL FRAME PRINCIPAL Y LOS PANELES DE LA APLICACIÓN
        JFrame frame = new JFrame("ProjectCodeX");
        JPanel inicio = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        // IMPLEMENTO AL CONTENEDOR LOS PANELES.
        contenedor.add(inicio,"Inicio");
        contenedor.add(p2, "Contraseña");
        contenedor.add(p3, "HASH");

        // DEFINO EL MENÚ PRINCIPAL DE LA APLICACIÓN
        JMenu menu = new JMenu("Funcionalidades");
        JMenuBar b_funcionalidades = new JMenuBar();
        JMenuItem i_principal = new JMenuItem("Inicio");
        JMenuItem i_password = new JMenuItem("Comprobador Contraseñas");
        JMenuItem i_hash = new JMenuItem("Función HASH");
        b_funcionalidades.add(menu);
        menu.add(i_principal);
        menu.add(i_password);
        menu.add(i_hash);
        // DEFINO OBJETOS DE LA SECCIÓN PRINCIPAL.
        JLabel lb_inicio_info = new JLabel("<html>Bienvenido a la aplicación. En esta aplicación se podrá acceder a las diferentes funcionalidades de ciberseguridad en las que se puede navegar por el menú.</html>");
        JLabel lb_inicio = new JLabel("Pantalla principal");

        ImageIcon logo = new ImageIcon("./assets/logo.png");
        JLabel imagen_logo = new JLabel(logo);

        //DEFINO OBJETOS DE LA FUNCIONALIDAD CONTRASEÑA.

        JPasswordField f1_text = new JPasswordField();
        JLabel f1_lb = new JLabel("Introduce tu contraseña");
        JLabel f1_result = new JLabel();
        JLabel f1_info = new JLabel("<html>Bienvenido/a a la funcionalidad de Comprobador de Contraseñas. Introduce una contraseña para comprobar si esta es segura o no</html>");

        /*
        Investigando me di cuenta que si una label tiene mucho texto, no hará un salto de linea automático dependiendo del tamaño del frame.
        Vi que hay una solución, si pones el texto  como etiquetas html, java interpretará código html y hará que el texto se ajuste de forma dinámica, es por ello que lo he usado así.
        */

        // DEFINO OBJETOS DE LA FUNCIÓN HASH.

        JLabel f2_titulo = new JLabel("Función HASH");
        JLabel f2_info = new JLabel("<html>Introduce un archivo para calcular su función HASH.</html>");
        JFileChooser f2_jfc = new JFileChooser();
        JButton f2_b_insertar = new JButton("Seleccionar Archivo");
        JButton f2_b_md5 = new JButton("MD5");
        JButton f2_b_sha1 = new JButton("SHA1");
        JButton f2_b_sha256 = new JButton("SHA256");
        JButton f2_b_sha512 = new JButton("SHA512");
        JTextArea f2_jtf_result = new JTextArea();

        f2_jtf_result.setLineWrap(true);
        f2_jtf_result.setEditable(false);
        f2_jtf_result.setBorder(BorderFactory.createEmptyBorder());

        f2_b_insertar.setBorder(new LineBorder(Color.BLUE, 4));
        f2_b_md5.setBorderPainted(false);
        f2_b_sha1.setBorderPainted(false);
        f2_b_sha256.setBorderPainted(false);
        f2_b_sha512.setBorderPainted(false);


        // L I S T E N E R S

        //LISTENER MENU CAMBIAR HASH
        ActionListener a_i_hash = z ->
        {
            contador_hash = false;

            if (!contador_hash)
                f2_jtf_result.setText("");

            cardLayout.show(contenedor, "HASH");
        };
            //LISTENER HASH PARA DETECTAR LA PULSACION DEL BOTON PARA ACTIVAR EL JFILECHOOSER


            ActionListener a_hash_jfc = z ->
            {
                f2_jfc.showOpenDialog(null);
                f = f2_jfc.getSelectedFile();

                if(!contador_jfc)
                    f2_jtf_result.setText("");
                f2_info.setText("<html>Archivo seleccionado: "+f.getAbsolutePath()+"</html>");
            };

            // LISTENERS HASH PARA DETECTAR LA PULSACIÓN DEL BOTÓN MD5, SHA1,SHA256 Y SHA512

            ActionListener a_hash_md5 = z ->
            {
                contador_hash = true;
                try {
                    String hash_md5 = Hash.getMD5(f);
                    f2_jtf_result.setText("MD5: " + hash_md5);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    contador_jfc = false;
                    f2_jtf_result.setText("No se ha seleccionado ningún archivo.");
                }


                p3.add(f2_jtf_result);
                p3.revalidate();
                p3.repaint();
            };

            ActionListener a_hash_sha1 = z -> {
                contador_hash = true;
                try {
                    String hash_md5 = Hash.getSHA1(f);
                    f2_jtf_result.setText("SHA1: " + hash_md5);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    contador_jfc = false;
                    f2_jtf_result.setText("No se ha seleccionado ningún archivo.");
                }
                p3.add(f2_jtf_result);
                p3.revalidate();
                p3.repaint();
            };

        ActionListener a_hash_sha256 = z -> {
            contador_hash = true;
            try {
                String hash_md5 = Hash.getSHA256(f);
                f2_jtf_result.setText("SHA256: " + hash_md5);
            } catch (IOException e) {
                System.out.println(e);
            } catch (Exception e) {
                contador_jfc = false;
                f2_jtf_result.setText("No se ha seleccionado ningún archivo.");
            }
            p3.add(f2_jtf_result);
            p3.revalidate();
            p3.repaint();
        };

        ActionListener a_hash_sha512 = z -> {
            contador_hash = true;
            try {
                String hash_md5 = Hash.getSHA512(f);
                f2_jtf_result.setText("SHA512: " + hash_md5);
            } catch (IOException e) {
                System.out.println(e);
            } catch (Exception e) {
                contador_jfc = false;
                f2_jtf_result.setText("No se ha seleccionado ningún archivo.");
            }
            p3.add(f2_jtf_result);
            p3.revalidate();
            p3.repaint();
        };
        //LISTENER QUE CAMBIA A LA SECCIÓN DEL INICIO.

        ActionListener a_i_inicio = z ->
        {
            cardLayout.show(contenedor, "Inicio");
        };

        //LISTENER QUECAMBIA AL SECCIÓN DE CONTRASEÑA.

        ActionListener a_i_password = z ->
        {
            cardLayout.show(contenedor, "Contraseña");
        };

            //LISTENER DEL JPF DE LA FUNCIONALIDAD CONTRASEÑA.

            ActionListener a_jpf_password = z ->
            {
                String password = String.valueOf(f1_text.getPassword());
                System.out.println(password);
                f1_result.setText("Se ha generado un reporte en la ruta del proyecto como resultado.txt");
                HBIP comprobador1 = new HBIP(password);
            };

        //IMPLEMENTACION DE TODOS LOS LISTENERS.

        //LISTENERS DE MENÚ
        i_principal.addActionListener(a_i_inicio);
        i_password.addActionListener(a_i_password);
        i_hash.addActionListener(a_i_hash);

        //LISTENER DE FUNCIONALIDAD CONTRASEÑA
        f1_text.addActionListener(a_jpf_password);

        //LISTENER DE FUNCIONALIDAD HASH
        f2_b_insertar.addActionListener(a_hash_jfc);
        f2_b_md5.addActionListener(a_hash_md5);
        f2_b_sha1.addActionListener(a_hash_sha1);
        f2_b_sha256.addActionListener(a_hash_sha256);
        f2_b_sha512.addActionListener(a_hash_sha512);

        //IMPLEMENTACIÓN DE LAYOUTS
        frame.setLayout(new BorderLayout());
        inicio.setLayout(new GridLayout(3,1));

        p1.setLayout(new FlowLayout());
        p2.setLayout(new GridLayout(4,1));
        p3.setLayout(new GridLayout(3,3));

        //IMPLEMENTO EL MENÚ AL PANEL
        p1.add(b_funcionalidades);

        //IMPLEMENTO PANEL DE LA SECCIÓN PRINCIPAL
        inicio.add(lb_inicio);
        inicio.add(imagen_logo);
        inicio.add(lb_inicio_info);

        //IMPLEMENTO LOS ELEMENTOS PANEL DE LA FUNCIONALIDAD CONTRASEÑA
        p2.add(f1_info);
        p2.add(f1_lb);
        p2.add(f1_text);
        p2.add(f1_result);

        //IMPLEMENTO LOS ELEMENTOS DEL PANEL DE LA FUNCIÓN HASH

        p3.add(f2_titulo);
        p3.add(f2_info);
        p3.add(f2_b_insertar);
        p3.add(f2_b_md5);
        p3.add(f2_b_sha1);
        p3.add(f2_b_sha256);
        p3.add(f2_b_sha512);

        //IMPORTO AL FRAME EL MENÚ (p1) Y EL CONTENEDOR QUE ACTUARÁ COMO CARGADOR DE TARJETAS PARA CAMBIAR DE UNO A OTRO
        frame.add(p1, BorderLayout.NORTH);
        frame.add(contenedor, BorderLayout.CENTER);


        //CONFIGURACIONES BÁSICAS
        frame.setSize(1500,1500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        p1.setBackground(new Color(14, 85, 92));
        p1.setVisible(true);
        inicio.setVisible(true);

        // ESTILOS

        //voy a cargar una fuente predeterminada que estoy utilizando también en la web del blog.
        //para ello uso la clase font especificando en los parámetros el archivo y el tamaño

        //creo la fuente

        Fuente f1 = new Fuente(Fuente.fuente1);
        Fuente f2 = new Fuente(Fuente.fuente2);
        Fuente f3 = new Fuente(Fuente.fuente3);

        Font fuente_etiquetas = f1.getFuente();
        Font fuente_titulo = f2.getFuente();
        Font fuente_jpf = f3.getFuente();

        //COLORES PARA REUTILIZAR Y NO ESTAR CREANDO TANTOS OBJETOS

        Color cian = new Color(182, 243, 248);
        Color cian_azul = new Color(87, 180, 244);
        Color azul_oscuro = new Color(5, 97, 161);
        Color gris = new Color(198, 189, 189);

        //ESTILOS PARA LA SECCIÓN PRINCIPAL
        lb_inicio.setBackground(gris);
        lb_inicio_info.setBackground(gris);
        imagen_logo.setBackground(gris);
        // estas lineas se ponen para que se pinte el background porque una label por defecto no lo hace
        lb_inicio.setOpaque(true);
        lb_inicio_info.setOpaque(true);
        imagen_logo.setOpaque(true);
        lb_inicio.setFont(fuente_titulo);
        lb_inicio_info.setFont(fuente_jpf);

        lb_inicio.setHorizontalAlignment(CENTER);
        lb_inicio.setVerticalAlignment(CENTER);
        lb_inicio_info.setHorizontalAlignment(CENTER);
        lb_inicio_info.setVerticalAlignment(CENTER);

        // ESTILOS PARA LA SECCIÓN CONTRASEÑA
        f1_text.setBackground(cian_azul);
        f1_result.setBackground(gris);
        f1_info.setBackground(gris);
        f1_lb.setBackground(gris);

        f1_result.setOpaque(true);
        f1_info.setOpaque(true);
        f1_lb.setOpaque(true);

        f1_text.setFont(fuente_jpf);
        f1_lb.setFont(fuente_titulo);
        f1_info.setFont(fuente_etiquetas);
        f1_result.setFont(fuente_etiquetas);

        f1_info.setHorizontalAlignment(SwingConstants.CENTER);
        f1_info.setVerticalAlignment(SwingConstants.CENTER);

        f1_result.setHorizontalAlignment(SwingConstants.LEFT);
        f1_result.setVerticalAlignment(SwingConstants.CENTER);

        f1_lb.setHorizontalAlignment(CENTER);
        f1_lb.setVerticalAlignment(CENTER);

        // ESTILOS PARA EL MENÚ

        menu.setOpaque(true);
        menu.setFont(fuente_etiquetas);
        menu.setForeground(gris);
        menu.setBackground(azul_oscuro);

        i_principal.setOpaque(true);
        i_principal.setForeground(azul_oscuro);
        i_principal.setFont(fuente_etiquetas);

        i_password.setOpaque(true);
        i_password.setForeground(azul_oscuro);
        i_password.setFont(fuente_etiquetas);

        i_hash.setOpaque(true);
        i_hash.setForeground(azul_oscuro);
        i_hash.setFont(fuente_etiquetas);

        // ESTILOS PARA LA FUNCIÓN HASH
        f2_titulo.setBackground(gris);
        f2_info.setBackground(gris);
        f2_jtf_result.setBackground(gris);
        f2_b_insertar.setBackground(gris);
        f2_b_md5.setBackground(gris);
        f2_b_sha1.setBackground(gris);
        f2_b_sha256.setBackground(gris);
        f2_b_sha512.setBackground(gris);
        p3.setBackground(gris);
        f2_titulo.setOpaque(true);
        f2_info.setOpaque(true);

        f2_titulo.setFont(fuente_jpf);
        f2_info.setFont(fuente_etiquetas);
        f2_jtf_result.setFont(fuente_etiquetas);
        f2_b_insertar.setFont(fuente_etiquetas);
        f2_b_md5.setFont(fuente_etiquetas);
        f2_b_sha1.setFont(fuente_etiquetas);
        f2_b_sha256.setFont(fuente_etiquetas);
        f2_b_sha512.setFont(fuente_etiquetas);

        f2_titulo.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
