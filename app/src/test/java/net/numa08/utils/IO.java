package net.numa08.utils;

import org.apache.commons.io.output.StringBuilderWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class IO {

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static String load(InputStream input) throws IOException {
        final StringBuilderWriter sw = new StringBuilderWriter();
        final InputStreamReader reader = new InputStreamReader(input, Charset.defaultCharset());
        copy(reader, sw, new char[DEFAULT_BUFFER_SIZE]);
        return sw.toString();
    }

    public static long copy(Reader input, Writer output, char[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while(EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
