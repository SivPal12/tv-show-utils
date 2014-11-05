package no.nith.sivpal12.tv.show.utils.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import no.nith.sivpal12.tv.show.utils.data.store.EpisodeStore;
import no.nith.sivpal12.tv.show.utils.pojo.Episode;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

public class MissingEpisodesTest {

    private static final String MISSING_EPISODES_FIELD_TRACKED = "trackedShows";
    private static final String MISSING_EPISODES_FIELD_EPISODE_STORE = "episodeStore";
    private static final String SOME_SHOW_NAME = "Dexter";
    private static final String MISSING_EPISODES_FIELD_MISSING = "missingEpisodes";

    @Test
    public void remove_ShowNotTracked_TracksShow() throws Exception {
        MissingEpisodes missingEpisodes = new MissingEpisodes();

        EpisodeStore mockEpisodeStore = mock(EpisodeStore.class);
        final Episode episode = Episode
                .fromRawFileName("dexter.S03E05.someName.mkv");
        final List<Episode> expected = Arrays.asList(episode);
        when(mockEpisodeStore.getEpisodesForShow(anyString())).thenReturn(
                expected);
        Whitebox.setInternalState(missingEpisodes,
                MISSING_EPISODES_FIELD_EPISODE_STORE, mockEpisodeStore);

        @SuppressWarnings("unchecked")
        final Set<String> tracked = (Set<String>) Whitebox
                .getInternalState(missingEpisodes,
                        MISSING_EPISODES_FIELD_TRACKED);
        assertEquals(0, tracked.size());
        Episode mockEpisode = mock(Episode.class);
        when(mockEpisode.getShowName()).thenReturn(SOME_SHOW_NAME);
        missingEpisodes.remove(mockEpisode);
        assertEquals(SOME_SHOW_NAME, tracked.iterator().next());
    }

    @Test
    public void remove_ShowAlreadyTracked_NotAddedTwice() throws Exception {
        MissingEpisodes missingEpisodes = new MissingEpisodes();

        EpisodeStore mockEpisodeStore = mock(EpisodeStore.class);
        final Episode episode = Episode
                .fromRawFileName("dexter.S03E05.someName.mkv");
        final List<Episode> expected = Arrays.asList(episode);
        when(mockEpisodeStore.getEpisodesForShow(anyString())).thenReturn(
                expected);
        Whitebox.setInternalState(missingEpisodes,
                MISSING_EPISODES_FIELD_EPISODE_STORE, mockEpisodeStore);

        @SuppressWarnings("unchecked")
        final Set<String> tracked = (Set<String>) Whitebox
                .getInternalState(missingEpisodes,
                        MISSING_EPISODES_FIELD_TRACKED);
        assertEquals(0, tracked.size());
        Episode mockEpisode = mock(Episode.class);
        when(mockEpisode.getShowName()).thenReturn(SOME_SHOW_NAME);
        missingEpisodes.remove(mockEpisode);
        missingEpisodes.remove(mockEpisode);
        assertEquals(SOME_SHOW_NAME, tracked.iterator().next());
        assertEquals(1, tracked.size());
    }

    // TODO Rewrite this
    @Test
    @Ignore
    public void remove_ShowNotTracked_AddsEpisodesFromEpisodeStore()
            throws Exception {
        MissingEpisodes missingEpisodes = new MissingEpisodes();
        EpisodeStore mockEpisodeStore = mock(EpisodeStore.class);
        final Episode episode = Episode
                .fromRawFileName("dexter.S03E05.someName.mkv");
        final List<Episode> expected = Arrays.asList(episode);
        when(mockEpisodeStore.getEpisodesForShow(anyString())).thenReturn(
                expected);
        Whitebox.setInternalState(missingEpisodes,
                MISSING_EPISODES_FIELD_EPISODE_STORE, mockEpisodeStore);

        missingEpisodes.remove(episode);

        @SuppressWarnings("unchecked")
        List<Episode> actual = (List<Episode>) Whitebox.getInternalState(
                missingEpisodes, MISSING_EPISODES_FIELD_MISSING);
        assertEquals(expected, actual);
    }

}
