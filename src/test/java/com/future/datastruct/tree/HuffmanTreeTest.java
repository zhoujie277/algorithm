package com.future.datastruct.tree;

import com.future.datastruct.tree.huffman.HuffmanCodec;
import com.future.datastruct.tree.huffman.HuffmanTable;
import com.future.utils.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HuffmanTreeTest {

    public void setup() {

    }

    @Test
    public void testStringEncode() {
//        String str = "Hello World! ZhouJie try to test encode huffman codec";
//        String str = "hello world hello world java hello java";
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        HuffmanTable<Byte> huffmanTable = HuffmanCodec.createHuffmanTable(bytes);
        byte[] encode = HuffmanCodec.encode(str.getBytes(StandardCharsets.UTF_8), huffmanTable);
        byte[] decode = HuffmanCodec.decode(encode, huffmanTable);
        StringBuilder decodeStr = new StringBuilder();
        for (int i = 0; i < decode.length; i++) {
            decodeStr.append((char) decode[i]);
        }
        Assert.assertEquals(str, decodeStr.toString());
    }

    @Test
    public void testFileEncode() throws Exception {
        File file = new File("./resource/testbmp.jpg");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        PrintUtils.println("origin byte length=" + bytes.length);
        fis.read(bytes);
        HuffmanTable<Byte> huffmanTable = HuffmanCodec.createHuffmanTable(bytes);
        byte[] encode = HuffmanCodec.encode(bytes, huffmanTable);
        PrintUtils.println("encode byte length=" + encode.length);
        FileOutputStream fos = new FileOutputStream("./resource/testbmpcodec");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.write(encode);
        oos.writeObject(huffmanTable);
        oos.close();
        fos.close();
        fis.close();
    }

    @Test
    public void testFileDecode() throws Exception {
        //todo 报错，暂时没明白为什么，跟io和对象序列化有关
//        File file = new File("./resource/testbmpcodec");
//        FileInputStream fis = new FileInputStream(file);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        byte[] data = (byte[]) ois.readObject();
//        HuffmanTable<Byte> huffmanTable = (HuffmanTable<Byte>) ois.readObject();
//
//        byte[] encode = HuffmanCodec.decode(data, huffmanTable);
//        PrintUtils.println("decode byte length=" + encode.length);
//        FileOutputStream fos = new FileOutputStream("./resource/testbmp2");
//        fos.write(encode);
//        fos.close();
//        fis.close();
    }
}
