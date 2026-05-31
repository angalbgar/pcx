package org.pcx;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.*;
import java.util.concurrent.*;

import org.pcx.tickets.*;
public class Main
{
    private static String activity = "Hola, soy Clitorino";

    public static void main(String[] args)
    {
        String token = "";
        JDA jda = JDABuilder.create(token,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
                  )
                .addEventListeners(new MessageReceivedListener())
                .addEventListeners(new Panel())
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.customStatus(activity))

                .build();
        System.out.println("Bot encendido");

        custom_status(jda);
    }

    public static void custom_status(JDA jda)
    {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            Activity act;
            if (activity.equals("Hola, soy Clitorino"))
            {
                activity = "No te metas conmigo...";
                act = Activity.customStatus(activity);
            }
            else
            {
                activity = "Hola, soy Clitorino";
                act = Activity.customStatus(activity);
            }
            jda.getPresence().setActivity(act);

            //System.out.println("Valor actual: " + activity);

        }, 0, 10, TimeUnit.SECONDS);
    }
}