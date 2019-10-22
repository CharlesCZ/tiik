public class Sign {

   private Character character;

    private  Double probability;

    private  Double unitOfInformation;

    private Integer occurences;

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
        return occurences;
    }

    public void setOccurences(Integer occurences) {
        this.occurences = occurences;
    }



    @Override
    public String toString() {
        return "Sign{" +
                "character=" + character +
                ", probability=" + probability +
                ", unitOfInformation=" + unitOfInformation +
                ", occurences=" + occurences +
                '}';
    }
}
