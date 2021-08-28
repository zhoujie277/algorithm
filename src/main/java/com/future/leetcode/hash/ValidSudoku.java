package com.future.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 请你判断一个9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 * <p>
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 *
 * @author jayzhou
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, HashSet<Character>> rowMap = new HashMap<>();
        HashMap<Integer, HashSet<Character>> columnMap = new HashMap<>();
        HashMap<Integer, HashSet<Character>> childMap = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                HashSet<Character> rowSet = rowMap.getOrDefault(i, new HashSet<>());
                if (rowSet.contains(c)) {
                    return false;
                }
                rowSet.add(c);
                rowMap.put(i, rowSet);

                HashSet<Character> columnSet = columnMap.getOrDefault(j, new HashSet<>());
                if (columnSet.contains(c)) {
                    return false;
                }
                columnSet.add(c);
                columnMap.put(j, columnSet);
                int childKey = 3 * (i / 3) + (j / 3);
                HashSet<Character> childSet = childMap.getOrDefault(childKey, new HashSet<>());
                if (childSet.contains(c)) {
                    return false;
                }
                childSet.add(c);
                childMap.put(childKey, childSet);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //输入：board =
        //[['5','3','.','.','7','.','.','.','.']
        //,['3','.','.','1','9','5','.','.','.']
        //,['.','9','8','.','.','.','.','3','.']
        //,['8','.','.','.','3','.','.','.','3']
        //,['4','.','.','8','.','3','.','.','1']
        //,['7','.','.','.','2','.','.','.','3']
        //,['.','3','.','.','.','.','2','8','.']
        //,['.','.','.','4','1','9','.','.','5']
        //,['.','.','.','.','8','.','.','7','9']]
        //输出：true
        //
        char[][] table = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'3', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '3', '.'},
                {'8', '.', '.', '.', '3', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '3'},
                {'.', '3', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        ValidSudoku sudoku = new ValidSudoku();
        System.out.println(sudoku.isValidSudoku(table));
    }
}
