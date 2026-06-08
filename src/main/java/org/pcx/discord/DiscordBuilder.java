package org.pcx.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.pcx.discord.functions.Configurador;
import org.pcx.discord.functions.FuncionAviso;

import java.awt.*;
import java.util.Scanner;
public class DiscordBuilder {
    public DiscordBuilder(String token)
    {
        JDA jda = JDABuilder.create(token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT
                )
                .addEventListeners(new Configurador())
                .addEventListeners(new FuncionAviso())
                .build();
        System.out.println("Bot encendido");


    }
}
