package org.pcx.discord.functions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
* Comando aviso de la aplicación prototipada del JDA.
* */
public class FuncionAviso extends ListenerAdapter
{
    private final Map<Long, Boolean> esperandoIdServidor = new HashMap<>();
    private final Map<Long, Boolean> esperandoIdCanal = new HashMap<>();



    public void onMessageReceived(MessageReceivedEvent event)
    {
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
        /*
        if (!canal_detectado.equals(general_channel))
        {
            return;
        }
        */
        pedirIDCanal(event);
        String id_canal_avisos = pedirIDCanal(event);

        funcionAviso(event, id_canal_avisos);

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

    public void funcionAviso(MessageReceivedEvent event, String id_canal_avisos)
    {
        if (id_canal_avisos == null)
        {
            event.getChannel().sendMessage("Se debe primero configurar un canal de avisos con !configurador").queue();
            return;
        }
        String aviso_prefijo = event.getMessage().getContentRaw();
        if (aviso_prefijo.equals("!aviso"))
        {
            Scanner sc = new Scanner(aviso_prefijo);
            sc.next();
            String aviso = sc.next();
            System.out.println(aviso);

            if (aviso == null)
            {
                event.getChannel().sendMessage("No has escrito ningún aviso, ejecuta el comando de nuevo.");
            }
            else
            {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("Aviso ProjectCodeX");
                eb.setDescription(aviso);
                eb.setColor(new Color(50, 111, 168));

                MessageEmbed me = eb.build();
                event.getJDA()
                        .getTextChannelById(id_canal_avisos)
                        .sendMessageEmbeds(Collections.singleton(me))
                        .queue();

            }
        }
    }

}
