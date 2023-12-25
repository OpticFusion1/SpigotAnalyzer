package optic_fusion1.spigotanalyzer.analyzer.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import static optic_fusion1.kitsune.Kitsune.LOGGER;
import optic_fusion1.kitsune.analyzer.java.file.FileAnalyzer;

public class PluginYamlAnalyzer extends FileAnalyzer {

    @Override
    public void analyze(File file) {
        try (ZipFile zipFile = new ZipFile(file)) {
            ZipEntry entry = zipFile.getEntry("plugin.yml");
            if (entry == null) {
                LOGGER.info("A plugin.yml file does not exist");
                return;
            }
            InputStream inputStream = zipFile.getInputStream(entry);
            String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            LOGGER.info(result);
        } catch (IOException ex) {
            Logger.getLogger(PluginYamlAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
