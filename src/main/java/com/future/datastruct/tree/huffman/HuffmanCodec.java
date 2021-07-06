package com.future.datastruct.tree.huffman;

import com.future.datastruct.node.WeightNode;
import com.future.datastruct.list.Queue;
import com.future.algoriithm.search.HashTab;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 哈夫曼编解码器
 */
public class HuffmanCodec {

    public static HuffmanTable<Byte> createHuffmanTable(byte[] bytes) {
        HashTab<Byte, Integer> countTab = new HashTab<>();
        for (int j = 0; j < bytes.length; j++) {
            Integer count = countTab.get(bytes[j]);
            if (count == null) {
                count = 0;
            }
            countTab.put(bytes[j], count + 1);
        }
        WeightNode<Byte>[] nodes = new WeightNode[countTab.size()];
        Iterator<Byte> iterator1 = countTab.iterator();
        int i = 0;
        while (iterator1.hasNext()) {
            Byte ch = iterator1.next();
            nodes[i] = HuffmanTree.newNode(ch, countTab.get(ch));
            i++;
        }
        HuffmanTree<Byte> tree = HuffmanTree.createTree(nodes);
//        PrintUtils.println(tree.weightPathLength());
        return tree.generateTable();
    }

    public static byte[] encode(byte[] bytes, HuffmanTable<Byte> table) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte aByte = bytes[i];
            builder.append(table.get(aByte));
        }
//        PrintUtils.println("encode length=" + builder.length());
//        PrintUtils.println("encode string=" + builder);
        int len = (builder.length() + 7) / 8;
        byte[] codecBytes = new byte[len];
        int j = 0;
        for (int i = 0; i < builder.length(); i += 8) {
            int end = i + 8;
            if (end > builder.length()) {
                end = builder.length();
            }
            String bitString = builder.substring(i, end);
            codecBytes[j] = bit2byte(bitString);
            j++;
        }
//        PrintUtils.println("encode bytes=" + Arrays.toString(codecBytes));
        return codecBytes;
    }

    private static byte bit2byte(String binaryStr) {
        byte result = 0;
        byte[] bytes = binaryStr.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            result += (bytes[i] - 48) * (1 << (bytes.length - 1 - i));
        }
        return result;
    }

    private static String byte2bitString(byte b, boolean keepHighBit) {
        int temp = b;
        if (keepHighBit)
            temp |= 0x100;
        String s = Integer.toBinaryString(temp);
        if (s.length() <= 8) {
            return s;
        }
        return s.substring(s.length() - 8);
    }

    public static byte[] decode(byte[] bytes, HuffmanTable<Byte> table) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String s = byte2bitString(bytes[i], i < bytes.length - 1);
            builder.append(s);
        }
        Queue<Byte> queue = new Queue<>();
        for (int i = 0; i < builder.length(); ) {
            int count = 0;
            while (i + count <= builder.length()) {
                String codec = builder.substring(i, i + count);
                Byte aByte = table.get(codec);
                if (aByte != null) {
                    queue.push(aByte);
                    break;
                }
                count++;
            }
            i += count;
        }
        byte[] result = new byte[queue.size()];
        int i = 0;
        for (Byte aByte : queue) {
            result[i] = aByte;
            i++;
        }
        return result;
    }
}
