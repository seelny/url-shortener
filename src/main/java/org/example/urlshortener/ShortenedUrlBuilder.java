package org.example.urlshortener;

public class ShortenedUrlBuilder {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringBuilder shortenedUrl;
    private long id;

    public StringBuilder getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenUrl(StringBuilder shortenUrl) {
        this.shortenedUrl = shortenUrl;
    }

    public long getId(){
        return this.id;
    }

    public ShortenedUrlBuilder(long id) {
        this.id = id;
        this.shortenedUrl = new StringBuilder();
    }

    public static String encode(int id){
        StringBuilder url = new StringBuilder();
        if (id < 1){
            throw new RuntimeException("Wrong Id");
        }
        else {

            while (id > 0) {
                url.append(ALPHABET.charAt((int)(id % 62)));
                id = id / 62;
            }
        }
        return  url.reverse().toString();
    }

}
