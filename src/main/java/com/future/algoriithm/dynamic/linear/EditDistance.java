package com.future.algoriithm.dynamic.linear;

/**
 * 编辑距离算法
 * <p>
 * 给定两个单词word1和word2，计算出将word1转换成word2所使用的最小操作数
 * 你可以对一个单词进行三种操作：
 * 1、插入一个字符
 * 2、删除一个字符
 * 3、替换一个字符
 * <p>
 * 示例：
 * 输入： word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (替换'h')
 * rorse -> rose (删除'r')
 * rose -> ros (删除'e')
 *
 * @author jayzhou
 */
public class EditDistance {

    /**
     * 一维数组的空间优化
     * 时间复杂度: O(n2)
     * 空间复杂度: O(n)
     */
    public static int product(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[] f = new int[chars2.length + 1];
        int leftTop, lastTop;
        for (int i = 0; i <= chars1.length; i++) {
            lastTop = 0;
            for (int j = 0; j <= chars2.length; j++) {
                leftTop = lastTop;
                lastTop = f[j];
                if (i == 0 && j == 0) {
                    f[j] = 0;
                } else if (i == 0) {
                    f[j] = j;
                } else if (j == 0) {
                    f[j] = i;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[j] = leftTop;
                    } else {
                        f[j] = Math.min(Math.min(f[j], f[j - 1]), leftTop) + 1;
                    }
                }
            }
        }
        return f[chars2.length];
    }

    /**
     * 滚动数组压缩
     * 时间复杂度：O(n2)
     * 空间复杂度：2 * O(n)
     */
    public static int beta(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int[][] f = new int[2][chars2.length + 1];
        for (int i = 0; i <= chars1.length; i++) {
            for (int j = 0; j <= chars2.length; j++) {
                if (i == 0 && j == 0) {
                    f[i & 1][j] = 0;
                } else if (i == 0) {
                    f[i & 1][j] = j;
                } else if (j == 0) {
                    f[i & 1][j] = i;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[i & 1][j] = f[(i - 1) & 1][j - 1];
                    } else {
                        f[i & 1][j] = Math.min(Math.min(f[(i - 1) & 1][j], f[i & 1][j - 1]), f[(i - 1) & 1][j - 1]) + 1;
                    }
                }
            }
        }
        return f[chars1.length & 1][chars2.length];
    }

    /**
     * 这其实是一道坐标型dp的应用
     * 思维过程：
     * * 设字符串A="horse"，B="ros"。
     * * 1）在单词A中增加一个字符。如果我们知道"horse"转换为"ro"需要的操作数为a，则A转换为B的操作数不会超过a+1；
     * * 2) 在单词A中删除一个字符。如果我们知道"hors"转换为"ros"需要的操作数为b，则A转换为B的操作数不会超过b+1；
     * * 3）在单词A中替换一个字符。我们我们知道"hors"转换为"ro"需要的操作数为c，则A转换为B的操作数不会超过c+1；
     * 那么从A转换为B的最少操作数应该为 min(a+1,b+1,c+1);
     * 另外一个示例：A="sea"，B="eat"。（不能用字符串长度类比，因为删除s之后，ea和eat，前面两个字符相等）
     * <p>
     * 阶段：将word1的前i个字符转换成word2的前j个字符，
     * 状态：将word1的前i个字符转换成word2的前j个字符所需要的最少操作数.故有 f[i][j] = min{xxx};
     * 决策：如果 f[i[j]中。word1[i] == word2[j]，则 f[i][j] = f[i-1][j-1]。否则，有下列三种情况：
     * 1、在word1中添加一个字符，使得 word1[i+1] == word[j], 则有：f[i][j] = f[i][j-1] + 1;
     * 2、在word1中替换一个字符，使得 word[i] == word[j], 则有: f[i][j] = f[i-1][j-1] + 1;
     * 3、在word1中删除一个字符, 使得 word[i-1] == word[j], 则有：f[i][j] = f[i-1][j] + 1;
     * 最优策略：从上述4种情况中寻找操作数最少的情况，构成一个子问题最优解，从而推导出全局最优解
     * 状态转移方程：f[i][j] = (word1[i]==word2[j])? f[i-1][j-1]: min{f[i-1][j-1], f[i][j-1], f[i-1][j]} + 1;
     *
     * 时间复杂度: O(n2)
     * 空间复杂度：O(n2)
     */
    public static int alpha(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        // 坐标型dp需要考虑初始值为0的情况。
        int[][] f = new int[chars1.length + 1][chars2.length + 1];
        for (int i = 0; i <= chars1.length; i++) {
            for (int j = 0; j <= chars2.length; j++) {
                if (i == 0 && j == 0) {
                    // 空串转空串
                    f[i][j] = 0;
                } else if (i == 0) {
                    // 空串转f[0][j]
                    f[i][j] = j;
                } else if (j == 0) {
                    // f[i][0]转空串
                    f[i][j] = i;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[i][j] = f[i - 1][j - 1];
                    } else {
                        f[i][j] = Math.min(Math.min(f[i - 1][j - 1], f[i][j - 1]), f[i - 1][j]) + 1;
                    }
                }
            }
        }
        return f[chars1.length][chars2.length];
    }

    public static void main(String[] args) {
        String word1 = "a";
        String word2 = "a";
        int alpha = alpha(word1, word2);
        System.out.println("alpha=" + alpha);
        int beta = beta(word1, word2);
        System.out.println("beta=" + beta);
        int product = product(word1, word2);
        System.out.println("product=" + product);
    }
}
