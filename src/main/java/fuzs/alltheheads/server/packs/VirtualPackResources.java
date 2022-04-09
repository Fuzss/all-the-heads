package fuzs.alltheheads.server.packs;

import com.google.common.collect.ImmutableSet;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VirtualPackResources implements PackResources {
    private static final String PACK_ICON_DEFAULT_NAME = "pack.png";

    private final String name;
    @Nullable
    private final Path packIconPath;
    private final PackMetadataSection metadataSection;
    private final Map<ResourceLocation, byte[]> data;

    public VirtualPackResources(String name, Component description, Map<ResourceLocation, byte[]> data) {
        this(name, null, description, data);
    }

    public VirtualPackResources(String name, @Nullable Path packIconPath, Component description, Map<ResourceLocation, byte[]> data) {
        this.name = name;
        this.packIconPath = packIconPath;
        this.metadataSection = new PackMetadataSection(description, PackType.CLIENT_RESOURCES.getVersion(SharedConstants.getCurrentVersion()));
        this.data = data;
    }

    @Nullable
    @Override
    public InputStream getRootResource(String name) throws IOException {
        if (name.equals(PACK_ICON_DEFAULT_NAME) && this.packIconPath != null) {
            if (Files.exists(this.packIconPath)) {
                return Files.newInputStream(this.packIconPath);
            }
        }
        throw new FileNotFoundException(name);
    }

    @Override
    public InputStream getResource(PackType p_10289_, ResourceLocation p_10290_) {
        return new ByteArrayInputStream(this.data.get(p_10290_));
    }

    @Override
    public Collection<ResourceLocation> getResources(PackType p_10284_, String namespace, String path, int maxDepth, Predicate<String> filter) {
        int currentPathDepth = StringUtils.countMatches(path, "/");
        return this.data.keySet().stream()
                .filter(location -> location.getNamespace().equals(namespace))
                .filter(location -> location.getPath().startsWith(path))
                .filter(location -> filter.test(location.getPath()))
                .filter(location -> StringUtils.countMatches(location.getPath(), "/") - currentPathDepth <= maxDepth)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasResource(PackType p_10292_, ResourceLocation p_10293_) {
        return this.data.containsKey(p_10293_);
    }

    @Override
    public Set<String> getNamespaces(PackType p_10283_) {
        return this.data.keySet().stream()
                .map(ResourceLocation::getNamespace)
                .collect(ImmutableSet.toImmutableSet());
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) {
        return deserializer.getMetadataSectionName().equals("pack") ? (T) this.metadataSection : null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void close() {

    }
}
