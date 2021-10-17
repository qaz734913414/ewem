package com.ewem.common.qrcode.impl;


import com.ewem.common.qrcode.factory.AbstractWorkerIdGeneratorFactory;
import com.ewem.common.utils.snowflake.SnowflakeIdUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author ewem
 * <p>
 * 获取雪花随机ID
 */
@Service
public class SnowflakeClient extends AbstractWorkerIdGeneratorFactory {

    /**
     * 最小workerId
     */
    public static final int INIT_WORKER_ID = 0;
    /**
     * 最大workerId
     */
    public static final int MAX_WORKER_ID = 16383;

    private static final Integer workerId = (int) SnowflakeIdUtils.nextLong(INIT_WORKER_ID, MAX_WORKER_ID);

    public static final int RADIX = 36;

    public static final int DEFAULT_LENGTH = 17;

    private static final Random RANDOM = new Random();

    private final static String[] RANDOM_ALL =
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "X", "Y", "Z", "#", "$", "*", "-", "_", "+", "=", "<", ">", "&", "^",
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private final static String[] RANDOM_NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private final static String[] RANDOM_LETTER_NUMBER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};


    public String code(String rule, Integer length) {
        if (null == length || length < DEFAULT_LENGTH) {
            length = DEFAULT_LENGTH;
        }
        switch (rule) {
            case "NUMBER":
                return numberCode(length);
            case "LETTER_NUMBER":
                return letterNumberCode(length);
            default:
                return letterNumberChar(length);
        }
    }


    /**
     * 获取码-指定workerId
     *
     * @param workerId
     * @return 数字类型
     */
    public long code(Integer workerId) {
        if (workerId == null) {
            throw new IllegalArgumentException("worker id is null");
        }
        return this.getSnowflakeGenerator(workerId).nextId();
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(new SnowflakeClient().code(1)).length());
    }

    /**
     * 获取指定长度数字类型的码
     *
     * @param length
     * @return
     */
    public String numberCode(Integer length) {
        StringBuilder id = new StringBuilder(length);
        String snowflakeId = Long.toString(code(workerId));
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (length - snowflakeId.length()); i++) {
            id.append(RANDOM_NUMBER[RANDOM.nextInt(RANDOM_NUMBER.length - 1)]);
        }
        return id.toString();
    }


    /**
     * 字母加数字
     *
     * @return
     */
    public String letterNumberCode(Integer length) {
        StringBuilder id = new StringBuilder(length);
        String snowflakeId = Long.toString(code(workerId), RADIX);
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (length - snowflakeId.length()); i++) {
            id.append(RANDOM_LETTER_NUMBER[RANDOM.nextInt(RANDOM_LETTER_NUMBER.length - 1)]);
        }
        return id.toString().toUpperCase();
    }

    /**
     * 获取码-大写
     *
     * @return
     */
    public String letterNumberChar(Integer length) {
        StringBuilder id = new StringBuilder(length);
        String snowflakeId = Long.toString(code(workerId), RADIX);
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (length - snowflakeId.length()); i++) {
            id.append(RANDOM_ALL[RANDOM.nextInt(RANDOM_ALL.length - 1)]);
        }
        return id.toString().toUpperCase();
    }


}
