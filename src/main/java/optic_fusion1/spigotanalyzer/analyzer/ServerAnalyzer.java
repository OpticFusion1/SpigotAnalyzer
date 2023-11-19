package optic_fusion1.spigotanalyzer.analyzer;

import optic_fusion1.kitsune.analyzer.java.code.CodeAnalyzer;
import static optic_fusion1.kitsune.util.Utils.log;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ServerAnalyzer extends CodeAnalyzer {

    @Override
    public void analyze(ClassNode classNode, MethodNode methodNode, MethodInsnNode methodInsnNode) {
        if (isMethodInsnNodeCorrect(methodInsnNode, "dispatchCommand", "(Lorg/bukkit/command/CommandSender;Ljava/lang/String;)Z")) {
            AbstractInsnNode minus = methodInsnNode.getPrevious();
            if (isAbstractNodeString(minus)) {
                String string = (String) ((LdcInsnNode) minus).cst;
                log(classNode, methodNode, methodInsnNode, "Dispatches the command " + string);
            } else {
                log(classNode, methodNode, methodInsnNode, "Dispatches a command");
            }
        }
    }

}
