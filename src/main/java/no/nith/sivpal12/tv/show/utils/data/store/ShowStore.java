package no.nith.sivpal12.tv.show.utils.data.store;

import no.nith.sivpal12.tv.show.utils.pojo.ShowInfo;
import no.nith.sivpal12.tv.show.utils.pojo.ShowName;
import no.nith.sivpal12.tv.show.utils.pojo.ShowSeason;
import no.nith.sivpal12.tv.show.utils.pojo.ShowUpdate;

import java.util.List;

public interface ShowStore {

    /**
     * Creates a new entry for the current show. TODO Insert correct exception
     *
     * @throws exception if show already exists i data store
     * @param showInfo {@link ShowInfo}
     */
    void storeShow(ShowInfo showInfo);

    /**
     * Checks if show has the correct number of seasons and episodes.
     *
     * @param showInfo Object containing show name, number of seasons and number of episodes in each
     *            season. TODO Insert correct exception
     * @throws exception if showInfo has more seasons than store
     * @throws exception if showInfo a season has more episodes than store
     * @return a list of ShowSeasons with the correct number of episodes.
     */
    List<ShowSeason> checkShow(ShowInfo showInfo);

    /**
     * Updates episodes in season for show.
     *
     * @param update Object that stores show name, season to update and new number of episodes.
     */
    void update(ShowUpdate update);

    /**
     * @see #update(ShowUpdate)
     */
    void update(List<ShowUpdate> updates);

    /**
     * Checks if show is stored.
     *
     * @param showName Object containing name of show.
     * @return
     */
    boolean isStored(ShowName showName);

}
