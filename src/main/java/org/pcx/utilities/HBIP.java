package org.pcx.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
/**
* Comprobador  de contraseñas. Te comprueba una contraseña y escribe a fichero si es segura o no.
* */
public class HBIP {

    private String password;
/**
* Instancias el comprobador.
* @param password Contraseña a analizar
* */
    public HBIP(String password) {

        this.password = password;

        try {

            MessageDigest md =
                    MessageDigest.getInstance("SHA-1");

            byte[] hashBytes =
                    md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hashBuilder =
                    new StringBuilder();

            for (byte b : hashBytes) {

                hashBuilder.append(
                        String.format("%02X", b)
                );
            }

            String sha1Hash =
                    hashBuilder.toString();

            String prefix =
                    sha1Hash.substring(0, 5);

            String suffix =
                    sha1Hash.substring(5);

            String url =
                    "https://api.pwnedpasswords.com/range/" + prefix;

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            boolean found = false;

            String resultado = "";
            String resultado1 = "";
            String[] lines =
                    response.body().split("\\r?\\n");

            for (String line : lines) {

                String[] parts =
                        line.split(":");

                String hashSuffix =
                        parts[0];

                String count =
                        parts[1];

                if (hashSuffix.equalsIgnoreCase(suffix)) {

                    resultado1 =
                            "Contraseña comprometida\n" +
                                    "Filtrada " + count + " veces\n";

                    found = true;
                    break;
                }
            }

            if (!found) {

                resultado =
                        "Contraseña no encontrada en las filtraciones conocidas.\n";
            }

            // Guardo resultado

            File f = new File("resultado.txt");

            if (!f.exists())
            {
                try
                {
                    f.createNewFile();
                }
                catch(IOException e){System.out.println(e);
                }
            }

            try (FileWriter writer =
                    new FileWriter("resultado.txt",true))
            {
                writer.write("------------------------------\n");
                writer.write("Contraseña: " + password+"\n");

                writer.write(resultado1);
                writer.write(resultado);
                writer.write("------------------------------\n\n");

            }

            } catch (Exception e) {

                e.printStackTrace();
            }
    }

}