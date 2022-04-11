package fuzs.alltheheads.client.model;

public class BuiltInSkullJsonData {
    public static final String SKULL_BLOCKSTATE_VARIANTS = """
            {
              "variants": {
                "": {
                  "model": "minecraft:block/skull"
                }
              }
            }
            """;
    public static final String SKULL_WALL_BLOCKSTATE_VARIANTS = """
            {
              "variants": {
                "": {
                  "model": "minecraft:block/skull"
                }
              }
            }
            """;
    public static final String SKULL_ITEM_MODEL = """
            {
              "parent": "minecraft:item/template_skull"
            }
            """;
    public static final String SKULL_ITEM_MODEL2 = """
            {
                "parent": "minecraft:item/template_skull",
                "overrides": [
                    %s
                ]
            }
                        
            """;
    public static final String SKULL_ITEM_MODEL3 = """
            { "predicate": { "skull_type": %s }, "model": "item/%s" }
            """;
}
