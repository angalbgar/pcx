package org.pcx.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
* Obtiene tres modelos de fuente basada en la fuente sanva_font.ttf.
* */
public class Fuente
{
    private Font fuente;
    /**
    * Fuente en negrita con tamaño de 24 pixeles.
    * */
    public static int fuente1 = 1;
    /**
    * Fuente en negrita con tamaño de 80 pixeles
    * */
    public static int fuente2 = 2;
    /**
    * Fuente en negrita con tamaño de 50 pixeles.
    * */
    public static int fuente3 = 3;
/**
* Instancia una fuente dependiendo de su atributo
* @param fuente_n Tipo de fuente
* */
    public Fuente(int fuente_n)
    {
        if (fuente_n == fuente1)
        {
            try {

                fuente = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("./fuentes/sanva_font.ttf")
                ).deriveFont(Font.BOLD,24);
            }
            catch (IOException e) {System.out.println(e);}
            catch (FontFormatException e){System.out.println(e);}

        }
        else if (fuente_n == fuente2)
        {
            try {
                fuente = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("./fuentes/sanva_font.ttf")
                ).deriveFont(Font.BOLD,80);
            }
            catch (IOException  e) {System.out.println(e);}
            catch (FontFormatException e){System.out.println(e);}
        } else if (fuente_n == fuente3)
        {
            try {
                fuente = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("./fuentes/sanva_font.ttf")
                ).deriveFont(Font.BOLD,50);
            }
            catch (IOException  e) {System.out.println(e);}
            catch (FontFormatException e){System.out.println(e);}
        }
    }
    /*
    * Obtiene la fuente.
    * @return Retorna la fuente.
    * */
    public Font getFuente(){return fuente;}
}
