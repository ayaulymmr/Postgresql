public class phone extends Devices{
    private String category;
    private String model;
    public phone(int devId, int price, int count, String category, String model) {
        super(devId, price, count);
        this.category = category;
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
