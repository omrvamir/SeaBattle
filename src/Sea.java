import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sea {
    private final String[][] sea;
    private String[][] seaGame;
    private List<String[]> ships;

    public Sea() {
        this.sea = new String[10][10];
        this.seaGame = new String[10][10];
        this.ships = new ArrayList<String[]>();
        createSea();
    }

    public void createSea() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sea[i][j] = Cell.getFreeCell();
                seaGame[i][j] = Cell.getFreeCell();
            }
        }
    }

    public boolean placementShip(String[] coordinates) { // расстановка кораблей
        if (!checkBorder(coordinates)) {
            System.out.println("Вы ввели неверные координаты (1 - 10)");
            return false;
        } else if (!checkShipInShip(coordinates)) {
            System.out.println("Ваш корабль находится на границе другого корабля");
            return false;
        } else {
            for (String coordinate : coordinates) {
                String[] cellCoordinate = coordinate.split(",");
                int firstCoordinate = Integer.parseInt(cellCoordinate[0]) - 1;
                int secondCoordinate = Integer.parseInt(cellCoordinate[1]) - 1;
                sea[firstCoordinate][secondCoordinate] = Cell.getShipCell(); // замена пустой ячейки на корабль
                placementHalo(firstCoordinate, secondCoordinate);
            }
            printSea();
            return true;
        }
    }

    public void placementHalo(int firstCoordinate, int secondCoordinate) { // создание ореола (границы) корабля
        if (firstCoordinate >= 1 && secondCoordinate >= 1 && firstCoordinate <= 8 && secondCoordinate <= 8) { // если
            // ячейка
            // не на
            // краю
            for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                    if (sea[i][j].equals(Cell.getShipCell())) // если в ячейке корабль:
                        continue; // пропустить
                    else // иначе
                        sea[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                }
            }
        } else if (firstCoordinate == 0 && secondCoordinate == 0) { // если ячейка у левом верхнем углу
            if (!sea[1][0].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[1][0] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[0][1].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[0][1] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[1][1].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[1][1] = Cell.getHaloCell(); // заменить ячейку на ореол
        } else if (firstCoordinate == 0 && secondCoordinate == 9) { // если ячейка у правом верхнем углу
            if (!sea[0][8].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[0][8] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[1][9].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[1][9] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[1][8].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[1][8] = Cell.getHaloCell(); // заменить ячейку на ореол
        } else if (firstCoordinate == 9 && secondCoordinate == 0) { // если ячейка у левом нижнем углу
            if (!sea[8][0].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[8][0] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[9][1].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[9][1] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[8][1].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[8][1] = Cell.getHaloCell(); // заменить ячейку на ореол
        } else if (firstCoordinate == 9 && secondCoordinate == 9) { // если ячейка у правом нижнем углу
            if (!sea[8][9].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[8][9] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[9][8].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[9][8] = Cell.getHaloCell(); // заменить ячейку на ореол
            if (!sea[8][8].equals(Cell.getShipCell())) // если в ячейке нет корабля:
                sea[8][8] = Cell.getHaloCell(); // заменить ячейку на ореол
        } else if (firstCoordinate >= 1 && secondCoordinate == 0) { // если ячейка в левом краю
            for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                for (int j = secondCoordinate; j < secondCoordinate + 2; j++) {
                    if (sea[i][j].equals(Cell.getShipCell())) // если в ячейке корабль:
                        continue; // пропустить
                    else // иначе
                        sea[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                }
            }
        } else if (firstCoordinate == 0 && secondCoordinate >= 1) { // если ячейка в верхнем краю
            for (int i = firstCoordinate; i < firstCoordinate + 2; i++) {
                for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                    if (sea[i][j].equals(Cell.getShipCell())) // если в ячейке корабль:
                        continue; // пропустить
                    else // иначе
                        sea[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                }
            }
        } else if (firstCoordinate >= 1 && secondCoordinate == 9) { // если ячейка в правом краю
            for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                for (int j = secondCoordinate - 1; j < secondCoordinate + 1; j++) {
                    if (sea[i][j].equals(Cell.getShipCell())) // если в ячейке корабль:
                        continue; // пропустить
                    else // иначе
                        sea[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                }
            }
        } else if (firstCoordinate == 9 && secondCoordinate >= 1) { // если ячейка в нижнем краю
            for (int i = firstCoordinate - 1; i < firstCoordinate + 1; i++) {
                for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                    if (sea[i][j].equals(Cell.getShipCell())) // если в ячейке корабль:
                        continue; // пропустить
                    else // иначе
                        sea[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                }
            }
        }
    }

    public boolean checkBorder(String[] coordinates) { // проверка координат за границами
        for (String coordinate : coordinates) {
            String[] cellCoordinate = coordinate.split(",");
            int firstCoordinate = Integer.parseInt(cellCoordinate[0]);
            int secondCoordinate = Integer.parseInt(cellCoordinate[1]);
            if (firstCoordinate > 10 || firstCoordinate < 1 || secondCoordinate > 10 || secondCoordinate < 1)
                return false;
        }
        return true;
    }

    public boolean checkShipInShip(String[] coordinates) { // проверка корабля в корабле (ореоле)
        for (String coordinate : coordinates) {
            String[] cellCoordinate = coordinate.split(",");
            int firstCoordinate = Integer.parseInt(cellCoordinate[0]) - 1;
            int secondCoordinate = Integer.parseInt(cellCoordinate[1]) - 1;
            if (!sea[firstCoordinate][secondCoordinate].equals(Cell.getFreeCell())) {
                System.out.println((firstCoordinate + 1) + ", " + (secondCoordinate + 1));
                return false;
            }
        }
        return true;
    }

    public boolean checkCorrectnessFourDeckShip(String[] coordinates) { // проверка на ровность четырехпалубного корабля
        if (coordinates.length != 4) {
            System.out.println("Вы ввели не 4 координаты");
            return false;
        }

        String[] firstCoordinate = coordinates[0].split(",");
        String[] secondCoordinate = coordinates[1].split(",");
        String[] thirdCoordinate = coordinates[2].split(",");
        String[] fourthCoordinate = coordinates[3].split(",");
        int firstFirst = Integer.parseInt(firstCoordinate[0]); // [X,_;_,_;_,_;_,_] первая координата первой ячейки
        int firstSecond = Integer.parseInt(firstCoordinate[1]); // [_,X;_,_;_,_;_,_] вторая координата первой ячейки
        int secondFirst = Integer.parseInt(secondCoordinate[0]); // [_,_;X,_;_,_;_,_] первая координата второй ячейки
        int secondSecond = Integer.parseInt(secondCoordinate[1]); // [_,_;_,X;_,_;_,_] вторая координата второй ячейки
        int thirdFirst = Integer.parseInt(thirdCoordinate[0]); // [_,_;_,_;X,_;_,_] первая координата третьей ячейки
        int thirdSecond = Integer.parseInt(thirdCoordinate[1]); // [_,_;_,_;_,X;_,_] вторая координата третьей ячейки
        int fourthFirst = Integer.parseInt(fourthCoordinate[0]); // [_,_;_,_;_,_;X,_] первая координата четвертой ячейки
        int fourthSecond = Integer.parseInt(fourthCoordinate[1]); // [_,_;_,_;_,_;_,X] вторая координата четвертой
        // ячейки

        int[] firsts = { firstFirst, secondFirst, thirdFirst, fourthFirst };
        int[] seconds = { firstSecond, secondSecond, thirdSecond, fourthSecond };

        if (coordinates[0].equals(coordinates[1]) || coordinates[0].equals(coordinates[2])
                || coordinates[0].equals(coordinates[3])
                || coordinates[1].equals(coordinates[2]) || coordinates[1].equals(coordinates[3])
                || coordinates[2].equals(coordinates[3])) {
            System.out.println("Несколько раз в одну и ту же ячейку");
            return false;
        }
        // else if (checkSize(firsts, seconds, 4))
        // return false;
        else if (firstSecond == secondSecond && secondSecond == thirdSecond && thirdSecond == fourthSecond) {
            if (Math.abs(firstFirst - secondFirst) != 1 || Math.abs(firstFirst - thirdFirst) != 1
                    || Math.abs(firstFirst - fourthFirst) != 1
                    || Math.abs(secondFirst - thirdFirst) != 1 || Math.abs(secondFirst - fourthFirst) != 1
                    || Math.abs(thirdFirst - fourthFirst) != 1) {
            } else {
                System.out.println("Вы ввели неверные координаты");
                return false;
            }
        } else if (firstFirst == secondFirst && secondFirst == thirdFirst && thirdFirst == fourthFirst) {
            if (Math.abs(firstSecond - secondSecond) != 1 || Math.abs(firstSecond - thirdSecond) != 1
                    || Math.abs(firstSecond - fourthSecond) != 1
                    || Math.abs(secondSecond - thirdSecond) != 1 || Math.abs(secondSecond - fourthSecond) != 1
                    || Math.abs(thirdSecond - fourthSecond) != 1) {
            } else {
                return false;
            }
        } else {
            System.out.println("Вы ввели неверные координаты");
            return false;
        }

        return placementShip(coordinates);
    }

    public boolean checkCorrectnessThreeDeckShip(String[] coordinates) { // проверка на ровность трехпалубного корабля
        if (coordinates.length != 3) {
            System.out.println("Вы ввели не 3 координаты");
            return false;
        }

        String[] firstCoordinate = coordinates[0].split(",");
        String[] secondCoordinate = coordinates[1].split(",");
        String[] thirdCoordinate = coordinates[2].split(",");
        int firstFirst = Integer.parseInt(firstCoordinate[0]); // [X,_;_,_;_,_] первая координата первой ячейки
        int firstSecond = Integer.parseInt(firstCoordinate[1]); // [_,X;_,_;_,_] вторая координата первой ячейки
        int secondFirst = Integer.parseInt(secondCoordinate[0]); // [_,_;X,_;_,_] первая координата второй ячейки
        int secondSecond = Integer.parseInt(secondCoordinate[1]); // [_,_;_,X;_,_] вторая координата второй ячейки
        int thirdFirst = Integer.parseInt(thirdCoordinate[0]); // [_,_;_,_;X,_] первая координата второй ячейки
        int thirdSecond = Integer.parseInt(thirdCoordinate[1]); // [_,_;_,_;_,X] вторая координата второй ячейки

        if (coordinates[0].equals(coordinates[1]) || coordinates[1].equals(coordinates[2])
                || coordinates[2].equals(coordinates[0])) {
            System.out.println("Несколько раз в одну и ту же ячейку");
            return false;
        } else if (firstFirst == secondFirst && secondFirst == thirdFirst) {
            if (Math.abs(firstSecond - secondSecond) != 1 || Math.abs(firstSecond - thirdSecond) != 1
                    || Math.abs(secondSecond - thirdSecond) != 1) {
            } else {
                System.out.println("Вы ввели неверные координаты (1)");
                return false;
            }
        } else if (firstSecond == secondSecond && secondSecond == thirdSecond) {
            if (Math.abs(firstFirst - secondFirst) != 1 || Math.abs(firstFirst - thirdFirst) != 1
                    || Math.abs(secondFirst - thirdFirst) != 1) {
            } else {
                System.out.println("Вы ввели неверные координаты (2)");
                return false;
            }
        }

        if (!placementShip(coordinates))
            return false;

        return true;
    }

    public boolean checkCorrectnessTwoDeckShip(String[] coordinates) { // проверка на ровность двухпалобного корабля
        if (coordinates.length != 2) {
            System.out.println("Вы ввели не 2 координаты");
            return false;
        }

        String[] firstCoordinate = coordinates[0].split(",");
        String[] secondCoordinate = coordinates[1].split(",");
        int firstFirst = Integer.parseInt(firstCoordinate[0]); // [X,_;_,_] первая координата первой ячейки
        int firstSecond = Integer.parseInt(firstCoordinate[1]); // [_,X;_,_] вторая координата первой ячейки
        int secondFirst = Integer.parseInt(secondCoordinate[0]); // [_,_;X,_] первая координата второй ячейки
        int secondSecond = Integer.parseInt(secondCoordinate[1]); // [_,_;_,X] вторая координата второй ячейки

        if (coordinates[0].equals(coordinates[1])) {
            System.out.println("Несколько раз в одну и ту же ячейку");
            return false;
        } else if (firstFirst != secondFirst && Math.abs(firstFirst - secondFirst) != 1) {
            System.out.println("Ячейка не прилегает к кораблю (первая координата)");
            return false;
        } else if (firstSecond != secondSecond && Math.abs(firstSecond - secondSecond) != 1) {
            System.out.println("Ячейка не прилегает к кораблю (вторая координата)");
            return false;
        }

        placementShip(coordinates);

        return true;
    }

    public boolean checkCorrectnessSingleDeckShip(String[] coordinates) { // проверка на ровность однопалобного корабля
        if (coordinates.length != 1) {
            System.out.println("Вы ввели не 1 координату");
            return false;
        }

        return placementShip(coordinates);
    }

    public boolean checkAttack(String[] attack) {
        try {
            int first = Integer.parseInt(attack[0]) - 1;
            int second = Integer.parseInt(attack[1]) - 1;

            if (attack.length != 2 || first > 9 || second > 9 || first < 0 || second < 0) {
                System.out.println("Вы ввели неверные координаты, введите заново");
                return false;
            }

            if (!seaGame[first][second].equals(Cell.getFreeCell())) {
                System.out.println("Сюда уже стреляли, введите заново");
                printSeaGame();
                return false;
            } else if (sea[first][second].equals(Cell.getShipCell())) {
                seaGame[first][second] = Cell.getDamagedCell();
                checkShipsAlive();
                System.out.println("Попал!, ход продолжается");
                printSeaGame();
                return false;
            } else if (!sea[first][second].equals(Cell.getShipCell())) {
                System.out.println("Мимо!, ход переходит следующему игроку");
                seaGame[first][second] = Cell.getHaloCell();
                printSeaGame();
                return true;
            }

            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Вы ввели неверные координаты, повторите попытку (формат: x,y)");
        }

        return false;
    }

    public void checkShipsAlive() {
        for (String[] ship : ships) {
            System.out.println(Arrays.toString(ship));
            boolean isAlive = true;
            for (String sh : ship) {
                String[] attackCoordinate = sh.split(",");

                int first = Integer.parseInt(attackCoordinate[0]) - 1; // [X,_]
                int second = Integer.parseInt(attackCoordinate[1]) - 1; // [_,X]
                if (!seaGame[first][second].equals(Cell.getDamagedCell())) {
                    isAlive = false;
                    break;
                }
            }
            if (isAlive) { // если корабль подбит
                ships.remove(ship); // удалить из массива кораблей
                System.out.println("Вы потопили корабль!");
                placementHalo(ship);
                break;
            }
        }
    }

    private void placementHalo(String[] ship) {
        for (String sh : ship) {
            String[] attackCoordinate = sh.split(",");

            int firstCoordinate = Integer.parseInt(attackCoordinate[0]) - 1; // [X,_]
            int secondCoordinate = Integer.parseInt(attackCoordinate[1]) - 1; // [_,X]

            if (firstCoordinate >= 1 && secondCoordinate >= 1 && firstCoordinate <= 8 && secondCoordinate <= 8) { // если ячейка не на краю
                for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                    for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                        if (seaGame[i][j].equals(Cell.getDamagedCell())) // если в ячейке корабль:
                            continue; // пропустить
                        else // иначе
                            seaGame[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                    }
                }
            } else if (firstCoordinate == 0 && secondCoordinate == 0) { // если ячейка у левом верхнем углу
                if (!seaGame[1][0].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[1][0] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[0][1].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[0][1] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[1][1].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[1][1] = Cell.getHaloCell(); // заменить ячейку на ореол
            } else if (firstCoordinate == 0 && secondCoordinate == 9) { // если ячейка у правом верхнем углу
                if (!seaGame[0][8].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[0][8] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[1][9].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[1][9] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[1][8].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[1][8] = Cell.getHaloCell(); // заменить ячейку на ореол
            } else if (firstCoordinate == 9 && secondCoordinate == 0) { // если ячейка у левом нижнем углу
                if (!seaGame[8][0].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[8][0] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[9][1].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[9][1] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[8][1].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[8][1] = Cell.getHaloCell(); // заменить ячейку на ореол
            } else if (firstCoordinate == 9 && secondCoordinate == 9) { // если ячейка у правом нижнем углу
                if (!seaGame[8][9].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[8][9] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[9][8].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[9][8] = Cell.getHaloCell(); // заменить ячейку на ореол
                if (!seaGame[8][8].equals(Cell.getDamagedCell())) // если в ячейке нет корабля:
                    seaGame[8][8] = Cell.getHaloCell(); // заменить ячейку на ореол
            } else if (firstCoordinate >= 1 && secondCoordinate == 0) { // если ячейка в левом краю
                for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                    for (int j = secondCoordinate; j < secondCoordinate + 2; j++) {
                        if (seaGame[i][j].equals(Cell.getDamagedCell())) // если в ячейке корабль:
                            continue; // пропустить
                        else // иначе
                            seaGame[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                    }
                }
            } else if (firstCoordinate == 0 && secondCoordinate >= 1) { // если ячейка в верхнем краю
                for (int i = firstCoordinate; i < firstCoordinate + 2; i++) {
                    for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                        if (seaGame[i][j].equals(Cell.getDamagedCell())) // если в ячейке корабль:
                            continue; // пропустить
                        else // иначе
                            seaGame[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                    }
                }
            } else if (firstCoordinate >= 1 && secondCoordinate == 9) { // если ячейка в правом краю
                for (int i = firstCoordinate - 1; i < firstCoordinate + 2; i++) {
                    for (int j = secondCoordinate - 1; j < secondCoordinate + 1; j++) {
                        if (seaGame[i][j].equals(Cell.getDamagedCell())) // если в ячейке корабль:
                            continue; // пропустить
                        else // иначе
                            seaGame[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                    }
                }
            } else if (firstCoordinate == 9 && secondCoordinate >= 1) { // если ячейка в нижнем краю
                for (int i = firstCoordinate - 1; i < firstCoordinate + 1; i++) {
                    for (int j = secondCoordinate - 1; j < secondCoordinate + 2; j++) {
                        if (seaGame[i][j].equals(Cell.getDamagedCell())) // если в ячейке корабль:
                            continue; // пропустить
                        else // иначе
                            seaGame[i][j] = Cell.getHaloCell(); // заменить ячейку на ореол
                    }
                }
            }
        }
    }

    public void addShips(String[] ship) {
        ships.add(ship);
    }

    public void printSea() { // отображение моря расстановки
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(sea[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printSeaGame() { // отображение моря игры
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(seaGame[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public List<String[]> getShips() { // delete
        return ships;
    }
}