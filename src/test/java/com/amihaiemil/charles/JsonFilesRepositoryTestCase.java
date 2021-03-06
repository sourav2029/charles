/**
 * Copyright (c) 2016-2017, Mihai Emil Andronache
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of charles nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */package com.amihaiemil.charles;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for {@link JsonFilesRepository}
 * @author Mihai Andronache (amihaiemil@gmail.com)
 */
public class JsonFilesRepositoryTestCase {
    
    @Test
    public void exportsPagesToFiles() throws Exception {
        WebPage page = new SnapshotWebPage(Mockito.mock(LivePage.class));
        page.setTextContent("text on page");
        page.setTitle("Title | Page");
        page.setName("index");
        page.setUrl("http://amihaiemil.com");

        
        List<WebPage> pages = new ArrayList<WebPage>();
        pages.add(page);
        Repository testRepo = new JsonFilesRepository("src/test/resources/");
        testRepo.export(pages);
        SnapshotWebPage readPage = (new ObjectMapper()).readValue(
        new File("src/test/resources/index.json"),
        SnapshotWebPage.class
        );
        
        assertTrue(readPage.getTitle().equals(page.getTitle()));
        assertTrue(readPage.getTextContent().equals(page.getTextContent()));

        assertTrue(readPage.getUrl().equals(page.getUrl()));
        
    }
}
