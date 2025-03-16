package game;

import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MNKWithTabooBoard implements Board {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.F, '*'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final Settings settings;
    private int steps;
    private final int stepsForDraw;
    private final Position position;

    public MNKWithTabooBoard(Settings settings) {
        this.steps = 0;
        this.settings = settings;
        this.cells = new Cell[settings.getRows()][settings.getColumns()];
        int stepsForDraw = 0;
        for (int row = 0; row < settings.getRows(); row++) {
            for (int column = 0; column < settings.getColumns(); column++) {
                if (row == column || row == settings.getColumns() - column - 1) {
                    cells[row][column] = Cell.F;
                } else {
                    cells[row][column] = Cell.E;
                    stepsForDraw++;
                }
            }
        }
        this.stepsForDraw = stepsForDraw;
        turn = Cell.X;
        this.position = new Position() {
            @Override
            public boolean isValid(final Move move) {
                return 0 <= move.getRow() && move.getRow() < settings.getRows()
                        && 0 <= move.getColumn() && move.getColumn() < settings.getColumns()
                        && cells[move.getRow()][move.getColumn()] == Cell.E;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder(" \t");
                for (int c = 0; c < settings.getColumns(); c++) {
                    sb.append(c);
                }
                for (int r = 0; r < settings.getRows(); r++) {
                    sb.append("\n");
                    sb.append(r);
                    sb.append("\t");
                    for (int c = 0; c < settings.getColumns(); c++) {
                        sb.append(SYMBOLS.get(cells[r][c]));
                    }
                }
                return sb.toString();
            }
        };
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        this.steps++;
        int stepX = move.getRow();
        int stepY = move.getColumn();
        cells[stepX][stepY] = move.getValue();

        Result result = checkWin(stepX, stepY);
        if (result == Result.WIN) {
            return result;
        }
        if (this.steps == stepsForDraw) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public boolean isTurn(int stepX, int stepY) {
        return 0 <= stepX && stepX < settings.getRows()
                && 0 <= stepY && stepY < settings.getColumns()
                && cells[stepX][stepY] == turn;
    }

    private Result checkWin(int stepX, int stepY) {
        if (getHorWinLine(stepX, stepY) || getVertWinLine(stepX, stepY)
                || getLeftDiagWinLine(stepX, stepY) || getRightDiagWinLine(stepX, stepY)) {
            return Result.WIN;
        } else {
            return Result.UNKNOWN;
        }
    }

    private boolean getHorWinLine(int stepX, int stepY) {
        int step = 0;
        while (isTurn(stepX, stepY)) {
            stepY--;
        }
        stepY++;
        while (isTurn(stepX, stepY)) {
            step++;
            stepY++;
        }
        return step >= settings.getForWins();
    }

    private boolean getVertWinLine(int stepX, int stepY) {
        int step = 0;
        while (isTurn(stepX, stepY)) {
            stepX--;
        }
        stepX++;
        while (isTurn(stepX, stepY)) {
            step++;
            stepX++;
        }
        return step >= settings.getForWins();
    }

    private boolean getLeftDiagWinLine(int stepX, int stepY) {
        int step = 0;
        while (isTurn(stepX, stepY)) {
            stepX--;
            stepY--;
        }
        stepX++;
        stepY++;
        while (isTurn(stepX, stepY)) {
            step++;
            stepX++;
            stepY++;
        }
        return step >= settings.getForWins();
    }

    private boolean getRightDiagWinLine(int stepX, int stepY) {
        int step = 0;
        while (isTurn(stepX, stepY)) {
            stepX--;
            stepY++;
        }
        stepX++;
        stepY--;
        while (isTurn(stepX, stepY)) {
            step++;
            stepX++;
            stepY--;
        }
        return step >= settings.getForWins();
    }
}
