//package com.asxy.util.id;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Date;
//
///**
// * 生成分布式id的工具类, 参照 twitter 的雪花算法 一个实例1秒钟生成最多支持生成10000个id, 超出id数量会阻塞等待
// * id字符串组成: 4 位机器码 + 6 位进程号 + 4 位序列号 + 10 位的秒级时间戳
// *
// * @author asxy
// */
//@Slf4j
//public class IdGeneratorSingleton {
//
//    /**
//     * 序号的最大值
//     */
//    private static final Integer MAX_SEQ = 9999;
//    /**
//     * 序号长度
//     */
//    private static final Integer SEQ_LENGTH = MAX_SEQ.toString().length();
//    /**
//     * 序号的格式
//     */
//    private static final String SEQ_FORMAT = "%0" + SEQ_LENGTH + "d";
//    /**
//     * id长度
//     */
//    private static final int ID_LENGTH = 24;
//
//    /**
//     * 当前序列号
//     */
//    private static int seq = 0;
//
//    /**
//     * 上次的时间戳
//     */
//    private long lastStamp = -1;
//
//    private static class SingletonHolder {
//
//        private static final IdGeneratorSingleton INSTANCE = new IdGeneratorSingleton();
//    }
//
//    private long tilNextSecond() {
//        long timestamp = DateUtils.getCurrentTimestampSeconds();
//        while (timestamp <= lastStamp) {
//            timestamp = DateUtils.getCurrentTimestampSeconds();
//        }
//        return timestamp;
//    }
//
//    private synchronized String nextSeq() {
//        long currentStamp = DateUtils.getCurrentTimestampSeconds();
//        if (currentStamp < lastStamp) {
//            throw new RuntimeException(String.format("时钟倒退过! 在 %d 秒内拒绝生成id", lastStamp - currentStamp));
//        }
//
//        // 新的秒数，序列从0开始，否则序列自增
//        if (currentStamp != lastStamp) {
//            seq = 0;
//        } else {
//            seq++;
//            if (seq > MAX_SEQ) {
//                //Twitter源代码中的逻辑是循环，直到下一个毫秒
//                currentStamp = tilNextSecond();
//                seq = 0;
//            }
//        }
//
//        lastStamp = currentStamp;
//        return String.format(SEQ_FORMAT, seq) + currentStamp;
//    }
//
//    /**
//     * 获取下一个 id
//     *
//     * @return id
//     */
//    public static String nextId() {
//        ThIdGeneratorSingleton instance = SingletonHolder.INSTANCE;
//        return IdInstanceNum.getMachineNum() + IdInstanceNum.getProcessNum() + instance.nextSeq();
//    }
//
//    /**
//     * 获取id的机器码
//     *
//     * @param id
//     * @return
//     */
//    public static String getMachineNum(String id) {
//        return isValid(id) ? StringUtils.left(id, IdInstanceNum.getMachineNumLength()) : null;
//    }
//
//    /**
//     * 获取id的进程码
//     *
//     * @param id
//     * @return
//     */
//    public static String getProcessNum(String id) {
//        return isValid(id) ?
//                StringUtils.mid(id, IdInstanceNum.getMachineNumLength(), IdInstanceNum.getProcessNumLength()) : null;
//    }
//
//    /**
//     * 获取id的生成时间
//     *
//     * @param id
//     * @return
//     */
//    public static Date getGeneratedDate(String id) {
//        return isValid(id) ? new Date(Long.valueOf(StringUtils.right(id, 10)) * 1000) : null;
//    }
//
//    /**
//     * id位数为24位, 纯数字
//     * 最后10位时间戳大于 1548950400(2019年1月1日)
//     *
//     * @param id
//     * @return
//     */
//    public static boolean isValid(String id) {
//        return id != null && id.length() == ID_LENGTH && StringUtils.isNumeric(id)
//                && Long.valueOf(StringUtils.right(id, 10)) > 1548950400L;
//    }
//
//}
