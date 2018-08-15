import java.util.Map;

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        LoginApi la = new LoginApi();
        BuyProductApi b= new BuyProductApi();
        AddToStash bw= new AddToStash();
        la.setNext(b);
        b.setNext(bw);
        la.execute(new Request("login"));
        la.execute(new Request("buy/car"));
    }


    static class Request {

        public Request(String path) {
            this.path = path;
        }

        String path;
        private Map<String, String> params;
    }

    interface Chain {
        void execute(Request r);
    }

    static abstract class BaseApi implements Chain {
        private Chain next;

        abstract boolean matches(Request R);

        abstract void performAction(Request r);

        @Override
        public void execute(Request r) {
            if (this.matches(r)) {
              this.performAction(r);
            } else if (next != null) {
                next.execute(r);
            } else {
                throw new IllegalStateException("No handlers for request" + r);
            }
        }

        public void setNext(Chain next) {
            this.next = next;
        }
    }

    static class LoginApi extends BaseApi {
        @Override
        boolean matches(Request r) {
            return r.path.equals("login");
        }

        @Override
        void performAction(Request r) {
            // logic here
            System.out.println("Logging in...");
        }

    }

    static class BuyProductApi extends BaseApi {
        @Override
        boolean matches(Request r) {
            return r.path.indexOf("buy/") == 0;
        }

        @Override
        void performAction(Request r) {
            System.out.printf("Buying product here ...%s ", r.path);

        }
    }

    static class AddToStash extends BaseApi {
        @Override
        boolean matches(Request r) {
            return "stash".equals(r.path);
        }

        @Override
        void performAction(Request r) {
            System.out.println("Putting item to stash  product here ...");

        }
    }
}
