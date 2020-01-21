package src;

public class Code {

    private Character character;
    private String  code;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Code(Character character, String code) {
        this.character = character;
        this.code = code;
    }
}
