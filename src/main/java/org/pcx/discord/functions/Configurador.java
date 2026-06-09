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


            //para q si el bot escribe, que no lea su propio mensaje
            if (event.getAuthor().isBot()) return;

        pedirIDServidor(event);
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
                esperandoIdCanal.remove(event.getAuthor().getIdLong());

                event.getChannel()
                        .sendMessage("Configuración completada correctamente.")
                        .queue();
                String jdbc_url = "jdbc:mysql://localhost:3307/discord";
                try ( Connection conn = DriverManager.getConnection(jdbc_url,"root","root");) {

                    System.out.println("Conexión establecida con la BBDD");

                } catch(SQLException se) {
                    se.printStackTrace();
                }

                return id_canal_avisos;
            }

        }
        return null;
    }
    }

