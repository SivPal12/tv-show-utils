package no.nith.sivpal12.tv.show.utils.pojo;

import no.nith.sivpal12.tv.show.utils.exeptions.TsuSeasonNotFoundException;

import java.util.List;

/**
 * Object containing show name, number of seasons and number of episodes in each season.
 */
public class ShowInfo {

    private final ShowName showName;
    private final List<ShowSeason> seasons;

    public ShowInfo(ShowName showName, List<ShowSeason> seasons) {
        this.showName = showName;
        this.seasons = seasons;
    }

    public ShowName getShowName() {
        return showName;
    }

    public int getNumberOfSeasons() {
        return seasons.size();
    }

    public int getNumberOfEpisodes(int seasonNumber) {
        for (ShowSeason season : seasons) {
            if (season.getSeasonNumber() == seasonNumber) {
                return season.getNumberOfEpisodes();
            }
        }
        throw new TsuSeasonNotFoundException(String.format(
                "Could not find season number '%d' in season'%s'", seasonNumber, showName));
    }
}
