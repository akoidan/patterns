public class CommandPattern {
    public static void main(String[] args) {
        GoHideoutCommand hideout = new GoHideoutCommand(new Keyboard());
        SwapGemsCommand swapGems = new SwapGemsCommand(new Mouse());
        Program program = new Program(hideout, swapGems);
        program.onF1Pressed();
        program.onF2Pressed();

        System.out.println("\nSwitching keybinding\n");
        program.setF1(swapGems);
        program.setF2(hideout);

        program.onF1Pressed();
        program.onF2Pressed();



    }

    interface AutoHotKeyCommand {
        void execute();
    }
    
    static class Program {
        AutoHotKeyCommand f1;
        AutoHotKeyCommand f2;

        public void setF1(AutoHotKeyCommand f1) {
            this.f1 = f1;
        }

        public void setF2(AutoHotKeyCommand f2) {
            this.f2 = f2;
        }


        public Program(AutoHotKeyCommand f1, AutoHotKeyCommand f2) {
            this.f1 = f1;
            this.f2 = f2;
        }

        public void onF1Pressed() {
            System.out.println("\nPressed f1");
            f1.execute();
        }
        public void onF2Pressed() {
            System.out.println("\nPressed f2");
            f2.execute();
        }

    }

    static class GoHideoutCommand implements AutoHotKeyCommand {

        private final Keyboard kb;

        public GoHideoutCommand(Keyboard kb) {
            this.kb = kb;
        }

        @Override
        public void execute() {
            System.out.println("Running hideout");
            kb.type("/hideout");
        }
    }

    static class SwapGemsCommand implements AutoHotKeyCommand {

        private final Mouse mm;

        public SwapGemsCommand(Mouse mm) {
            this.mm = mm;
        }

        @Override
        public void execute() {
            System.out.println("Running swap gems");
            mm.move(1,2);
            mm.click();
            mm.move(2,3);
            mm.click();
            mm.move(1,2);
            mm.click();
        }
    }

    static class Mouse {
        int x,y;
        public void move(int x, int y) {
            this.x = x;
            this.y =y;
            System.out.printf("Moved mouse to %d,%d \n", x,y);
        }

        public void click() {
            System.out.printf("Clicked on %d,%d \n", x,y);
        }
    }

    static class Keyboard {

        public void type(String text) {
            System.out.printf("Typed %s \n", text);
        }

    }
}
