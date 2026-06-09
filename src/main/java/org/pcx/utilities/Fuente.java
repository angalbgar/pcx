package org.pcx.utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fuente
{
    private Font fuente;
    public static int fuente1 = 1;
    public static int fuente2 = 2;
    public static int fuente3 = 3;

    public Fuente(int fuente_n)
    {
        if (fuente_n == fuente1)
        {
            try {

                fuente = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("./fuentes/sanva_font.ttf")
                ).deriveFont(Font.BOLD,24f);
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
                ).deriveFont(Font.BOLD,80f);
            }
            catch (IOException  e) {System.out.println(e);}
            catch (FontFormatException e){System.out.println(e);}
        } else if (fuente_n == fuente3)
        {
            try {
                fuente = Font.createFont(
                        Font.TRUETYPE_FONT,
                        new File("./fuentes/sanva_font.ttf")
                ).deriveFont(Font.BOLD,50f);
            }
            catch (IOException  e) {System.out.println(e);}
            catch (FontFormatException e){System.out.println(e);}
        }
    }

    public Font getFuente(){return fuente;}
}
