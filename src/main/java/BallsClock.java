import java.util.ArrayList;
import java.util.Collections;

public class BallsClock {

    private static ArrayList<Integer> mins = new ArrayList<>();
    private static ArrayList<Integer> fiveMins = new ArrayList<>();
    private static ArrayList<Integer> hours = new ArrayList<>();
    private static ArrayList<Integer> stack;
    private static ArrayList<Integer> initialState = new ArrayList<>();
    private static int days;
    private static boolean AM = true;

    public static void main(String[] args) throws Exception {
        getCycleDays(27);
        getClockState(27, 325);
    }

    private static void getClockState(int stackCount, int minutes) throws Exception {
        checkInput(stackCount);
        fillStackWithBalls(stackCount);
        for (int i = 0; i < minutes; i++) {
            addBallToMins();
        }
        printSecondModeResult();
    }


    public static void getCycleDays(int stackCount) throws Exception {
        checkInput(stackCount);
        long startTime = System.nanoTime();
        boolean isOrderInitial = false;
        initialState.addAll(fillStackWithBalls(stackCount));
        while (!isOrderInitial) {
            addBallToMins();
            isOrderInitial = checkInitialOrder(stack, initialState);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        printFirstModeResult(stackCount, duration);
    }

    private static void addBallToMins() {
        if (mins.size() < 4) {
            mins.add(stack.get(0));
            stack.remove(0);
        } else {
            addBallToFiveMins();
        }
    }

    private static void addBallToFiveMins() {
        if (fiveMins.size() < 11) {
            fiveMins.add(stack.get(0));
            stack.remove(0);

            clear(mins);
        } else {
            addBallToHours();
        }
    }

    private static void addBallToHours() {
        if (hours.size() < 11) {
            hours.add(stack.get(0));
            stack.remove(0);

            clear(mins);
            clear(fiveMins);
        } else {
            reFillStackWithBalls(stack.get(0));
        }
    }

    private static void reFillStackWithBalls(int lastBall) {
        clear(mins);
        clear(fiveMins);
        clear(hours);

        stack.remove(0);
        stack.add(lastBall);

        countDays();
    }

    private static void clear(ArrayList list) {
        Collections.reverse(list);
        stack.addAll(list);
        list.clear();
    }

    private static void countDays() {
        AM = !AM;
        if (AM) {
            days++;
        }
    }

    private static ArrayList<Integer> fillStackWithBalls(int count) {
        stack = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            stack.add(i + 1);
            if (stack.size() > count) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return stack;
    }

    private static boolean checkInitialOrder(ArrayList<Integer> stack, ArrayList initial) {
        if (stack.size() == initial.size()) {
            for (int i = 0; i < initial.size(); i++) {
                if (initial.get(i) != stack.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static void printSecondModeResult() {
        System.out.println("{" + "\"Min\":" + mins + "," + "\"FiveMin\":" + fiveMins + "," + "\"Hour\":" + hours + "," + "\"Main\":" + stack + "}");
    }

    private static void printFirstModeResult(int ballsCount, long milliseconds) {
        System.out.printf("%s balls cycle after %s days" + "\n" + "Completed in %s milliseconds (%s seconds)",
                ballsCount, days, milliseconds, milliseconds * 0.001);
    }

    private static void checkInput(int stackCount) throws Exception {
        if (stackCount < 27 || stackCount > 127) {
            throw new Exception("Input is outside of the range, please put correct value");
        }
    }
}
