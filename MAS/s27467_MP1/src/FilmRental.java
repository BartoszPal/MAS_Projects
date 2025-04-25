import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FilmRental extends ObjectPlus {
    private static Integer maxAmountOfDays = 10;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Boolean isReturned;

    public FilmRental() {
        this.startDate = LocalDateTime.now();
        this.finishDate = startDate.plusDays(maxAmountOfDays);
        setReturned(false);
    }

    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(FilmRental.class);
    }

    public static Integer getMaxAmountOfDays() {
        return maxAmountOfDays;
    }

    public static void setMaxAmountOfDays(Integer maxAmountOfDays) {
        if (maxAmountOfDays <= 0) {
            throw new IllegalArgumentException("Max amount of films must be greater than 0");
        }
        FilmRental.maxAmountOfDays = maxAmountOfDays;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) throws IllegalArgumentException {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be empty");
        } else if (startDate.isAfter(finishDate)) {
            throw new IllegalArgumentException("Start date cannot be after finish date");
        }
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) throws IllegalArgumentException {
        if (finishDate == null) {
            throw new IllegalArgumentException("Finish date cannot be empty");
        } else if (finishDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Finish date cannot be before start date");
        }
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "FilmRental{" +
                "startDate=" + startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) +
                ", finishDate=" + finishDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRental that = (FilmRental) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(finishDate, that.finishDate) && Objects.equals(isReturned, that.isReturned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, finishDate, isReturned);
    }
}