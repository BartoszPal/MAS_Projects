import java.time.LocalDateTime;

public class FilmActorRole extends ObjectPlus {
    private Actor actor;
    private Film film;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private ActorRole role;

    public FilmActorRole(Actor actor, Film film, ActorRole role) {
        setActor(actor);
        setFilm(film);
        setRole(role);
        setCreationDate(LocalDateTime.now());
        setEndDate(null);
    }

    public void destroyFilmActorRole() {
        if (film != null) {
            film.removeRole(this);
            film = null;
        }
        if (actor != null) {
            actor.removeRole(this);
            actor = null;
        }
        ObjectPlus.removeFromExtent(this);
    }

    public Actor getActor() {
        return actor;
    }

    private void setActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be empty");
        }
        if (this.actor != null){
            this.actor.removeRole(this);
        }
        this.actor = actor;
        actor.addRole(this);
    }

    public Film getFilm() {
        return film;
    }

    private void setFilm(Film film) {
        if (film == null) {
            throw new IllegalArgumentException("Film cannot be empty");
        }
        if (this.film != null){
            this.film.removeRole(this);
        }
        this.film = film;
        film.addRole(this);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date cannot be empty");
        } else if (creationDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Creation date cannot be in the future");
        }
        if (endDate != null && creationDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Creation date cannot be after end date");
        }
        this.creationDate = creationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        if (endDate != null) {
            if (endDate.isBefore(creationDate)) {
                throw new IllegalArgumentException("End date cannot be before creation date");
            }
        }
        this.endDate = endDate;
    }

    public ActorRole getRole() {
        return role;
    }

    public void setRole(ActorRole role) {
        if(role == null){
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }
}
