package seedu.billboard.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A Tag is considered unique by comparing their hashcodes.  As such, adding and updating of
 * Tag uses hashcode and equals() for equality so as to ensure that the Tag being added, updated
 * or removed is unique in terms of identity in the UniqueTagList.
 *
 * Supports a minimal set of Map operations.
 *
 */
public class UniqueTagList {
    private Map<String, Tag> tagList = new HashMap<>();

    /**
     * Checks if tag of specific name exists in list.
     * @param tagName  Name of the tag.
     * @return tag's existence.
     */
    public boolean contains(String tagName) {
        requireNonNull(tagName);
        return tagList.containsKey(tagName);
    }

    /**
     * Retrieves tag which has the tag name given in the argument.
     * The Tag must exist in the list.
     * @param tagName name of the tag.
     * @return Tag of the specific tag name.
     */
    public Tag retrieveTag(String tagName) {
        requireNonNull(tagName);
        return tagList.get(tagName);
    }

    /**
     * Adds a tag with the name given in the argument into the list.
     * Tag must not exist in the list.
     * @param tagName name of the tag.
     */
    public void add(String tagName) {
        requireNonNull(tagName);
        tagList.put(tagName, new Tag(tagName));
    }

    /**
     * Checks and adds tags that do not exist in the list.
     * @param toAdd list of tag names to be checked.
     */
    public void addNewTags(List<String> toAdd) {
        requireNonNull(toAdd);
        for (String tagName: toAdd) {
            if (!contains(tagName)) {
                add(tagName);
            }
        }
    }

    /**
     * Retrieves corresponding tags according to the list of tag names.
     * If tag does not exist, it will be added to the list.
     * @param toRetrieve List of tag names.
     * @return Set of all tags retrieved.
     */
    public Set<Tag> retrieveTags(List<String> toRetrieve) {
        Set<Tag> toReturn = new HashSet<>();
        addNewTags(toRetrieve);
        for (String current : toRetrieve) {
            toReturn.add(retrieveTag(current));
        }
        return Collections.unmodifiableSet(toReturn);
    }

    /**
     * Removes mapping of specified tag name and its corresponding tag.
     * Tag name must exist in the list.
     * @param tagName to be removed
     */
    public void remove(String tagName) {
        requireNonNull(tagName);
        tagList.remove(tagName);
    }

    /**
     * Removes mapping of all tags specified in argument.
     * @param tags to be removed.
     */
    public void removeAll(List<Tag> tags) {
        requireNonNull(tags);
        for (Tag tag : tags) {
            remove(tag.tagName);
        }
    }

    /**
     * Sets current map to the one specified in the argument.
     * @param tagList to replace the current map.
     */
    public void setList(Map<String, Tag> tagList) {
        requireNonNull(tagList);
        this.tagList = new HashMap<>(tagList);
    }

    /**
     * Returns an unmodifiable current map.
     * @return current map.
     */
    public Map<String, Tag> getTagList() {
        return Collections.unmodifiableMap(tagList);
    }

    /**
     * Returns a list of unique tag names.
     * @return list of unique tag names.
     */
    public List<String> getTagNames() {
        return List.copyOf(tagList.keySet());
    }
}
