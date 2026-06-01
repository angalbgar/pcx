package org.pcx;

import org.pcx.window.AppGrafica;
import org.pcx.utilities.*;
import org.pcx.discord.*;
import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        //Cargo la aplicación gráfica

        AppGrafica window = new AppGrafica();
        //Importante, si se carga el proyecto con IntelliJ, cargará el proyecto desde la ruta PCX-PROJECT, lo cual hace que la ruta del token tiene que ser así.

        //System.out.println(System.getProperty("user.dir"));

        //Cargo el bot de discord.


        File f = new File("token.txt");
        try(Scanner tokenreader = new Scanner(f))
        {
            if (tokenreader.hasNext()) {
                String token = tokenreader.nextLine();
                //System.out.println(token);
                DiscordBuilder dc = new DiscordBuilder(token);
            }
            else
            {
                System.out.println("No se ha proporcionado un token");
            }
        }
        catch(FileNotFoundException e){System.out.println(e);}


    }
}
