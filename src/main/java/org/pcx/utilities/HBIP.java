package org.pcx.utilities;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class HBIP {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una contraseña: ");

        String password = scanner.nextLine();

        try {

            // =========================
            // 1. SHA-1
            // =========================

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

            System.out.println("SHA-1: " + sha1Hash);

            // =========================
            // 2. Prefix y suffix
            // =========================

            String prefix =
                    sha1Hash.substring(0, 5);

            String suffix =
                    sha1Hash.substring(5);

            // =========================
            // 3. Llamada API
            // =========================

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

            // =========================
            // 4. Buscar coincidencia
            // =========================

            boolean found = false;

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

                    System.out.println(
                            "Contraseña comprometida"
                    );

                    System.out.println(
                            "Filtrada " + count + " veces"
                    );

                    found = true;

                    break;
                }
            }

            if (!found) {

                System.out.println("Contraseña no encontrada");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
