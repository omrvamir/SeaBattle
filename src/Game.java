import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    public static void startGame(Player player) {
//        Scanner scanner = new Scanner(System.in);
        File inputCoordinates1 = new File("Coordinates2.txt");

        try {
            Scanner scanner = new Scanner(inputCoordinates1);

            System.out.println("Начнем расставлять корабли на поле игрока: " + player.getName() + ". Другой игрок, не смотри!");

            while (true) {
                System.out.println("Введи координаты (1 из 1) четырёхпалубного корабля (формат: x,y;x,y;x,y;x,y)");
                try {
                    String[] fourDeckShip = scanner.nextLine().split(";");
                    if (player.checkCorrectnessFourDeckShip(fourDeckShip)) {
                        player.addShips(fourDeckShip);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }


            while (true) {
                System.out.println("Введи координаты первого (1 из 2) трёхпалубного корабля (формат: x,y;x,y;x,y)");
                String[] threeDeckShip1 = scanner.nextLine().split(";");
                if (player.checkCorrectnessThreeDeckShip(threeDeckShip1)) {
                    player.addShips(threeDeckShip1);
                    break;
                }
            }

            while (true) {
                System.out.println("Введи координаты второго (2 из 2) трёхпалубного корабля (формат: x,y;x,y;x,y)");
                String[] threeDeckShip2 = scanner.nextLine().split(";");
                if (player.checkCorrectnessThreeDeckShip(threeDeckShip2)) {
                    player.addShips(threeDeckShip2);
                    break;
                }
            }

            while (true) {
                System.out.println("Введи координаты первого (1 из 3) двухпалубного корабля (формат: x,y;x,y)");
                try {
                    String[] twoDeckShip1 = scanner.nextLine().split(";");
                    if (player.checkCorrectnessTwoDeckShip(twoDeckShip1)) {
                        player.addShips(twoDeckShip1);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты второго (2 из 3) двухпалубного корабля (формат: x,y;x,y)");
                try {
                    String[] twoDeckShip2 = scanner.nextLine().split(";");
                    if (player.checkCorrectnessTwoDeckShip(twoDeckShip2)) {
                        player.addShips(twoDeckShip2);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты третьего (3 из 3) двухпалубного корабля (формат: x,y;x,y)");
                try {
                    String[] twoDeckShip3 = scanner.nextLine().split(";");
                    if (player.checkCorrectnessTwoDeckShip(twoDeckShip3)) {
                        player.addShips(twoDeckShip3);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты первого (1 из 4) однопалубного корабля (формат: x,y)");
                try {
                    String[] singleDeckShip1 = {scanner.nextLine()};
                    if (player.checkCorrectnessSingleDeckShip(singleDeckShip1)) {
                        player.addShips(singleDeckShip1);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты второго (2 из 4) однопалубного корабля (формат: x,y)");
                try {
                    String[] singleDeckShip2 = {scanner.nextLine()};
                    if (player.checkCorrectnessSingleDeckShip(singleDeckShip2)) {
                        player.addShips(singleDeckShip2);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты третьего (3 из 4) однопалубного корабля (формат: x,y)");
                try {
                    String[] singleDeckShip3 = {scanner.nextLine()};
                    if (player.checkCorrectnessSingleDeckShip(singleDeckShip3)) {
                        player.addShips(singleDeckShip3);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            while (true) {
                System.out.println("Введи координаты четвертого (4 из 4) однопалубного корабля (формат: x,y)");
                try {
                    String[] singleDeckShip4 = {scanner.nextLine()};
                    if (player.checkCorrectnessSingleDeckShip(singleDeckShip4)) {
                        player.addShips(singleDeckShip4);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверные координаты");
                }
            }

            System.out.println("Игрок " + player.getName() + " расставил корабли на своем поле.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
