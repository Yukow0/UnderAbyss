package ch.yukow0.underabyss.Tags;

import ch.yukow0.underabyss.Underabyss;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AirFilters {
    public static final TagKey<Item> AIR_FILTERS = ItemTags.create(Identifier.fromNamespaceAndPath("underabyss", "air_filter"));
}
