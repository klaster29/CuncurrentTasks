public class Loot {
    private final int numberOfLoot;
    private int cost;
    private boolean isValued;

    public Loot(int numberOfLoot) {
        this.numberOfLoot = numberOfLoot;
        isValued = false;
    }

    public int getNumberOfLoot() {
        return numberOfLoot;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isValued() {
        return isValued;
    }

    public void setValued(boolean valued) {
        isValued = valued;
    }

    @Override
    public String toString() {
        return "Loot{" +
                "numberOfLoot=" + numberOfLoot +
                ", cost=" + cost +
                ", isValued=" + isValued +
                '}';
    }
}
