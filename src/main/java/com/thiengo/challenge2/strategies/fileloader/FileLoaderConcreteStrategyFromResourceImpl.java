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
 * This concrete strategy implements a load from resources
 *
 * @author tiago
 */
public class FileLoaderConcreteStrategyFromResourceImpl
        implements FileLoaderStrategy {

    @Override
    public Optional<InputStream> getFileAsInputStream(String fileName) {

        return Optional.ofNullable(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream(fileName));

    }

    @Override
    public Optional<InputStream> getFileAsInputStream(Path filePath) {
//        The concrete strategy implementation by file path differ only by 
//        parameter manipulation
        return getFileAsInputStream(filePath.getFileName().toString());
    }

}
