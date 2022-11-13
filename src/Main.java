import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя первого игрока: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Введите имя второго игрока: ");
        Player player2 = new Player(scanner.nextLine());

        Random random = new Random();

        int firstPlayer = random.nextInt(2);
        if (firstPlayer == 0) {
            Game.startGame(player1);
            Game.startGame(player2);

            while(true) {
                if (!player1.getShips().isEmpty()) {
                    Play.play(player1);
                }
                if (player1.getShips().isEmpty()) {
                    System.out.println("Победил игрок: " + player1.getName());
                    break;
                }
                if (!player2.getShips().isEmpty()) {
                    Play.play(player2);
                }
                if (player2.getShips().isEmpty()) {
                    System.out.println("Победил игрок: " + player2.getName());
                    break;
                }
            }
        } else if (firstPlayer == 1) {
            Game.startGame(player2);
            Game.startGame(player1);

            while(true) {
                if (!player2.getShips().isEmpty()) {
                    Play.play(player2);
                }
                if (player2.getShips().isEmpty()) {
                    System.out.println("Победил игрок: " + player2.getName());
                    break;
                }
                if (!player1.getShips().isEmpty()) {
                    Play.play(player1);
                }
                if (player1.getShips().isEmpty()) {
                    System.out.println("Победил игрок: " + player1.getName());
                    break;
                }
            }
        }


    }
}
