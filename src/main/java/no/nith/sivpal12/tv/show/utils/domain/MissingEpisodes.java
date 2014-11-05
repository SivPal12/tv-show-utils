package no.nith.sivpal12.tv.show.utils.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import no.nith.sivpal12.tv.show.utils.data.store.EpisodeStore;
import no.nith.sivpal12.tv.show.utils.pojo.Episode;

public class MissingEpisodes {

    private EpisodeStore episodeStore = new EpisodeStore();
    private Set<String> trackedShows = new HashSet<String>();
    private List<Episode> missingEpisodes = new LinkedList<>();
    
    /**
     * Removes episode from tracked episodes. If show is not tracked, all
     * seasons and episodes will be added to missing episodes.
     * 
     * @param episode
     */
    public void remove(Episode episode) {
        if (!trackedShows.contains(episode.getShowName())) {
            missingEpisodes.addAll(episodeStore.getEpisodesForShow(episode
                    .getShowName()));
            trackedShows.add(episode.getShowName());
        }

        missingEpisodes.remove(episode);
    }

    public List<Episode> getMissingEpisodes() {
        return missingEpisodes;
    }
}
