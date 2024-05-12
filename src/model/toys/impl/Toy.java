package model.toys.impl;

import model.ToysBase;
import util.Validator;

public class Toy extends ToysBase {

    public Toy(int id, String toyName, int countToy, double chanse) {
        super(id, toyName, countToy, chanse);
    }

    public Toy(){
        super();
    }

    public Toy createToy(String[] args){
        Validator vl = new Validator();
        setId(Integer.parseInt(vl.isId(args[0])));
        setToyName(args[1]);
        setCountToy(Integer.parseInt(vl.isCount(args[2])));
        setChanse(Double.parseDouble(vl.isChanse(args[3])));
        return this;
    }

}
