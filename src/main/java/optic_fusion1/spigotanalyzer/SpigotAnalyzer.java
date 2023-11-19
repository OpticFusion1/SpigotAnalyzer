package optic_fusion1.spigotanalyzer;

import optic_fusion1.kitsune.Kitsune;
import optic_fusion1.kitsune.analyzer.java.JavaAnalyzer;
import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.BanListAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.BukkitAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.EventAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PlayerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PluginManagerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.ServerAnalyzer;
import org.pf4j.Plugin;

public class SpigotAnalyzer extends Plugin {

    private JavaAnalyzer javaAnalyzer = Kitsune.getInstance().getAnalyzerManager().getJavaAnalyzer();

    @Override
    public void start() {
        registerCodeAnalyzer("org/bukkit/entity/Player", new PlayerAnalyzer());
        registerCodeAnalyzer("org/bukkit/command/CommandSender", new PlayerAnalyzer());
        registerCodeAnalyzer("org/bukkit/Bukkit", new BukkitAnalyzer());
        registerCodeAnalyzer("org/bukkit/Server", new ServerAnalyzer());
        registerCodeAnalyzer("org/bukkit/BanList", new BanListAnalyzer());
        registerCodeAnalyzer("org/bukkit/event/player/PlayerChatEvent", new EventAnalyzer("PlayerChatEvent"));
        registerCodeAnalyzer("org/bukkit/event/player/AsyncPlayerChatEvent", new EventAnalyzer("AsyncPlayerChatEvent"));
        registerCodeAnalyzer("org/bukkit/plugin/PluginManager", new PluginManagerAnalyzer());
    }

    private void registerCodeAnalyzer(String classPath, CodeAnalyzer analzyer) {
        javaAnalyzer.registerCodeAnalyzer(classPath, analzyer);
    }

}
