package no.nith.sivpal12.tv.show.utils.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class EpisodeTest {

    @Test
    public void fromRawFileName_NormalInput_Ok() throws Exception {
        final Episode episode = Episode
                .fromRawFileName("dexter.S01E01.name.mkv");
        assertEquals("dexter", episode.getShowName());
        assertEquals(1, episode.getSeasonNumber());
        assertEquals(1, episode.getEpisodeNumber());
        assertEquals("name", episode.getEpisodeName());
    }

    @Test
    public void fromRawFileName_IllegalInput_ReturnsNull() throws Exception {
        assertNull(Episode.fromRawFileName("thisIsNotAn Episode!"));
    }
}
