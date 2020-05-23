package by.step.thoughts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathExampleGenerator {

    private List<Character> availableOps = new ArrayList<>();

    private Random rand = new Random();

    public MathExampleGenerator() {
        availableOps.add('+');
        availableOps.add('-');
        availableOps.add('*');
    }

    public Result next(int min, int max) {
        int opIndex = rand.nextInt(availableOps.size());
        return next(min, max, availableOps.get(opIndex));
    }

    private Result next(int min, int max, char op) {

        if (!availableOps.contains(op))
            return null;

        int val1 = rand.nextInt((max - min) + 1) + min;
        int val2 = rand.nextInt((max - min) + 1) + min;

        return new Result(val1, val2, op, getResult(val1, val2, op));
    }

    private int getResult(int val1, int val2, char op) {
        switch (op) {
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
        }
        return 0;
    }

    public static class Result {
        private int val1, val2;
        private char op;
        private int res;

        Result(int val1, int val2, char op, int res) {
            this.val1 = val1;
            this.val2 = val2;
            this.op = op;
            this.res = res;
        }

        public int getVal1() {
            return val1;
        }

        public int getVal2() {
            return val2;
        }

        public char getOp() {
            return op;
        }

        public int getRes() {
            return res;
        }
    }

}
