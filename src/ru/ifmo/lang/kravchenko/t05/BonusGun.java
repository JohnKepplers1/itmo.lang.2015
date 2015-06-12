package ru.ifmo.lang.kravchenko.t05;

import java.util.Calendar;

public class BonusGun implements RussianRoulette.Gun {
    public boolean fire() {
        Calendar calendar = Calendar.getInstance();
        if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            return true;
        }
        if (calendar.get(Calendar.WEEK_OF_MONTH) == 1) {
            return true;
        } else {
            return false;
        }
    }
}