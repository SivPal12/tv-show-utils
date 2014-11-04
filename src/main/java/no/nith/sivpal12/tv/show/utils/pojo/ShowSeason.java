package no.nith.sivpal12.tv.show.utils.pojo;

public class ShowSeason {

    private final int seasonNumber;
    private final int numberOfEpisodes;

    public ShowSeason(int seasonNumber, int numberOfEpisodes) {
        this.seasonNumber = seasonNumber;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
