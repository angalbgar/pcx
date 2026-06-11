package org.pcx.discord.functions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.ZoneId;

/**
* Comando de la aplicación prototipada que muestra la información pública de un servidor
* */
public class Informacion extends ListenerAdapter
{
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getContentRaw().startsWith("!info"))
        {
            //Aqui defino algunas variables de información pública del servidor para mostrar
            String nombre_servidor = event.getGuild().getName();
            String id_servidor = event.getGuild().getId();
            String dueño_servidor = event.getGuild().getOwner().getUser().getName();
            Integer numero_miembros = event.getGuild().getMemberCount();

            /*
            * Gracias al ide me permite autocompletar y ver mas rapido la documentación, por ejemplo
            * para el tema de obtener la hora, me devuelve un OffsetTime, y por defecto el formato se ve mal,
            * pero gracias al ide, entre otras muchas cosas me permite ver los parámetros que se necesita de forma rápida y
            * me doy cuenta rapido de los métodos que tiene OffsetTime, y me permite ver qué necesito algo para ajustar la fecha.
            *
            * Lo digo para explicar también porque utilizo IntelliJ en el desarrollo, que lo considero bastante util por esto.
            * */

            String fecha_creacion = event.getGuild()
                    .getTimeCreated()
                    .atZoneSameInstant(ZoneId.systemDefault()).toLocalDate().toString();

            EmbedBuilder eb = new EmbedBuilder();
            eb
                    .setTitle("Información del servidor " + nombre_servidor)
                    .setFooter("Id del servidor: " +  id_servidor)
                    .setColor(new Color(38, 22, 216))
                    .setDescription("Dueño del servidor: "  + dueño_servidor + "\nNúmero de miembros: " + numero_miembros + "\nFecha de creación: " + fecha_creacion);

            event.getChannel().sendMessageEmbeds(eb.build()).queue();

        }
    }
}
