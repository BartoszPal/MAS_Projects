import java.time.LocalDateTime;
import java.util.*;

public class Film extends ObjectPlus {
    private String title;
    private LocalDateTime releaseDate;
    private String description;
    private List<Director> directedBy = new ArrayList<>();
    private List<Director> playedBy = new ArrayList<>();
    private List<FilmActorRole> roles = new ArrayList<>();

    public Film(String title, String description, LocalDateTime releaseDate) {
        setTitle(title);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public void destroyFilm() {
        if (!roles.isEmpty()) {
            for (FilmActorRole filmActorRole : new ArrayList<>(roles)) {
                filmActorRole.destroyFilmActorRole();
            }
            this.roles.clear();
        }
        if (!directedBy.isEmpty()) {
            for (Director director : new ArrayList<>(directedBy)) {
                director.removeDirects(this);
            }
            this.directedBy.clear();
        }
        if (!playedBy.isEmpty()) {
            for (Director director : new ArrayList<>(playedBy)) {
                director.removePlays(this);
            }
            this.playedBy.clear();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public void addDirectedBy(Director director) {
        if (!directedBy.contains(director) && director != null) {
            directedBy.add(director);
            director.addDirects(this);
        }
    }

    public void removeDirectedBy(Director director) {
        if (directedBy.contains(director) && director != null) {
            if (playedBy.contains(director)) {
                playedBy.remove(director);
                director.removePlays(this);
            }
            directedBy.remove(director);
            director.removeDirects(this);
        }
    }

    public void addPlaysBy(Director director) {
        if(director == null){
            throw new IllegalArgumentException("Director cannot be empty");
        }
        if (!directedBy.contains(director)) {
            throw new IllegalArgumentException("Director needs to be a director of this film in order to play in it");
        }
        if (!playedBy.contains(director)) {
            playedBy.add(director);
            director.addPlays(this);
        }
    }

    public void removePlaysBy(Director director) {
        if (playedBy.contains(director) && director != null) {
            playedBy.remove(director);
            director.removePlays(this);
        }
    }

    public List<Director> getDirectedBy() {
        return Collections.unmodifiableList(directedBy);
    }

    public void addRole(Actor actor, ActorRole role) {
        if(actor == null || role == null){
            throw new IllegalArgumentException("Film and role are obligatory");
        }
        new FilmActorRole(actor, this, role);
    }

    public void addRole(FilmActorRole role) {
        if(role == null){
            throw new IllegalArgumentException("Role cannot be empty");
        }
//        for (FilmActorRole existingRole : roles) {
//            if (existingRole.equals(role)) {
//                throw new IllegalArgumentException("This exact role for this film already exists");
//            }
//        }
        this.roles.add(role);
    }

    public void removeRole(FilmActorRole filmActorRole) {
        if (filmActorRole != null && roles.contains(filmActorRole)) {
            roles.remove(filmActorRole);
            filmActorRole.destroyFilmActorRole();
        }
    }

    public List<Director> getPlayedBy() {
        return Collections.unmodifiableList(playedBy);
    }

    private boolean isEmpty(String value) {
        return value == null || value.isBlank();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (isEmpty(title)) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be empty"); // moze byc null
        }
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null){
            throw new IllegalArgumentException("Description cannot be empty");
        } else if (description.trim().length() < 100) {
            throw new IllegalArgumentException("Description must be at least 100 characters");
        } else if (description.trim().length() > 500) {
            throw new IllegalArgumentException("Description is too long, max 500 characters");
        }
        this.description = description.trim();
    }

    @Override
    public String toString() {
        return "Film{" + "title='" + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, description);
    }
}