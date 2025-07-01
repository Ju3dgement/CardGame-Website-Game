package mainPackage;

public class QCard extends EventCard {
    private final int stages;

    public QCard(int stages) {
        super("Q" + stages);
        this.stages = stages;
    }

    public int getStages() {
        return stages;
    }
}
