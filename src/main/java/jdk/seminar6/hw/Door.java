package jdk.seminar6.hw;

public class Door {
    private boolean isPriseBehind;

    public Door(boolean isPriseBehind) {
        this.isPriseBehind = isPriseBehind;
    }

    public boolean isPriseBehind() {
        return isPriseBehind;
    }

    public void setPriseBehind(boolean priseBehind) {
        isPriseBehind = priseBehind;
    }


    @Override
    public String toString() {
        return isPriseBehind ? "Here is CAR" : "Here is GOAT";
    }
}
