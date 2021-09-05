package com.ewem.common.qrcode.factory;

import java.util.concurrent.TimeUnit;

/**
 * @author ewem
 * <p>
 * 雪花抽象类
 */
public class AbstractSnowflake {
    /**
     * 机器id所占的位数
     */
    protected final int workerIdBits = 14;
    /**
     * 序列在id中占的位数
     */
    protected final int sequenceBits = 18;
    /**
     * 时间截中占的位数
     */
    protected int timeBits = 31;

    /**
     * 开始时间截 (2021-01-01)
     */
    protected final long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(1609430400000L);

    /**
     * 工作机器ID(0~16383)
     */
    protected long workerId;

    /**
     * 秒内序列(0~262413)
     */
    protected long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    protected long lastTimestamp = -1L;
}
