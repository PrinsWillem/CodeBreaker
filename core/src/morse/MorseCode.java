package morse;

import java.util.*;

public class MorseCode {
    private HashMap<String, String> letterToMorse, morseToLetter = new HashMap<>();

    public MorseCode() {
        this.letterToMorse = new HashMap<>();
        letterToMorse.put("a", ".-");
        letterToMorse.put("b", "-...");
        letterToMorse.put("c", "-.-");
        letterToMorse.put("d", "-..");
        letterToMorse.put("e", ".");
        letterToMorse.put("f", "..-.");
        letterToMorse.put("g", "--.");
        letterToMorse.put("h", "....");
        letterToMorse.put("i", "..");
        letterToMorse.put("j", ".---");
        letterToMorse.put("k", "-.");
        letterToMorse.put("l", ".-..");
        letterToMorse.put("m", "--");
        letterToMorse.put("n", "-.");
        letterToMorse.put("o", "---");
        letterToMorse.put("p", ".--.");
        letterToMorse.put("q", "--.-");
        letterToMorse.put("r", ".-.");
        letterToMorse.put("s", "...");
        letterToMorse.put("t", "-");
        letterToMorse.put("u", "..-");
        letterToMorse.put("v", "...-");
        letterToMorse.put("w", ".--");
        letterToMorse.put("x", "-..-");
        letterToMorse.put("y", "-.--");
        letterToMorse.put("z", "--..");

        List<Object> values = Arrays.asList(letterToMorse.values().toArray());
        List<Object> keys = Arrays.asList(letterToMorse.keySet().toArray());
        for(int i = 0; i < values.size(); i++) {
            morseToLetter.put(values.get(i).toString(), keys.get(i).toString());
        }
    }

    public String getOneMorseStringTranslated(String code) {
        return morseToLetter.get(code);
    }

    public String getMorseWordTranslation(ArrayList<String> code) {
        ArrayList<String> translatedCode = new ArrayList<>();
        String arrayToString = code.toString()
                .replace(", ", "")
                .replace(", ", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
        for(String codeString : arrayToString.split(" ")) {
            translatedCode.add(this.getOneMorseStringTranslated(codeString));
        }
        String translationString = String.join(",", translatedCode);
        return translationString.replaceAll(",", "").replace("null", " ");
    }

}
