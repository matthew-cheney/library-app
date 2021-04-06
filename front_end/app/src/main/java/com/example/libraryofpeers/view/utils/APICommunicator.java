package com.example.libraryofpeers.view.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APICommunicator {

    final String urlTemplate = "https://openlibrary.org/api/books?bibkeys=ISBN:%s&format=json&jscmd=data";

    public class BookResult {
        private String title;
        private String author;
        private String publishDate;

        public BookResult(String title, String author, String publish_date) {
            this.title = title;
            this.author = author;
            this.publishDate = publish_date;
        }

        public BookResult() {
            this.title = null;
            this.author = null;
            this.publishDate = null;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }
    }

    public BookResult getBookFromISBN(String isbn) {
        try {
            String urlString = String.format(urlTemplate, isbn);
            JsonObject res = getJSON(new URL(urlString));
            BookResult bookResult = new BookResult();
            String isbnKey = "ISBN:" + isbn;
            JsonObject innerRes = (JsonObject) res.get(isbnKey);
            System.out.println(innerRes);

            try {
                bookResult.setTitle(innerRes.get("title").getAsString());
            } catch (Exception e) {
                bookResult.setTitle(null);
            }

            try {
                JsonArray authors = (JsonArray) innerRes.get("authors");
                StringBuilder authorString = new StringBuilder();
                for (int i = 0; i < authors.size(); i++) {
                    if (i > 0) {
                        authorString.append(", ");
                    }
                    authorString.append(((JsonObject) authors.get(i)).get("name").getAsString());
                }
                bookResult.setAuthor(authorString.toString());
            } catch (Exception e) {
                bookResult.setAuthor(null);
            }

            try {
                bookResult.setPublishDate(innerRes.get("publish_date").getAsString());
            } catch (Exception e) {
                bookResult.setPublishDate(null);
            }

            return bookResult;

        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private JsonObject getJSON(URL url) throws IOException {
        //inline will store the JSON data streamed in string format
        String inline = "";

        //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //Set the request to GET or POST as per the requirements
        conn.setRequestMethod("GET");
        //Use the connect method to create the connection bridge
        conn.connect();
        //Get the response status of the Rest API
        int responsecode = conn.getResponseCode();
        System.out.println("Response code is: " + responsecode);

        //Iterating condition to if response code is not 200 then throw a runtime exception
        //else continue the actual process of getting the JSON data
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            //Scanner functionality will read the JSON data from the stream
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            System.out.println("\nJSON Response in String format");
            System.out.println(inline);
            //Close the stream when reading the data has been finished
            sc.close();
        }

        JsonElement jsonElement = JsonParser.parseString(inline);

        //Disconnect the HttpURLConnection stream
        conn.disconnect();

        return jsonElement.getAsJsonObject();
    }

//    public static void main(String[] args) {
//        BookResult res = new APICommunicator().getBookFromISBN("9780735211292");
//        System.out.println(res.getTitle());
//        System.out.println(res.getAuthor());
//        System.out.println(res.getPublishDate());
//    }

}
