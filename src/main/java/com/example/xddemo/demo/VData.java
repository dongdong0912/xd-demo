package com.example.xddemo.demo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Author: xuedong
 * Date: 2023/12/15
 */
@Data
public class VData {

    /**
     * 假设正确格式为(如： 3B 09 01 02 03 04 05 53 0A)
     * 3B  X1  X2....Xn  Xm  0A
     * 3B头  X1长度   X2-Xn数据  Xm校验  0A结束
     * X1长度为3B到0A所有字节的长度
     * Xm=(3B+X1+X2+...+Xn)&0XFF
     */
    //每一包数据最大长度
    private int dataMaxSize = 50;
    //缓存区长度
    private int buffSize = dataMaxSize * 2;
    //缓存区
    private byte[] buff = new byte[buffSize];
    //缓存区可存入的下一个数据下标（即已存入的数据长度）
    private int buffIndex = 0;
    //头
    private byte hed = 0X3B;
    //尾
    private byte end = 0X0A;
    //输出
    private byte[] outBuff;



    private List<byte[]> outBuffList= Lists.newArrayList();

    //加入缓存
    public void buffData(byte oneByte) {
        if (buffIndex >= buff.length) {
            //缓存区已满，删除第一个
            if (buff.length - 1 >= 0) {
                System.arraycopy(buff, 1, buff, 0, buff.length - 1);
                buff[buff.length - 1] = 0X00;
                buffIndex = buff.length - 1;
            }
        }
        buff[buffIndex] = oneByte;
        buffIndex++;
        //新数据已加入缓存，从缓存中检验是否有完整数据
        VerificationData();

    }

    //新数据已加入缓存，从缓存中检验是否有完整数据
    private void VerificationData() {

        //System.out.println("BUFF：" +Arrays.toString(buff));

        if (buffIndex < 4) {
            //头+长度+data +校验+尾 就算数据为空也至少要4位
        } else {
            if (buff[0] == hed) {
                //数据长度
                int dataLength = buff[1] & 0XFF;
                //System.out.println("dataLength：" +dataLength);
                if (dataLength <= dataMaxSize) {
                    if (buffIndex >= dataLength) {
                        if (buff[dataLength - 1] == end) {
                            int CRC = 0;
                            for (int i = 0; i < dataLength - 2; i++) {
                                CRC = CRC + buff[i];
                            }

                            if (CRC == buff[dataLength - 2]) {
                                //是条完整的数据
                                outBuff = new byte[dataLength];
                                System.arraycopy(buff, 0, outBuff, 0, dataLength);
                                //use outBuff
                                useOutBuff(outBuff);
                                outBuffList.add(outBuff);

                                //清除已使用数据
                                System.arraycopy(buff, dataLength, buff, 0, dataLength);
                                for (int i = 0; i < dataLength; i++) {
                                    buff[buffIndex - dataLength + i] = 0X00;
                                }

                                buffIndex = buffIndex - dataLength;
                                //再次检验
                                VerificationData();

                            } else {
                                System.out.println("校验："+CRC);
                                //校验不相等
                                System.arraycopy(buff, 1, buff, 0, buffIndex - 1);
                                buff[buffIndex - 1] = 0X00;
                                buffIndex = buffIndex - 1;
                                //再次检验
                                VerificationData();
                            }


                        } else {
                            //数据尾对不上，不是一条数据
                            System.arraycopy(buff, 1, buff, 0, buffIndex - 1);
                            buff[buffIndex - 1] = 0X00;
                            buffIndex = buffIndex - 1;
                            //再次检验
                            VerificationData();
                        }

                    } else {

                        //已缓存的数据还没有这没多，继续等待缓存
                        //本方法可能存在干扰数据正好与hed相等，然后第二位是干扰数据或者是真的数据hed,此时把第二位当数据长度会出现问题
                        //比如缓存中有数据3B 28 3B 09 ..... 0A，其中3B 28为干扰数据，后面还有个完整的数据包，
                        //那么此方法会等到再有数据进来，缓存中有0X28个数据时才会判断出3B 28为无效数据，就造成了延时问题，
                        //或者后面不会再有数据进来，那么后面那个真的完整数据就会存在缓存中而没有拿出来使用。
                        //解决办法：
                        //一、增加hed头为多个字节，减少干扰和hed的相等的几率。
                        //二、在此继续向后面检查是否有完整数据，但是检测前不清除前面的buff,如果有的话再清空buff中的使用数据和前面的无效数据

                    }

                } else {
                    //数据长度超过规定长度，肯定是错误数据，舍弃1位
                    System.arraycopy(buff, 1, buff, 0, buffIndex - 1);
                    buff[buffIndex - 1] = 0X00;
                    buffIndex = buffIndex - 1;
                    //再次检验
                    VerificationData();
                }

            } else {
                //第一个数据不是hed,舍弃
                System.arraycopy(buff, 1, buff, 0, buffIndex - 1);
                buff[buffIndex - 1] = 0X00;
                buffIndex = buffIndex - 1;
                //再次检验
                VerificationData();
            }
        }
    }

    private void useOutBuff(byte[] outBuff) {
       // System.out.println("收到数据：" + Arrays.toString(outBuff));
    }
}
