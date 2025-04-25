package Wieloaspektowe;

import Enums.Condition;
import Enums.MyStatus;

public class DVD extends FilmCopy {
    private Integer regionCode;
    private Boolean hasBonusContent;

    public DVD(MyStatus status, Condition condition, Double price, String warrantyInfo, Integer regionCode, Boolean hasBonusContent) {
        super(status, condition, price, warrantyInfo);
        setRegionCode(regionCode);
        setHasBonusContent(hasBonusContent);
    }

    public DVD(MyStatus status, Condition condition, Double price, Integer timesRented, Integer regionCode, Boolean hasBonusContent) {
        super(status, condition, price, timesRented);
        setRegionCode(regionCode);
        setHasBonusContent(hasBonusContent);
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        if (regionCode == null) {
            throw new IllegalArgumentException("Region code cannot be empty");
        }
        if (regionCode < 1 || regionCode > 6) {
            throw new IllegalArgumentException("Invalid DVD region code");
        }
        this.regionCode = regionCode;
    }

    public Boolean hasBonusContent() {
        return hasBonusContent;
    }

    public void setHasBonusContent(Boolean hasBonusContent) {
        if (hasBonusContent == null) {
            throw new IllegalArgumentException("Bonus content cannot be empty");
        }
        this.hasBonusContent = hasBonusContent;
    }

    @Override
    public void play() {
        System.out.println("DVD (Region " + regionCode + ") is playing in standard definition.");
        if (hasBonusContent) {
            System.out.println("Bonus content is available.");
        }
    }

    @Override
    public Double calculateRentalPrice() {
        double base = this.getPrice();
        if (hasBonusContent) base += 2.5;
        if (getCondition() == Condition.USED) base -= 3.0;
        return base;
    }

    @Override
    public void rentFilm() {
        this.play();
        double price = this.calculateRentalPrice();
        System.out.println("Rental price: " + price + "zł");
    }
}

