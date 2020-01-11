package src;

public class Sign {

  private Character character;

    private   Double probability;

    private   Double unitOfInformation;

    private  Integer occurrences;


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Double getUnitOfInformation() {
        return unitOfInformation;
    }

    public void setUnitOfInformation(Double unitOfInformation) {
        this.unitOfInformation = unitOfInformation;
    }

    public Integer getOccurences() {
        return occurrences;
    }

    public void setOccurences(Integer occurences) {
        this.occurrences = occurences;
    }



    @Override
    public String toString() {
        return "src.Sign{" +
                "character=" + character +
                ", probability=" + probability +
                ", unitOfInformation=" + unitOfInformation +
                ", occurences=" + occurrences +
                '}';
    }
}
