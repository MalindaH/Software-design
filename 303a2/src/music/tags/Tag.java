package music.tags;

/**
 * Tag interface is implemented in TagType enum (containing expected and 
 * optional tags) and CustomTag class. Tag is to be used in methods that 
 * are common to both TagType and CustomTag.
 * 
 * Note: expected/optional tags are called by `TagType.GENRE`;
 * custom tags are called by `CustomTag.get("name")`
 */
public interface Tag {
	
}
