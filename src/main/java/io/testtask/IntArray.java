package io.testtask;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntArray {

    public static void concatArraysFromFilesAndSortDistinctValuesToConsole(String firstFileName, String secondFileName) throws IOException, NullPointerException {
        concatArraysAndSortDistinctValuesToConsole(
                uploadArrayFromFile(firstFileName, " "),
                uploadArrayFromFile(secondFileName, " ")
        );
    }

    public static void concatArraysAndSortDistinctValuesToConsole(int[] firstArrays, int[] secondArrays) {
        int[] result = IntStream.concat(IntStream.of(checkAndGetArray(firstArrays)), IntStream.of(checkAndGetArray(secondArrays)))
                .sorted()
                .distinct()
                .toArray();
        System.out.print(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] uploadArrayFromFile(String fileName, String delimiter) throws IOException, NullPointerException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        String line = StringUtils.normalizeSpace(IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8));
        String[] splitLine = line.split(delimiter);
        return Arrays.stream(splitLine)
                .mapToInt(Integer::parseInt)
                .toArray();

    }

    private static int[] checkAndGetArray(int[] array) {
        if (array == null) {
            log.warn("Array was nullable");
            return new int[]{};
        }
        return array;
    }
}
