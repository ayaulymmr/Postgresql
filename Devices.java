public class Devices {
    private int devId;
    private int price;
    private int count;

    public Devices(int devId, int price, int count) {
        this.devId = devId;

        this.price = price;
        this.count = count;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
