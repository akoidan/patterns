public class TemplateMethodPattern {
    public static void main(String[] args) {
        // template pattern is widely used by lots of frameworks
        // like lifecycle hooks in Vue/React/android/orms like django/sequalize
        // the idea that we have some methods in commons class that get called
        // if when we need to, and we override them in child class to put some logic
        // like in sequalize  we can add hook beforeSave and normalize object data there
        // e.g. trim values
        Vue vue = new MainComponent();
        vue.runHooks();
    }

    static class MainComponent extends Vue {
        @Override
        void created() {

            System.out.println("Main component created");
        }
    }
    static abstract class Vue {


        void beforeCreate() {

        }
        void created() {

        }

        void beforeMount() {

        }

        void runHooks() {
            beforeCreate();
            created();
            beforeMount();
            mount();
            updated();
            beforeDestroy();
            destroyed();
        }

        void mount() {

        }

        void updated() {

        }

        void beforeDestroy() {

        }

        void destroyed() {

        }
    }
}
