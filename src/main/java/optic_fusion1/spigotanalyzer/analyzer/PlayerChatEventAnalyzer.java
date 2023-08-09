package optic_fusion1.spigotanalyzer.analyzer;

import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import optic_fusion1.kitsune.util.BytecodeUtils;
import static optic_fusion1.kitsune.util.Utils.log;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class PlayerChatEventAnalyzer extends CodeAnalyzer {

    @Override
    public void analyze(ClassNode classNode, MethodNode methodNode, MethodInsnNode methodInsnNode) {
        if (isMethodInsnNodeCorrect(methodInsnNode, "getMessage", "()Ljava/lang/String;")) {
            AbstractInsnNode next = methodInsnNode.getNext();
            if (BytecodeUtils.isAbstractNodeString(next)) {
                String string = (String) ((LdcInsnNode) next).cst;
                log(classNode, methodNode, methodInsnNode, "Checks if PlayerChatEvent#getMessage equals or contains " + string);
            } else {
                log(classNode, methodNode, methodInsnNode, "Checks if PlayerChatEvent#getMessage equals or contains something");
            }
        }
    }

}
