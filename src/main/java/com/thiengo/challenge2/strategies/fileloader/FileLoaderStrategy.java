/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thiengo.challenge2.strategies.fileloader;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

/**
 * The File loader strategy will permit diferents ways to read files, returning
 * as InputStream to be treated later
 *
 * @author tiago
 */
public interface FileLoaderStrategy {

    /**
     * Strategy to read a file as IputStream by file name.
     *
     * @param fileName
     * @return
     * @see java.io.InputStream
     */
    public Optional<InputStream> getFileAsInputStream(final String fileName);

    /**
     * Strategy to read a file as IputStream by file path.
     *
     * @param filePath
     * @return
     * @see java.io.Path
     */
    public Optional<InputStream> getFileAsInputStream(final Path filePath);
}
