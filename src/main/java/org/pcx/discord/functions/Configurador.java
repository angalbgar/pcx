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

        //pedirIDServidor(event);
        pedirIDCanal(event);

    }

    /*
    *  public void pedirIDServidor(MessageReceivedEvent event)
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
    * */


    public void pedirIDCanal(MessageReceivedEvent event)
    {
        if (esperandoIdCanal.containsKey(event.getAuthor().getIdLong()))
        {
            String id_canal_avisos = event.getMessage().getContentRaw();

            if (id_canal_avisos.length() <= 18)
            {

                event.getChannel()
                        .sendMessage("El ID proporcionado no es un long válido.")
                        .queue();

                esperandoIdCanal.remove(event.getAuthor().getIdLong());
                return;
            }

            try
            {
                Long.parseLong(id_canal_avisos);
            }
            catch (NumberFormatException e)
            {
                event.getChannel()
                        .sendMessage("La ID proporcionada es inválida, no puede contener letras.")
                        .queue();

                esperandoIdCanal.remove(event.getAuthor().getIdLong());
                return;
            }

            boolean existe = event.getGuild().getGuildChannelById(id_canal_avisos) != null;

            if (existe) {

                event.getChannel()
                        .sendMessage("Configuración completada correctamente.")
                        .queue();
                String jdbc_url = "jdbc:mysql://localhost:3307/discord";
                try ( Connection conn = DriverManager.getConnection(jdbc_url,"root","root");) {

                    System.out.println("Conexión establecida con la BBDD");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


                    String id_servidor = event.getGuild().getIdLong()+"";
                    String nombre_servidor = event.getGuild().getName();

                    ResultSet rs = stmt.executeQuery("SELECT * FROM CANAL WHERE SERVIDOR_ID = "+id_servidor);

                    if (rs.next()){
                        String rs_id_servidor = rs.getString("SERVIDOR_ID");

                        String modificacion_canal = "UPDATE CANAL SET ID_CANAL = " + id_canal_avisos + " WHERE SERVIDOR_ID = " + "'"+rs_id_servidor+"'";
                        if (rs_id_servidor.equals(id_servidor))
                        {
                            stmt.executeUpdate(modificacion_canal);
                            System.out.println("Modificación completada correctamente.");

                            esperandoIdCanal.remove(event.getAuthor().getIdLong());
                            return;
                      }
                    }
                    else
                    {
                        String insercion_servidor = "INSERT INTO SERVIDOR(ID_SERVIDOR,NOMBRE) VALUES("+id_servidor+",'"+nombre_servidor+"')";
                        String insercion_canal = "INSERT INTO CANAL(SERVIDOR_ID,TIPO_CANAL,ID_CANAL) VALUES("+id_servidor+","+"'Canal avisos'"+","+id_canal_avisos+")";
                        stmt.execute(insercion_servidor);
                        stmt.execute(insercion_canal);
                        System.out.println("Inserciones realizadas correctamente.");

                        esperandoIdCanal.remove(event.getAuthor().getIdLong());
                        return;
                    }



                } catch(SQLException se)
                {
                    se.printStackTrace();
                    event.getChannel()
                            .sendMessage("No se ha podido insertar a base de datos porque hubo un problema.")
                            .queue();

                    esperandoIdCanal.remove(event.getAuthor().getIdLong());
                    return;
                }
            }

            else
            {
                event.getChannel()
                        .sendMessage("El ID del canal proporcionado no existe en este servidor, por favor vuelva a ejecutar el comando.")
                        .queue();

                esperandoIdCanal.remove(event.getAuthor().getIdLong());
                return;
            }

        }

        if (event.getMessage().getContentRaw().equals("!configurador"))
        {
            System.out.println("configurador");
            event.getChannel()
                    .sendMessage("Por favor escribe a continuación el ID del **Canal de Avisos** que se utilizará para mandar los avisos. Puede obtener el ID haciendo click derecho sobre un canal y copiar el código del ID:")
                    .queue();
            esperandoIdCanal.put(event.getAuthor().getIdLong(), true);
        }
    }
    }

