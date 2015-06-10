package ru.ifmo.lang.kravchenko.t09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineProcessor {


    public static void main(final String[] args) throws IOException {
        Path output = Paths.get(args[1]);
        Path input = Paths.get(args[0]);
        Stream<String> myStream = Files.readAllLines(input).stream();
        for (int counter = 2; counter < args.length; counter++) {

            if (args[counter].equals("sort")) {
                myStream = myStream.sorted();
            }
            if (args[counter].equals("skip")) {
                myStream = myStream.skip(Integer.parseInt(args[counter + 1]));
            }
            if (args[counter].equals("limit")) {
                myStream = myStream.limit(Integer.parseInt(args[counter + 1]));
            }
            if (args[counter].equals("shuffle")) {
                ArrayList<String> myList = (ArrayList) myStream.collect(Collectors.toList());
                Collections.shuffle(myList);
                myStream = myList.stream();
            }
            if (args[counter].equals("distinct")) {
                myStream = myStream.distinct();
            }
           /* if (args[counter].equals("filter")) {
                Pattern pattern = Pattern.compile(args[counter]);
                myStream = myStream.filter(i -> pattern.matcher(i).matches());
            }
        */}

        Files.write(output, (List) myStream.collect(Collectors.toList()));

    }

}


