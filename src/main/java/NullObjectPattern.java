public class NullObjectPattern {
    public static void main(String[] args) {

    }

    static void sendAnimal(Animal a) {
        a.speak();
    }

    abstract static class Animal {
        abstract void speak ();
    }

    static class Dog extends Animal {
        void speak () {
            System.out.println("Bark-bark!!!");
        }
    }

    static class NullAnimal {
        void speak() {

        }
    }
}
