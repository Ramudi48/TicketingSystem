package oop.cwk.ramudi.TicketingSystem;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostingTool {

    public static void sendPostReq(String urlString){ //for now left as a non static method
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            System.out.println("Request sent with code " + responseCode);
            connection.disconnect();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void SendPostReqWithJson(String urlString, String json) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        System.out.println("Request sent with json " + json);

        //automatic handling

        try(OutputStream outputStream = connection.getOutputStream()){
            /**
             * when sending http requests data needs to be in binary format
             */
            byte[] bytes = json.getBytes("UTF-8");
            outputStream.write(bytes);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Request sent with code " + responseCode);
        connection.disconnect();

    }
}
