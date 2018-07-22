import java.util.Random;

public class FactoryMain {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {

        }
    }

    static class Asteroid {
        double size;
        int x;
        int y;
        double speed;

        public Asteroid(double size, int x, int y, int speed) {
            this.size = size;
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

    }

    class AsteroidFactory {

        int difficulty;
        Random r = new Random();

        public AsteroidFactory(int difficulty) {
            this.difficulty = difficulty;
        }

        Asteroid createAsteroid()  {
            double size = r.nextInt(10)* difficulty / 3;
            return new Asteroid(size, r.nextInt(2), r.nextInt(100), r.nextInt(100));
        }
    }
}
