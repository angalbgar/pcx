package org.pcx.discord.functions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
* Comando aviso de la aplicación prototipada del JDA.
* */
public class FuncionAviso extends ListenerAdapter
{
    private String mensaje;
    public void onMessageReceived(MessageReceivedEvent event)
    {
        /*
        * Ahora crearemos un embed y lo crearemos en el canal especificado, por ello tendremos que obtener el canal guardado previamente en base de datos
        *
        * */
        if (event.getMessage().getContentRaw().startsWith("!aviso"))
        {
            //Con esto obtengo el mensaje acortado quitando el prefijo
            try
            {
                mensaje = event.getMessage().getContentRaw().substring("!aviso ".length());
            }
            catch(StringIndexOutOfBoundsException e)
            {
                System.out.println(e);
                event.getChannel().sendMessage("Se debe escribir un mensaje !aviso **[mensaje]**").queue();
                return;
            }

            String jdbc_url = "jdbc:mysql://localhost:3307/discord";

            try (Connection conn = DriverManager.getConnection(jdbc_url,"root","root"))
            {

                Statement stmt = conn.createStatement();
                /*Aquí haré la consulta de datos, si existe algún registro querrá decir que mandaré el embed, y sino, será que el usuario no ejecutó !configurador*/
                String id_server = event.getGuild().getId();
                String sql = "SELECT * FROM CANAL WHERE SERVIDOR_ID = "+id_server;

                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next())
                {

                    String id_canal = rs.getString("ID_CANAL");

                    //genero el embed, aquí lo personalizo a mi gusto pero siempre poniendo el mensaje sacado de avisos.
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("Aviso para el servidor").setDescription(mensaje).setColor(new Color(10, 241, 214)).setFooter("Mensaje enviado por " + event.getAuthor().getName());
                    //mandamos el embed al canal especificado y obtenido de la base de datos
                    event.getJDA().getTextChannelById(id_canal).sendMessageEmbeds(eb.build()).queue();

                }
                else
                {
                    event.getChannel().sendMessage("No se ha configurado un canal previamente, se debe usar !configurador").queue();
                }

            } catch (SQLException e)
            {
                System.out.println(e);
            }
        }

    }
}
