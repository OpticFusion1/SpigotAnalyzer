package optic_fusion1.spigotanalyzer.analyzer;

import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import static optic_fusion1.kitsune.util.Utils.log;
import optic_fusion1.kitsune.util.BytecodeUtils;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class PlayerAnalyzer extends CodeAnalyzer {

    @Override
    public void analyze(ClassNode classNode, MethodNode methodNode, MethodInsnNode methodInsnNode) {
        if (isMethodInsnNodeCorrect(methodInsnNode, "setOp", "(Z)V")) {
            AbstractInsnNode previous = methodInsnNode.getPrevious();
            boolean setOpTrue = BytecodeUtils.matches(previous, 1);
            log(classNode, methodNode, methodInsnNode, "Found Player#setOp(" + setOpTrue + ")");
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "setGameMode", "(Lorg/bukkit/GameMode;)V")) {
            FieldInsnNode gameModeEnum = (FieldInsnNode) methodInsnNode.getPrevious();
            String gameModeName = gameModeEnum.name;
            log(classNode, methodNode, methodInsnNode, "Sets player's gamemode to " + gameModeName);
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "getName", "()Ljava/lang/String;")) {
            AbstractInsnNode plus = methodInsnNode.getNext();
            if (BytecodeUtils.isAbstractNodeString(plus)) {
                String name = (String) ((LdcInsnNode) plus).cst;
                log(classNode, methodNode, methodInsnNode, "Checks if a player's name is equal to " + name);
            } else {
                log(classNode, methodNode, methodInsnNode, "Gets a player's name");
            }
        }
    }
}
