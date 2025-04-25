package Wieloaspektowe;

import Enums.Condition;
import Enums.MyStatus;

public class BluRay extends FilmCopy {
    private Boolean is4K;
    private Integer layerCount;

    public BluRay(MyStatus status, Condition condition, Double price, String warrantyInfo, Boolean is4K, Integer layerCount) {
        super(status, condition, price, warrantyInfo);
        setIs4K(is4K);
        setLayerCount(layerCount);
    }

    public BluRay(MyStatus status, Condition condition, Double price, Integer timesRented, Boolean is4K, Integer layerCount) {
        super(status, condition, price, timesRented);
        setIs4K(is4K);
        setLayerCount(layerCount);
    }

    public Boolean is4K() {
        return is4K;
    }

    public void setIs4K(Boolean is4K) {
        if (is4K == null) {
            throw new IllegalArgumentException("Is 4k cannot be empty");
        }
        this.is4K = is4K;
    }

    public Integer getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(Integer layerCount) {
        if (layerCount == null) {
            throw new IllegalArgumentException("Layer count cannot be empty");
        }
        if (layerCount < 1 || layerCount > 2) {
            throw new IllegalArgumentException("BluRay can only have 1 or 2 layers");
        }
        this.layerCount = layerCount;
    }

    @Override
    public void play() {
        System.out.println("BluRay is playing in " + (is4K ? "4K" : "Full HD") + " resolution.");
    }

    @Override
    public Double calculateRentalPrice() {
        double base = getPrice();
        if (is4K) base += 3.0;
        if (layerCount == 2) base += 2.0;
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
