package fuzs.alltheheads.client.model.geom.builders;

import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.builders.CubeDefinition;

public interface CubeDefinitionFactory {

    CubeDefinition withOrigin(Vector3f origin);
}
