package net.numa08.gistlist.gist;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GistAPIBaseTest {

    @Test
    public void testUrl() {
        assertThat(GistAPIBase.PUBLIC_LIST, is("https://api.github.com/gists/public"));
    }
}
