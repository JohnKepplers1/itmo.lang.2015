package ru.ifmo.lang.kravchenko.t05;

import java.util.Random;

public class MyGun implements RussianRoulette.Gun {
    private String numberOfBullets;

    public MyGun(String numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }

    public boolean fire() {
        Random random = new Random();
        return (random.nextFloat() <= Integer.parseInt(numberOfBullets) / 6);
    }


}