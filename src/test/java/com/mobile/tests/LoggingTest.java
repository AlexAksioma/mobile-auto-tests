package com.mobile.tests;

import com.mobile.framework.config.MobileConfig;
import com.mobile.framework.core.AdbManager;
import com.mobile.tests.utils.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


@Listeners(TestNGListener.class)

public class LoggingTest {

    @BeforeMethod
    public void setupEnvironment() throws IOException, InterruptedException {
        if(AdbManager.isPackageInstalled(MobileConfig.APP_PACKAGE)){
            AdbManager.uninstallApp(MobileConfig.APP_PACKAGE);
        }
    }


    @Test
    public void installAppTestPositive() throws IOException, InterruptedException {
        AdbManager.installApp(MobileConfig.APK_PATH);
        Assert.assertTrue(AdbManager.isPackageInstalled(MobileConfig.APP_PACKAGE));
    }

    @Test
    public void installAppTestNegative_AlreadyExist() throws IOException, InterruptedException {
        AdbManager.installApp(MobileConfig.APK_PATH);

        RuntimeException exception = Assert.expectThrows(
                RuntimeException.class,
                ()->AdbManager.installApp(MobileConfig.APK_PATH)
        );

        Assert.assertTrue(exception.getMessage().contains("INSTALL_FAILED_ALREADY_EXISTS"));
    }


}
