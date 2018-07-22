import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class CompositePattern {

// doesn't belong to patttern
    static PrintStream out;

    @FunctionalInterface
    interface WithHTml {
        void run();
    }

    private static void withHtml(WithHTml body) throws Exception{
        File myFile = new File("data.html");
        out = new PrintStream(new FileOutputStream(myFile));
        CompositePattern.out.printf("<!DOCTYPE html><html lang=\"en\"><head>   <meta charset=\"UTF-8\">   <title>Title</title></head><body>");
        body.run();
        CompositePattern.out.printf("</body></html>");
        out.flush();
    }
//end doesn't belong to patttern


    public static void main(String[] args) throws Exception {
        // composite pattern allows us to hierarchically traverse via objects that can be either leaf or node
        // e.g. todo list, each todo can be complex or simple
        withHtml(() -> {
           TodoList todoList = new TodoList(Arrays.asList(
                   new SingleTodo("Chose the best vacancy"),
                   new TodoList(Arrays.asList(
                           new TodoList(Arrays.asList(
                                   new SingleTodo("Study language syntax"),
                                   new SingleTodo("Study best practices")
                           ), "Learn programming"),
                           new SingleTodo("Pass the interview")
                   ), "Find job")
           ), "Earn money");
           todoList.print();;
       });
    }



    private static class TodoList implements Todo{
        List<Todo> todos;
        String text;

        public TodoList(List<Todo> todos, String text) {
            this.todos = todos;
            this.text = text;
        }

        @Override
        public void print() {
            CompositePattern.out.printf("<ul>");
            CompositePattern.out.printf("<b>"+text+"</b>");
            for (Todo todo : this.todos) {
                CompositePattern.out.printf("<li>");
                todo.print();
                CompositePattern.out.printf("</li>");
            }

            CompositePattern.out.printf("</ul>");
        }
    }

    interface Todo {
        void print();
    }

    private static class SingleTodo implements Todo {

        String text;

        public SingleTodo(String text) {
            this.text = text;
        }

        @Override
        public void print() {
            CompositePattern.out.printf("%s", text);
        }
    }
}
