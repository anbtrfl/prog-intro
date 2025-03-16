package game;

public class TournamentTable {

    private final int[][] pointTable;
    private final int[][] gameTable;
    private final int amountPlayers;
    private int[] nowInGame;


    public TournamentTable(int amountPlayers) {
        this.amountPlayers = amountPlayers;
        this.pointTable = new int[amountPlayers][amountPlayers];
        this.gameTable = new int[amountPlayers][amountPlayers];
        this.nowInGame = new int[]{0, 0};
        for (int i = 0; i < amountPlayers; i++) {
            for (int j = 0; j < amountPlayers; j++) {
                pointTable[i][j] = 0;
                if (i == j) {
                    gameTable[i][j] = 0;
                } else {
                    gameTable[i][j] = 1;
                }
            }
        }
    }

    public void writeWin(int result) {
        int firstPlayer = nowInGame[0];
        int secondPlayer = nowInGame[1];
        if (result == 1) {
            pointTable[firstPlayer][secondPlayer] += 3;
        } else if (result == 2) {
            pointTable[secondPlayer][firstPlayer] += 3;
        } else {
            pointTable[firstPlayer][secondPlayer] += 1;
            pointTable[secondPlayer][firstPlayer] += 1;
        }
    }

    public int[] getNextPair() {
        for (int i = 0; i < amountPlayers; i++) {
            for (int j = 0; j < amountPlayers; j++) {
                if (gameTable[i][j] > 0) {
                    gameTable[i][j] = 0;
                    nowInGame = new int[]{i, j};
                    return nowInGame;
                }
            }
        }
        nowInGame = new int[]{1, 1};
        return nowInGame;
    }

    public String showResultTable() {
        final StringBuilder sb = new StringBuilder(" |");
        for (int c = 0; c < amountPlayers; c++) {
            sb.append(c + 1);
        }
        sb.append("\n");
        sb.append("-".repeat(amountPlayers + 2));
        for (int r = 0; r < amountPlayers; r++) {
            sb.append("\n");
            sb.append(r + 1);
            sb.append("|");
            for (int c = 0; c < amountPlayers; c++) {
                sb.append(pointTable[r][c]);
            }
        }
        return sb.toString();
    }
}