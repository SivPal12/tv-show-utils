package no.nith.sivpal12.tv.show.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import no.nith.sivpal12.tv.show.utils.exeptions.TsuLibraryRootPathNotAFolderException;
import no.nith.sivpal12.tv.show.utils.pojo.Episode;

public class TvLibrary {

    private final File libraryRootFolder;

    private TvLibrary(File libraryRootFolder) {
        this.libraryRootFolder = libraryRootFolder;
    }

    /**
     * Creates a new Tv library for the given folder
     *
     * @param libraryRootFolder Has to be a folder
     * @return A tv library
     */
    public static TvLibrary create(File libraryRootFolder) {
        if (!libraryRootFolder.isDirectory()) {
            throw new TsuLibraryRootPathNotAFolderException(String.format(
                    "Library root has to be a folder. Got '%s'",
                    libraryRootFolder.getAbsolutePath()));
        }
        return new TvLibrary(libraryRootFolder);
    }

    public List<Episode> scanForShows() {
        List<Episode> shows = new ArrayList<Episode>();

        List<File> allFilesInFolder = getAllFilesInFolder(libraryRootFolder);

        for (File file : allFilesInFolder) {
            Episode possibleEpisode = Episode.fromRawFileName(file.getName());
            if (possibleEpisode != null) {
                shows.add(possibleEpisode);
            }
        }

        return shows;
    }

    private List<File> getAllFilesInFolder(File folder) {
        List<File> files = new ArrayList<File>();
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getAllFilesInFolder(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }

}
