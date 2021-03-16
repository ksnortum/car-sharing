package carsharing.model;

public class Customer {
    private final long id;
    private final String name;
    private Long rentedCarId = null;

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(Long rentedCarId) {
        this.rentedCarId = rentedCarId;
    }
}
