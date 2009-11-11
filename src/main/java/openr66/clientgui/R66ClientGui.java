/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package openr66.clientgui;

import goldengate.common.logging.GgInternalLogger;
import goldengate.common.logging.GgInternalLoggerFactory;
import goldengate.common.logging.GgSlf4JLoggerFactory;

import org.jboss.netty.logging.InternalLoggerFactory;

import ch.qos.logback.classic.Level;

import openr66.client.DirectTransfer;
import openr66.client.Message;
import openr66.configuration.FileBasedConfiguration;
import openr66.context.ErrorCode;
import openr66.context.R66Result;
import openr66.database.DbConstant;
import openr66.database.data.DbHostAuth;
import openr66.database.data.DbRule;
import openr66.database.exception.OpenR66DatabaseNoConnectionError;
import openr66.database.exception.OpenR66DatabaseSqlError;
import openr66.protocol.configuration.Configuration;
import openr66.protocol.localhandler.packet.TestPacket;
import openr66.protocol.localhandler.packet.ValidPacket;
import openr66.protocol.networkhandler.NetworkTransaction;
import openr66.protocol.utils.ChannelUtils;
import openr66.protocol.utils.R66Future;

/**
 *
 * @author frederic
 */
public class R66ClientGui {
    /**
     * Internal Logger
     */
    static protected volatile GgInternalLogger logger;

    public static NetworkTransaction networkTransaction = null;

    public static void main(String[] args) {
        InternalLoggerFactory.setDefaultFactory(new GgSlf4JLoggerFactory(
                Level.WARN));
        if (logger == null) {
            logger = GgInternalLoggerFactory.getLogger(R66ClientGui.class);
        }
        if (args.length < 1) {
            System.err.println("Need client with no database support configuration file as argument");
            System.exit(2);
        }
        if (! FileBasedConfiguration
                .setClientConfigurationFromXml(args[0])) {
            logger
                    .error("Needs a correct configuration file as first argument");
            if (DbConstant.admin != null && DbConstant.admin.isConnected) {
                DbConstant.admin.close();
            }
            ChannelUtils.stopLogger();
            System.exit(2);
        }
        Configuration.configuration.pipelineInit();
        networkTransaction = new NetworkTransaction();
        R66ClientGuiApp.start(args);
    }

    public static void exit() {
        if (networkTransaction != null) {
            networkTransaction.closeAll();
            networkTransaction = null;
        }
        System.exit(0);
    }

    public static String GuiHostId;

    public static String GuiInfo;

    public static String GuiRule;

    public static boolean GuiMd5;

    public static String GuiFile;

    public static String GuiResultat;

    public static void checkConnection() {
        R66Future result = new R66Future(true);
        TestPacket packet = new TestPacket("MSG", "TestConnection", 100);
        Message transaction = new Message(networkTransaction, result,
                GuiHostId, packet);
        transaction.run();
        result.awaitUninterruptibly();
        if (result.isSuccess()) {
            R66Result r66result = result.getResult();
            ValidPacket info = (ValidPacket) r66result.other;
            GuiResultat = "<html>Test Message\n    SUCCESS\n    " +
                    info.getSheader();
        } else {
            GuiResultat = "<html>Test Message\n    FAILURE\n    " +
                    result.getResult().toString();
        }
    }

    public static void startsTransfer() {
        long time1 = System.currentTimeMillis();
        R66Future future = new R66Future(true);
        DirectTransfer transaction = new DirectTransfer(future, GuiHostId,
                GuiFile, GuiRule, GuiInfo, GuiMd5, 65536, networkTransaction);
        transaction.run();
        future.awaitUninterruptibly();
        long time2 = System.currentTimeMillis();
        long delay = time2 - time1;
        R66Result result = future.getResult();
        if (future.isSuccess()) {
            if (result.runner.getErrorInfo() == ErrorCode.Warning) {
                GuiResultat = "<html>WARNED\n    " +
                        result.runner.toShortString() +
                        "\n    <REMOTE>" +
                        GuiHostId +
                        "</REMOTE>" +
                        "\n    <FILEFINAL>" +
                        (result.file != null? result.file.toString() +
                                "</FILEFINAL>" : "no file") + "\n    delay: " +
                        delay;
            } else {
                GuiResultat = "<html>SUCCESS\n    " +
                        result.runner.toShortString() +
                        "\n    <REMOTE>" +
                        GuiHostId +
                        "</REMOTE>" +
                        "\n    <FILEFINAL>" +
                        (result.file != null? result.file.toString() +
                                "</FILEFINAL>" : "no file") + "\n    delay: " +
                        delay;
            }
        } else {
            if (result == null || result.runner == null) {
                GuiResultat = "<html>Transfer in\n    FAILURE with no Id\n    "+
                    "\n    <REMOTE>" +
                    GuiHostId + "</REMOTE>\n    "+ future
                        .getCause().getMessage();
            } else if (result.runner.getErrorInfo() == ErrorCode.Warning) {
                GuiResultat = "<html>Transfer is\n    WARNED\n    " +
                        result.runner.toShortString() + "\n    <REMOTE>" +
                        GuiHostId + "</REMOTE>\n    "+ future.getCause().getMessage();
            } else {
                GuiResultat = "<html>Transfer in\n    FAILURE\n    " +
                        result.runner.toShortString() + "\n    <REMOTE>" +
                        GuiHostId + "</REMOTE>\n    "+ future.getCause().getMessage();
            }
        }
    }
    public static String [] getHostIds() {
        String []results = null;
        DbHostAuth[] dbHostAuths;
        try {
            dbHostAuths = DbHostAuth.getAllHosts(null);
        } catch (OpenR66DatabaseNoConnectionError e) {
            results = new String[1];
            results[0] = "NoHostFound";
            return results;
        } catch (OpenR66DatabaseSqlError e) {
            results = new String[1];
            results[0] = "NoHostFound";
            return results;
        }
        if (dbHostAuths.length == 0) {
            results = new String[1];
            results[0] = "NoHostFound";
            return results;
        }
        results = new String[dbHostAuths.length];
        for (int i = 0; i < dbHostAuths.length; i++) {
            results[i] = dbHostAuths[i].getHostid();
        }
        return results;
    }
    public static String [] getRules() {
        String []results = null;
        DbRule[] dbRules;
        try {
            dbRules = DbRule.getAllRules(null);
        } catch (OpenR66DatabaseNoConnectionError e) {
            results = new String[1];
            results[0] = "NoRuleFound";
            return results;
        } catch (OpenR66DatabaseSqlError e) {
            results = new String[1];
            results[0] = "NoRuleFound";
            return results;
        }
        if (dbRules.length == 0) {
            results = new String[1];
            results[0] = "NoRuleFound";
            return results;
        }
        results = new String[dbRules.length];
        for (int i = 0; i < dbRules.length; i++) {
            results[i] = dbRules[i].idRule;
        }
        return results;
    }
}
