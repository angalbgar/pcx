package org.pcx.discord.functions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class Configurador extends ListenerAdapter
{
    private final Map<Long, Boolean> esperandoIdServidor = new HashMap<>();
    private final Map<Long, Boolean> esperandoIdCanal = new HashMap<>();

    public void onMessageReceived(MessageReceivedEvent event)
    {
        String jdbc_url = "jdbc:mysql://localhost:3307/practicas";
        try ( Connection conn = DriverManager.getConnection(jdbc_url,"root","root");) {

            System.out.println("Conexión establecida con la BBDD");

        } catch(SQLException se) {
            se.printStackTrace();
        }

            //para q si el bot escribe, que no lea su propio mensaje
            if (event.getAuthor().isBot()) return;

        /* esto es para decirle al bot que solo lea un canal, ignorando el resto.
        Ya que por defecto este listener escuchará por todos los canales.
        */

            String canal_detectado = event.getChannel().getId();
            //TextChannel textChannel = guild.getTextChannelById(channelId);

            //if (textChannel != null) {
            // Existe y además es un canal de texto
            //}

        if (!event.getChannel().getId().equals(canal_detectado)) return;

        pedirIDCanal(event);
        String id_canal_avisos = pedirIDCanal(event);

    }


    public void pedirIDServidor(MessageReceivedEvent event)
    {
        if (esperandoIdServidor.containsKey(event.getAuthor().getIdLong()))
        {
            String id_servidor = event.getMessage().getContentRaw();

            String id_servidor_actual = event.getGuild().getId();



            if (id_servidor.equals(id_servidor_actual))
            {

                event.getChannel().sendMessage("ID correcto.").queue();
                event.getChannel().sendMessage("Escribe el ID del **canal** para mandar avisos:").queue();

                esperandoIdCanal.put(event.getAuthor().getIdLong(), true);


            }
            else
            {
                event.getChannel().sendMessage("El ID proporcionado es incorrecto. Vuelve a ejecutar !configurador.").queue();
            }

            esperandoIdServidor.remove(event.getAuthor().getIdLong());


        }

        if (event.getMessage().getContentRaw().equals("!configurador"))
        {
            System.out.println("configurador");
            event.getChannel()
                    .sendMessage("Por favor escribe a continuación el ID del **Servidor**:")
                    .queue();
            esperandoIdServidor.put(event.getAuthor().getIdLong(), true);
        }
    }

    public String pedirIDCanal(MessageReceivedEvent event)
    {
        if (esperandoIdCanal.containsKey(event.getAuthor().getIdLong()))
        {
            String id_canal_avisos = event.getMessage().getContentRaw();

            boolean existe = event.getGuild().getGuildChannelById(id_canal_avisos) != null;

            if (existe) {
                event.getChannel().sendMessage("Configuración completada correctamente. Utiliza !aviso **[mensaje]** para crear anuncios en el servidor.").queue();
                return id_canal_avisos;
            } else {
                event.getChannel().sendMessage("Canal no encontrado en el servidor. Vuelve a ejecutar !configurador").queue();
            }

            esperandoIdCanal.remove(event.getAuthor().getIdLong());

        }
        return null;
    }
    }

