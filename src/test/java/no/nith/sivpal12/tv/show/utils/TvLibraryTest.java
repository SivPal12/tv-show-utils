package no.nith.sivpal12.tv.show.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import no.nith.sivpal12.tv.show.utils.exeptions.TsuLibraryRootPathNotAFolderException;
import no.nith.sivpal12.tv.show.utils.pojo.Episode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TvLibraryTest {

    private static final String EPISODE_NAME_1 = "dexter.S01E01.name.mkv";
    private static final String EPISODE_NAME_2 = "dexter.S01E02.name.mkv";
    private static final String EPISODE_NAME_3 = "dexter.S01E03.name.mkv";

    @InjectMocks
    private TvLibrary tvLibrary;
    @Mock
    private File mockLibrary;

    @Test(expected = TsuLibraryRootPathNotAFolderException.class)
    public void create_NotADirecotry_ThrowsException() throws Exception {
        File mockLib = mock(File.class);
        when(mockLib.isDirectory()).thenReturn(false);
        TvLibrary.create(mockLib);
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
}
