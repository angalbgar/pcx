package org.pcx;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.DiscordAssets;
import net.dv8tion.jda.api.utils.ImageFormat;
import net.dv8tion.jda.api.components.buttons.*;
import net.dv8tion.jda.api.components.actionrow.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.callbacks.*;
import java.awt.Color;
import java.awt.*;
import java.util.*;
import java.io.*;

public class MessageReceivedListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String testing_channel = "1234629239475671082";
        String general_channel = "1316593572916760686";

        String detected_channel = event.getChannel().getId();

        //para q si el bot escribe, que no lea su propio mensaje
        if (event.getAuthor().isBot()) return;

        /* esto es para decirle al bot que solo lea un canal, ignorando el resto.
        if (!detected_channel.equals(general_channel))
        {
            return;
        }
        */
        //Obtengo el nombre del autor q escribió en el canal.
        String username = event.getAuthor().getName();
        String message = event.getMessage().getContentDisplay();
        String name_channel = event.getChannel().getName();
        String date_message = event.getMessage().getTimeCreated().toString();

        String log = "Usuario: " + username + "\nCanal: " + name_channel + "\nHora del mensaje: " + date_message + "\nMensaje: " + message + "\n\n";
        System.out.println(log);

        String result_message = "**Usuario**: " + username + "\n**Canal: **" + name_channel + "\n**Hora del mensaje: **" + date_message + "\n**Mensaje: **" + message;

        //Creo los logs
        String pathlog = "C:\\Users\\angel\\IdeaProjects\\PCX-BOT\\src\\main\\log\\log.txt";
        File f = new File(pathlog);

        try (FileWriter fw = new FileWriter(f, true)) {
            if (!f.exists()) f.createNewFile();
            fw.write(log);
            System.out.println("Log guardado en la ruta de logs.");

        } catch (IOException e) {
            System.out.println(e);
        }

        net.dv8tion.jda.api.components.buttons.Button button = net.dv8tion.jda.api.components.buttons.Button.primary("button_id", "Botón");


        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Mensaje enviado por " + "**" + username + "**");
        eb.setDescription(result_message);
        eb.setColor(new Color(50, 111, 168));

        String avatar_id = event.getAuthor().getAvatarId();
        String user_id = event.getAuthor().getId();

        String url_image = DiscordAssets.userAvatar(ImageFormat.PNG, user_id, avatar_id).getUrl(1024);

        //String url_image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSU6CqmvhhWUNF1440n49rsstcMd9wpdcjcKA&s";

        eb.setImage(url_image);
        eb.setFooter("Atentamente, Clitorino");
        MessageEmbed me = eb.build();
        event.getJDA()
                .getTextChannelById(testing_channel)
                .sendMessageEmbeds(Collections.singleton(me))
                .addComponents(ActionRow.of(button))
                .queue();

        User user = event.getAuthor();

        

        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessageEmbeds(Collections.singleton(me)))
                .queue();
        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage("Me cago en tus muertos"))
                .queue();
        //tester para comprobar que un canal detectado por el bot esté únicamente en el canal que especifico


    }


}