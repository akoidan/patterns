public class VisitorPattern {
    public static void main(String[] args) {
        // the main point of visitor pattern is add functionality to
        // model classes w/o having its code there
        Visitor slowEngineVisitor = new SlowEngineVisitor();
        Byke byke = new Byke();
        Car car = new Car();
        byke.accept(slowEngineVisitor);
        car.accept(slowEngineVisitor);
    }

    interface Visitor {
        void startEngine(Car c);
        void startEngine(Byke c);
    }

    static class SlowEngineVisitor implements Visitor {

        @Override
        public void startEngine(Car c) {
            // some heavy logic here, which we don't want to have in model
            System.out.printf("Slowly starting %s car \n", c);
        }

        @Override
        public void startEngine(Byke c) {
            // some heavy logic here, which we don't want to have in model
            System.out.printf("Slowly starting %s byke \n", c);
        }
    }

    static class Car {
        void accept(Visitor v) {
            v.startEngine(this);
        }

        @Override
        public String toString() {
            return "awesome";
        }
    }

    static class Byke {
        void accept(Visitor v) {
            v.startEngine(this);
        }
        @Override
        public String toString() {
            return "cool";
        }
    }
}
