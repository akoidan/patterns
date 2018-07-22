import java.util.ArrayList;
import java.util.List;

public class FascadePattern {
    public static void main(String[] args) {
        // bad if user need every time to do that
//        Container container = new Container();
//        Button button = new Button();
//        Label label = new Label();
//        container.children.add(label);
//        container.children.add(button);
//        label.text = "labl";

        // Good if we can wrap it
        UIFascade uiFascade = new UIFascade();
        Container container = uiFascade.setupUI();

    }


    interface Element {};

    private static class Button implements Element{
        private void click() {

        }

    }
    private static class Label implements Element{
        private String text;

    }
    private static class Container{
        List<Element> children = new ArrayList<>();
    }

    private static class UIFascade {

        public Container setupUI () {
            Container container = new Container();
            Button button = new Button();
            Label label = new Label();
            container.children.add(label);
            container.children.add(button);
            label.text = "labl";
            return container;
        }
    }
}
