package com.ewem.common.utils.snowflake;

import java.util.Random;

/**
 * @author ewem
 * <p>
 * 算法工具类
 */
public class SnowflakeIdUtils {


    private static final Random RANDOM = new Random();

    /**
     * 最小workerId
     */
    public static final int INIT_WORKER_ID = 0;
    /**
     * 最大workerId
     */
    public static final int MAX_WORKER_ID = 16383;


    public static long nextLong(final long startInclusive, final long endExclusive) {
        isTrue(endExclusive >= startInclusive, "Start value must be smaller or equal to end value.");
        isTrue(startInclusive >= 0, "Both range values must be non-negative.");

        if (startInclusive == endExclusive) {
            return startInclusive;
        }
        return (long) nextDouble(startInclusive, endExclusive);
    }

    public static void isTrue(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    public static double nextDouble(final double startInclusive, final double endInclusive) {
        isTrue(endInclusive >= startInclusive, "Start value must be smaller or equal to end value.");
        isTrue(startInclusive >= 0, "Both range values must be non-negative.");

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextDouble());
    }


}
