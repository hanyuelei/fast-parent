package com.framework.common.util.verifycode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {
	 /**
     * 图片 展示用
     */
    private BufferedImage image;
    /**
     * 随机数
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime localDateTime;
    /**
     * 数字验证码结果值
     */
    private int value;
    
    public ImageCode(BufferedImage image, String code, int second) {
        this.image = image;
        this.code = code;
        // 多少秒后
        this.localDateTime = LocalDateTime.now().plusSeconds(second);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime localDateTime) {
        this.image = image;
        this.code = code;
        this.localDateTime = localDateTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

    public ImageCode(BufferedImage image, int value) {
        this.image = image;
        this.value = value;
    }
	
    
}
