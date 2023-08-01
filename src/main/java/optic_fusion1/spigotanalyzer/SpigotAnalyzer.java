package optic_fusion1.spigotanalyzer;

import optic_fusion1.kitsune.Kitsune;
import optic_fusion1.kitsune.analyzer.java.JavaAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.BukkitAnalyzer;
import optic_fusion1.spigotanalyzer.analyzer.PlayerAnalyzer;
import org.pf4j.Plugin;

public class SpigotAnalyzer extends Plugin {

    @Override
    public void start() {
        JavaAnalyzer javaAnalyzer = Kitsune.getInstance().getAnalyzerManager().getJavaAnalyzer();
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/entity/Player", new PlayerAnalyzer());
        javaAnalyzer.registerCodeAnalyzer("org/bukkit/Bukkit", new BukkitAnalyzer());
    }

}
