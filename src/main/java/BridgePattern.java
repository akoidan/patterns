public class BridgePattern {
    public static void main(String[] args) {
        //good code
        Form userSaveForm1 = new UserForm(new SaveAction());
        userSaveForm1.performAction();

        //bad code
        UserSaveForm userSaveForm2 = new UserSaveForm();
        userSaveForm2.save();
    }


    // bad code, cartesian product of each = 9 classes
    static class UserSaveForm { void save() { System.out.println("user saved"); }}
    static class UserShowForm { void show() { System.out.println("user shown"); }}
    static class UserEditForm { void edit() { System.out.println("user edited"); }}
    static class MessageShowForm { void show() { System.out.println("message shown"); }}
    static class MessageSaveForm { void save() { System.out.println("messages saved"); }}
    static class MessageEditForm { void edit() { System.out.println("message edited"); }}
    static class ItemEditForm { void edit() { System.out.println("item edited"); }}
    static class ItemSaveForm { void save() { System.out.println("item saved"); }}
    static class ItemShowForm { void show() { System.out.println("item shown"); }}

    // Good code
    abstract static class Form {
        protected final Action action;

        Form(Action a) {
            this.action = a;
        }

        public abstract void performAction();
    }


    static class UserForm extends Form {

        UserForm(Action a) {
            super(a);
        }

        @Override
        public void performAction() {
            System.out.printf("user %s\n", action.execute());
        }
    }

    static class MessageForm extends Form {

        MessageForm(Action a) {
            super(a);
        }

        @Override
        public void performAction() {
            System.out.printf("message %s\n", action.execute());
        }
    }


    static class ItemForm extends Form {

        ItemForm(Action a) {
            super(a);
        }


        @Override
        public void performAction() {
            System.out.printf("item %s\n", action.execute());
        }
    }

    interface Action { String execute();}

    static class SaveAction implements Action{
        public String execute() {
            return "saved";
        }
    }

    static class EditAction implements Action{
        public String execute() {
            return "edited";
        }
    }

    static class ShowAction implements Action{
        public String execute() {
            return "shown";
        }
    }
}
