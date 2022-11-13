import java.util.Scanner;

public class Play {
    public static void play(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ход игрока: " + player.getName() + ". Введите координаты (формат: x,y)");
        while(!player.getShips().isEmpty()) {
            String[] attack = scanner.nextLine().split(",");
            if (player.checkAttack(attack)) {
                break;
            }
        }
    }
}