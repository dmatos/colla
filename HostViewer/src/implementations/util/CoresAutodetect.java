package implementations.util;

public class CoresAutodetect {

    public static int detect() {
        Runtime runtime = Runtime.getRuntime();

        return runtime.availableProcessors();
    }
}
