package com.ewem.common.qrcode.impl;


import com.ewem.common.qrcode.factory.AbstractWorkerIdGeneratorFactory;
import com.ewem.common.utils.snowflake.SnowflakeIdUtils;
import org.apache.commons.compress.utils.Sets;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

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

    public static final int DEFAULT_LENGTH = 16;

    private static final Random RANDOM = new Random();

    private final static String[] RANDOM_APPEND =
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "X", "Y", "Z", "#", "$", "*", "-", "_", "+", "=", "<", ">", "&", "^",
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};


    public static void main(String[] args) {
        SnowflakeClient bambooLeafSnowflakeClient = new SnowflakeClient();
        Long startTime = System.currentTimeMillis();
        Set<String> codes = Sets.newHashSet();
        for (int i = 0; i < 1000000; i++) {
            codes.add(bambooLeafSnowflakeClient.getCodeUpperCase(1, 17));
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(codes.size());
        System.out.println(codes.stream().findFirst().get());
        System.out.println(codes.stream().findFirst().get().length());
        System.out.println(endTime - startTime);
    }


    /**
     * 获取码
     * @return 数字类型
     */
    public long getCode() {
        return this.getSnowflakeGenerator(workerId).nextId();
    }

    /**
     * 获取码-指定workerId
     *
     * @param workerId
     * @return 数字类型
     */
    public long getCode(Integer workerId) {
        if (workerId == null) {
            throw new IllegalArgumentException("worker id is null");
        }
        return this.getSnowflakeGenerator(workerId).nextId();
    }

    /**
     * 获取码-大写
     * @return
     */
    public String getCodeUpperCase() {
        StringBuilder id = new StringBuilder(DEFAULT_LENGTH);
        String snowflakeId = Long.toString(getCode(workerId), RADIX);
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (DEFAULT_LENGTH - snowflakeId.length()); i++) {
            id.append(RANDOM_APPEND[RANDOM.nextInt(RANDOM_APPEND.length - 1)]);
        }
        return id.toString().toUpperCase();
    }


    /**
     * 获取码-指定长度
     *
     * @param workerId
     * @param length 指定长度
     * @return
     */
    public String getCodeUpperCase(Integer workerId, Integer length) {
        if (length < 16) {
            throw new IllegalArgumentException("length < 16");
        }
        StringBuilder id = new StringBuilder(length);

        String snowflakeId = Long.toString(getCode(workerId), RADIX);
        id.append(snowflakeId);
        //不够length,后补随机长度
        for (int i = 0; i < (length - snowflakeId.length()); i++) {
            id.append(RANDOM_APPEND[RANDOM.nextInt(RANDOM_APPEND.length - 1)]);
        }
        return id.toString().toUpperCase();
    }


}
