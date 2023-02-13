package com.wjyoption.common.utils.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ReduceImg {
	
	static Logger logger = LoggerFactory.getLogger(ReduceImg.class);
//    /**
//     * 指定图片宽度和高度和压缩比例对图片进行压缩
//     * 
//     * @param imgsrc
//     *            源图片地址
//     * @param imgdist
//     *            目标图片地址
//     * @param widthdist
//     *            压缩后图片的宽度
//     * @param heightdist
//     *            压缩后图片的高度
//     * @param rate
//     *            压缩的比例
//     */
//    public static boolean reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, Float rate) {
//        try {
//            File srcfile = new File(imgsrc);
//            // 检查图片文件是否存在
//            if (!srcfile.exists()) {
//                logger.info("文件不存在");
//                return false;
//            }
//            // 如果比例不为空则说明是按比例压缩
//            if (rate != null && rate > 0) {
//                //获得源图片的宽高存入数组中
//                int[] results = getImgWidthHeight(srcfile);
//                if (results == null || results[0] == 0 || results[1] == 0) {
//                    return false;
//                } else {
//                    //按比例缩放或扩大图片大小，将浮点型转为整型
//                    widthdist = (int) (results[0] * rate);
//                    heightdist = (int) (results[1] * rate);
//                }
//            }
//            System.out.println(widthdist+"::" + heightdist);
//            // 开始读取文件并进行压缩
//            Image src = ImageIO.read(srcfile);
//
//            // 构造一个类型为预定义图像类型之一的 BufferedImage
//            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
//
//            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
//            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
//
//            File distFile = new File(imgdist);
//            if(!distFile.getParentFile().exists()){
//            	distFile.getParentFile().mkdirs();
//            }
//            //创建文件输出流
//            FileOutputStream out = new FileOutputStream(distFile);
//            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            //关闭文件输出流
//            out.close();
//            return true;
//        } catch (Exception ef) {
//            logger.error(ef.getMessage(),ef);
//        }
//        return false;
//    }
    /**
     * 指定图片宽度和高度和压缩比例对图片进行压缩
     * 
     * @param imgsrc
     *            源图片地址
     * @param imgdist
     *            目标图片地址
     * @param widthdist
     *            压缩后图片的宽度
     * @param heightdist
     *            压缩后图片的高度
     * @param rate
     *            压缩的比例
     */
    public static boolean reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查图片文件是否存在
            if (!srcfile.exists()) {
                logger.info("文件不存在");
                return false;
            }
            // 如果比例不为空则说明是按比例压缩
            if (rate != null && rate > 0) {
                //获得源图片的宽高存入数组中
                int[] results = getImgWidthHeight(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return false;
                } else {
                    //按比例缩放或扩大图片大小，将浮点型转为整型
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            System.out.println(widthdist+"::" + heightdist);
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            File distFile = new File(imgdist);
            if(!distFile.getParentFile().exists()){
            	distFile.getParentFile().mkdirs();
            }
            //创建文件输出流
            FileOutputStream out = new FileOutputStream(distFile);
            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
            String name = srcfile.getName();
            String[] split = name.split("\\.");
            ImageIO.write(tag, split[split.length-1], out);
            out.flush();
            //关闭文件输出流
            out.close();
            return true;
        } catch (Exception ef) {
            logger.error(ef.getMessage(),ef);
        }
        return false;
    }

    /**
     * 获取图片宽度和高度
     * 
     * @param 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            // 获得文件输入流
            is = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            src = ImageIO.read(is);
            result[0] =src.getWidth(null); // 得到源图片宽
            result[1] =src.getHeight(null);// 得到源图片高
            is.close();  //关闭输入流
        } catch (Exception ef) {
            ef.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        String srcpath = "C:\\Users\\caoliang\\Pictures\\Camera Roll\\1Z629105604-2.jpg";
        String distpath = "D:\\down\\a\\1Z629105604-2-1.jpg";
        File srcfile = new File(srcpath); 
        File distfile = new File(distpath);
        
        System.out.println("压缩前图片大小：" + srcfile.length());
//        reduceImg(srcpath, distpath, 500, 500, null);
        reduceImg(srcpath, distpath, 0, 0, 0.1f);
        System.out.println("压缩后图片大小：" + distfile.length());

    }
}