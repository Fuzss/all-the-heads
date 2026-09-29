# All The Heads

A Minecraft mod. Downloads can be found on [CurseForge](https://www.curseforge.com/members/fuzs_/projects) and [Modrinth](https://modrinth.com/user/Fuzs).

![](banner.png)

---

This mod expands Minecraft's vanilla heads into a fully data-driven system. Instead of one hardcoded head per mob, every
entity can have any number of heads, each with its own model layers, tint colours, lighting, note block sound, drop
rules and mob-disguise behaviour. Out of the box the mod ships definitions for hundreds of mobs and their variants;
everything is defined in JSON and can be changed, removed or extended with data packs, while the actual geometry is
provided by a fixed set of built-in models.

---

## Table of contents

- [Overview](#overview)
- [The head item and block](#the-head-item-and-block)
- [Data-driven head types](#data-driven-head-types)
    - [Where definitions live](#where-definitions-live)
    - [A minimal head](#a-minimal-head)
    - [Full schema](#full-schema)
    - [A complete example](#a-complete-example)
    - [`entity_predicate`](#entity_predicate)
    - [`shape`](#shape)
    - [`loot_table` and `charged_creeper_drop`](#loot_table-and-charged_creeper_drop)
    - [`custom_name`](#custom_name)
    - [`mob_disguise`](#mob_disguise)
    - [`note_block_sound`](#note_block_sound)
    - [`models`](#models)
    - [Colours](#colours)
- [Making heads drop from mobs](#making-heads-drop-from-mobs)
    - [Mob loot tables and tags](#mob-loot-tables-and-tags)
    - [Charged creeper drops](#charged-creeper-drops)
- [Resource packs and textures](#resource-packs-and-textures)
- [Built-in values reference](#built-in-values-reference)
    - [Model types](#model-types)
    - [Special block model types](#special-block-model-types)
    - [Colour types](#colour-types)
    - [Custom entity sub-predicates](#custom-entity-sub-predicates)

---

## Overview

- **Every mob can have a head.** A head type is a data pack entry that matches one or more entities via a
  standard [entity predicate](https://minecraft.wiki/w/Advancement/Conditions/entity) plus a few extra predicates added
  by the mod.
- **Heads are real blocks and items.** Each head is the same `alltheheads:mob_head` item, distinguished by the
  `alltheheads:head_type` data component. Place them, wear them, put them on armour stands, use them as note block
  instruments and let dispensers equip them exactly like vanilla heads.
- **Multi-layer models.** A head can combine any number of the built-in model layers (base model, eyes, overlay, wool,
  gel, armour, …). Each layer has its own texture, optional tint and optional light level.
- **Generalised mob disguise.** Wearing a head halves the detection range of the mob that head matches, extending
  vanilla's skeleton/zombie/creeper/piglin behaviour to every mob.
- **Data-driven drops.** Heads can drop from mobs through loot tables, from charged creeper explosions, or both.

You can obtain heads by killing the matching mob (governed by the head's loot table and predicate), by killing it with a
charged creeper (for heads with `charged_creeper_drop`), from the two creative tabs (**All The Heads** and **All The
Villagers**), via `/give` as shown in
[The head item and block](#the-head-item-and-block), or by letting a dispenser equip one onto a player, mob or armour
stand with an empty helmet slot. Building a wither still requires the vanilla wither skeleton skull.

---

## The head item and block

Everything in the mod is built from three registered objects:

| Object         | ID                          | Notes                            |
|----------------|-----------------------------|----------------------------------|
| Item           | `alltheheads:mob_head`      | Also the standing block item.    |
| Standing block | `alltheheads:mob_head`      | Block entity `alltheheads:head`. |
| Wall block     | `alltheheads:mob_wall_head` | Same block entity.               |

The head type is stored in the **`alltheheads:head_type` data component**, a reference to an entry in the
`alltheheads:head` registry. The same component is copied to and from the block entity when placing and breaking, so a
placed head keeps its identity.

You can give yourself any head with a command. The built-in dolphin head, for example:

```mcfunction
/give @s alltheheads:mob_head[alltheheads:head_type="alltheheads:minecraft/dolphin"]
```

> Head type IDs are registry IDs. For heads that target vanilla entities the built-in ID follows the
> pattern `alltheheads:minecraft/<mob>`, because the ID is derived from the entity's resource location.
> Custom heads use whatever namespace and path you put them in (see below).

Because heads are ordinary equippable items, they hide the wearer's locator-bar waypoint (vanilla behaviour) and can be
enchanted with Curse of Binding / Curse of Vanishing through an anvil.

---

## Data-driven head types

Head types are **registry entries**, not plain JSON files loaded ad hoc. That means they are loaded from data packs, are
reloadable with `/reload`, can be referenced by tags, are synced to clients and are covered by the
vanilla [data pack](https://minecraft.wiki/w/Data_pack) mechanics. If you are not yet familiar with data packs, read
the [Minecraft Wiki page](https://minecraft.wiki/w/Data_pack) first.

### Where definitions live

The registry key is `alltheheads:head`, so a definition is placed at:

```
data/<your_namespace>/alltheheads/head/<path>.json
```

and gets the head type ID `<your_namespace>:<path>`. For example,
`data/mypack/alltheheads/head/golden_zombie.json` registers `mypack:golden_zombie`.

The mod's own definitions use the namespace `alltheheads` and mirror vanilla entity IDs, e.g.
`data/alltheheads/alltheheads/head/minecraft/dolphin.json` registers
`alltheheads:minecraft/dolphin`.

### A minimal head

A head only strictly needs `entity_predicate`, `shape` and `models`. This defines a zombie head that uses the vanilla
zombie texture and the generic humanoid model:

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:zombie"
  },
  "custom_name": "mypack.zombie",
  "shape": {
    "width": 8.0,
    "height": 8.0,
    "depth": 8.0
  },
  "note_block_sound": "minecraft:entity.zombie.ambient",
  "models": [
    {
      "model": "alltheheads:humanoid_head",
      "asset_id": "minecraft:entity/zombie/zombie"
    }
  ]
}
```

### Full schema

| Field                  | Type                                                                      | Required | Default | Description                                                          |
|------------------------|---------------------------------------------------------------------------|----------|---------|----------------------------------------------------------------------|
| `entity_predicate`     | [EntityPredicate](https://minecraft.wiki/w/Advancement/Conditions/entity) | yes      | –       | Selects the entities this head belongs to.                           |
| `shape`                | object                                                                    | yes      | –       | Bounding box and render scale.                                       |
| `models`               | list of model objects                                                     | yes      | –       | One or more render layers, drawn in order.                           |
| `custom_name`          | string                                                                    | no       | –       | Translation-key suffix used for the item name.                       |
| `mob_disguise`         | boolean                                                                   | no       | `true`  | Whether wearing this head halves the matching mob's detection range. |
| `note_block_sound`     | sound event ID                                                            | no       | –       | Sound played when the head sits on a note block.                     |
| `loot_table`           | loot table ID                                                             | no       | –       | Loot table that drops this head from mobs.                           |
| `charged_creeper_drop` | boolean                                                                   | no       | `true`  | Whether the head drops from a charged creeper kill.                  |

> `loot_table` and `charged_creeper_drop` are technically part of a nested `Loot` object, but its codec is
> flattened, so they are written at the **top level** of the JSON exactly as shown above.

### A complete example

This definition uses every field of a head type, including all optional ones:

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:sheep",
    "minecraft:components": {
      "minecraft:sheep/color": "red"
    },
    "minecraft:type_specific/sheep": {
      "sheared": false
    }
  },
  "custom_name": "mypack.red_sheep",
  "mob_disguise": true,
  "note_block_sound": "minecraft:entity.sheep.ambient",
  "loot_table": "mypack:entities/heads/red_sheep",
  "charged_creeper_drop": true,
  "shape": {
    "width": 6.0,
    "height": 6.0,
    "depth": 8.0,
    "scale": 1.0
  },
  "models": [
    {
      "model": "alltheheads:sheep_head",
      "asset_id": "minecraft:entity/sheep/sheep"
    },
    {
      "model": "alltheheads:sheep_head",
      "asset_id": "minecraft:entity/sheep/sheep_wool_undercoat",
      "color": {
        "type": "alltheheads:sheep",
        "dye": "red"
      }
    },
    {
      "model": "alltheheads:sheep_head/wool",
      "asset_id": "minecraft:entity/sheep/sheep_wool",
      "color": {
        "type": "alltheheads:sheep",
        "dye": "red"
      },
      "block_light": 15
    }
  ]
}
```

The `color` value shown is only one of four colour sources; the others are demonstrated under
[Colours](#colours). `block_light` is optional and, here, makes the wool layer render full-bright.

### `entity_predicate`

This is the vanilla [entity predicate](https://minecraft.wiki/w/Advancement/Conditions/entity)
used by advancements. The most common fields are:

- `minecraft:entity_type` – an entity type or entity type tag.
- `minecraft:components` – matches data components, e.g. `{"minecraft:cow/variant": "minecraft:warm"}`.
- `minecraft:type_specific/<type>` – vanilla type-specific predicates such as
  `{"minecraft:type_specific/sheep": {"sheared": true}}`.
- `minecraft:equipment`, `minecraft:location`, `minecraft:effects`, `minecraft:nbt`, `minecraft:flags`,
  `minecraft:vehicle`, `minecraft:passenger`, `minecraft:targeted_entity`, `minecraft:distance`, etc.

In addition, All The Heads registers **custom sub-predicates** for properties that vanilla does not expose
(see [Custom entity sub-predicates](#custom-entity-sub-predicates)). They are written alongside the vanilla fields:

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:creeper",
    "alltheheads:creeper": {
      "powered": true
    }
  }
}
```

All parts of a predicate must match. This is what allows the mod to have many heads per mob (one per variant) and to
pick the correct one for drops, disguises and the loot-table injection.

### `shape`

The shape controls the block's voxel/collision shape and how the model is scaled.

| Field    | Type   | Required | Default | Range | Description                                                                 |
|----------|--------|----------|---------|-------|-----------------------------------------------------------------------------|
| `width`  | double | yes      | –       | 1–24  | Head width in 1/16 block units.                                             |
| `height` | double | yes      | –       | 1–24  | Head height in 1/16 block units.                                            |
| `depth`  | double | yes      | –       | 1–24  | Head depth in 1/16 block units.                                             |
| `scale`  | double | no       | `1.0`   | ≥ 0   | Uniform multiplier applied to both the bounding box and the rendered model. |

The final bounding box is `width × height × depth`, multiplied by `scale`. `scale` also scales the rendered model, so
large heads are shrunk to fit. Built-in examples:

| Head                | `width` × `height` × `depth` | `scale` | Result           |
|---------------------|------------------------------|---------|------------------|
| Most humanoid heads | 8 × 8 × 8                    | 1.0     | 8×8×8 box        |
| Villager            | 8 × 10 × 8                   | 0.9375  | ~7.5×9.4×7.5 box |
| Warden              | 16 × 16 × 10                 | 0.6     | 9.6×9.6×6 box    |
| Ravager             | 16 × 20 × 16                 | 0.5     | 8×10×8 box       |
| Sniffer             | 13 × 14 × 20                 | 0.5     | 6.5×7×10 box     |

A good rule of thumb is to describe the natural size of the model and choose a `scale` that brings the largest dimension
close to 8–10. The shape is also used to position the model when placed on the ground or a wall, so getting it roughly
right matters for alignment.

### `loot_table` and `charged_creeper_drop`

- `loot_table` points to a loot table that produces the head. The mod nests this table inside the matching entity's loot
  table, gated by the head's `entity_predicate`. If omitted, the head does not drop from normal mob kills.
- `charged_creeper_drop` (default `true`) makes the head drop when the matching entity is killed by a charged creeper,
  independent of `loot_table`. This mirrors the vanilla charged-creeper head drops and is generalised to every head.

See [Making heads drop from mobs](#making-heads-drop-from-mobs) for complete examples.

### `custom_name`

`custom_name` is appended to the item's translation key, `block.alltheheads.mob_head.<custom_name>`. For example
`"custom_name": "mypack.zombie"` produces the key `block.alltheheads.mob_head.mypack.zombie`, which you translate in
a [language file](https://minecraft.wiki/w/Resource_pack#Language). If you omit
`custom_name`, the item is simply called "Mob Head".

When generating the mod's own definitions the name is derived automatically from the head type ID, which is why the
built-in dolphin head uses `"custom_name": "minecraft.dolphin"`.

### `mob_disguise`

When `true` (the default), wearing the head halves the detection range of any entity that matches the head's
`entity_predicate`. This generalises the vanilla behaviour where wearing a skeleton skull reduces the detection range of
skeletons (and similarly for creepers, zombies and piglins) to every mob. Set it to
`false` if a head should never act as a disguise.

### `note_block_sound`

The sound event played when the head block is placed directly above a note block, replacing the vanilla head sounds. Use
any sound event ID, e.g. `minecraft:entity.dolphin.ambient_water`. If omitted, the note block uses the default
custom-head instrument behaviour.

### `models`

`models` is an ordered list of layers. They are drawn back-to-front in the order given, so put the base model first and
overlays/eyes/armour after it.

Each entry:

| Field         | Type                      | Required | Default                | Description                                                         |
|---------------|---------------------------|----------|------------------------|---------------------------------------------------------------------|
| `model`       | model type ID             | no       | `alltheheads:mob_head` | One of the [built-in model types](#model-types).                    |
| `asset_id`    | texture ID                | no       | missing texture        | Texture path, resolved to `assets/<namespace>/textures/<path>.png`. |
| `color`       | [colour object](#colours) | no       | no tint                | Tints the layer.                                                    |
| `block_light` | integer                   | no       | world light            | Forces the layer's block light, `0`–`15`.                           |

`asset_id` is written **without** the `textures/` prefix and `.png` extension. For example
`"asset_id": "minecraft:entity/dolphin/dolphin"` loads
`assets/minecraft/textures/entity/dolphin/dolphin.png`.

> `asset_id` is ignored by [special block model types](#special-block-model-types), which render a block
> instead of a textured entity model.

A head with a base model, an emissive eyes layer and a tinted armour layer:

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:wither"
  },
  "shape": {
    "width": 8.0,
    "height": 8.0,
    "depth": 8.0
  },
  "models": [
    {
      "model": "alltheheads:humanoid_head",
      "asset_id": "minecraft:entity/wither/wither",
      "block_light": 15
    },
    {
      "model": "alltheheads:wither_head/shield",
      "asset_id": "minecraft:entity/wither/wither_armor",
      "color": {
        "type": "alltheheads:constant",
        "value": -8355712
      }
    }
  ]
}
```

### Colours

The `color` object is a type-dispatched value. The `type` field selects the colour source; the remaining fields depend
on it. All four types are built in and cannot be added by packs (see [Colour types](#colour-types)):

```json
{
  "type": "alltheheads:constant",
  "value": -8355712
}
```

```json
{
  "type": "alltheheads:dye",
  "dye": "orange"
}
```

```json
{
  "type": "alltheheads:sheep",
  "dye": "white"
}
```

```json
{
  "type": "alltheheads:rainbow"
}
```

`constant.value` is either a packed RGB integer or a `[r, g, b]` float array with components in `0.0`–`1.0`. Negative
integers are just the signed representation of a 24-bit colour (e.g. `-8355712` is `0xFF808080`).

```json
{
  "type": "alltheheads:constant",
  "value": [
    0.5,
    0.5,
    0.5
  ]
}
```

---

## Making heads drop from mobs

A head can drop in two independent ways.

### Mob loot tables and tags

For a head to drop from a mob when the mob is killed by a player, two pieces are needed:

1. The head definition has a `loot_table` that produces the head.
2. The head is added to a **head type tag** named after the entity's default loot table.

The tag path is the entity's default loot table with `:` replaced by `/`. For the vanilla zombie the loot table is
`minecraft:entities/zombie`, so the tag is `alltheheads:minecraft/entities/zombie`, stored at:

```
data/alltheheads/tags/alltheheads/head/minecraft/entities/zombie.json
```

```json
{
  "values": [
    "mypack:golden_zombie"
  ]
}
```

> The tag lives in the `alltheheads` namespace because the mod looks up exactly that tag. Data packs merge
> tag values, so you can add your head to a built-in tag (for example `alltheheads:minecraft/entities/dolphin`)
> without replacing the existing entries.

The loot table referenced by `loot_table` is a normal entity loot table. It must drop
`alltheheads:mob_head` and set the `alltheheads:head_type` component to your head. A typical example with Looting
support:

```json
{
  "type": "minecraft:entity",
  "pools": [
    {
      "rolls": 1.0,
      "conditions": [
        {
          "condition": "minecraft:killed_by_player"
        },
        {
          "condition": "minecraft:random_chance_with_enchanted_bonus",
          "enchantment": "minecraft:looting",
          "unenchanted_chance": 0.05,
          "enchanted_chance": {
            "type": "minecraft:linear",
            "base": 0.05,
            "per_level_above_first": 0.01
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "alltheheads:mob_head",
          "functions": [
            {
              "function": "minecraft:set_components",
              "components": {
                "alltheheads:head_type": "mypack:golden_zombie"
              }
            }
          ]
        }
      ]
    }
  ]
}
```

At runtime the mod injects a nested reference to this table into the entity's loot table, guarded by the head's
`entity_predicate`. This means the head definition's predicate is what decides *which* variant drops; the loot table
itself only needs the drop chance and the component.

Because the injection is keyed on the entity's default loot table, modded entities work too: use their default loot
table path for the tag (e.g. `alltheheads:mymod/entities/my_mob`).

### Charged creeper drops

Any head with `charged_creeper_drop: true` (the default) drops when the matching entity is killed by a charged creeper,
exactly like vanilla heads. This path does not require a `loot_table` or a tag. The predicate is evaluated against the
killed entity, so a head only drops for entities it matches.

---

## Resource packs and textures

If a head reuses a vanilla texture (as all the built-in heads do), no resource pack is needed. To use a custom texture,
ship it in a [resource pack](https://minecraft.wiki/w/Resource_pack) and point `asset_id`
at it:

```
assets/mypack/textures/entity/golden_zombie.png
```

```json
{
  "model": "alltheheads:humanoid_head",
  "asset_id": "mypack:entity/golden_zombie"
}
```

The model geometry itself is **client-side and fixed** – you cannot add new geometry through a pack. You can only choose
one of the built-in model types and supply a texture for it. Model types that are overlays (`/eyes`, `/overlay`,`/wool`,
`/gel`, `/markings`, `/decor`, `/charge`, `/shield`) are designed to be layered on top of their base model and often
expect the same UV layout as the base.

---

## Built-in values reference

This section lists every **hardcoded** value that a pack author can reference. These are compiled into the mod and
cannot be added to; anything shipped as a data definition is a normal JSON file that you can override.

### Model types

A model type is a client-side geometry layer plus a fixed render style. The `model` field of a model layer must be one
of the IDs below.

Render style key:

- **cutout** – standard opaque/cutout entity rendering.
- **translucent** – alpha-blended rendering, for semi-transparent layers.
- **emissive** – rendered through the eyes/glow render type, ignoring light.
- **energy** – animated energy-swirl shader (creeper charge, wither shield).
- **block** – renders a vanilla block instead of an entity model (see the next section).

| Model type ID                          | Renders                                   | Style       |
|----------------------------------------|-------------------------------------------|-------------|
| `alltheheads:allay_head`               | Allay                                     | translucent |
| `alltheheads:armadillo_head`           | Armadillo                                 | cutout      |
| `alltheheads:axolotl_head`             | Axolotl                                   | cutout      |
| `alltheheads:bat_head`                 | Bat                                       | cutout      |
| `alltheheads:bee_head`                 | Bee                                       | cutout      |
| `alltheheads:bogged_head`              | Bogged                                    | cutout      |
| `alltheheads:breeze_head`              | Breeze                                    | cutout      |
| `alltheheads:breeze_head/eyes`         | Breeze eyes                               | emissive    |
| `alltheheads:camel_head`               | Camel                                     | cutout      |
| `alltheheads:chicken_head`             | Temperate chicken                         | cutout      |
| `alltheheads:cold_chicken_head`        | Cold chicken                              | cutout      |
| `alltheheads:cold_cow_head`            | Cold cow                                  | cutout      |
| `alltheheads:cod_head`                 | Cod                                       | cutout      |
| `alltheheads:copper_golem_head`        | Copper golem                              | cutout      |
| `alltheheads:copper_golem_head/eyes`   | Copper golem eyes                         | emissive    |
| `alltheheads:creaking_head`            | Creaking                                  | cutout      |
| `alltheheads:creaking_head/eyes`       | Creaking eyes                             | emissive    |
| `alltheheads:creeper_head/charge`      | Creeper armour overlay                    | energy      |
| `alltheheads:dolphin_head`             | Dolphin                                   | cutout      |
| `alltheheads:enderman_head`            | Enderman                                  | cutout      |
| `alltheheads:enderman_head/eyes`       | Enderman eyes                             | emissive    |
| `alltheheads:endermite_head`           | Endermite                                 | cutout      |
| `alltheheads:feline_head`              | Ocelot / cat                              | cutout      |
| `alltheheads:fox_head`                 | Fox                                       | cutout      |
| `alltheheads:frog_head`                | Frog                                      | cutout      |
| `alltheheads:ghast_head`               | Ghast                                     | cutout      |
| `alltheheads:goat_head`                | Goat                                      | cutout      |
| `alltheheads:guardian_head`            | Guardian                                  | cutout      |
| `alltheheads:happy_ghast_head`         | Happy ghast                               | cutout      |
| `alltheheads:hoglin_head`              | Hoglin                                    | cutout      |
| `alltheheads:horse_head`               | Horse                                     | cutout      |
| `alltheheads:horse_head/markings`      | Horse markings overlay                    | translucent |
| `alltheheads:humanoid_head`            | Player / humanoid (default)               | cutout      |
| `alltheheads:humanoid_head/overlay`    | Humanoid outer layer                      | cutout      |
| `alltheheads:illager_head`             | Villager / illager                        | cutout      |
| `alltheheads:iron_golem_head`          | Iron golem                                | cutout      |
| `alltheheads:llama_head`               | Llama                                     | cutout      |
| `alltheheads:llama_head/decor`         | Trader llama decor                        | cutout      |
| `alltheheads:magma_cube_head`          | Magma cube                                | cutout      |
| `alltheheads:mob_head`                 | Generic 8×8×8 head (default when omitted) | cutout      |
| `alltheheads:mob_head/overlay`         | Generic outer layer                       | cutout      |
| `alltheheads:nautilus_head`            | Nautilus                                  | cutout      |
| `alltheheads:panda_head`               | Panda                                     | cutout      |
| `alltheheads:parched_head`             | Parched                                   | cutout      |
| `alltheheads:parrot_head`              | Parrot                                    | cutout      |
| `alltheheads:phantom_head`             | Phantom                                   | cutout      |
| `alltheheads:phantom_head/eyes`        | Phantom eyes                              | emissive    |
| `alltheheads:pig_head`                 | Pig                                       | cutout      |
| `alltheheads:piglin_head`              | Piglin (animated ears)                    | cutout      |
| `alltheheads:polar_bear_head`          | Polar bear                                | cutout      |
| `alltheheads:pufferfish_head`          | Pufferfish                                | cutout      |
| `alltheheads:rabbit_head`              | Rabbit                                    | cutout      |
| `alltheheads:ravager_head`             | Ravager                                   | cutout      |
| `alltheheads:salmon_head`              | Salmon                                    | cutout      |
| `alltheheads:sheep_head`               | Sheep                                     | cutout      |
| `alltheheads:sheep_head/wool`          | Sheep wool                                | cutout      |
| `alltheheads:shulker_head`             | Shulker                                   | cutout      |
| `alltheheads:silverfish_head`          | Silverfish                                | cutout      |
| `alltheheads:slime_head`               | Slime                                     | cutout      |
| `alltheheads:slime_head/gel`           | Slime gel                                 | translucent |
| `alltheheads:sniffer_head`             | Sniffer                                   | cutout      |
| `alltheheads:spider_head`              | Spider                                    | cutout      |
| `alltheheads:spider_head/eyes`         | Spider eyes                               | emissive    |
| `alltheheads:squid_head`               | Squid                                     | cutout      |
| `alltheheads:strider_head`             | Strider                                   | cutout      |
| `alltheheads:sulfur_cube_head`         | Sulfur cube                               | cutout      |
| `alltheheads:sulfur_cube_head/gel`     | Sulfur cube gel                           | translucent |
| `alltheheads:tadpole_head`             | Tadpole                                   | cutout      |
| `alltheheads:temperate_cow_head`       | Temperate cow                             | cutout      |
| `alltheheads:tropical_fish_head/large` | Large tropical fish                       | cutout      |
| `alltheheads:tropical_fish_head/small` | Small tropical fish                       | cutout      |
| `alltheheads:turtle_head`              | Turtle                                    | cutout      |
| `alltheheads:vex_head`                 | Vex                                       | translucent |
| `alltheheads:villager_head`            | Villager                                  | cutout      |
| `alltheheads:warden_head`              | Warden                                    | cutout      |
| `alltheheads:warm_cow_head`            | Warm cow                                  | cutout      |
| `alltheheads:witch_head`               | Witch                                     | cutout      |
| `alltheheads:wither_head/shield`       | Wither armour shield                      | energy      |
| `alltheheads:wolf_head`                | Wolf                                      | cutout      |

All model types use the standard skull animation (the head bobs while worn by a moving entity or while placed and
powered by redstone). The piglin head additionally flaps its ears. Layers rendered with the emissive (`/eyes`) style are
always drawn full-bright; to make any other layer glow, set `block_light: 15`
on it (this is what the built-in blaze, magma cube, glow squid, vex, allay and wither heads do).

### Special block model types

These model types render a vanilla block instead of an entity model. `asset_id` and `color` are ignored;
`block_light` still applies.

| Model type ID                                  | Block              |
|------------------------------------------------|--------------------|
| `alltheheads:sulfur_cube_head/regular`         | Grass Block        |
| `alltheheads:sulfur_cube_head/bouncy`          | Oak Log            |
| `alltheheads:sulfur_cube_head/slow_bouncy`     | Stone              |
| `alltheheads:sulfur_cube_head/slow_flat`       | Iron Block         |
| `alltheheads:sulfur_cube_head/fast_flat`       | Moss Block         |
| `alltheheads:sulfur_cube_head/light`           | White Wool         |
| `alltheheads:sulfur_cube_head/fast_sliding`    | Blue Ice           |
| `alltheheads:sulfur_cube_head/slow_sliding`    | Red Mushroom Block |
| `alltheheads:sulfur_cube_head/high_resistance` | Soul Sand          |
| `alltheheads:sulfur_cube_head/sticky`          | Honeycomb Block    |
| `alltheheads:sulfur_cube_head/explosive`       | TNT                |
| `alltheheads:sulfur_cube_head/hot`             | Magma Block        |

### Colour types

| `type`                 | Fields                                      | Description                                                     |
|------------------------|---------------------------------------------|-----------------------------------------------------------------|
| `alltheheads:constant` | `value` (RGB integer or `[r, g, b]` floats) | A fixed colour.                                                 |
| `alltheheads:dye`      | `dye` (dye colour)                          | The vanilla dye's texture-diffuse colour.                       |
| `alltheheads:sheep`    | `dye` (dye colour)                          | The darker colour used for sheep wool (white is special-cased). |
| `alltheheads:rainbow`  | none                                        | Cycles through every dye colour, 25 ticks per colour.           |

Valid dye colour values: `white`, `orange`, `magenta`, `light_blue`, `yellow`, `lime`, `pink`, `gray`,
`light_gray`, `cyan`, `purple`, `blue`, `brown`, `green`, `red`, `black`.

### Custom entity sub-predicates

These are extra fields you can place inside `entity_predicate`. They are evaluated in addition to any vanilla fields and
all must match.

| Field                       | Fields                                          | Values                                                                |
|-----------------------------|-------------------------------------------------|-----------------------------------------------------------------------|
| `alltheheads:bee`           | `angry`, `has_nectar`                           | booleans                                                              |
| `alltheheads:copper_golem`  | `state`                                         | `unaffected`, `exposed`, `weathered`, `oxidized`                      |
| `alltheheads:creeper`       | `powered`                                       | boolean                                                               |
| `alltheheads:ghast`         | `charging`                                      | boolean                                                               |
| `alltheheads:horse`         | `markings`                                      | `none`, `white`, `white_field`, `white_dots`, `black_dots`            |
| `alltheheads:panda`         | `variant`                                       | `normal`, `lazy`, `worried`, `playful`, `brown`, `weak`, `aggressive` |
| `alltheheads:strider`       | `cold`                                          | boolean                                                               |
| `alltheheads:tropical_fish` | `pattern`, `base_color`, `pattern_color`        | vanilla pattern ID, dye colour, dye colour                            |
| `alltheheads:vex`           | `charging`                                      | boolean (must be present, otherwise the predicate never matches)      |
| `alltheheads:villager_data` | `villager_type`, `villager_profession`, `level` | villager type ID, profession ID, `1`–`5`                              |
| `alltheheads:wolf`          | `angry`, `tame`                                 | booleans                                                              |

Examples:

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:villager",
    "alltheheads:villager_data": {
      "villager_type": "minecraft:plains",
      "villager_profession": "minecraft:librarian"
    }
  }
}
```

```json
{
  "entity_predicate": {
    "minecraft:entity_type": "minecraft:tropical_fish",
    "alltheheads:tropical_fish": {
      "pattern": "kob",
      "base_color": "orange",
      "pattern_color": "white"
    }
  }
}
```
