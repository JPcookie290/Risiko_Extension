public class WinCondition {
    private final String text;
    private final int number;
    private boolean conditionMet;
    private String board;

    public WinCondition(String text, int number, String board){
        this.text = text;
        this.number = number;
        this.conditionMet = false;
        this.board = board;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    public boolean getConditionMet() {
        return conditionMet;
    }

    public void setConditionMet() {
        this.conditionMet = !conditionMet;
    }

    public String getBoard() {
        return board;
    }
}
