package com.lawinfo.domain.wechat.request;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by wangrongtao on 2016/10/22.
 */
@XmlRootElement(name = "xml")
public class WeChatCommonRequestMessage implements Serializable {

    private String signature;
    private long timestamp;
    private String nonce;
    private String openid;
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link）
    private String MsgType;
    // 消息id，64位整型
    private long MsgId;
    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;
    // 地理位置维度
    private String Location_X;
    // 地理位置经度
    private String Location_Y;
    // 地图缩放大小
    private String Scale;
    // 地理位置信息
    private String Label;
    // 图片链接
    private String PicUrl;
    private String Content;
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WeChatCommonRequestMessage{" +
                "signature='" + signature + '\'' +
                ", timestamp=" + timestamp +
                ", nonce='" + nonce + '\'' +
                ", openid='" + openid + '\'' +
                ", ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId=" + MsgId +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                ", Location_X='" + Location_X + '\'' +
                ", Location_Y='" + Location_Y + '\'' +
                ", Scale='" + Scale + '\'' +
                ", Label='" + Label + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", Content='" + Content + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", Format='" + Format + '\'' +
                '}';
    }
}
