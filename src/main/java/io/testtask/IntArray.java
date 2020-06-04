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

    public static void concatArraysFromFilesAndSortDistinctValuesToConsole(String firstFileName, String secondFileName) {
        concatArraysAndSortDistinctValuesToConsole(
                uploadArrayFromFile(firstFileName, " "),
                uploadArrayFromFile(secondFileName, " ")
        );
    }

    public static void concatArraysAndSortDistinctValuesToConsole(int[] firstArrays, int[] secondArrays) {
        int[] result = IntStream.concat(IntStream.of(firstArrays), IntStream.of(secondArrays))
                .sorted()
                .distinct()
                .toArray();
        System.out.print(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static int[] uploadArrayFromFile(String fileName, String delimiter) {
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            String line = StringUtils.normalizeSpace(IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8));
            String[] splitLine = line.split(delimiter);
            return Arrays.stream(splitLine)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e.getCause());
            return new int[]{};
        } catch (NullPointerException e) {
            log.error("File Not Found: " + fileName, e.getCause());
            return new int[]{};
        }
    }
}
