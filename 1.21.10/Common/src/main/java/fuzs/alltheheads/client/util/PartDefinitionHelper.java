package fuzs.alltheheads.client.util;

import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Map;
import java.util.Set;

/**
 * TODO Copied from Minecraft 1.21.9, remove when updating to that version.
 */
public final class PartDefinitionHelper {
    private PartDefinitionHelper() {
        // NO-OP
    }

    public static PartDefinition clearRecursively(PartDefinition partDefinition) {
        for (Map.Entry<String, PartDefinition> entry : partDefinition.getChildren()) {
            clearRecursively(partDefinition.clearChild(entry.getKey()));
        }

        return partDefinition;
    }

    public static void retainPartsAndChildren(PartDefinition partDefinition, Set<String> set) {
        for (Map.Entry<String, PartDefinition> entry : partDefinition.getChildren()) {
            if (!set.contains(entry.getKey())) {
                retainPartsAndChildren(partDefinition.clearChild(entry.getKey()), set);
            }
        }
    }

    public static void retainExactParts(PartDefinition partDefinition, Set<String> set) {
        for (Map.Entry<String, PartDefinition> entry : partDefinition.getChildren()) {
            if (set.contains(entry.getKey())) {
                clearRecursively(entry.getValue());
            } else {
                retainExactParts(partDefinition.clearChild(entry.getKey()), set);
            }
        }
    }
}
