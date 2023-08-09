package optic_fusion1.spigotanalyzer;

import optic_fusion1.kitsune.Kitsune;
import optic_fusion1.kitsune.analyzer.java.JavaAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.BanListAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.BukkitAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PlayerAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PlayerChatEventAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PluginManagerAnalyzer;
import org.pf4j.Plugin;

public class SpigotAnalyzer extends Plugin {

    @Override
    public void start() {
        JavaAnalyzer javaAnalyzer = Kitsune.getInstance().getAnalyzerManager().getJavaAnalyzer();
        PlayerAnalyzer playerAnalyzer = new PlayerAnalyzer();
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/entity/Player", playerAnalyzer);
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/command/CommandSender", playerAnalyzer);
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/Bukkit", new BukkitAnalyzer());
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/BanList", new BanListAnalyzer());
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/event/player/PlayerChatEvent", new PlayerChatEventAnalyzer());
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/plugin/PluginManager", new PluginManagerAnalyzer());
    }

}
