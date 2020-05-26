package Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paterny {

    public Paterny() {

//        String text = "Sprawdź";
//        Pattern p;
//        Matcher m;
//
//        p = Pattern.compile("[A-Z][a-z]*źł");
//        m = p.matcher(text);
//        m.matches();


        Pattern nazwaPattern;
        nazwaPattern = Pattern.compile("[A-z]*");

        nazwaPattern.matcher("tekst").matches();
    }
}
