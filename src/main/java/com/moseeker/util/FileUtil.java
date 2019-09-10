package com.moseeker.util;

/**
 * @Description TODO
 * @Author Rays
 * @DATE 2019-09-04
 **/

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片压缩Utils
 *
 * @author worstEzreal
 * @version V1.1.0
 * @date 2018/3/12
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 根据指定大小压缩图片
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位kb
     * @param imageId     影像编号
     * @return 压缩质量后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String imageId) {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            logger.info("【图片压缩】imageId={} | 图片原大小={}kb | 压缩后大小={}kb",
                    imageId, srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }

    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args) {
        Font font = new Font("微软雅黑", Font.PLAIN, 50);                     //水印字体
        String srcImgPath="/Users/wulei/Desktop/归档/Rays.jpeg"; //源图片地址
        String tarImgPath="/Users/wulei/Desktop/归档/Rays.jpeg"; //待存储的地址
        String waterMarkContent="此照片仅供招聘使用";  //水印内容
        Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
        new FileUtil().mark(srcImgPath, tarImgPath, waterMarkContent,font,color);
    }

    /**
     * 给图片增加文字水印
     *
     * @param imgPath
     *            -要添加水印的图片路径
     * @param outImgPath
     *            -输出路径
     * @param text-文字
     * @param font
     *            -字体
     * @param color
     *            -颜色
     */
    public static void mark(String imgPath, String outImgPath, String text, Font font, Color color) {
        try {
            // 读取原图片信息
            File imgFile = null;
            Image img = null;
            if (imgPath != null) {
                imgFile = new File(imgPath);
            }
            if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
                img = ImageIO.read(imgFile);
            }
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            mark(bufImg, img, text, font, color);
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 加文字水印
    private static void mark(BufferedImage bufImg, Image img, String text, Font font, Color color) {
        Graphics2D g = bufImg.createGraphics();
        int x = (int) (bufImg.getWidth()-getWatermarkLength(text,g)-(font.getSize()*text.length()));;
        int y = (int) (bufImg.getHeight()*0.96);;
        //设置水印的坐标
        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.setColor(color);
        g.setFont(font);
        x = x<0?0:x;
        g.drawString(text, x, y);
        g.dispose();
    }

}
