import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class StatePattern {
    public static void main(String[] args) {
        RtcDataChannel rtcDataChannel = new RtcDataChannel();
        rtcDataChannel.changeStateTo(new ChannelInitingState(rtcDataChannel));
        rtcDataChannel.onDataMessage("message before opened");
        rtcDataChannel.open();
        rtcDataChannel.onDataMessage("message while opened");
        rtcDataChannel.close();
        rtcDataChannel.onDataMessage("Message while closed");

    }


    private interface State {
        void onDataMessage(String text);
        void open();
        void close();
    }

//    if we don't want to pass state
//    private interface State {
//        State onDataMessage(String text);
//        State open();
//        State close();
//     and we set state which was returned
//    }



    static private class RtcDataChannel{

        State state;

        public void changeStateTo(State state) {
            this.state = state;
        }

        public void onDataMessage(String text) {
            state.onDataMessage(text);
        }


        public void open() {
            state.open();
        }


        public void close() {
            state.close();
        }
    }

    private static class ChannelInitingState  implements State {

        private RtcDataChannel rtcDataChannel;

        public ChannelInitingState(RtcDataChannel rtcDataChannel) {
            this.rtcDataChannel = rtcDataChannel;
        }

        List<String> queue = new ArrayList<>();
        public void onDataMessage(String text) {
            queue.add(text);
        }


        @Override
        public void open() {
            rtcDataChannel.changeStateTo(new ChannelOpenState(rtcDataChannel));
            for (String s : queue) {
                rtcDataChannel.onDataMessage(s);
            }
        }

        @Override
        public void close() {
            rtcDataChannel.changeStateTo(new ChannelClosedState(rtcDataChannel));
        }
    }

    private static class ChannelOpenState implements State {

        private RtcDataChannel rtcDataChannel;

        public ChannelOpenState(RtcDataChannel rtcDataChannel) {
            this.rtcDataChannel = rtcDataChannel;
        }


        PrintStream out = System.out;
        public void onDataMessage(String text) {
            out.printf("%s;\n", text);
        }

        @Override
        public void open() {
            //already opened
        }

        @Override
        public void close() {
            rtcDataChannel.changeStateTo(new ChannelClosedState(rtcDataChannel));
        }
    }

    private static class ChannelClosedState  implements State {

        private RtcDataChannel rtcDataChannel;

        public ChannelClosedState(RtcDataChannel rtcDataChannel) {
            this.rtcDataChannel = rtcDataChannel;
        }
        public void onDataMessage(String text) {
            throw new RuntimeException("Can't send message cause channel is closed");
        }


        @Override
        public void open() {
            throw new RuntimeException("Can't reopen closed channel");
        }

        @Override
        public void close() {
            // already closed
        }
    }

}
