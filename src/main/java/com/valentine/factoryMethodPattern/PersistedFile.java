package com.valentine.factoryMethodPattern;

public class PersistedFile {

    private String path;
    private String content;
    private Encryptor encryptor;


    public PersistedFile(String path, String content, Encryptor encryptor) {
        this.path = path;
        this.content = content;
        this.encryptor = encryptor;
    }


    public void persist() {
        encryptor.writeToDisk(content, path);
    }

}

