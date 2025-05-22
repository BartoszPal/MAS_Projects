public enum ActorRole {
    LEAD_ROLE("Lead role", "The main character in the film"),
    SUPPORTING_ROLE("Supporting role", "A character that plays an important, but secondary role"),
    MINOR_ROLE("Minor role", "A character with a small part in the film"),
    VILLAIN("Villain", "A character who opposes the protagonist, typically a bad guy"),
    HERO("Hero", "The main protagonist, typically the good character"),
    COMEDIC_ROLE("Comedic role", "A character meant to provide humor in the film"),
    TRAGIC_HERO("Tragic hero", "A protagonist who faces a tragic downfall"),
    ROMANTIC_LEAD("Romantic lead", "The character involved in the film's romance storyline"),
    CAMEO("Cameo", "A brief appearance by a well-known actor or personality"),
    NARRATOR("Narrator", "A character who narrates the story, either from within or outside the plot"),
    WITNESS("Witness", "A character who serves as a witness to the events of the story"),
    MENTOR("Mentor", "A guiding character, often helping the protagonist grow"),
    FRIEND("Friend", "A supporting character who is close to the protagonist"),
    PARENT("Parent", "A character who serves as a parent figure to the protagonist"),
    RIVAL("Rival", "A character who competes with the protagonist"),
    HISTORICAL_FIGURE("Historical figure", "A character based on a real historical figure"),
    FICTIONAL_CHARACTER("Fictional character", "A character that is completely made up for the story"),
    DIGITAL_CHARACTER("Digital character", "A character created using CGI, often voiced by an actor");

    private final String roleName;
    private final String description;

    ActorRole(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.roleName + ": " + this.description;
    }
}
