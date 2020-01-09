package com.framework.common.util.verifycode;
/**
 * 
* @ClassName: VerifyCodeUtil  
* @Description: TODO(验证码util)  
* @author hanyl
* @date 2019年12月12日 下午7:23:46  
*
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeUtil {

    /**
     * 算术表达式验证码
     *
     * 1.干扰线的产生
     * 2.范围随机颜色，随机数
     *
     * @param response
     * @return
     */
    public static ImageCode drawImageVerificate(int width, int height) {
        //在内存中创建图片
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //创建图片上下文
        Graphics2D g = image.createGraphics();
        //产生随机对象，此随机对象主要用于算术表达式的数字
        Random random= new Random();
        //设置背景
        g.setColor(getRandomColor(240,250));
        //设置字体
        g.setFont(new Font("微软雅黑",Font.PLAIN,22 ));
        //开始绘制
        g.fillRect(0,0,width,height);
 
        //干扰线的绘制，绘制干扰线到图片中
        g.setColor(getRandomColor(180,230));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            g.drawLine(x,y,x1,y1);
        }
 
        //开始进行对算术验证码表达式的拼接
        int num1 = (int)(Math.random()*10+1);
        int num2 = (int)(Math.random()*10+1);
        int fuhao = random.nextInt(3);//产生一个0-2之间的随机整数
        //记录符号
        String fuhaostr = null;
        int result = 0;
        switch (fuhao){
            case 0: fuhaostr = "+";result = num1 + num2;break;
            case 1: fuhaostr = "-";result = num1 - num2;break;
            case 2: fuhaostr = "*";result = num1 * num2;break;
        }
        //拼接算术表达式，用户图片显示
        String calc = num1 + " " + fuhaostr + " " + num2 + " = ?";
        //设置随机颜色
        g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
        //绘制表达式
        g.drawString(calc,5,25);
        //结束绘制
        return new ImageCode(image,result);
//        try {
//            //输出图片到页面
//        	OutputStream out = response.getOutputStream();
//            ImageIO.write(image, "JPEG", out);
//            out.flush();
//            out.close();
//            return new ImageCode(image,result);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
    }
 
    /**
     * 范围随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandomColor(int fc, int bc) {
        //利用随机数
        Random random = new Random();
        //随机颜色，了解颜色-Color(red,green,blue).rgb三元色0-255
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    public static ImageCode drawImageVerificate() {
    	
    	return drawImageVerificate( 120, 25);
    }
}
