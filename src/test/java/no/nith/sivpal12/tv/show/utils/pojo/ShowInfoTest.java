package no.nith.sivpal12.tv.show.utils.pojo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.nith.sivpal12.tv.show.utils.exeptions.TsuSeasonNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class ShowInfoTest {

    private static final String SHOW_INFO_FIELD_SEASONS = "seasons";

    @InjectMocks
    public ShowInfo objectUnderTest;

    @Mock
    private List<ShowSeason> seasons;

    private final Random random = new Random();

    @Test
    public void getNumberOfSeasons_RandomSeasonNumber_ReturnsCorrectNumber() {
        final int randInt = random.nextInt();
        when(seasons.size()).thenReturn(randInt);
        assertEquals(randInt, objectUnderTest.getNumberOfSeasons());
    }

    @Test
    public void getNumberOfEpisodes_RandSeasonWithRandEipisodes_ReturnsCorrectValue() {
        final int randSeason = random.nextInt();
        final int randNumEpisodes = random.nextInt();
        List<ShowSeason> localSeasons = new ArrayList<>();
        localSeasons.add(new ShowSeason(randSeason, randNumEpisodes));
        Whitebox.setInternalState(objectUnderTest, SHOW_INFO_FIELD_SEASONS, localSeasons);
        assertEquals(randNumEpisodes, objectUnderTest.getNumberOfEpisodes(randSeason));
    }

    @Test(expected = TsuSeasonNotFoundException.class)
    public void getNumberOfEpisodes_SeasonNotInList_ThrowsTsuSeasonNotFoundException() {
        Whitebox.setInternalState(objectUnderTest, SHOW_INFO_FIELD_SEASONS,
                new ArrayList<ShowSeason>());
        objectUnderTest.getNumberOfEpisodes(10);
    }

}
