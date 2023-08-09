package optic_fusion1.spigotanalyzer.analyzer;

import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import static optic_fusion1.kitsune.util.Utils.log;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class PluginManagerAnalyzer extends CodeAnalyzer {

    @Override
    public void analyze(ClassNode classNode, MethodNode methodNode, MethodInsnNode methodInsnNode) {
        if (isMethodInsnNodeCorrect(methodInsnNode, "loadPlugin", "(Ljava/io/File;)Lorg/bukkit/plugin/Plugin;")) {
            log(classNode, methodNode, methodInsnNode, "Loads a plugin");
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "enablePlugin", "(Lorg/bukkit/plugin/Plugin;)V")) {
            log(classNode, methodNode, methodInsnNode, "Enables a plugin");
        }
    }

}
