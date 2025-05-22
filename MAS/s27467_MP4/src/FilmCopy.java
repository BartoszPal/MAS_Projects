import java.util.List;

public class FilmCopy extends ObjectPlus {
    private Double price;
    private String serialNumber; // np DVD-PL-00123

    public FilmCopy(Double price, String serialNumber) {
        setPrice(price);
        setSerialNumber(serialNumber);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if (serialNumber == null || serialNumber.isEmpty()) {
            throw new IllegalArgumentException("Serial number cannot be null or empty");
        }

        try {
            List<? extends FilmCopy> extentOfClass = ObjectPlus.getExtent(this.getClass());
            boolean exists = extentOfClass.stream().anyMatch(copy -> copy != this && serialNumber.equals(copy.getSerialNumber()));
            if (exists) {
                throw new IllegalArgumentException("Serial number must be unique");
            }
            this.serialNumber = serialNumber;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double newPrice) {
        if (newPrice == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        // ograniczenie statyczne
        if (newPrice < 5) {
            throw new IllegalArgumentException("Price cannot be lower than 5");
        } else if (newPrice > 30) {
            throw new IllegalArgumentException("Price cannot be higher than 30");
        }

        // ogranicznie dynamiczne
        if (this.price != null) {
            if (newPrice < this.price) {
                throw new IllegalArgumentException("Price cannot decrease");
            }
            double maxIncrease = this.price * 1.20;
            if (newPrice > maxIncrease) {
                throw new IllegalArgumentException("Price cannot increase by more than 20%");
            }
        }
        this.price = newPrice;
    }
}
