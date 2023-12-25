package optic_fusion1.spigotanalyzer;

import optic_fusion1.kitsune.Kitsune;
import optic_fusion1.kitsune.analyzer.java.JavaAnalyzer;
import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import optic_fusion1.kitsune.analyzer.java.file.FileAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.BanListAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.BukkitAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.EventAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.PlayerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.PluginManagerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.code.ServerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.file.PluginYamlAnalyzer;
import org.pf4j.Plugin;

public class SpigotAnalyzer extends Plugin {

    private JavaAnalyzer javaAnalyzer = (JavaAnalyzer) Kitsune.getInstance().getAnalyzerManager()
            .getAnalyzerForExtension("jar");

    @Override
    public void start() {
        // TODO: Implement a way to automatically register these
        registerCodeAnalyzer("org/bukkit/entity/Player", new PlayerAnalyzer());
        registerCodeAnalyzer("org/bukkit/command/CommandSender", new PlayerAnalyzer());
        registerCodeAnalyzer("org/bukkit/Bukkit", new BukkitAnalyzer());
        registerCodeAnalyzer("org/bukkit/Server", new ServerAnalyzer());
        registerCodeAnalyzer("org/bukkit/BanList", new BanListAnalyzer());
        registerCodeAnalyzer("org/bukkit/event/player/PlayerChatEvent", new EventAnalyzer("PlayerChatEvent"));
        registerCodeAnalyzer("org/bukkit/event/player/AsyncPlayerChatEvent", new EventAnalyzer("AsyncPlayerChatEvent"));
        registerCodeAnalyzer("org/bukkit/plugin/PluginManager", new PluginManagerAnalyzer());
        
        registerFileAnalyzer(new PluginYamlAnalyzer());
    }

    private void registerFileAnalyzer(FileAnalyzer analyzer) {
        javaAnalyzer.registerFileAnalyzer(analyzer);
    }

    private void registerCodeAnalyzer(String classPath, CodeAnalyzer analzyer) {
        javaAnalyzer.registerCodeAnalyzer(classPath, analzyer);
    }

}
