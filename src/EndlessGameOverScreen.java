import java.awt.*;

public class EndlessGameOverScreen extends GameOverScreen {
    private int score;

    private EndlessScores endlessScores;

    public EndlessGameOverScreen(GameWindow gameWindow, int score) {
        super(gameWindow, score);
        this.score = score;
        this.endlessScores = new EndlessScores();
        endlessScores.addScore(score);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", WIDTH / 2 - 150, 200);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, WIDTH / 2 - 150, 200 + 50);

        // draw high scores
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("High Scores:", WIDTH / 2 - 150, 250 + 30);
        g.drawString("Date | Time | Score", WIDTH / 2 - 150, 280 + 30);
        String[] dates = endlessScores.getDates();
        String[] times = endlessScores.getTimes();
        int[] scores = endlessScores.getScores();
        for (int i = 0; i < dates.length; i++) {
            g.drawString(dates[i] + " " + times[i] + " " + scores[i], WIDTH / 2 - 150, 340 + i * 30);
        }
    }
}
