import java.time.LocalDateTime;
import java.util.Objects;

public class Rating extends ObjectPlus {
    private Client ratedBy;
    private Film ratedFilm;
    private Integer score;
    private String comment;
    private LocalDateTime dateRated;

    public Rating(Client client, Film film, Integer score, String comment, LocalDateTime dateRated) {
        setRatedBy(client);
        setRatedFilm(film);
        setScore(score);
        setComment(comment);
        setDateRated(dateRated);

        client.addRating(this);
        film.addRating(this);
    }

    public void destroyRating() {
        if (ratedFilm != null) {
            ratedFilm.removeRating(this);
            ratedFilm = null;
        }
        if (ratedBy != null) {
            ratedBy.removeRating(this);
            ratedBy = null;
        }
        ObjectPlus.removeFromExtent(this);
    }

    public Client getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(Client ratedBy) {
        if (ratedBy == null) {
            throw new IllegalArgumentException("Client cannot be empty");
        }
        this.ratedBy = ratedBy;
        ratedBy.addRating(this);
    }

    public Film getRatedFilm() {
        return ratedFilm;
    }

    public void setRatedFilm(Film ratedFilm) {
        if (ratedFilm == null) {
            throw new IllegalArgumentException("Film cannot be empty");
        }
        this.ratedFilm = ratedFilm;
        ratedFilm.addRating(this);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        if (score == null || score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be between 1 and 10");
        }
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment != null && comment.length() > 500) {
            throw new IllegalArgumentException("Comment is too long (max 500 characters)");
        }
        this.comment = comment == null || comment.isBlank() ? null : comment;
    }

    public LocalDateTime getDateRated() {
        return dateRated;
    }

    public void setDateRated(LocalDateTime dateRated) {
        if (dateRated == null) {
            throw new IllegalArgumentException("Rating date cannot be empty");
        } else if (dateRated.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Rating date cannot be in the future");
        }
        this.dateRated = dateRated;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "score=" + score +
                ", comment='" + comment + '\'' +
                ", dateRated=" + dateRated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(ratedBy, rating.ratedBy) && Objects.equals(ratedFilm, rating.ratedFilm) && Objects.equals(score, rating.score) && Objects.equals(comment, rating.comment) && Objects.equals(dateRated, rating.dateRated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratedBy, ratedFilm, score, comment, dateRated);
    }
}
