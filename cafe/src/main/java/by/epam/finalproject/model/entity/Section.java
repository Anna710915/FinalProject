package by.epam.finalproject.model.entity;

/**
 * The type Section.
 */
public class Section extends CustomEntity{
    private long sectionId;
    private String sectionName;
    private boolean isAccessible;

    /**
     * Instantiates a new Section.
     */
    public Section(){}

    /**
     * Instantiates a new Section.
     *
     * @param sectionId    the section id
     * @param sectionName  the section name
     * @param isAccessible the is accessible
     */
    public Section(long sectionId, String sectionName, boolean isAccessible) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.isAccessible = isAccessible;
    }

    /**
     * Instantiates a new Section.
     *
     * @param sectionName  the section name
     * @param isAccessible the is accessible
     */
    public Section(String sectionName, boolean isAccessible) {
        this.sectionName = sectionName;
        this.isAccessible = isAccessible;
    }

    /**
     * Instantiates a new Section.
     *
     * @param sectionName the section name
     */
    public Section(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * Gets section id.
     *
     * @return the section id
     */
    public long getSectionId() {
        return sectionId;
    }

    /**
     * Sets section id.
     *
     * @param sectionId the section id
     */
    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * Gets section name.
     *
     * @return the section name
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Sets section name.
     *
     * @param sectionName the section name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * Is accessible boolean.
     *
     * @return the boolean
     */
    public boolean isAccessible() {
        return isAccessible;
    }

    /**
     * Sets accessible.
     *
     * @param accessible the accessible
     */
    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (sectionId != section.sectionId) return false;
        return sectionName != null ? sectionName.equals(section.sectionName) : section.sectionName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (sectionId ^ (sectionId >>> 32));
        result = 31 * result + (sectionName != null ? sectionName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Section{");
        sb.append("sectionId=").append(sectionId);
        sb.append(", sectionName='").append(sectionName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
