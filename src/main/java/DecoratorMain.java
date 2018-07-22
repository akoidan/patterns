public class DecoratorMain {
    public static void main(String[] args) {
        SugarCofee sugarCofee = new SugarCofee(new BigCoffee(new Cappuccino()));
        System.out.printf("%s costs %.2f", sugarCofee.getName(), sugarCofee.getPrice());
    }

    interface Coffee {
        double getPrice();
        String getName();
    }

    static abstract class CoffeeDecorator implements Coffee{
        protected Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
    }

    static class BigCoffee extends CoffeeDecorator {

        public BigCoffee(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double getPrice() {
            return coffee.getPrice() * 1.5;
        }

        @Override
        public String getName() {
            return "Big " +  coffee.getName();
        }
    }

    static class SugarCofee extends CoffeeDecorator {

        public SugarCofee(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double getPrice() {
            return coffee.getPrice() + 2;
        }

        @Override
        public String getName() {
            return coffee.getName()  + " with sugar";
        }
    }

    static class Cappuccino implements Coffee {

        @Override
        public double getPrice() {
            return 10;
        }

        @Override
        public String getName() {
            return "cappuccino";
        }
    }



}
