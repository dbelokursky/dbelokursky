package ru.job4j.array;

/**
 * Class ArrayMerge. Решение задачи слияния двух отсортированных массивов.
 */
public class ArrayMerge {

    /**
     * Метод производит слияние двух отсортированных массивов.
     * @param fArr **первый массив**
     * @param sArr **второй массив**
     * @return **объедененный массив**
     */
    public int[] merge(int[] fArr, int[] sArr) {
        int[] result = new int[fArr.length + sArr.length];
        int fInd = 0;
        int sInd = 0;
        int rInd = 0;

        if (fArr.length == 0) {
            System.arraycopy(sArr, 0, result, 0, sArr.length);
        } else if (sArr.length == 0) {
            System.arraycopy(fArr, 0, result, 0, fArr.length);
        } else {
            while (rInd < result.length) {
                result[rInd] = fArr[fInd] < sArr[sInd] ? fArr[fInd++] : sArr[sInd++];
                if (fInd == fArr.length) {
                    System.arraycopy(sArr, sInd, result, ++rInd, sArr.length - sInd);
                    break;
                }
                if (sInd == sArr.length) {
                    System.arraycopy(fArr, fInd, result, ++rInd, fArr.length - fInd);
                    break;
                }
                rInd++;
            }

        }
        return result;
    }
}
