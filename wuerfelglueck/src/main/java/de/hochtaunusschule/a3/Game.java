package de.hochtaunusschule.a3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Game {
    private final Random random;
    private final Field[] fields = new Field[40];;
    private final List<Player> players;

    public Game(Random random, Dice one, Dice two) {
        this.random = random;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(this, i);
        }
        players = Arrays.asList(
            new Player(this, one, 39, 0), //blue
            new Player(this, two, 19 , 20) //yellow
        );
    }

    public Dice play() {
        int playerId = 0;
        while (true) {
            if (playerId >= 2) {
                playerId = 0;
            }
            Player player = players.get(playerId);
            if (finished(player) >= 4) {
                return player.getDice();
            }
            makeTurn(player);
            playerId++;
        }
    }

    private int finished(Player player) {
        int finished = 0;
        for (int i = 1; i <= 4; i++) {
            if (player.canFinish(i)) {
                finished++;
            }
        }
        return finished;
    }

    private void makeTurn(Player player) {
        int countDice = 0;
        boolean throwDiceAgain = true;
        while(countDice <= 3 && throwDiceAgain) {
            throwDiceAgain = false;
            int roll = player.getDice().roll(random);
            if (roll == 6) {
                throwDiceAgain = true;
            }
            if (roll == 6 && player.hasFiguresInStart()
                && (player.getStart().isEmpty() || player.getStart().notAcquiredBy(player))) {
                player.takeFigureFromStart();
                moveFigure(player.getStart(), player, null);
            } else if (player.hasFiguresInStart() && !player.getStart().isEmpty() && !player.getStart().notAcquiredBy(player)) {
                Field nextPosition = player.getStart().next(roll);
                if (nextPosition.notAcquiredBy(player)) {
                    moveFigure(nextPosition, player, player.getStart());
                } else {
                    moveBestFigure(roll, player);
                }
            } else if(playerHasNoFiguresOnFields(player) && !throwDiceAgain) {
                throwDiceAgain = true;
                countDice ++;
            } else if (!mayOverride(roll, player)) {
                moveBestFigure(roll, player);
            }
        }
    }

    private boolean mayOverride(int roll, Player player) {
        Optional<Field> best = overrideFigure(roll, player).stream()
            .min(Comparator.comparingInt(field -> player.getTarget().getIndex() - field.getIndex()));
        best.ifPresent(field -> moveFigure(field.next(roll), player, field));
        return best.isPresent();
    }

    private void moveBestFigure(int dice, Player player) {
        possibleMoves(dice, player).stream()
            .min(Comparator.comparingInt(field -> player.getTarget().getIndex() - field.getIndex()))
            .ifPresent(field -> moveFigure(field.next(dice), player, field));
    }

    public void moveFigure(Field to, Player player, Field from) {
        if (from != null) {
            from.clear();
        }
        int finishField = to.getIndex() - player.getTarget().getIndex();
        if (from != null
            && from.getIndex() <= player.getTarget().getIndex()
            && finishField > 0
            && finishField <= 4
            && !player.canFinish(finishField)) {
            player.finish(finishField);
            return;
        }
        to.aquire(player);
    }

    public Field field(int position) {
        return fields[position % 40];
    }

    public boolean playerHasNoFiguresOnFields(Player player) {
        return Arrays.stream(fields).noneMatch(p -> p.acquiredBy(player));
    }

    public List<Field> overrideFigure(int dice, Player player) {
        List<Field> figures = new ArrayList<>();
        Arrays.stream(fields).filter(p -> p.acquiredBy(player)).forEach(figures::add);
        List<Field> removeFigures = new ArrayList<>();
        for (Field figure : figures) {
            Field nextField = figure.next(dice);
            if ((figure.getIndex() <= player.getTarget().getIndex() && nextField.getIndex() > player.getTarget().getIndex())
                || !nextField.notAcquiredBy(player)) {
                removeFigures.add(figure);
            }
        }
        figures.removeAll(removeFigures);
        return figures;
    }

    public List<Field> possibleMoves(int dice, Player player) {
        List<Field> figures = new ArrayList<>();
        Arrays.stream(fields).filter(p -> p.acquiredBy(player)).forEach(figures::add);

        Set<Field> removeFigures = new HashSet<>();
        for (Field figure : figures) {
            Field nextField = figure.next(dice);
            int finishField = nextField.getIndex() - player.getTarget().getIndex();
            if (figure.getIndex() <= player.getTarget().getIndex()
                && nextField.getIndex() > player.getTarget().getIndex()
                && (finishField > 4 || player.canFinish(finishField))) {
                removeFigures.add(figure);
            } else if (nextField.acquiredBy(player)) {
                removeFigures.add(figure);
            }
        }
        figures.removeAll(removeFigures);
        return figures;
    }
}
