package com.example.xddemo;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author xuedong
 * Date: 2025/4/11
 */
public class JsonDemo {


    public static String flattenAndSort(String jsonStr) {

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Map<String, String> flatMap = new TreeMap<>();
        flatten(jsonObject, flatMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : flatMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    private static void flatten(JSONObject jsonObject, Map<String, String> flatMap) {
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof JSONObject) {
                flatten((JSONObject) value, flatMap);
            } else {
                flatMap.put(key, value.toString());
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {


        String a = "{\"clientIp\": \"10.60.9.155\", \"methodName\": \"OpenApiReportInfoController.save\", \"executeTime\": 14, \"params\": [{\"reportBaseInfoBO\": {\"projectCode\": \"XM000002\", \"hospitalCode\": null, \"extId\": \"H2505230IPWC2_7EA7C8BD\", \"prescriptionDoctorCode\": \"管理员\", \"prescriptionDoctorName\": null, \"reportingDoct orCode\": \"YH000005\", \"reportingDoctorName\": \"黄健\", \"recordTimeStart\": \"2025-05-23 11:39:46\", \"recordTimeEnd\": \"2025-05-24 11:30:05\", \"reviewDate\": \"2025-05-24 21:16:45\", \"conclusion\": \"提示：窦性心律\\ r\\n      偶发房性早搏 成对房早\\r\\n      室性早搏 成对室早 室性融合波\\r\\n      ST-T动态改变\\r\\n      HRV SDNN>50ms\", \"feeType\": null}, \"peopleIdentifierBO\": {\"name\": \"毛文春\", \"gender\": \"1\", \"idCardN o\": \"12010619580318651X\", \"isCardNoCertified\": true, \"phoneNumber\": null, \"presentIllness\": null, \"pastIllness\": null}, \"healthIndicatorBO\": {\"deviceModel\": null, \"bluetoothConnectCode\": null, \"phoneModel\": null, \"deviceSerial\": null, \"softwareVersion\": null, \"bloodSugar\": null, \"measureTimeLabel\": null, \"bloodPressureMeasureTimeLabel\": null, \"systolicPressure\": null, \"systolicPressureStatus\": null, \"diastol icPressure\": null, \"diastolicPressureStatus\": null, \"heartRate\": null, \"oxygenSaturation\": null, \"whr\": null, \"bodyMassIndex\": null, \"boneDensityPart\": null, \"bmd\": null, \"sos\": null, \"boneDensityZ\": null, \"bon eDensityT\": null, \"boneDensityConclusion\": null, \"risk\": null, \"strength\": null, \"measureTime\": null, \"source\": \"cic\", \"label\": null, \"attachment\": [{\"code\": \"holter\", \"attachmentCode\": null, \"fileName\": null, \" values\": [\"http://10.60.10.206:8080/REPORT_DATA/2/25/05/H2505230IPWC2_7EA7C8BD/H2505230IPWC2.pdf\"]}], \"baihuiAmbulatoryEcgBO\": {\"startTime\": \"2025-05-2311:39:46\", \"endTime\": \"2025-05-2411:30:05\", \" timeLong\": 85819, \"totalBeats\": \"118712\", \"numberChannels\": \"12\", \"heartRateList\": [{\"type\": 1, \"value\": 63, \"unit\": \"次\", \"recordTime\": \"05-24 06:25:34\"}, {\"type\": 2, \"value\": 120, \"unit\": \"次\", \"recordTime\": \"0 5-24 08:56:29\"}, {\"type\": 3, \"value\": 84, \"unit\": \"次\", \"recordTime\": null}], \"fusionWave\": \"0\", \"tachycardiaList\": [{\"type\": 1, \"value\": -1748016000, \"unit\": \"秒\", \"recordTime\": null}, {\"type\": 2, \"value\": -17480 16000, \"unit\": \"秒\", \"recordTime\": null}, {\"type\": 3, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 4, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}], \"rrLongBO\": {\"count\": 0, \"countUnit\": \"次\", \"maxLong\": 0, \"maxLongUnit\": \"秒\", \"recordTime\": \"\"}, \"rrLongLullList\": [{\"type\": 1, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 2, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 3, \"value\": 0, \"unit\": \"次\", \" recordTime\": null}, {\"type\": 4, \"value\": 327, \"unit\": \"秒\", \"recordTime\": null}, {\"type\": 5, \"value\": null, \"unit\": null, \"recordTime\": \"2025-05-23 16:41:34\"}], \"atrialArrhythmiaList\": [{\"type\": 1, \"value\": 9, \"un it\": \"次\", \"recordTime\": null}, {\"type\": 2, \"value\": 7, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 3, \"value\": 1, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 4, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 8, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 9, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 11, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 12, \"value\": 0, \"unit\": \"次/分\", \"recordTi me\": \"\"}, {\"type\": 14, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 15, \"value\": 0, \"unit\": \"次/分\", \"recordTime\": \"\"}, {\"type\": 16, \"value\": 0, \"unit\": \"秒\", \"recordTime\": null}, {\"type\": 17, \"value\": 0, \"uni t\": \"阵\", \"recordTime\": null}, {\"type\": 18, \"value\": 0, \"unit\": \"次/分\", \"recordTime\": \"\"}, {\"type\": 19, \"value\": 0, \"unit\": \"秒\", \"recordTime\": null}], \"ventricularArrhythmiaList\": [{\"type\": 1, \"value\": 185, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 2, \"value\": 183, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 3, \"value\": 1, \"unit\": \"对\", \"recordTime\": null}, {\"type\": 4, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 5, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 6, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 8, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 12, \"value\": 0, \"unit\": \"次/分\", \"recordTime \": \"\"}, {\"type\": 14, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}], \"diagnosisEctopicRhythmList\": [{\"type\": 1, \"value\": 9, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 2, \"value\": 7, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 3, \"value\": 1, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 5, \"value\": 0, \"unit\": \"阵\", \"recordTime\": \"CT_VE_Run_FT_Time\"}, {\"type\": 6, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 7, \"value\": 0, \" unit\": \"次/分\", \"recordTime\": \"\"}, {\"type\": 8, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}], \"borderlineArrhythmiaList\": [{\"type\": 1, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 4, \"value\": 0, \"unit\": \" 次\", \"recordTime\": null}], \"heartRateVariabilityAnalysisList\": [{\"type\": 1, \"value\": 103, \"unit\": \"ms\", \"recordTime\": null}, {\"type\": 2, \"value\": 97, \"unit\": \"ms\", \"recordTime\": null}, {\"type\": 3, \"value\": 30, \"uni t\": \"ms\", \"recordTime\": null}, {\"type\": 4, \"value\": 12, \"unit\": \"ms\", \"recordTime\": null}, {\"type\": 5, \"value\": 0, \"unit\": \"%\", \"recordTime\": null}, {\"type\": 7, \"value\": 25, \"unit\": null, \"recordTime\": null}, {\"type\": 9, \"value\": 1, \"unit\": null, \"recordTime\": null}, {\"type\": 10, \"value\": 92, \"unit\": null, \"recordTime\": null}, {\"type\": 11, \"value\": 57, \"unit\": null, \"recordTime\": null}], \"atrialFibrillationAnalysisList\": [{\"type\": 1, \"value\": 0, \"unit\": \"次\", \"recordTime\": null}, {\"type\": 2, \"value\": 0, \"unit\": \"阵\", \"recordTime\": null}, {\"type\": 3, \"value\": 0, \"unit\": \"秒\", \"recordTime\": null}], \"stAnalysisList\": [{\"type\": 1, \"value\": 0, \"unit \": \"mV\", \"recordTime\": \"\", \"desc\": null}, {\"type\": 3, \"value\": 0, \"unit\": \"mV\", \"recordTime\": \"05-23 13:35:23\", \"desc\": null}], \"pacemakerAnalysisList\": [{\"type\": 2, \"value\": 0, \"unit\": null, \"recordTime\": null, \"de sc\": null}, {\"type\": 3, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc\": null}, {\"type\": 4, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc\": null}, {\"type\": 5, \"value\": 0, \"unit\": null, \"recordTime\": null, \"des c\": null}, {\"type\": 6, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc\": null}, {\"type\": 7, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc\": null}, {\"type\": 8, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc \": null}, {\"type\": 9, \"value\": 0, \"unit\": null, \"recordTime\": null, \"desc\": null}]}, \"baihuiBloodPressureTypeBO\": null, \"ambulatoryEcgBO\": null, \"bloodPressureTypeBO\": null, \"ecgSoundBO\": null, \"bloodSugarBO\": n ull, \"singleEcgBO\": null, \"liverBO\": null, \"plantarisBO\": null}}], \"result\": \"没有查询到开方医生的信息\", \"errorName\": null}";

        List<String> strings = splitUtf8Safe(a, 3800);


        for (String string : strings) {
            System.out.println(string);
        }


    }

    public static List<String> splitUtf8Safe(String input, int maxBytesPerChunk) {
        List<String> result = new ArrayList<>();

        if (input == null || input.isEmpty()) {
            return result;
        }

        byte[] byteArray = input.getBytes(StandardCharsets.UTF_8);
        int length = byteArray.length;
        int offset = 0;

        while (offset < length) {
            int endIndex = Math.min(offset + maxBytesPerChunk, length);

            // 回退，确保不截断多字节字符（避免乱码）
            while (endIndex > offset &&
                    endIndex < length &&
                    isUtf8ContinuationByte(byteArray[endIndex])) {
                endIndex--;
            }

            // 防止无法回退，强制前进一段（极端情况下）
            if (endIndex == offset) {
                endIndex = Math.min(offset + maxBytesPerChunk, length);
            }

            byte[] chunk = Arrays.copyOfRange(byteArray, offset, endIndex);
            result.add(new String(chunk, StandardCharsets.UTF_8));

            offset = endIndex;
        }

        return result;
    }

    /**
     * 判断一个字节是否为 UTF-8 的 continuation byte（10xxxxxx）
     */
    private static boolean isUtf8ContinuationByte(byte b) {
        return (b & 0xC0) == 0x80;
    }





}
