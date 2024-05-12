package model;

public class ToysBase {
    private int id;
    private String toyName;
    private int countToy;
    private double chanse;

    public ToysBase(int id, String toyName, int countToy, double chanse) {
        this.id = id;
        this.toyName = toyName;
        this.countToy = countToy;
        this.chanse = chanse;
    }
    public ToysBase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public int getCountToy() {
        return countToy;
    }

    public void setCountToy(int countToy) {
        this.countToy = countToy;
    }

    public double getChanse() {
        return chanse;
    }

    public void setChanse(double chanse) {
        this.chanse = chanse;
    }

    public String toString(){
        return String.format("Id: %s; Name: %s; Count: %d; Chanse: %s%;\n", String.valueOf(id), toyName, countToy, String.valueOf(chanse));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ToysBase toysBase = (ToysBase) o;
        return Integer.compare(this.getId(), toysBase.getId()) == 0
                && CharSequence.compare(this.getToyName(), toysBase.getToyName()) == 0
//                && Integer.compare(this.getCountToy(), toysBase.getCountToy()) == 0
                && Double.compare(this.getChanse(), toysBase.getChanse()) == 0;
    }

}
