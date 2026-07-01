package com.mobile.framework.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Provides methods for interacting with Android devices through ADB (Android Debug Bridge).
 *
 * This class is responsible for executing ADB commands such as installing
 * and uninstalling applications, starting and stopping applications,
 * capturing screenshots, and reading Logcat output.
 */
public final class AdbManager {
    private AdbManager(){}

    public static void installApp(String apkPath)
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "install", apkPath));
    }

    public static void reinstallApp(String apkPath)
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "install", "-r", apkPath));
    }

    public static void uninstallApp(String packageName)
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "uninstall", packageName));
    }

    public static void startApp(String packageName, String activityName)
            throws IOException, InterruptedException {

        String code = runCommand(List.of("adb", "shell", "am", "start", "-n",
                packageName + "/" + activityName));
    }

    public static void stopApp(String packageName)
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "shell", "am", "force-stop", packageName));
    }

    public static void clearLogcat()
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "logcat", "-c"));
    }

    public static String getLogcat()
            throws IOException, InterruptedException {

        return runCommand(List.of("adb", "logcat", "-d", "-t", "100", "*:E"));
    }

    public static void takeScreenshotToDevice(String fileName)
            throws IOException, InterruptedException {

        runCommand(List.of("adb", "shell", "screencap", "-p", "/sdcard/" + fileName));
    }

    public static void takeScreenshot(String fileName)
            throws IOException, InterruptedException {

        Path screenshotsDir = Path.of("build", "screenshots");
        Files.createDirectories(screenshotsDir);
        Path screenshotPath = screenshotsDir.resolve(fileName + ".png");

        runCommand(List.of("adb", "exec-out", "screencap", "-p"), screenshotPath);
    }

    public static boolean isPackageInstalled(String packageName)
            throws IOException, InterruptedException {

        String output = runCommand(List.of("adb", "shell", "pm", "list", "packages", packageName));
        return output.contains(packageName);
    }

    private static String runCommand(List<String> command)
            throws IOException, InterruptedException {

        Process process = new ProcessBuilder(command)
                .start();

        String output = new String(process.getInputStream().readAllBytes());
        String error = new String(process.getErrorStream().readAllBytes());

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException(
                    "ADB command \"" + String.join(" ", command)
                            + "\" failed with exit code " + exitCode
                            + (error.isBlank() ? "" : System.lineSeparator() + error)
            );
        }

        return output;
    }

    private static void runCommand(List<String> command, Path outputFile)
            throws IOException, InterruptedException {

        Process process = new ProcessBuilder(command)
                .redirectOutput(outputFile.toFile())
                .start();

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            String error = new String(process.getErrorStream().readAllBytes()).trim();

            throw new RuntimeException(
                    "ADB command \"" + String.join(" ", command)
                            + "\" failed with exit code " + exitCode
                            + (error.isEmpty() ? "" : System.lineSeparator() + error)
            );
        }
    }
}