package model;

import util.Validator;

import java.util.List;

public class Toy {

    private int id;
    private String toyName;
    private int countToy;
    private int chanse;

    public Toy(int id, String toyName, int countToy, int chanse) {
        this.id = id;
        this.toyName = toyName;
        this.countToy = countToy;
        this.chanse = chanse;
    }

    public Toy() {
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

    public void setChanse(int chanse) {
        this.chanse = chanse;
    }


    public String toString() {
        return String.format("Id: %d Name: %s Count: %d Chanse: %d\n", id, toyName, countToy, chanse);
    }

    public Toy createToy(String[] args) {
        Validator vl = new Validator();
        Toy toy = new Toy();
        toy.setId(vl.isId(args[0]));
        toy.setToyName(args[1]);
        toy.setCountToy(vl.isCount(args[2]));
        toy.setChanse(vl.isChanse(args[3]));
        return toy;
    }

    public void decreaseCountToy() {
        countToy--;
    }

//    public Toy createToy(Toy toy){
//        toy.setId(toy.getId());
//        toy.setToyName(toy.getToyName());
//        toy.setCountToy(getCountToy());
//        toy.setChanse(toy.getChanse());
//        return toy;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Toy toysBase = (Toy) o;
        if (Integer.compare(this.getId(), toysBase.getId()) != 0) {
            if (CharSequence.compare(this.getToyName(), toysBase.getToyName()) != 0) {
                return false;
            }
        }
        return true;
//        return Integer.compare(this.getId(), toysBase.getId()) == 0
//                && CharSequence.compare(this.getToyName(), toysBase.getToyName()) == 0
////                && Integer.compare(this.getCountToy(), toysBase.getCountToy()) == 0;
    }



}
