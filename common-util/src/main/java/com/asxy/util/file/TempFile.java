package com.asxy.util.file;

/**
 *
 * 生成临时文件
 *
 * @author asxy
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


public class TempFile implements Closeable {
    public synchronized static TempFile create(String extension) throws IOException {
        Path temp = Paths.get("./temp");
        File dir = new File(temp.toUri());
        if (!dir.exists()) {
            dir.mkdir();
        }
        Path filePath = Paths.get(temp.toString(), String.format("%s.%s", UUID.randomUUID().toString().replace("-", ""), extension));
        File file = new File(filePath.toUri());
        if (!file.createNewFile()) {
            throw new IOException(String.format("文件[%s]已存在创建失败", file.toURI()));
        }
        return new TempFile(filePath.toString());
    }

    public synchronized static TempFile createVirtual(String extension) throws IOException {
        Path temp = Paths.get("./temp");
        File dir = new File(temp.toUri());
        if (!dir.exists()) {
            dir.mkdir();
        }
        Path filePath = Paths.get(temp.toString(), String.format("%s.%s", UUID.randomUUID().toString().replace("-", ""), extension));
        return new TempFile(filePath.toString());
    }

    private String path;

    private TempFile(String filePath) {
        this.path = filePath;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        return new File(this.getPath());
    }

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(this.getPath());
    }

    public OutputStream getOutputStream() throws FileNotFoundException {
        return new FileOutputStream(this.getPath(),true);
    }

    public void copy(String targetPath) throws IOException {
        Files.copy(Paths.get(this.getPath()), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void close() throws IOException {
        synchronized (this) {
            if (this.getFile().exists()) {
                this.getFile().delete();
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            this.close();
        } finally {
            super.finalize();
        }
    }
}