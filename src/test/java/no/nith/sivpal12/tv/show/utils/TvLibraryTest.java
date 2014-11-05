package no.nith.sivpal12.tv.show.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import no.nith.sivpal12.tv.show.utils.domain.MissingEpisodes;
import no.nith.sivpal12.tv.show.utils.exeptions.TsuLibraryRootPathNotAFolderException;
import no.nith.sivpal12.tv.show.utils.pojo.Episode;
import no.nith.sivpal12.tv.show.utils.services.TvBaseService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TvLibraryTest {

    private static final String EPISODE_NAME_1 = "dexter.S01E01.name.mkv";
    private static final String EPISODE_NAME_2 = "dexter.S01E02.name.mkv";
    private static final String EPISODE_NAME_3 = "dexter.S01E03.name.mkv";
    private static final List<Episode> NON_EMPTY_LIST = Arrays
            .asList(mock(Episode.class));

    @InjectMocks
    private TvLibrary tvLibrary;
    @Mock
    private File mockLibrary;

    // Can't be injected
    private TvBaseService mockTvBaseService;

    @Before
    public void setUp() {
        mockTvBaseService = mock(TvBaseService.class);
        Whitebox.setInternalState(tvLibrary, "tvBaseService", mockTvBaseService);
    }

    @Test(expected = TsuLibraryRootPathNotAFolderException.class)
    public void create_NotADirecotry_ThrowsException() throws Exception {
        File mockLib = mock(File.class);
        when(mockLib.isDirectory()).thenReturn(false);
        TvLibrary.create(mockLib);
    }

    @Test
    public void create_DirectoryIn_Ok() throws Exception {
        File mockLib = mock(File.class);
        when(mockLib.isDirectory()).thenReturn(true);
        final TvLibrary library = TvLibrary.create(mockLib);
        assertNotNull(library);
    }

    @Test
    public void scanForShows_DirectoryHasTvShowFiles_FindsThem() {
        File mockParentDir = mock(File.class);
        when(mockParentDir.isDirectory()).thenReturn(true);
        File ep1 = mock(File.class);
        when(ep1.isDirectory()).thenReturn(false);
        when(ep1.getName()).thenReturn(EPISODE_NAME_1);
        File ep2 = mock(File.class);
        when(ep2.isDirectory()).thenReturn(false);
        when(ep2.getName()).thenReturn(EPISODE_NAME_2);
        File ep3 = mock(File.class);
        when(ep3.isDirectory()).thenReturn(false);
        when(ep3.getName()).thenReturn(EPISODE_NAME_3);
        File[] episodes = { ep1, ep2, ep3 };
        when(mockParentDir.listFiles()).thenReturn(episodes);
        File[] files = { mockParentDir };
        when(mockLibrary.listFiles()).thenReturn(files);

        List<Episode> shows = tvLibrary.scanForShows();
        assertEquals(Episode.fromRawFileName(EPISODE_NAME_1), shows.get(0));
        assertEquals(Episode.fromRawFileName(EPISODE_NAME_2), shows.get(1));
        assertEquals(Episode.fromRawFileName(EPISODE_NAME_3), shows.get(2));

        verify(mockLibrary).listFiles();
    }

    @Test
    public void findMissingEpisodes_NullInput_NotNull() throws Exception {
        assertNotNull(tvLibrary.findMissingEpisodes(null));
    }

    @Test
    public void findMissingEpisodes_EmptyList_EmptyList() throws Exception {
        assertEquals(0,
                tvLibrary.findMissingEpisodes(new ArrayList<Episode>()).size());
    }

    @Test
    public void findMissingEpisodes_ValidInput_CreatesMissingEpisodeObject()
            throws Exception {
        ArrayList<Episode> episodeList = new ArrayList<Episode>();
        episodeList.add(mock(Episode.class));

        when(mockTvBaseService.createMissingEpisode()).thenReturn(
                mock(MissingEpisodes.class));

        tvLibrary.findMissingEpisodes(episodeList);
        verify(mockTvBaseService).createMissingEpisode();
    }

    @Test
    public void findMissingEpisodes_EmptyInput_ReturnsEarly()
            throws Exception {
        tvLibrary.findMissingEpisodes(new ArrayList<Episode>());
        verify(mockTvBaseService, never()).createMissingEpisode();
    }

    @Test
    public void findMissingEpisodes_RandomNumberOfEpisodes_CallsRemoveEpisodeSameAmountOfTimes()
            throws Exception {
        int numEpisodes = new Random().nextInt(30);
        List<Episode> episodes = new ArrayList<Episode>();
        for (int i = 0; i < numEpisodes; i++) {
            episodes.add(mock(Episode.class));
        }

        MissingEpisodes mockMissingEpisodes = mock(MissingEpisodes.class);
        when(mockTvBaseService.createMissingEpisode()).thenReturn(
                mockMissingEpisodes);
        
        tvLibrary.findMissingEpisodes(episodes);
        verify(mockMissingEpisodes, times(numEpisodes)).remove(
                any(Episode.class));
    }

    @Test
    public void findMissingEpisodes_ValidInput_ReturnsListFromMissingEpisodeObject() {
        List<Episode> expectedResult = new ArrayList<Episode>();
        expectedResult.add(Episode.fromRawFileName(EPISODE_NAME_1));
        expectedResult.add(Episode.fromRawFileName(EPISODE_NAME_2));
        expectedResult.add(Episode.fromRawFileName(EPISODE_NAME_3));

        MissingEpisodes mockMissingEpisodes = mock(MissingEpisodes.class);
        when(mockMissingEpisodes.getMissingEpisodes()).thenReturn(
                expectedResult);
        when(mockTvBaseService.createMissingEpisode()).thenReturn(
                mockMissingEpisodes);
        List<Episode> actualResult = tvLibrary
                .findMissingEpisodes(NON_EMPTY_LIST);

        assertEquals(expectedResult, actualResult);

        verify(mockMissingEpisodes).getMissingEpisodes();
    }
}
