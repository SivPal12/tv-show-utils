package no.nith.sivpal12.tv.show.utils.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.nith.sivpal12.tv.show.utils.domain.MissingEpisodes;
import no.nith.sivpal12.tv.show.utils.exeptions.TsuUnknownExeption;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.freebase.Freebase;
import com.google.api.services.freebase.Freebase.Search;

public class TvBaseService {

    private static final String FREEBASE_API_KEY = "AIzaSyBZOYjNCl_1yFCGztsHPWRcmyTTDRd2ouA";
    private Freebase freebase;

    public TvBaseService() {
        try {
            freebase = new Freebase.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    new JacksonFactory(), null)
                    .setApplicationName("tv-show-utils")
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new TsuUnknownExeption(
                    "Exception not documented in Freebase library", e);
        }
    }

    public MissingEpisodes createMissingEpisode() {
        // TODO
        throw new RuntimeException("not yet implemented");
    }

    public int getSeasonsForShow(String showName) {
        try {
            Search search = freebase.search();
            search.setKey(FREEBASE_API_KEY);
            search.setQuery(showName);
            search.setDomain(Arrays.asList("tv"));
            search.setType(Arrays.asList("/tv/tv_program"));
            search.setLimit(1);
            search.setOutput("(/tv/tv_program/number_of_seasons)");
            HttpResponse resp = search.executeMedia();

            String jsonResponse = resp.parseAsString();

            Matcher numSeasonsMatcher = Pattern
                    .compile(
                            ".+\\\"\\/tv\\/tv_program\\/number_of_seasons\\\":\\[\\\"(\\d+)\\\"\\].+")
                    .matcher(jsonResponse);
            if (!numSeasonsMatcher.matches()) {
                return 0;
            }

            return Integer.parseInt(numSeasonsMatcher.group(1));
        } catch (IOException e) {
            throw new TsuUnknownExeption(
                    "Exception not documented in Freebase library", e);
        }
    }

    public int getEpisodesInSeason(String showName, int seasonNumber) {
        try {
            Search search = freebase.search();
            search.setKey(FREEBASE_API_KEY);
            search.setQuery(showName + " season " + seasonNumber);
            search.setDomain(Arrays.asList("tv"));
            search.setType(Arrays.asList("/tv/tv_series_season"));
            search.setLimit(1);
            search.setOutput("(/tv/tv_series_season/number_of_episodes)");
            HttpResponse resp = search.executeMedia();

            String jsonResponse = resp.parseAsString();

            Matcher numEpisodesInSeasonMatcher = Pattern
                    .compile(
                            ".+\\\"\\/tv\\/tv_series_season\\/number_of_episodes\\\":\\[\\\"(\\d+)\\\"\\].+")
                    .matcher(jsonResponse);
            if (!numEpisodesInSeasonMatcher.matches()) {
                return 0;
            }

            return Integer.parseInt(numEpisodesInSeasonMatcher.group(1));
        } catch (IOException e) {
            throw new TsuUnknownExeption(
                    "Exception not documented in Freebase library", e);
        }
    }

}
