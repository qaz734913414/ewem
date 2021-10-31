package com.ewem.common.qrcode.impl;


import cn.hutool.core.util.RandomUtil;
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

    public static final int SNOWFLAKE_DEFAULT_LENGTH = 18;

    /**
     * 防伪码默认长度
     */
    public static final int ANTI_DEFAULT_LENGTH = 6;

    private static final Random RANDOM = new Random();

    private final static String[] RANDOM_ALL =
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "X", "Y", "Z", "#", "$", "*", "-", "_", "+", "=", "<", ">", "&", "^",
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private final static String[] RANDOM_NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private final static String[] RANDOM_LETTER_NUMBER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};


    /**
     * 二维码
     *
     * @param rule
     * @param length
     * @return
     */
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
                return letterNumberCharCode(length);
        }
    }

    /**
     * 防伪码
     *
     * @param rule
     * @param length
     * @return
     */
    public String antiCode(String rule, Integer length) {
        if (null == length || length < ANTI_DEFAULT_LENGTH) {
            length = ANTI_DEFAULT_LENGTH;
        }
        return rule.equals("LETTER_NUMBER") ? letterNumberAntiCode(length) : numberAntiCode(length);
    }


    /**
     * 获取码-指定workerId
     *
     * @return 数字类型
     */
    public long code(Integer workerId) {
        if (workerId == null) {
            throw new IllegalArgumentException("worker id is null");
        }
        return this.getSnowflakeGenerator(workerId).nextId();
    }

    public static void main(String[] args) {
    }


    /**
     * 指定长度数字类型防伪码
     *
     * @param length
     * @return
     */
    private String numberAntiCode(Integer length) {
        String snowflakeId = Long.toString(code(RandomUtil.randomInt(INIT_WORKER_ID, MAX_WORKER_ID)));
        return snowflakeId.substring(SNOWFLAKE_DEFAULT_LENGTH - length);
    }

    /**
     * 指定长度字母加数字类型防伪码
     *
     * @return
     */
    private String letterNumberAntiCode(Integer length) {
        StringBuilder id = new StringBuilder(length);
        String snowflakeId = Long.toString(code(RandomUtil.randomInt(INIT_WORKER_ID, MAX_WORKER_ID)), RADIX);
        if (snowflakeId.length() >= length) {
            return snowflakeId.substring(snowflakeId.length() - length).toUpperCase();
        }
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (length - snowflakeId.length()); i++) {
            id.append(RANDOM_LETTER_NUMBER[RANDOM.nextInt(RANDOM_LETTER_NUMBER.length - 1)]);
        }
        return id.toString().toUpperCase();
    }

    /**
     * 指定长度数字类型码
     *
     * @param length
     * @return
     */
    private String numberCode(Integer length) {
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
     * 指定长度字母加数字类型码
     *
     * @return
     */
    private String letterNumberCode(Integer length) {
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
    private String letterNumberCharCode(Integer length) {
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
