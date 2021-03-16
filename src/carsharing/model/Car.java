package carsharing.model;

public class Car {
    private final long id;
    private final String name;
    private final long companyId;

    public Car(long id, String name, long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCompanyId() {
        return companyId;
    }
}
