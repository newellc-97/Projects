public enum Step {
    UP("U"), DOWN("D"), LEFT("L"), RIGHT("R");

    private final String letter;

    Step(String letter) {
	this.letter = letter;
    }

    public String getLetter() {
	return this.letter;
    }
}

