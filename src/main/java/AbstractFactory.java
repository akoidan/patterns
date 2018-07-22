
public class AbstractFactory {
    public static void main(String[] args) {
        UIFactory factory = getFactory();
        Button button = factory.createButton();
        InputText inputText = factory.createInputText();
        Label label = factory.createLabel();
    }

    static UIFactory getFactory() {
        String property = System.getProperty("os.name").toLowerCase();
        if (property.contains("win")) {
            return new WindowsFactory();
        } else {
            return new LinuxFactory();
        }
    }
    
    interface UIFactory {
        Button createButton();
        Label createLabel();
        InputText createInputText();
    }

    static class WindowsFactory implements UIFactory {
        public Button createButton() {
            return new WindowsButton();
        }
        public Label createLabel() {
            return new WindowsLabel();
        }
        public InputText createInputText() {
            return new WindowsInputText();
        }
    }

    static class LinuxFactory implements UIFactory {
        public Button createButton() {
            return new LinuxButton();
        }
        public Label createLabel() {
            return new LinuxLabel();
        }
        public InputText createInputText() {
            return new LinuxInputText();
        }
    }
    
    abstract static class Button {
        void click() {
            System.out.println("CLicked");
        }
    }
    static class WindowsButton extends Button { }
    static class LinuxButton extends Button { }
    
    
    abstract static class Label { 
        String text;
    }
    static class WindowsLabel extends Label { }
    static class LinuxLabel extends Label { }
    
    
    abstract static class InputText { 
        String text;
    }
    static class WindowsInputText extends InputText { }
    static class LinuxInputText extends InputText { }
    
}
