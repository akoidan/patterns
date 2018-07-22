public class ProxyPattern {
    public static void main(String[] args) {
        IProxy ip = new CheckBalancePay(10);
        ip.request(5, "jacket");
    }

    interface IProxy {
        void request(int cost, String product);
    }


    static class  PayProduct implements IProxy{
        int balance;

        public PayProduct(int balance) {
            this.balance = balance;
        }

        public void request(int cost, String product) {
            balance-= cost;
            System.out.printf("Payed %d for product %s", cost, product);
        }
    }

    static class CheckBalancePay implements IProxy {
        final PayProduct pp;

        public CheckBalancePay(int defaultMoney) {
            this.pp = new PayProduct(defaultMoney);
        }

        public void request(int cost, String product) {
            if (pp.balance < cost) {
                throw new IllegalStateException("Not enough money");
            }
            pp.request(cost, product);
        }
    }

}
