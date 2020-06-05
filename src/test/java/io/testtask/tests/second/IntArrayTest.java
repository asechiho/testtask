package io.testtask.tests.second;

import io.testtask.IntArray;
import io.testtask.utils.Assert;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class IntArrayTest {

    @Test(dataProvider = "validArrayCombinations")
    public void concatArrayTest(int[] first, int[] second, String expectedResult) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream, true));

        IntArray.concatArraysAndSortDistinctValuesToConsole(first, second);

        String result = byteArrayOutputStream.toString();
        Assert.getAssert().getHardAssert().assertEquals(result, expectedResult);
    }

    @SneakyThrows
    @Test(dataProvider = "validFiles")
    public void checkReadArrayFromFiles(String fileName, int[] expectedArray) {
        int[] array = IntArray.uploadArrayFromFile(fileName, " ");
        Assert.getAssert().getHardAssert().assertEquals(array, expectedArray);
    }

    @SneakyThrows
    @Test
    public void checkUploadArrayFromFileAndLogToConsole() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream, true));

        IntArray.concatArraysFromFilesAndSortDistinctValuesToConsole("testdata/first.txt", "testdata/second.txt");

        String result = byteArrayOutputStream.toString();
        Assert.getAssert().getHardAssert().assertEquals(result, "-100000 -100 -3 -2 -1 0 1 2 3 5 6 7 100 1000000");
    }

    @DataProvider
    private Object[][] validFiles() {
        return new Object[][]{
                {"testdata/first.txt", new int[]{1, 3, 6, 1, 2}},
                {"testdata/second.txt", new int[]{-100, -100000, -1, -2, -3, 0, 5, 7, 3, 100, 1000000}},
                {"testdata/onenum.txt", new int[]{-100}},
                {"testdata/moreonespace.txt", new int[]{-100, 12, 1, 1}}
        };
    }

    @DataProvider
    private Object[][] validArrayCombinations() {
        return new Object[][]{
                {new int[]{1, 3, 6, 1, 2}, new int[]{5, 7, 3}, "1 2 3 5 6 7"},
                {new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}, Integer.MIN_VALUE + " " + Integer.MAX_VALUE},
                {new int[]{-1, -9, 0, 9, 8, 0}, new int[]{}, "-9 -1 0 8 9"},
                {new int[]{}, new int[]{-1, 0, 9, 8, 0}, "-1 0 8 9"},
                {null, new int[]{1, 2}, "1 2"},
                {new int[]{1, 2}, null, "1 2"}
        };
    }
}
