package com.ewem.common.qrcode.impl;


import com.ewem.common.exception.BaseException;
import com.ewem.common.qrcode.factory.AbstractSnowflake;
import com.ewem.common.qrcode.factory.BitsAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author ewem
 * <p>
 * 雪花算法默认实现类
 */
public class DefaultSnowflakeGenerator extends AbstractSnowflake {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSnowflakeGenerator.class);
    /**
     * Stable fields after spring bean initializing
     */
    protected BitsAllocator bitsAllocator;

    public DefaultSnowflakeGenerator(int workerId) {
        super.workerId = workerId;
        // initialize bits allocator
        bitsAllocator = new BitsAllocator(timeBits, workerIdBits, sequenceBits);

        // initialize worker id
        if (workerId > bitsAllocator.getMaxWorkerId()) {
            throw new RuntimeException("Worker id " + workerId + " exceeds the max " + bitsAllocator.getMaxWorkerId());
        }
        logger.info("Initialized bits(1, {}, {}, {}) for workerID:{}", timeBits, workerIdBits, sequenceBits, workerId);
    }


    public Long nextId() {
        try {
            return nextSnowId();
        } catch (Exception e) {
            logger.error("Generate unique id exception. ", e);
            throw new BaseException("Generate unique id exception");
        }
    }


    /**
     * Get SnowId
     *
     * @return SnowId
     * @throws BaseException in the case: Clock moved backwards; Exceeds the max timestamp
     */
    protected synchronized long nextSnowId() {
        long currentSecond = getCurrentSecond();
        if (currentSecond == lastTimestamp) {
            // At the same second, increase sequence
            sequence = (sequence + 1) & bitsAllocator.getMaxSequence();
            // Exceed the max sequence, we wait the next second to generate SnowId
            if (sequence == 0) {
                currentSecond = getNextSecond(lastTimestamp);
            }
        } else if (currentSecond < lastTimestamp) {
            // Clock moved backwards, refuse to generate SnowId
            //计算时间差
            long refusedSeconds = lastTimestamp - currentSecond;
            logger.warn("Clock moved backwards. Refusing for {} seconds", refusedSeconds);
            sequence = (sequence + 1) & bitsAllocator.getMaxSequence();
            // Exceed the max sequence, we wait the next second to generate SnowId
            if (sequence == 0) {
                //使用未来时间
                currentSecond = lastTimestamp + 1;
            } else {
                //使用未来时间
                currentSecond = lastTimestamp;
            }
        } else {
            // At the different second, sequence restart from zero
            sequence = 0L;
        }
        lastTimestamp = currentSecond;
        long deltaSeconds = currentSecond - epochSeconds;
        // Allocate bits for SnowId
        return bitsAllocator.allocate(deltaSeconds, workerId, sequence);
    }

    /**
     * Get next millisecond
     */
    private long getNextSecond(long lastTimestamp) {
        long timestamp = getCurrentSecond();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentSecond();
        }
        return timestamp;
    }

    /**
     * Get current second
     */
    private long getCurrentSecond() {
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (currentSecond - epochSeconds > bitsAllocator.getMaxDeltaSeconds()) {
            throw new BaseException("Timestamp bits is exhausted. Refusing UID generate. Now: " + currentSecond);
        }
        return currentSecond;
    }
}
