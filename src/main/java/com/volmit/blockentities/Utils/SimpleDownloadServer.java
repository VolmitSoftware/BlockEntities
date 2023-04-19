package com.volmit.blockentities.Utils;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class SimpleDownloadServer {

    private static final String ERROR_INVALID_REQUEST = "The requested file is not allowed to be served by this server.";
    private static final String ERROR_NOT_FOUND = "The requested file cannot be found.";
    private static final String ERROR_FILE_TOO_BIG = "The requested file is larger than the allowed file size. [%d > %d]";

    private final File packDir;

    private HttpServer server;

    private long maxFileSize = -1;
    private String contentType = "application/octet-stream";
    private Predicate<File> filePredicate;

    public SimpleDownloadServer(String endpoint, File fileDir, String ip, int port) {
        this.packDir = fileDir;
        try {
            this.server = HttpServer.create(new InetSocketAddress(ip, port), 0);
            this.server.createContext("/" + endpoint + "/", this::handleRequest);
            this.server.setExecutor(null);
        } catch (IOException e) {
            System.out.println("Failed to start download server: " + e.getMessage());
        }
    }

    public SimpleDownloadServer setMaximumFilesize(long size) {
        this.maxFileSize = size;
        return this;
    }

    public SimpleDownloadServer setFilePredicate(Predicate<File> predicate) {
        this.filePredicate = predicate;
        return this;
    }

    public SimpleDownloadServer start() {
        this.server.start();
        return this;
    }

    private void handleRequest(HttpExchange e) throws IOException {
        File desiredFile = new File(packDir, e.getRequestURI().toString().substring(e.getHttpContext().getPath().length()));
        System.out.println("Received download request for file \"" + FilenameUtils.getName(desiredFile.getName()) + "\"...");
        if (!verifyRequest(e, desiredFile)) {
            System.out.println("Failed to serve file due to invalid request.");
            return;
        }


        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(desiredFile))) {
            int filesize = (int) Files.size(Path.of(desiredFile.getPath()));
            byte[] array = new byte[filesize];
            is.read(array, 0, filesize);
            try (OutputStream os = e.getResponseBody()) {
                e.getResponseHeaders().add("Content-Type", contentType);
                e.sendResponseHeaders(200, filesize);
                os.write(array);
            }
        }
        System.out.println("Successfully served file.");
    }

    private boolean verifyRequest(HttpExchange e, File f) throws IOException {
        if (filePredicate != null && !filePredicate.test(f)) {
            e.getResponseHeaders().add("Content-Type", "text/plain");
            e.sendResponseHeaders(403, ERROR_INVALID_REQUEST.length());
            try (OutputStream os = e.getResponseBody()) {
                os.write(ERROR_INVALID_REQUEST.getBytes());
            }
            return false;
        }

        if (!f.exists() || f.isDirectory()) {
            e.getResponseHeaders().add("Content-Type", "text/plain");
            e.sendResponseHeaders(404, ERROR_NOT_FOUND.length());
            try (OutputStream os = e.getResponseBody()) {
                os.write(ERROR_NOT_FOUND.getBytes());
            }
            return false;
        }

        if (this.maxFileSize > 0) {
            long filesize = Files.size(Path.of(f.getPath()));
            if (filesize > this.maxFileSize) {
                e.getResponseHeaders().add("Content-Type", "text/plain");
                String msg = String.format(ERROR_FILE_TOO_BIG, filesize, this.maxFileSize);
                e.sendResponseHeaders(403, msg.length());
                try (OutputStream os = e.getResponseBody()) {
                    os.write(msg.getBytes());
                }
                return false;
            }
        }

        return true;
    }
}