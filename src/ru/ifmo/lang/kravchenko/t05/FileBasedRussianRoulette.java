package ru.ifmo.lang.kravchenko.t05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Random;


public class FileBasedRussianRoulette implements RussianRoulette {
    private String victim;
    private ArrayList<Path> listOfVictims = new ArrayList<Path>();

    public FileBasedRussianRoulette(String victim) {
        this.victim = victim;
    }

    public static void main(String[] args) throws IOException {
        String victim = args[0];
        String bullets = args[1];
        RussianRoulette roulette = new FileBasedRussianRoulette(victim);
        if (bullets.equals("bonus")) {
            Gun gun = new BonusGun();
            roulette.play(gun);
        } else {
            Gun gun = new MyGun(bullets);
            roulette.play(gun);
        }
    }

    public void play(Gun gun) {

        try {
            visitFiles();

            Random random = new Random();
            if (!listOfVictims.isEmpty()) {
                Path victim = listOfVictims.get(random.nextInt(listOfVictims.size()));
                boolean shot = gun.fire();
                if (shot) {
                    Files.delete(victim);
                    System.out.println("Файл удалён. " + victim.toString());
                } else {
                    System.out.println("Файл не удалён. " + victim.toString());
                }
            } else {
                System.out.println("Файлов нет.");
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }


    }

    private void visitFiles() throws IOException {
        listOfVictims.clear();
        Path pathToFiles = Paths.get(victim);
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException {
                listOfVictims.add(path);
                return FileVisitResult.CONTINUE;
            }

        };
        Files.walkFileTree(pathToFiles, visitor);
    }


}

