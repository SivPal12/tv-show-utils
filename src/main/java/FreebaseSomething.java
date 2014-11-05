import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.freebase.Freebase;
import com.google.api.services.freebase.Freebase.Search;

public class FreebaseSomething {

    private static Freebase freebase;

    public static void main(String[] args) throws GeneralSecurityException,
            IOException {
        freebase = new Freebase.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                new JacksonFactory(), null)
                .setApplicationName("tv-show-utils")
                .build();

        Search search = freebase.search();
        search.setQuery("Dexter");
        search.setDomain(Arrays.asList("tv"));
        search.setType(Arrays.asList("/tv/tv_program"));
        search.setLimit(2);
        search.setOutput("(/tv/tv_program/number_of_seasons)");
        HttpResponse resp = search.executeMedia();

        String jsonRespinse = resp.parseAsString();

        System.out.println(jsonRespinse);
    }
}
