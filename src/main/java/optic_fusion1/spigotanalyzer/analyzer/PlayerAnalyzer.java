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
        if (isMethodInsnNodeCorrect(methodInsnNode, "getName", "()Ljava/lang/String;")
                || isMethodInsnNodeCorrect(methodInsnNode, "getDisplayName", "()Ljava/lang/String;")) {
            AbstractInsnNode plus = methodInsnNode.getNext();
            if (BytecodeUtils.isAbstractNodeString(plus)) {
                String name = (String) ((LdcInsnNode) plus).cst;
                log(classNode, methodNode, methodInsnNode, "Checks if a player's name is equal to " + name);
            } else {
                log(classNode, methodNode, methodInsnNode, "Gets a player's name");
            }
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "kickPlayer", "(Ljava/lang/String;)V")) {
            AbstractInsnNode minus = methodInsnNode.getPrevious();
            if (isAbstractNodeString(minus)) {
                String reason = (String) ((LdcInsnNode) minus).cst;
                log(classNode, methodNode, methodInsnNode, "Kicks the player due to " + reason);
            } else {
                log(classNode, methodNode, methodInsnNode, "Kicks a player");
            }
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "banPlayer", "(Ljava/lang/String;)V")
                || isMethodInsnNodeCorrect(methodInsnNode, "banPlayerFull", "(Ljava/lang/String;)Lorg/bukkit/BanEntry;")) {
            AbstractInsnNode minus = methodInsnNode.getPrevious();
            if (isAbstractNodeString(minus)) {
                String reason = (String) ((LdcInsnNode) minus).cst;
                log(classNode, methodNode, methodInsnNode, "Bans the player due to " + reason);
            } else {
                log(classNode, methodNode, methodInsnNode, "Bans a player");
            }
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "setInvulnerable", "(Z)V")) {
            AbstractInsnNode minus = methodInsnNode.getPrevious();
            boolean isInvlunerable = BytecodeUtils.matches(minus, 1);
            if (isInvlunerable) {
                log(classNode, methodNode, methodInsnNode, "Makes the player invulnerable");
            }
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "setInvisible", "(Z)V")) {
            AbstractInsnNode minus = methodInsnNode.getPrevious();
            boolean isInvlunerable = BytecodeUtils.matches(minus, 1);
            if (isInvlunerable) {
                log(classNode, methodNode, methodInsnNode, "Makes the player invisible");
            }
        }
        if (isMethodInsnNodeCorrect(methodInsnNode, "setBanned", "(Z)V")) {
            log(classNode, methodNode, methodInsnNode, "Bans a player");
        }
    }
}
