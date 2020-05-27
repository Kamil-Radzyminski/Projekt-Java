package Patterns;

import java.util.regex.Pattern;

public class Patterns {

    public static Pattern getLettersPattern() {
        Pattern ImieNazwiskoPattern = Pattern.compile("[A-Z]{1}[a-z]{0,28}");
        return ImieNazwiskoPattern;
    }

    public static Pattern getCharactersPattern(Integer maxCharacters) {
        Pattern CharactersPattern = Pattern.compile("[\\w]{1," + maxCharacters + "}");
        return CharactersPattern;
    }
    
    public static Pattern getSexPattern(){
        Pattern SexPattern = Pattern.compile("(m|f){1}");
        return SexPattern;
    }
    
    public static Pattern getIloscPattern(){
        Pattern IloscPattern = Pattern.compile("[0-9]+[\\.]{1}[0-9]+");
        return IloscPattern;
    }

}
