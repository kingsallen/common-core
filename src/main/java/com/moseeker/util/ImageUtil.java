package com.moseeker.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.twelvemonkeys.imageio.plugins.jpeg.JPEGImageWriter;

/**
 * @category 图片工具类
 */
public final class ImageUtil {

	public static final String IMAGE_FORMAT_JPEG = "jpeg";
	public static final String IMAGE_FORMAT_PNG = "png";
	public static final String IMAGE_FORMAT_GIF = "gif";
	public static final String IMAGE_FORMAT_BMP = "bmp";

	/**
	 * 压缩图片
	 * 
	 * @param path 图片所在的文件夹路径
	 * @param file 图片
	 * @param name 图片名
	 * @param width 目标宽
	 * @param height 目标高
	 * @param percent 百分比
	 * @throws IOException
	 */
	public static final void compressImage(String path, File file, String name, int width, int height, float percent, String imageFormat) throws IOException {
		if (StringUtils.isEmpty(path) || StringUtils.isEmpty(name)) {
			return;
		}
		String suffix = "_" + width + "×" + height;
		Image src = ImageIO.read(file); // 构造Image对象
		String img_midname = path + name.substring(0, name.indexOf(".")) + suffix + name.substring(name.indexOf("."));
		int old_w = src.getWidth(null); // 得到源图宽
		int old_h = src.getHeight(null);
		int new_w = 0;
		int new_h = 0; // 得到源图长
		double w2 = (old_w * 1.00) / (width * 1.00);
		double h2 = (old_h * 1.00) / (height * 1.00);
		// 图片跟据长宽留白，成一个正方形图。
		BufferedImage oldpic;
		if (old_w > old_h) {
			oldpic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
		} else {
			if (old_w < old_h) {
				oldpic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
			} else {
				oldpic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
			}
		}
		Graphics2D g = oldpic.createGraphics();
		g.setColor(Color.white);
		if (old_w > old_h) {
			g.fillRect(0, 0, old_w, old_w);
			g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
		} else {
			if (old_w < old_h) {
				g.fillRect(0, 0, old_h, old_h);
				g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
			} else {
				// g.fillRect(0,0,old_h,old_h);
				g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
			}
		}
		g.dispose();
		src = oldpic;
		// 图片调整为方形结束
		if (old_w > width)
			new_w = (int) Math.round(old_w / w2);
		else
			new_w = old_w;
		if (old_h > height)
			new_h = (int) Math.round(old_h / h2);// 计算新图长宽
		else
			new_h = old_h;
		BufferedImage image_to_save = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		image_to_save.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		FileOutputStream fos = new FileOutputStream(img_midname); // 输出到文件流
		switch (imageFormat) {
		case IMAGE_FORMAT_JPEG:
			saveAsJPEG(100, image_to_save, percent, fos);
			break;
		default:
			saveAsJPEG(100, image_to_save, percent, fos);
			break;
		}
		fos.close();
	}

	/**
	 * 另存为JPEG
	 * 
	 * @param dpi 分辨率
	 * @param image_to_save 要处理的图像图片
	 * @param JPEGcompression 压缩比
	 * @param fos 文件输出流
	 * @throws IOException
	 */
	public static final void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {
		// Image writer
		JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		// and metadata
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);
		if (dpi != null) {
			Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi));
			jfif.setAttribute("Ydensity", Integer.toString(dpi));
		}
		if (JPEGcompression >= 0 && JPEGcompression <= 1f) {
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);
		}
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();
	}
	
}
