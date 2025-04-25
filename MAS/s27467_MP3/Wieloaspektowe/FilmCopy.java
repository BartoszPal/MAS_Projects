package Wieloaspektowe;

import Enums.Condition;
import Enums.MyStatus;

public abstract class FilmCopy extends ObjectPlus {
    private MyStatus status;
    private Condition condition;
    private Double price;
    private String warrantyInfo;
    private Integer timesRented;

    public FilmCopy(MyStatus status, Condition condition, Double price, String warrantyInfo) {
        setStatus(status);
        setCondition(condition);
        setPrice(price);
        if (this.condition == Condition.NEW) {
            setWarrantyInfo(warrantyInfo);
        } else {
            throw new UnsupportedOperationException("This film is not new so it cannot have warranty info");
        }
    }

    public FilmCopy(MyStatus status, Condition condition, Double price, Integer timesRented) {
        setStatus(status);
        setCondition(condition);
        setPrice(price);
        if (this.condition == Condition.USED) {
            setTimesRented(timesRented);
        } else {
            throw new UnsupportedOperationException("This film is not used so it cannot have field timesRented");
        }
    }

    public void incrementTimesRented() {
        if (this.condition != Condition.USED) {
            throw new UnsupportedOperationException("Only used copies track rental count.");
        }
        this.timesRented++;
        System.out.println("Updated times rented: " + timesRented);
    }

    public void activateTrialPeriod() {
        if (this.condition != Condition.NEW) {
            throw new UnsupportedOperationException("Only new copies can activate a trial period.");
        }
        System.out.println("Trial period activated! You can watch this film for free for 3 days.");
    }

    public String getWarrantyInfo() {
        if (this.condition != Condition.NEW) {
            throw new UnsupportedOperationException("This film copy is not new so it doesn't have field warrantyInfo");
        }
        return warrantyInfo;
    }

    public void setWarrantyInfo(String warrantyInfo) {
        if (warrantyInfo == null) {
            throw new IllegalArgumentException("Warranty info cannot be null");
        } else if (this.condition != Condition.NEW) {
            throw new UnsupportedOperationException("This film copy is not new so it doesn't have field warrantyInfo");
        }
        this.warrantyInfo = warrantyInfo;
    }

    public Integer getTimesRented() {
        if (this.condition != Condition.USED) {
            throw new UnsupportedOperationException("This film copy is not used so it doesn't have field timesRented");
        }
        return timesRented;
    }

    public void setTimesRented(Integer timesRented) {
        if (timesRented == null) {
            throw new IllegalArgumentException("Times rented cannot be null");
        } else if (this.condition != Condition.USED) {
            throw new UnsupportedOperationException("This film copy is not used so it doesn't have field timesRented");
        }
        this.timesRented = timesRented;
    }

    public void destroy() {
        ObjectPlus.removeFromExtent(this);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        } else if (price < 10) {
            throw new IllegalArgumentException("Price cannot be lower than 10");
        }
        this.price = price;
    }

    public MyStatus getStatus() {
        return status;
    }

    public void setStatus(MyStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        this.status = status;
    }

    public Condition getCondition() {
        return condition;
    }

    private void setCondition(Condition condition) {
        if (condition == null) {
            throw new IllegalArgumentException("Condition cannot be empty");
        }
        this.condition = condition;
    }

    public abstract void play();

    public abstract Double calculateRentalPrice();

    public abstract void rentFilm();
}
