package no.nith.sivpal12.tv.show.utils.pojo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Episode {

    // showName.S01E01.episodeName.mkv
    private static final String REGEX_PATTERN = "^(\\w+)\\.S(\\d{2})E(\\d{2})\\.(\\w+)\\.[a-zA-z]{1,3}$";
    private final String showName, episodeName;
    private final int seasonNumber, episodeNumber;

    private Episode(String showName, int seasonNumber, int episodeNumber,
            String episodeName) {
        this.showName = showName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
    }

    public static Episode fromRawFileName(String rawFileName) {
        Matcher matcher = Pattern.compile(REGEX_PATTERN).matcher(rawFileName);
        if (!matcher.matches()) {
            return null;
        }

        return new Episode(matcher.group(1),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)), matcher.group(4));
    }

    public String getShowName() {
        return showName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + episodeName.hashCode();
        result = prime * result + episodeNumber;
        result = prime * result + seasonNumber;
        result = prime * result + showName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Episode other = (Episode) obj;
        if (episodeName == null) {
            if (other.episodeName != null)
                return false;
        } else if (!episodeName.equals(other.episodeName))
            return false;
        if (episodeNumber != other.episodeNumber)
            return false;
        if (seasonNumber != other.seasonNumber)
            return false;
        if (showName == null) {
            if (other.showName != null)
                return false;
        } else if (!showName.equals(other.showName))
            return false;
        return true;
    }

}
