/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.example.xddemo.demo;



import java.util.Date;

/**
 * pdf签名实体
 *
 * @author june
 * @version V1.0
 * @since 2018-11-19 19:10
 */
public class PdfSign {
    /**
     * 证书
     */
    private byte[] certBytes;

    /**
     * 证书密码
     */
    private String certPwd;

    /**
     * 姓名
     */
    private String signName;

    /**
     * 身份证号
     */
    private String certNo;

    /**
     * 签名日期
     */
    private Date signDate;



    /**
     * 签名图片
     */
    private byte[] signPic;

    /**
     * 签名图片base64
     */
    private String signPicStr;

    /**
     * 是否审核通过
     */
    private Boolean isAuditPass;

    /**
     * 证书私钥
     * 20200328日新增浙江ca证书私钥
     */
    private String privateSecretKey;

    /**
     * 证书内容
     * 20200328日新增浙江ca证书内容
     */
    private String cert;

    /**
     * 签章坐标X轴（像素--浙江ca等使用）
     */
    private Float x;

    /**
     * 签章坐标Y轴（像素--浙江ca等使用）
     */
    private Float y;

    /**
     * 签章坐标X轴（位置--医信签使用）
     */
    private Float positionX;

    /**
     * 签章坐标Y轴（（位置--医信签使用）
     */
    private Float positionY;

    /**
     * 签名业务序号
     */
    private Long bizSeqId;

    /**
     * 处方id
     */
    private String prescriptionId;

    /**
     * ca平台appid
     */
    private String appId;

    /**
     * ca平台bizCode
     */
    private String bizCode;

    /**
     * 盖章页码
     */
    private Integer pageNum;

    /**
     * UC用户ID
     */
    private String userId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 待签名文件kanoId
     */
    private String kanoId;
    /**
     * 待签名文件完整请求地址
     */
    private String fileUrl;
    /**
     * 签字/盖章高度
     */
    private Integer height;
    /**
     * 签字/盖章宽度
     */
    private Integer width;
    /**
     * 是否附加签名时间，签名时间显示于签字/印章图片的下方
     * 1：附加；0：不附加 (默认)；
     */
    private String isTimestamp;


    public byte[] getCertBytes() {
        return certBytes;
    }

    public void setCertBytes(byte[] certBytes) {
        this.certBytes = certBytes;
    }

    public String getCertPwd() {
        return certPwd;
    }

    public void setCertPwd(String certPwd) {
        this.certPwd = certPwd;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }



    public byte[] getSignPic() {
        return signPic;
    }

    public void setSignPic(byte[] signPic) {
        this.signPic = signPic;
    }

    public String getSignPicStr() {
        return signPicStr;
    }

    public void setSignPicStr(String signPicStr) {
        this.signPicStr = signPicStr;
    }

    public Boolean getAuditPass() {
        return isAuditPass;
    }

    public void setAuditPass(Boolean auditPass) {
        isAuditPass = auditPass;
    }

    public String getPrivateSecretKey() {
        return privateSecretKey;
    }

    public void setPrivateSecretKey(String privateSecretKey) {
        this.privateSecretKey = privateSecretKey;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getPositionX() {
        return positionX;
    }

    public void setPositionX(Float positionX) {
        this.positionX = positionX;
    }

    public Float getPositionY() {
        return positionY;
    }

    public void setPositionY(Float positionY) {
        this.positionY = positionY;
    }

    public Long getBizSeqId() {
        return bizSeqId;
    }

    public void setBizSeqId(Long bizSeqId) {
        this.bizSeqId = bizSeqId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKanoId() {
        return kanoId;
    }

    public void setKanoId(String kanoId) {
        this.kanoId = kanoId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getIsTimestamp() {
        return isTimestamp;
    }

    public void setIsTimestamp(String isTimestamp) {
        this.isTimestamp = isTimestamp;
    }
}
