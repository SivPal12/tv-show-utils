package no.nith.sivpal12.tv.show.utils.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TvBaseServiceIntTest {

    private static final String SHOW_NAME_HOW_I_MET_YOUR_MOTHER = "How I Met Your Mother";
    private static final String SHOW_NAME_SCRUBS = "Scrubs";
    private static final String SHOW_NAME_HOUSE_M_D = "House";
    private static final String SHOW_NAME_DEXTER = "Dexter";
    private TvBaseService tvBaseService;

    @Before
    public void setUp() {
        tvBaseService = new TvBaseService();
    }

    @Test
    public void getSeasonsForShow_KnownShows_ReturnsRightNumberOfSeasons()
            throws Exception {
        assertEquals(8, tvBaseService.getSeasonsForShow(SHOW_NAME_DEXTER));
        assertEquals(8, tvBaseService.getSeasonsForShow(SHOW_NAME_HOUSE_M_D));
        assertEquals(9, tvBaseService.getSeasonsForShow(SHOW_NAME_SCRUBS));
        assertEquals(9,
                tvBaseService
                        .getSeasonsForShow(SHOW_NAME_HOW_I_MET_YOUR_MOTHER));
    }

    @Test
    public void getEpisodesInSeason_KnownEpisodesInSeason_ReturnsRightNumber()
            throws Exception {
        // Dexter
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 1));
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 2));
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 3));
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 4));
        // Not on Freebase
        // assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER,
        // 5));
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 6));
        assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER, 7));
        // Not on Freebase
        // assertEquals(12, tvBaseService.getEpisodesInSeason(SHOW_NAME_DEXTER,
        // 8));

        // HIMYM
        assertEquals(22, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 1));
        assertEquals(22, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 2));
        assertEquals(20, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 3));
        assertEquals(24, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 4));
        // Freebase is wrong
        // assertEquals(24, tvBaseService.getEpisodesInSeason(
        // SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 5));
        assertEquals(24, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 6));
        // Missing
        // assertEquals(24, tvBaseService.getEpisodesInSeason(
        // SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 7));
        assertEquals(24, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 8));
        assertEquals(24, tvBaseService.getEpisodesInSeason(
                SHOW_NAME_HOW_I_MET_YOUR_MOTHER, 9));
        
        //Scrubs
        assertEquals(24, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 1));
        assertEquals(22, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 2));
        assertEquals(22, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 3));
        assertEquals(25, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 4));
        assertEquals(24, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 5));
        assertEquals(22, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 6));
        assertEquals(11, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 7));
        // Freebase is wrong
        // assertEquals(19, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS,
        // 8));
        assertEquals(13, tvBaseService.getEpisodesInSeason(SHOW_NAME_SCRUBS, 9));

        // House MD
        assertEquals(22,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 1));
        assertEquals(24,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 2));
        assertEquals(24,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 3));
        assertEquals(16,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 4));
        assertEquals(24,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 5));
        assertEquals(22,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 6));
        assertEquals(23,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 7));
        assertEquals(22,
                tvBaseService.getEpisodesInSeason(SHOW_NAME_HOUSE_M_D, 8));
    }
}
