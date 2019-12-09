package com.moseeker.util;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description TODO
 * @Author Rays
 * @DATE 2019-09-03
 **/
public class FileCheckTool {

    private static class MagicNumberMatcher {

        private final byte[] head ;
        private byte[] tail ;

        public MagicNumberMatcher(byte[] head) {
            this.head = head;
        }
        public MagicNumberMatcher(byte[] head, byte[] tail) {
            this.head = head;
            this.tail = tail;
        }
        public boolean match(byte[] fileData){
            if ( head != null && head.length > 0){
                for(int i=0;i<head.length;i++){
                    if(fileData[i] != head[i]){
                        return false ;
                    }
                }
            }
            if ( tail != null && tail.length > 0){
                //
                int lenDiff = fileData.length - tail.length;
                if ( lenDiff < 0) return false ;

                for(int i=0;i<tail.length;i++){
                    if(fileData[lenDiff+i] != tail[i]){
                        return false ;
                    }
                }
            }
            return true ;
        }
    }

    /**
     * 利用magic number判断文件格式
     * reference: http://wikipedia.moesalih.com/Magic_number_(programming)#Magic_numbers_in_files
     */
    //private static final byte[] MAGIC_TXT = {(byte) 0x41,(byte) 0x47,(byte) 0x6B,(byte) 0x49} ;
    private static final byte[] MAGIC_JPEG_HEAD = {(byte) 0xFF,(byte) 0xD8} ;
    private static final byte[] MAGIC_JPEG_END = {(byte) 0xFF,(byte) 0xD9} ;
    private static final byte[] MAGIC_PNG = {(byte) 0x89,(byte) 0x50,(byte) 0x4E,(byte) 0x47,
            (byte) 0x0D,(byte) 0x0A,(byte) 0x1A,(byte) 0x0A} ; // \211 P N G \r \n \032 \n
    private static final byte[] MAGIC_PDF = {(byte) 0x25,(byte) 0x50,(byte) 0x44,(byte) 0x46} ; // %PDF
    private static final byte[] MAGIC_JPG = {(byte) 0xFF,(byte) 0xD8,(byte) 0xFF,(byte) 0xE0} ;
    private static final byte[] MAGIC_MICROSOFT_OFFICE = {(byte) 0xD0,(byte) 0xCF,(byte) 0x11,(byte) 0xE0} ; // doc xls
    private static final byte[] MAGIC_MICROSOFT_OFFICE_NEW = {(byte) 0x50,(byte) 0x4B,(byte) 0x3,(byte) 0x4} ;// docx xlsx

    private static List<String> fileSupport = new ArrayList<String>(){{add(".DOC");add(".DOCX");add(".PDF");add(".JPG");add(".JPEG");add(".PNG");add(".HTML");add(".XML");}};

    private static List<String> imgFileSupport = new ArrayList<String>(){{add(".PNG");add(".JPG");add(".JPEG");
        add(".PNG");}};
    private static List<MagicNumberMatcher> MAGIC_NUMBER_MATCHERS = new ArrayList<>();
    private static List<MagicNumberMatcher> IMG_MAGIC_NUMBER_SUPPORT = new ArrayList<>();

    private static long fileSize = 1024*1024*5;  //5M

    static {
        IMG_MAGIC_NUMBER_SUPPORT.add(new MagicNumberMatcher(MAGIC_JPEG_HEAD,MAGIC_JPEG_END));
        for (byte[] head : Arrays.asList(MAGIC_JPG, MAGIC_PNG)) {
            IMG_MAGIC_NUMBER_SUPPORT.add(new MagicNumberMatcher(head));
        }

        for (byte[] head : Arrays.asList( MAGIC_MICROSOFT_OFFICE, MAGIC_PDF, MAGIC_MICROSOFT_OFFICE_NEW)) {
            MAGIC_NUMBER_MATCHERS.add(new MagicNumberMatcher(head));
        }
        MAGIC_NUMBER_MATCHERS.addAll(IMG_MAGIC_NUMBER_SUPPORT);
    }

    public static boolean checkFileLength(long length) {
        return checkFileLength(length, null);
    }

    public static boolean checkMagicNumber(byte[] bytes) {
        for( MagicNumberMatcher matcher : MAGIC_NUMBER_MATCHERS){
            if(matcher.match(bytes)){
                return true ;
            }
        }
        return false ;
    }

    public static boolean checkImgMagicNumber(byte[] bytes) {
        for( MagicNumberMatcher matcher : IMG_MAGIC_NUMBER_SUPPORT){
            if(matcher.match(bytes)){
                return true ;
            }
        }
        return false ;
    }

    public static boolean checkFileLength(long length, Long lengthUpper) {
        long lengthMax;
        if (lengthUpper == null || lengthUpper <= 0) {
            lengthMax = fileSize;
        } else {
            lengthMax = lengthUpper;
        }
        return length < lengthMax;
    }

    /**
     * 校验文件是否是支持的文件格式
     * @param fileName 文件名称
     * @param fileSupport 支持的文件后缀
     * @return true 支持；false 不支持
     */
    public static boolean checkFileName(String fileName, List<String> fileSupport) {
        List<String> fileSupportParam;
        if (fileSupport == null || fileSupport.size() == 0) {
            fileSupportParam = FileCheckTool.fileSupport;
        } else {
            fileSupportParam = fileSupport;
        }
        for (String support : fileSupportParam) {
            if (fileName.toUpperCase().endsWith(support)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验文件是否是支持的文件格式
     * @param fileName 文件名称
     * @return true 支持；false 不支持
     */
    public static boolean checkFileName(String fileName) {
        return checkFileName(fileName, null) ;
    }

    /**
     * 校验文件是否是支持的文件格式
     * @param fileName 文件名称
     * @return true 支持；false 不支持
     */
    public static boolean checkImgFileName(String fileName) {
        return checkFileName(fileName, FileCheckTool.imgFileSupport) ;
    }

    /**
     * 猎聘网导出的简历是html格式，后缀名".doc"
     * @param fileContent 文本内容
     * @return
     */
    private static boolean isHtmlOrXml(byte[] fileContent){
        // 跳过空格
        int skipBlank = 0 ;
        byte[] blankChars = new byte[]{' ','\t','\n','\r'};
        do{
            if(!ArrayUtils.contains(blankChars,fileContent[skipBlank])){
                break;
            }
        }while (++skipBlank<fileContent.length);
        // xml及html第一个非空字符一定是'<'
        if(skipBlank >= fileContent.length - 4 || fileContent[skipBlank] != ((byte)'<')){
            return false ;
        }

        String html = new String(fileContent,skipBlank,fileContent.length-skipBlank, com.google.common.base.Charsets.UTF_8);

        // 如果不是以'<'开头，跳过
        // 如果有注释，跳过注释
        for(String prefix : new String[]{"<?xml ","<html","<!DOCTYPE","<?XML","<HTML","<!doctype"}){
            if( html.startsWith(prefix)) return true ;
        }
        return false ;
    }
    /**
     * 校验文件是否是支持的文件格式
     * @param fileName 文件名称
     * @param fileContent 文件内容
     * @return true 支持；false 不支持
     */
    public static boolean checkFileFormat(String fileName,byte[] fileContent) {
        if(fileContent.length <= 4) {
            // 如果文件太小，不可能匹配任何文档或图片格式，直接返回false。造成的原因可能是空文件。
            return false ;
        }
        if(fileName.toUpperCase().endsWith(".TXT")) return true;
        if( !checkFileName(fileName)) return false;
        return checkMagicNumber(fileContent) || isHtmlOrXml(fileContent);
    }

    /**
     * 校验文件是否是支持的文件格式
     * @param fileContent 文件内容
     * @return true 支持；false 不支持
     */
    public static boolean checkFileFormat(byte[] fileContent) {
        return checkMagicNumber(fileContent) || isHtmlOrXml(fileContent);
    }

    /**
     * 校验文件是否是支持的文件格式
     * @param fileName 文件名称
     * @param fileContent 文件内容
     * @return true 支持；false 不支持
     */
    public static boolean checkImgFileFormat(String fileName,byte[] fileContent) {
        return checkImgFileName(fileName) && checkImgMagicNumber(fileContent);
    }

   /* public static void main(String[] args) throws IOException {
        File file = new File("/Users/qiancheng/Desktop/fileverify/py 转图片.png");//"郭倩倩-37岁-15年经验-推广主任-猎聘简历(1).doc");
        String content = new String(IOUtils.toByteArray(new FileInputStream(file)));
        System.out.print(checkFileFormat(file.getName(), IOUtils.toByteArray(new FileInputStream(file))));
    }*/
}
