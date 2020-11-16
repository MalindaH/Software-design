package music.tags;

/**
 * Different types of expected and optional tags
 * 
 * Note: expected/optional tags are called by `TagType.GENRE`;
 * custom tags are called by `CustomTag.get("name")`
 */
public enum TagType implements Tag
{
    TITLE, TIME, ARTIST, BPM, GENRE, COMPOSER
}
