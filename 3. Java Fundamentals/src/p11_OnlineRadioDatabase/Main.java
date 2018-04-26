package p11_OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {



            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Integer songsAdded = 0;
            Integer secondsTop = 0;
            Integer n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                try {
                    String[] input = reader.readLine().split(";");
                    String artist = input[0];
                    if (artist.length() < 3 || artist.length() > 20)
                        throw new InvalidArtistNameException("Artist name should be between 3 and 20 symbols.");
                    String name = input[1];
                    if (name.length() < 3 || name.length() > 30)
                        throw new InvalidSongNameException("Song name should be between 3 and 30 symbols.");
                    try {
                        int[] time = Arrays.stream(input[2].split(":")).mapToInt(Integer::parseInt).toArray();
                        int minutes = time[0];
                        if (minutes > 14 || minutes < 0)
                            throw new InvalidSongMinutesException("Song minutes should be between 0 and 14.");
                        int seconds = time[1];
                        if (seconds < 0 || seconds > 59)
                            throw new InvalidSongSecondsException("Song seconds should be between 0 and 59.");
                        seconds += minutes*60;
                        secondsTop += seconds;
                        songsAdded ++;
                        System.out.println("Song added.");
                    } catch (Exception e) {
                        System.out.println("Invalid song length.");
                    } catch (InvalidSongMinutesException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidSongSecondsException e) {
                        System.out.println(e.getMessage());
                    }

                } catch (Exception e) {
                    System.out.println("Invalid song.");
                } catch (InvalidArtistNameException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidSongNameException e) {
                    System.out.println(e.getMessage());
                }

            }
        int secondsFinal = secondsTop % 60;
        int minutesFinal = secondsTop / 60 % 60;
        int hoursFinal = secondsTop / 60 / 60;

        StringBuilder sb = new StringBuilder();
        sb.append(hoursFinal).append("h ").append(minutesFinal).append("m ").append(secondsFinal).append("s");

        System.out.printf("Songs added: %d%n", songsAdded);
        System.out.printf("Playlist length: %s", sb.toString());


        }
}


class InvalidArtistNameException extends Throwable {
    public InvalidArtistNameException(String s) {
        super(s);
    }
}

class InvalidSongNameException extends Throwable {
    public InvalidSongNameException(String s) {
        super(s);
    }
}

class InvalidSongSecondsException extends Throwable {
    public InvalidSongSecondsException(String s) {
        super(s);
    }
}

class InvalidSongMinutesException extends Throwable {
    public InvalidSongMinutesException(String s) {
        super(s);
    }
}